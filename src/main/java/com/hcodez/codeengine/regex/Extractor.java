package com.hcodez.codeengine.regex;

import com.hcodez.codeengine.model.Code;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class Extractor {

    /**
     * Get a list of codes from a String
     * @param input the input string
     * @return the list of codes
     */
    public static ArrayList<Code> getCodesFromString(String input) {

        ArrayList<Code> list = new ArrayList<>();

        Matcher matcherPrivate              = Patterns.PATTERN_PRIVATE_CODE.matcher(input);
        Matcher matcherPublic               = Patterns.PATTERN_PUBLIC_CODE_NO_PASSCODE.matcher(input);
        Matcher matcherPublicWithPasscode   = Patterns.PATTERN_PUBLIC_CODE_WITH_PASSCODE.matcher(input);

        while (matcherPrivate.find()) {
            list.add(new Code(
                    matcherPrivate.group("identifier"),
                    null,
                    null,
                    null
            ));
        }

        while (matcherPublic.find()) {
            list.add(new Code(
                    matcherPublic.group("identifier"),
                    matcherPublic.group("owner"),
                    null,
                    null
            ));
        }

        while (matcherPublicWithPasscode.find()) {
            list.add(new Code(
                    matcherPublicWithPasscode.group("identifier"),
                    matcherPublicWithPasscode.group("owner"),
                    matcherPublicWithPasscode.group("passcode"),
                    null
            ));
        }
        return list;
    }

}
