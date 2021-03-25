package com.github.dawidstankiewicz.forum.exception;

import com.github.dawidstankiewicz.forum.UnitsTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForumExceptionTest extends UnitsTestCase {

    @Test
    public void testShouldGetMessageBasedOnErrorCode() {
        ForumException exception = new ForumException();
        assertEquals("Internal server error", exception.getMessage());
    }

}
