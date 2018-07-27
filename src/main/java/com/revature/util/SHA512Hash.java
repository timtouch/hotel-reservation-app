package com.revature.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashes the registered password with SHA-512
 * Source code taken from:
 * @author Lokesh Gupta  https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 */

public class SHA512Hash
{
    /**
     * Takes a string and hashes it with SHA-512.
     * @param passwordToHash
     * @return a SHA-512 hashed password
     */
    public static String getSHA512SecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();

            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}