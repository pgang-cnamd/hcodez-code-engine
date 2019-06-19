package com.hcodez.codeengine.parser;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Factory for creating code objects.
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
     * Get a list of codes from a String
     *
     * @param input the input string
     * @return the list of codes
     */
    public ArrayList<Code> parseString(String input) {

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

                /*extract the code*/
                final Code code = new Code();

                /*extract code fields*/
                code.setIdentifier(matcher.group("identifier"));

                if (matcher.groupCount() > 1) {
                    code.setOwner(matcher.group("owner"));
                    code.setPublicStatus(true);

                    if (matcher.groupCount() == 3) {
                        code.setPasscode(matcher.group("passcode"));
                    }

                } else {
                    code.setPublicStatus(false);
                }

                /*add the code in the workingRawOutput, along with it's start position*/
                workingRawOutput.addParserProcessingMaterial(new ParserProcessingMaterial(code, matcher.start()));

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
}
