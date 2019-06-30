package com.github.dawidstankiewicz.forum.exception;

import static org.junit.Assert.assertEquals;

import com.github.dawidstankiewicz.forum.UnitsTestCase;
import com.github.dawidstankiewicz.forum.exception.ForumException.ErrorCode;
import org.junit.Test;

public class ForumExceptionTest extends UnitsTestCase {

    @Test
    public void testShouldGetMessageBasedOnErrorCode() {
        ForumException exception = new ForumException(ErrorCode.INTERNAL_ERROR);
        assertEquals("Internal server error", exception.getMessage());
    }

}
