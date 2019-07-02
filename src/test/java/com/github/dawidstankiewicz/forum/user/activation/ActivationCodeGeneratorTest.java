package com.github.dawidstankiewicz.forum.user.activation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.github.dawidstankiewicz.forum.UnitsTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivationCodeGeneratorTest extends UnitsTestCase {

    @Autowired
    ActivationCodeGenerator activationCodeGenerator;

    @Test
    public void testShouldGenerateActivationCode() {
        String generated = activationCodeGenerator.generate();
        int expectedLength = 64;

        assertNotNull(generated);
        assertEquals(expectedLength, generated.length());
    }

    @Test
    public void testShouldGenerateUniqueCode() {
        String first = activationCodeGenerator.generate();
        String second = activationCodeGenerator.generate();

        assertNotNull(first);
        assertNotNull(second);
        assertNotEquals(first, second);
    }

}
