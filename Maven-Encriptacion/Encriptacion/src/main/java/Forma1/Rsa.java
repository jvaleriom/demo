/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Rsa {

    public PrivateKey PrivateKey = null;
    public PublicKey PublicKey = null;

    public void setPrivateKeyString(String key) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        byte[] encodedPrivateKey = stringToBytes(key);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec
                = new PKCS8EncodedKeySpec(encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        this.PrivateKey = privateKey;
    }

    public void setPublicKeyString(String key) throws NoSuchAlgorithmException,
            InvalidKeySpecException {

        byte[] encodedPublicKey = stringToBytes(key);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec
                = new X509EncodedKeySpec(encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        this.PublicKey = publicKey;
    }

    public String getPrivateKeyString() {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = 
                new PKCS8EncodedKeySpec(this.PrivateKey.getEncoded());
        return bytesToString(pkcs8EncodedKeySpec.getEncoded());
    }

    public String getPublicKeyString() {
        X509EncodedKeySpec x509EncodedKeySpec
                = new X509EncodedKeySpec(this.PublicKey.getEncoded());
        return bytesToString(x509EncodedKeySpec.getEncoded());
    }

    public void genKeyPair(int size) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(size);
        KeyPair kp = kpg.genKeyPair();

        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        this.PrivateKey = privateKey;
        this.PublicKey = publicKey;
    }

    public String Encrypt(String plain) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException,
            InvalidKeySpecException, UnsupportedEncodingException,
            NoSuchProviderException {

        byte[] encryptedBytes;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, this.PublicKey);
        encryptedBytes = cipher.doFinal(plain.getBytes());

        return bytesToString(encryptedBytes);

    }

    public String Decrypt(String result) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        byte[] decryptedBytes;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, this.PrivateKey);
        decryptedBytes = cipher.doFinal(stringToBytes(result));
        return new String(decryptedBytes);
    }

    public String bytesToString(byte[] b) {
        byte[] b2 = new byte[b.length + 1];
        b2[0] = 1;
        System.arraycopy(b, 0, b2, 1, b.length);
        return new BigInteger(b2).toString(36);
    }

    public byte[] stringToBytes(String s) {
        byte[] b2 = new BigInteger(s, 36).toByteArray();
        return Arrays.copyOfRange(b2, 1, b2.length);
    }

    public void saveToDiskPrivateKey(String path) throws IOException {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new 
        FileOutputStream(path), "UTF-8"));
            out.write(this.getPrivateKeyString());
            out.close();
        } catch (Exception e) {

        }
    }

    public void saveToDiskPublicKey(String path) {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new 
        FileOutputStream(path), "UTF-8"));
            out.write(this.getPublicKeyString());
            out.close();
        } catch (Exception e) {

        }
    }

    public void openFromDiskPublicKey(String path) throws IOException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        String content = this.readFileAsString(path);
        this.setPublicKeyString(content);
    }

    public void openFromDiskPrivateKey(String path) throws IOException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        String content = this.readFileAsString(path);
        this.setPrivateKeyString(content);
    }

    private String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    public static void main(String[] args) throws Exception {

        //Definimos un texto a cifrar
        String password = "JuanValerioMayta1999";

        System.out.println("\nCifrar password original:");
        System.out.println(password);

        //Instanciamos la clase
        Rsa rsa = new Rsa();

        //Generamos un par de claves
        //Admite claves de 512, 1024, 2048 y 4096 bits
        rsa.genKeyPair(512);

        String file_private = "C:\\Users\\jvale\\Desktop\\demoPri.txt";
        String file_public = "C:\\Users\\jvale\\Desktop\\demoPub.txt";

        //Las guardamos asi podemos usarlas despues
        //a lo largo del tiempo
        rsa.saveToDiskPrivateKey("C:\\Users\\jvale\\Desktop\\demoPri.txt");
        rsa.saveToDiskPublicKey("C:\\Users\\jvale\\Desktop\\demoPub.txt");

        //Ciframos y e imprimimos, el texto cifrado
        //es devuelto en la variable secure
        String secure = rsa.Encrypt(password);

        System.out.println("\nCifrado:");
        System.out.println(secure);

        //A modo de ejemplo creamos otra clase rsa
        Rsa rsa2 = new Rsa();

        //A diferencia de la anterior aca no creamos
        //un nuevo par de claves, sino que cargamos
        //el juego de claves que habiamos guadado
        rsa2.openFromDiskPrivateKey("C:\\Users\\jvale\\Desktop\\demoPri.txt");
        rsa2.openFromDiskPublicKey("C:\\Users\\jvale\\Desktop\\demoPub.txt");

        //Le pasamos el texto cifrado (secure) y nos 
        //es devuelto el texto ya descifrado (unsecure) 
        String unsecure = rsa2.Decrypt(secure);

        //Imprimimos
        System.out.println("\nDescifrado el Password original:");
        System.out.println(unsecure);

    }

    public PrivateKey getPrivateKey() {
        return PrivateKey;
    }

    public void setPrivateKey(PrivateKey PrivateKey) {
        this.PrivateKey = PrivateKey;
    }

    public PublicKey getPublicKey() {
        return PublicKey;
    }

    public void setPublicKey(PublicKey PublicKey) {
        this.PublicKey = PublicKey;
    }
}
