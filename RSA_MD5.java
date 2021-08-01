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

public class RSA_MD5{


    public static void main(String [] ar) throws Exception {

        long startTime = System.currentTimeMillis();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text : ");
        String plainText = sc.nextLine();


        // Get an instance of the RSA key generator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);

        // Generate the KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the public and private key
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("Original Text  : "+plainText);

        // Encryption
        byte[] cipherTextArray = encrypt1(plainText, publicKey);
        String encryptedText1 = Base64.getEncoder().encodeToString(cipherTextArray);

        String encryptedText2 = getMD5(encryptedText1);
        byte[] hash = encryptedText2.getBytes();

        long endTime = System.currentTimeMillis();

        System.out.println("Encrypted Text By RSA : "+encryptedText1);
        System.out.println("Number Of Characters In RSA Encrypted Text is : "+encryptedText1.length());
        System.out.println("Length Of RSA Encrypted Text in bits : "+cipherTextArray.length);

        System.out.println("Encrypted Text By RSA : "+encryptedText2);
        System.out.println("Number Of Characters In MD5 Hashed Text in bytes  : "+encryptedText2.length());
        System.out.println("Length Of MD5 Hashed Text in bytes : "+hash.length);
        System.out.println(" Encryption Time in Milli Seconds : " + (endTime - startTime) + " milliseconds");
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
