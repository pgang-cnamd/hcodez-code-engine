package com.hcodez.generator;

import com.hcodez.codeengine.IdentifierGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IdentifierGeneratorTest {

    private static final int GENERATED_IDENTIFIERS_COUNT = 1000;

    @Test
    public void GeneratorGenerateIdentifierTest() {
        List<String> generatedIdentifiers = new ArrayList<>();

        for (int i = 0; i < GENERATED_IDENTIFIERS_COUNT; i++) {
            generatedIdentifiers.add(IdentifierGenerator.getInstance().generateIdentifier());
        }

        for (int i = 0; i < GENERATED_IDENTIFIERS_COUNT; i++) {
            for (int j = 0; j < GENERATED_IDENTIFIERS_COUNT; j++) {
                if (i == j) {
                    continue;
                }
                assert !generatedIdentifiers.get(i).equals(generatedIdentifiers.get(j));
            }
        }
    }
}
