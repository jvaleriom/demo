///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Forma2;
//
//import java.io.Serializable;
//import java.security.Key;
//import java.util.Base64;
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;
//
///**
// *
// * @author jvale
// */
//public class AES {
//
//    private static String ENCRYPT_KEY = "clave-compartida-no-reveloar-nunca";
//
//    private static String encript(String text) throws Exception {
//        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
//
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
//
//        byte[] encrypted = cipher.doFinal(text.getBytes());
//
//        return Base64.encodeBytes(encrypted);
//    }
//
//    private static String decrypt(String encrypted) throws Exception {
//        byte[] encryptedBytes = Base64.decode(encrypted.replace("\n", ""));
//
//        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
//
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, aesKey);
//
//        String decrypted = new String(cipher.doFinal(encryptedBytes));
//
//        return decrypted;
//    }
//}
