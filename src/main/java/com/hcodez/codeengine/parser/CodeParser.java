package com.hcodez.codeengine.parser;

import com.hcodez.codeengine.builder.CodeBuilder;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Parser for extracting codes from different types of input
 */
public class CodeParser {

    private final ArrayList<CodeType> codeTypes;


    public CodeParser() {
        codeTypes = new ArrayList<>();
    }


    /**
     * Add a code type for the parser to look after.
     *
     * @param codeType the code type
     * @return this object
     */
    public CodeParser addCodeType(CodeType codeType) {
        if (this.codeTypes.contains(codeType))
            throw new RuntimeException("can't scan for the same code type twice");

        this.codeTypes.add(codeType);
        return this;
    }

    /**
     * Add a list of CodeTypes to the code parser code list
     * @param codes the list of codes
     * @return this object
     */
    public CodeParser addCodeTypes(List<CodeType> codes) {
        for (final CodeType codeType: codes) {
            this.addCodeType(codeType);
        }
        return this;
    }

    /**
     * Add a number of codes to the code parser code list
     * @param codes the list of codes
     * @return this object
     */
    public CodeParser addCodeType(CodeType... codes) {
        this.addCodeTypes(Arrays.asList(codes));
        return this;
    }

    /**
     * Get a list of codes from a String
     *
     * @param input the input string
     * @return the list of codes
     */
    public ArrayList<Code> parseString(String input) {

        if (this.codeTypes.size() == 0) {
            return null;
        }

        /*clean up the input(remove all whitespaces and endlines*/
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[\n\r]", "");

        /*final code list*/
        final ArrayList<Code> finalCodeList = new ArrayList<>();

        /*
        initialize the raw parsing output before adding to it
        */
        final ArrayList<CodeParserCodeTypeDependantRawOutput> rawParseOutput = new ArrayList<>();

        /*the number of codes found in total*/
        int universalCodeCount = 0;

        /*extract raw codes and positions based on CodeType*/
        for (int i = 0; i < codeTypes.size(); i++) {

            /*get the current CodeType*/
            final CodeType codeType = codeTypes.get(i);

            /*initialize the current code type dependant raw parser output*/
            final CodeParserCodeTypeDependantRawOutput workingRawOutput = new CodeParserCodeTypeDependantRawOutput();

            /*create a new matcher that matches patterns on the given input String*/
            final Matcher matcher = codeType.getPattern().matcher(input);

            while (matcher.find()) {
                /*add the code in the workingRawOutput, along with it's start position*/
                workingRawOutput.addParserProcessingMaterial(
                        new ParserProcessingMaterial(getCodeFromMatcher(matcher, codeType),
                                matcher.start()));

                /*increase the universalCodeCount*/
                universalCodeCount++;
            }

            /*add the extracted raw output into the rawParseOutput, then proceed to the next CodeType*/
            rawParseOutput.add(workingRawOutput);
        }

        /*process(and sort) the codes into the final code list, then return it*/
        for (int localCodeCount = 0; localCodeCount < universalCodeCount; localCodeCount++) {

            /*the lowest start position a code can have*/
            int lowestStartPosition = input.length();
            /*the index of the code with the lowest start position*/
            int codeTypeItemIndexLowestStartPosition = 0;

            /*check the first parsed processing material for each code type*/
            for (int rawParseOutputIndex = 0; rawParseOutputIndex < rawParseOutput.size(); rawParseOutputIndex++) {

                /*
                retrieve the start position of the first item in the raw code list for the current code type
                if the current code type does not have any parsing material left, skip it
                */
                int workingCodeStartPosition;
                try {
                    workingCodeStartPosition = rawParseOutput.get(rawParseOutputIndex).getCurrentParserProcessingMaterial().getCodeStartPosition();
                } catch (Exception ignored) {
                    continue;
                }

                if (workingCodeStartPosition < lowestStartPosition) {
                    lowestStartPosition = workingCodeStartPosition;
                    codeTypeItemIndexLowestStartPosition = rawParseOutputIndex;
                }
            }

            /*extract this round's winning parser processing material*/
            final CodeParserCodeTypeDependantRawOutput winningCodeTypeDependantRawOutput = rawParseOutput.get(codeTypeItemIndexLowestStartPosition);
            final ParserProcessingMaterial winningParserMaterial = winningCodeTypeDependantRawOutput.getCurrentParserProcessingMaterial();

            /*add the code in the final code list*/
            finalCodeList.add(
                    winningParserMaterial.getCode()
            );

            /*advance to the next parsing material in the winning raw output*/
            winningCodeTypeDependantRawOutput.removeCurrentProcessingMaterial();
            rawParseOutput.set(codeTypeItemIndexLowestStartPosition, winningCodeTypeDependantRawOutput);
        }

        return finalCodeList;
    }

    /**
     * Parse a single code from a string(the first code found for a code type)
     * To be used when the developer knows only a single code will be send for parsing
     *
     * @param input the input string that needs to be parser
     * @return the code found
     */
    public Code parseSingle(String input) {

        if (this.codeTypes.size() == 0) {
            return null;
        }

        /*clean up the input(remove all whitespaces and endlines*/
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[\n\r]", "");

        /*final code list*/
        final ArrayList<Code> finalCodeList = new ArrayList<>();

        /*find the first code for the first code type*/
        for (final CodeType codeType: this.codeTypes) {

            /*create a new matcher that matches patterns on the given input String*/
            final Matcher matcher = codeType.getPattern().matcher(input);

            if (matcher.find()) {
                return getCodeFromMatcher(matcher, codeType);
            }
        }
        return null;
    }

    /**
     * Extract a Code from a matcher
     * @param matcher the matcher to be used
     * @param codeType the code type for the Code
     * @return the Code
     */
    private Code getCodeFromMatcher(final Matcher matcher, final CodeType codeType) {
        /*create the code builder used to build the code*/
        final CodeBuilder codeBuilder = CodeBuilder.createBuilder().withCodeType(codeType);

        /*extract code fields*/
        codeBuilder.withIdentifier(matcher.group("identifier"));

        if (matcher.groupCount() > 1) {
            codeBuilder.withOwner(matcher.group("owner"));

            if (matcher.groupCount() == 3) {
                codeBuilder.withPasscode(matcher.group("passcode"));
            }

        }
        return codeBuilder.build();
    }
}
