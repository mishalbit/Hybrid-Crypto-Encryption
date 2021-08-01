import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.*;
import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AES_RSA_MD5{


    public static void main(String[]ar) throws Exception {

        long startTime = System.currentTimeMillis();

        Scanner sc = new Scanner(System.in);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        // Generate Key AES
        SecretKey key = keyGenerator.generateKey();

        // Generating IV.(AES)
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        System.out.println("Enter plainText:");
        String originaltext1 = sc.nextLine();
        System.out.println("Original Text  : "+originaltext1);

        //Encryption AES
        byte[] cipherText = encrypt(originaltext1.getBytes(),key, IV);
        String encryptedText1 = Base64.getEncoder().encodeToString(cipherText);
        //System.out.println("Encrypted Text : "+Base64.getEncoder().encodeToString(cipherText) );



        // Get an instance of the RSA key generator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);

        // Generate the KeyPair RSA
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the public and private key RSA
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        //System.out.println("AES Encrypted Text  : "+plainText);

        // Encryption RSA
        byte[] cipherTextArray = encrypt1(encryptedText1, publicKey);
        String encryptedText2 = Base64.getEncoder().encodeToString(cipherTextArray);

        String encryptedtext3 = getMD5(encryptedText2);
        byte[] hash = encryptedText2.getBytes();

        long endTime = System.currentTimeMillis();

        System.out.println("Encrypted Text By AES : "+encryptedText1);
        System.out.println("Number Of Characters In AES Encrypted Text in bytes : "+encryptedText1.length());
        System.out.println("Length Of AES Encrypted Text in bits : "+cipherText.length);

        System.out.println("Encrypted Text By RSA : "+encryptedText2);
        System.out.println("Number Of Characters In RSA Encrypted Text in bytes  : "+encryptedText2.length());
        System.out.println("Length Of RSA Encrypted Text in bits : "+cipherTextArray.length);

        System.out.println("Encrypted Text By RSA : "+encryptedtext3);
        System.out.println("Number Of Characters In MD5 Hashed Text in bytes  : "+encryptedtext3.length());
        System.out.println("Length Of MD5 Hashed Text in bits  : "+hash.length);

        System.out.println(" Encryption Time in Milli Seconds : " + (endTime - startTime) + " milliseconds");

    }

    public static byte[] encrypt (byte[] plaintext,SecretKey key,byte[] IV ) throws Exception
    {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);

        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);

        return cipherText;
    }

    public static byte[] encrypt1 (String plainText,PublicKey publicKey ) throws Exception
    {
        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");

        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plainText.getBytes()) ;

        return cipherText;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
