package com.hcodez.codeengine.parser;

import com.hcodez.codeengine.model.Code;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Factory for creating code objects.
 */
public class CodeParser {

    private class Pair <A, B> {

        private A first;

        private B second;

        public Pair() {}

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public void setFirst(A first) {
            this.first = first;
        }

        public B getSecond() {
            return second;
        }

        public void setSecond(B second) {
            this.second = second;
        }

        @Override
        public String toString() {
            return "(" +
                    this.first.toString() +
                    ", " +
                    this.second.toString() +
                    ")";
        }
    }


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
    public ArrayList parseString(String input) {

        /*clean up the input(remove all whitespaces and endlines*/
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[\n\r]", "");

        /*final code list*/
        final ArrayList<Code> finalCodeList = new ArrayList<>();

        /*
        A pairs each are constructed if the parser is instructed to look after a code type => there will be
        codeTypes.length() number of pairs.
        B arrays contain <code, startPosition> for a given CodeType and are extracted one at a time, for
        each CodeType.
        The local index counter is used to indicate what index are we currently looking at for each B array.
        */
        final ArrayList
                <Pair /*A pairs*/
                        <ArrayList /*B array*/
                                <Pair /*B array pairs*/
                                        <Code, /*the actual code*/
                                         Integer /*the code's starting position in the String*/
                                         >
                                >,
                                Integer /*local index counter*/
                        >
                > rawCodeList = new ArrayList<>();

        /*the number of codes found in total*/
        int universalCodeCount = 0;

        /*extract raw codes and positions based on CodeType*/
        for (int i = 0; i < codeTypes.size(); i++) {

            /*get the current CodeType*/
            final CodeType codeType = codeTypes.get(i);

            /*get the current object for working with the current CodeType*/
            final Pair<ArrayList<Pair<Code, Integer>>, Integer> workingRawOutput = new Pair<>();
            workingRawOutput.first = new ArrayList<>();
            workingRawOutput.second = 0;

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
                workingRawOutput.first.add(new Pair<>(code, matcher.start()));

                /*increase the universalCodeCount*/
                universalCodeCount++;
            }

            /*add the extracted raw output into the rawCodeList, then proceed to the next CodeType*/
            rawCodeList.add(workingRawOutput);
        }

        /*process(and sort) the codes into the final code list, then return it*/
        for (int localCodeCount = 0; localCodeCount < universalCodeCount; localCodeCount++) {

            /*the lowest start position a code can have*/
            int lowestStartPosition = input.length();
            /*the index of the code with the lowest start position*/
            int codeTypeItemIndexLowestStartPosition = 0;

            for (int rawCodeListIndex = 0; rawCodeListIndex < rawCodeList.size(); rawCodeListIndex++) {

                /*
                retrieve the start position of the item in the raw code list pointed by the code type
                current index
                */
                int workingCodeStartPosition = rawCodeList
                        .get(rawCodeListIndex) /*get the code type raw list*/
                        .first /*get the code list*/
                        .get(rawCodeList.get(rawCodeListIndex).second) /*get the pointer of the code type raw list*/
                        .second; /*get the start position*/

                if (workingCodeStartPosition < lowestStartPosition) {
                    lowestStartPosition = workingCodeStartPosition;
                    codeTypeItemIndexLowestStartPosition = rawCodeListIndex;
                }
            }

            /*extract this round's winning pair*/
            final Pair<ArrayList<Pair<Code, Integer>>, Integer> winningCodeTypePair = rawCodeList.get(codeTypeItemIndexLowestStartPosition);

            /*add the code in the final code list*/
            finalCodeList.add(
                    winningCodeTypePair.first.get(winningCodeTypePair.second).first
            );

            /*increase the pointer index of the winning pair*/
            winningCodeTypePair.second++;
            /*add changes in the raw code list*/
            rawCodeList.set(codeTypeItemIndexLowestStartPosition, winningCodeTypePair);
        }

        return rawCodeList;
    }
}
