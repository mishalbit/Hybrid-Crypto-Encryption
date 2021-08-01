# Hybrid-Crypto-Encryption
Hybrid encryption using the symetric, asymmetric and hashing technique. Here data encryption is implemented in through java in several libraries. It encrypts the given and data and further encryption is carried for obtained public keys. It's main aspect to secure the public key during encryption. It have three steps to create the fire wall. 
AES+RSA+MD5 : 

To implement  AES+RSA+MD5  Encryption in Java, these are the packages we use that are in the java cryptosystem. The Given below are the imported packages :-

            
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
import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

In this approach a single input will undergo on three encryption level,input that has given by the user will undergo AES Encryption firstly and gives an encrypted text which has the length (24,44,64,....) then this encrypted text undergoes RSA Encryption and produces the RSA Encrypted text which has the length (684). After these both encryptions the latest RSA Encrypted text will be feeded as the input for the MD5 Hashing algorithm which results in a Hashed text for the RSA Encrypted Text having 32 characters, Which makes the key more robust to crack. 

AES Output ------> RSA input----> RSA Output-------> MD5 input------> Hashed text[Final output]

