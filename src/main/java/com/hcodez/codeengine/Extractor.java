package com.hcodez.codeengine;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.patterns.PrivateCode;
import com.hcodez.codeengine.patterns.PublicCode;
import com.hcodez.codeengine.patterns.PublicCodeWithPasscode;

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

        Matcher matcherPrivate              = PrivateCode.PATTERN.matcher(input);
        Matcher matcherPublic               = PublicCode.PATTERN.matcher(input);
        Matcher matcherPublicWithPasscode   = PublicCodeWithPasscode.PATTERN.matcher(input);

        Code code;

        while (matcherPrivate.find()) {
            code = new Code();
            code.setIdentifier(matcherPrivate.group("identifier"));
            list.add(code);
        }

        while (matcherPublic.find()) {
            code = new Code();
            code.setIdentifier(matcherPublic.group("identifier"));
            code.setOwner(matcherPublic.group("owner"));
            code.setPublicStatus(true);
            list.add(code);
        }

        while (matcherPublicWithPasscode.find()) {
            code = new Code();
            code.setIdentifier(matcherPublicWithPasscode.group("identifier"));
            code.setOwner(matcherPublicWithPasscode.group("owner"));
            code.setPasscode(matcherPublicWithPasscode.group("passcode"));
            code.setPublicStatus(true);
            list.add(code);
        }
        return list;
    }

}
