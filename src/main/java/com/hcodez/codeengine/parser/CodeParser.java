package com.hcodez.codeengine.parser;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.MutableCode;
import com.hcodez.codeengine.model.CodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for extracting codes from different types of input
 */
public class CodeParser {

    private static final Logger logger = LoggerFactory.getLogger(CodeParser.class);

    /**
     * CodeTypes that will pe parsed
     */
    private final Set<CodeType> codeTypes;


    public CodeParser() {
        codeTypes = new HashSet<>();
    }


    /**
     * Add a code type for the parser to look after.
     *
     * @param codeType the code type
     * @return this object
     */
    public CodeParser addCodeType(CodeType codeType) {
        logger.trace("added code type {}", codeType);
        this.codeTypes.add(codeType);
        return this;
    }

    /**
     * Add a list of CodeTypes to the code parser code list
     * @param codes the list of codes
     * @return this object
     */
    public CodeParser addCodeTypes(List<CodeType> codes) {
        logger.trace("adding multiple code types from list: {}", codes);
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
    public List<Code> parseString(String input) {
        logger.debug("parsing input string");

        /*if no code types are given, return an empty list*/
        if (this.codeTypes.size() == 0) {
            logger.warn("no code types specified");
            return null;
        }

        /*final code list*/
        final ArrayList<Code> finalCodeList = new ArrayList<>();

        /*clean up the input(remove all whitespaces and endlines*/
        input = cleanInput(input);

        /*
        initialize the raw parsing output before adding to it
        */
        final ArrayList<CodeParserCodeTypeDependantRawOutput> rawParseOutput = new ArrayList<>();

        /*the number of codes found in total*/
        int universalCodeCount = 0;

        /*extract raw codes and positions based on CodeType*/
        for (CodeType codeType : codeTypes) {
            logger.debug("parsing for code type {}", codeType.toString());
            /*initialize the current code type dependant raw parser output*/
            final CodeParserCodeTypeDependantRawOutput workingRawOutput = new CodeParserCodeTypeDependantRawOutput();

            /*create a new matcher that matches patterns on the given input String*/
            List<Pattern> patterns = codeType.getPatterns();
            if (patterns == null) {
                logger.error("could not retrieve patterns for code type " + codeType.toString());
                return null;
            }
            for (final Pattern pattern : codeType.getPatterns()) {
                final Matcher matcher = pattern.matcher(input);

                while (matcher.find()) {
                    logger.debug("found {}", getCodeFromMatcher(matcher, codeType).toString());
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
        }

        logger.debug("sorting {} codes", universalCodeCount);

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
            logger.debug("sorting round won by {}" + winningParserMaterial.getCode().toString());
            finalCodeList.add(
                    winningParserMaterial.getCode()
            );

            /*advance to the next parsing material in the winning raw output*/
            winningCodeTypeDependantRawOutput.removeCurrentProcessingMaterial();
            rawParseOutput.set(codeTypeItemIndexLowestStartPosition, winningCodeTypeDependantRawOutput);
        }

        logger.info("parsed input into {}", finalCodeList);
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
        logger.debug("parsing input string for one code");

        if (this.codeTypes.size() == 0) {
            logger.warn("no code types specified");
            return null;
        }

        /*clean up the input(remove all whitespaces and endlines*/
        input = cleanInput(input);

        /*final code list*/
        final ArrayList<Code> finalCodeList = new ArrayList<>();

        /*find the first code for the first code type*/
        for (final CodeType codeType: this.codeTypes) {
            /*create matchers that match patterns on the given input String*/
            List<Pattern> patterns = codeType.getPatterns();
            if (patterns == null) {
                logger.error("could not retrieve patterns for code type " + codeType.toString());
                return null;
            }
            for (final Pattern pattern : codeType.getPatterns()) {
                final Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    return getCodeFromMatcher(matcher, codeType);
                }
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
        final MutableCode.MutableCodeBuilder codeBuilder = MutableCode.builder().codeType(codeType);

        /*extract code fields*/
        codeBuilder.identifier(matcher.group("identifier"));

        if (matcher.groupCount() > 1) {
            codeBuilder.owner(matcher.group("owner"));

            if (matcher.groupCount() == 3) {
                codeBuilder.passcode(matcher.group("passcode"));
            }
        }
        return codeBuilder.build();
    }

    private String cleanInput(String input) {
        /*clean up the input(remove all whitespaces and endlines*/
        logger.debug("cleaning up the input string");
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[\n\r]", "");
        logger.info("parsing input string: {}", input);
        return input;
    }
}
