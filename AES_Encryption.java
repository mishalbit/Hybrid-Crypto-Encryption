import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class AES_Encryption
{


    public static void main(String[] args) throws Exception
    {

        long startTime = System.currentTimeMillis();

        Scanner sc = new Scanner(System.in);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        // Generate Key
        SecretKey key = keyGenerator.generateKey();

        // Generating IV.
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

         System.out.println("Enter plainText:");
        String originaltext1 = sc.nextLine();
        System.out.println("Original Text  : "+originaltext1);


        byte[] cipherText = encrypt(originaltext1.getBytes(),key, IV);
        String encryptedText = Base64.getEncoder().encodeToString(cipherText);

        long endTime = System.currentTimeMillis();

        System.out.println("Encrypted Text By AES : "+encryptedText);
        System.out.println("Number Of Characters In AES Encrypted Text in bytes  : "+encryptedText.length());
        System.out.println("Length Of AES Encrypted Text in bits : "+cipherText.length);

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
}
