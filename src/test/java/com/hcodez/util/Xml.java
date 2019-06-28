package com.hcodez.util;

import com.hcodez.codeengine.builder.CodeBuilder;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Xml {

    private Document loadXmlDocument(String inputFile) throws IOException, SAXException, ParserConfigurationException {
        InputStream inputStream = TestCommon.getResourceAsInputStream(inputFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(inputStream);
    }

    private Code getCodeFromXmlElement(Element element, CodeType codeType) {
        CodeBuilder codeBuilder = CodeBuilder.createBuilder()
                .withIdentifier(element.getAttribute("identifier"))
                .withCodeType(CodeType.fromString(element.getAttribute("codeType")));

        switch (codeType) {
            case PRIVATE:
                return codeBuilder
                        .build();

            case PUBLIC_NO_PASSCODE:
                return codeBuilder
                        .withOwner(element.getAttribute("owner"))
                        .build();

            case PUBLIC_WITH_PASSCODE:
                return codeBuilder
                        .withOwner(element.getAttribute("owner"))
                        .withPasscode(element.getAttribute("passcode"))
                        .build();

            default:
                return null;
        }
    }

    public ArrayList<Code> loadCodesFromXml(CodeType codeType) throws ParserConfigurationException, SAXException, IOException {
        ArrayList<Code> list = new ArrayList<>();

        Document document = loadXmlDocument("/xml/codes_private.xml");
        document.getDocumentElement().normalize();

        NodeList codeList = document.getElementsByTagName("code");

        for (int i = 0; i < codeList.getLength(); i++) {
            final Element element = (Element) codeList.item(i);
            list.add(getCodeFromXmlElement(element, codeType));
        }

        return list;
    }
}
