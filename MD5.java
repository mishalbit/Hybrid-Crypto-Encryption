import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MD5 {
    public static String getMD5(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() <32) {     // we can use random padding
                hashtext = "1" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        long startTime = System.currentTimeMillis();

        Scanner sc  = new Scanner(System.in);

        System.out.println("Enter the plainText : ");
        String input = sc.nextLine();
        String encryptedtext = getMD5(input);
        byte[] hash = encryptedtext.getBytes();


        long endTime = System.currentTimeMillis();

        System.out.println("The Hashed Text is : "+encryptedtext);
        System.out.println("Number Of Characters In MD5 Encrypted Text is : "+encryptedtext.length());
        System.out.println("Length Of MD5 Hashed Text in bytes : "+hash.length);

        System.out.println(" Encryption Time in Milli Seconds : " + (endTime - startTime) + " milliseconds");
    }
}
