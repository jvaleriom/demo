/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author jvale
 */
public class SHA2_SHA1_MD5 {
    
    public static void main(String[] args) {
		
	MessageDigest md = null;
	String password = "password D:";
        try {
            
            //SHA-512
            md= MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            byte[] mb = md.digest();
            System.out.println(Hex.encodeHex(mb));
            
            //SHA-1
            md= MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            mb = md.digest();
            System.out.println(Hex.encodeHex(mb));
            
            //MD5
            md= MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            mb = md.digest();
            System.out.println(Hex.encodeHex(mb));
            
        } catch (NoSuchAlgorithmException e) {
            //Error
        }
    }
}
