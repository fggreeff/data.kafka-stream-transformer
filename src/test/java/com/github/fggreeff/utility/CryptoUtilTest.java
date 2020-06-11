package com.github.fggreeff.utility;

import org.junit.jupiter.api.Test;

import static com.github.fggreeff.utility.CryptoUtil.keyHashFor;
import static org.junit.jupiter.api.Assertions.*;

class CryptoUtilTest {


    @Test
    void keyHashFor_withNull() {
        assertNull(keyHashFor(null));
    }

    /**
     * Checks that the input is canonical and hashed
     * Prior to hashing, the customer's email address is pre-formatted as follow:
     * <ol>
     *     <li>left and right trimmed of whitespace</li>
     *     <li>converted to lowercase</li>
     * </ol>
     */
    @Test
    void keyHashFor_formatsInputPriorToHashing() {
        assertEquals(
                keyHashFor("joe.cool@example.com"),
                keyHashFor(" \tJOe.Cool@exampLe.com ")
        );
    }
}