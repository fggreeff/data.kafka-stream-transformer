package com.github.fggreeff.utility;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Utility class containing cryptographic functions.
 */
public class CryptoUtil {

    /**
     * Reformats a string to a canonical format and hashes it (lowercase, trimmed and final SHA-256).
     *
     * @param str the string to be canonicalised and hashed.
     */
    public static final String keyHashFor(String str) {
        return str == null ? null : DigestUtils.sha256Hex(str.trim().toLowerCase());
    }
}
