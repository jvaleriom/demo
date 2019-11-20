/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author PC23
 */
public class Sha2 {

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        Scanner in = new Scanner("peru");
//        String input = in.nextLine();
//
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(input.getBytes());
//
//        byte[] digest = md.digest();
//        StringBuffer sb = new StringBuffer();
//        for (byte b : digest) {
//            sb.append(String.format("%02x", b & 0xff));
//        }
//
//        System.out.println("SHA256 Hash: " + sb.toString());
//    }
    public static StringBuffer SHA2(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());

            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(SHA2("valerio"));
    }
}
