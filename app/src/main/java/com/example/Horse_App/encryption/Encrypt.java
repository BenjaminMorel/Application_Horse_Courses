package com.example.Horse_App.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that manages the encryption of the passwords in the database
 */
public class Encrypt {

    /**
     * Static method that will be called in the Database Initializer, login and register method
     * in order to encrypt the passwords before putting them in the database
     * @param s Plain text String
     * @return Encrypted String
     */
    public static final String md5(final String s) {
        final String MD5 = "MD5";

        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            // Return encrypted string
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
