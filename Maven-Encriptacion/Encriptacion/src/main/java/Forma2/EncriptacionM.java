package Forma2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncriptacionM {

    public static void main(String[] args) {
        String cadenaDeTexto = "demo";
        String cadenaEncriptada = "";
        try {
            System.out.println("Cadena original > " + cadenaDeTexto);
            cadenaEncriptada = encriptar(cadenaDeTexto);
            System.out.println("Cadena encriptada > " + cadenaEncriptada);
            String cadenaDesencriptada = desencriptar(cadenaEncriptada);
            System.out.println("Cadena desencriptada > " + cadenaDesencriptada);
        } catch (UnsupportedEncodingException uee) {
            System.out.println("Ups!! > " + uee);
        }
    }

    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    private static String desencriptar(String s) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }
}
