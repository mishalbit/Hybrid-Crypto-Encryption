import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.*;
import javax.crypto.Cipher;

public class RSA_Encryption
{
    //static String plainText = "Plain text which need to be encrypted by Java RSA Encryption in ECB Mode";

    public static void main(String[] args) throws Exception
    {

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
        String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);

        long endTime = System.currentTimeMillis();

        System.out.println("Encrypted Text By RSA : "+encryptedText);
        System.out.println("Number Of Characters In RSA Encrypted Text in bytes  : "+encryptedText.length());
        System.out.println("Length Of RSA Encrypted Text in bits : "+cipherTextArray.length);

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
}