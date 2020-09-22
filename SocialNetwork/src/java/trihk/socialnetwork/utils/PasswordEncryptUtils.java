/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TriHuynh
 */
public class PasswordEncryptUtils {

    public static String encryptSHA256(String orgString) {
        MessageDigest messageDigest;
        String encrypt = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes = messageDigest.digest(orgString.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashInBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            encrypt = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(PasswordEncryptUtils.class.getName()).log(Level.SEVERE, null, e);
        }
        return encrypt;
    }
}
