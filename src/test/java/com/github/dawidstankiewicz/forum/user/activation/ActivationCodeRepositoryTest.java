package com.github.dawidstankiewicz.forum.user.activation;

import static org.junit.Assert.assertNotNull;

import com.github.dawidstankiewicz.forum.IntegrationTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivationCodeRepositoryTest extends IntegrationTestCase {

    @Autowired
    private ActivationCodeRepository repository;


    @Test
    public void testSaveShouldReturnEntity() {
        ActivationCode activationCode = new ActivationCode("XXX");
        activationCode = repository.save(activationCode);

        assertNotNull(activationCode);
    }
}
