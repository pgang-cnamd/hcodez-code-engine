package com.hcodez.codeengine.factory;

import com.hcodez.codeengine.model.Code;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Factory for creating code objects.
 */
public class CodeFactory {

    private final ArrayList<CodeTypes.CodeType> codeTypes;


    public CodeFactory() {
        codeTypes = new ArrayList<>();
    }


    /**
     * Add a code type for the factory to look after.
     *
     * @param codeType the code type
     * @return this object
     */
    public CodeFactory addCodeType(CodeTypes.CodeType codeType) {
        this.codeTypes.add(codeType);
        return this;
    }

    /**
     * Get a list of codes from a String
     *
     * @param input the input string
     * @return the list of codes
     */
    public ArrayList<Code> getCodeListFromString(String input) {

        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("[\n\r]", "");

        /*final code list*/
        final ArrayList<Code> codeList = new ArrayList<>();

        /*extract codes from string*/
        for (final CodeTypes.CodeType codeType : codeTypes) {
            final Matcher matcher = codeType.getPattern().matcher(input);

            while (matcher.find()) {

                final Code code = new Code();

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

                codeList.add(code);
            }
        }

        return codeList;
    }
}
