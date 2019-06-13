package com.hcodez.codeengine.factory;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.patterns.PrivateCode;
import com.hcodez.codeengine.patterns.PublicCode;
import com.hcodez.codeengine.patterns.PublicCodeWithPasscode;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class CodeFactory {


    public CodeFactory() {

    }

    /**
     * Get a list of codes from a String
     * @param input the input string
     * @return the list of codes
     */
    public static ArrayList<Code> createCodeListFromReader(String input, CodeTypes.CodeType... types) {
        // TODO: 2019-06-13 finish implementing

        ArrayList<Code> codeList = new ArrayList<>();

        Matcher matcherPrivate              = PrivateCode.PATTERN.matcher(input);
        Matcher matcherPublic               = PublicCode.PATTERN.matcher(input);
        Matcher matcherPublicWithPasscode   = PublicCodeWithPasscode.PATTERN.matcher(input);

        Code code;

        while (matcherPrivate.find()) {
            code = new Code();
            code.setIdentifier(matcherPrivate.group("identifier"));
            codeList.add(code);
        }

        while (matcherPublic.find()) {
            code = new Code();
            code.setIdentifier(matcherPublic.group("identifier"));
            code.setOwner(matcherPublic.group("owner"));
            code.setPublicStatus(true);
            codeList.add(code);
        }

        while (matcherPublicWithPasscode.find()) {
            code = new Code();
            code.setIdentifier(matcherPublicWithPasscode.group("identifier"));
            code.setOwner(matcherPublicWithPasscode.group("owner"));
            code.setPasscode(matcherPublicWithPasscode.group("passcode"));
            code.setPublicStatus(true);
            codeList.add(code);
        }
        return codeList;
    }

}
