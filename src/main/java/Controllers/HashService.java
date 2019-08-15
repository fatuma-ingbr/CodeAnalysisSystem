package Controllers;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class HashService {

    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    private static final int SALT_BYTES = 24;
    private static final int HASH_BYTES = 24;
    private static final int PBKDF2_ITERATIONS = 1000;

    private static final int ITERATION_INDEX = 0;
    private static final int SALT_INDEX = 1;
    private static final int PBKDF2_INDEX= 2;

    public static String createHash(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return createHash(password.toCharArray());
    }

    private static String createHash(char[] password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        //generating random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);

        //hashing the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);

        //formatting iterations
        return PBKDF2_ITERATIONS + ":" + convertToHex(salt) + ":" + convertToHex(hash);

    }

    //Validating password
    public static boolean Validate(String password, String hash) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return Validate(password.toCharArray(), hash);
    }

    private static boolean Validate(char[] password, String hash) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //Decoding the hash
        String [] hashParams = hash.split(":");
        int iterations = Integer.parseInt(hashParams[ITERATION_INDEX]);
        byte[] salt = convertFromHex(hashParams[SALT_INDEX]);
        byte[] hashes = convertFromHex(hashParams[PBKDF2_INDEX]);

        //computing the hash of the password using the same hash parameters
        byte[] newHash = pbkdf2(password,salt,iterations,hashes.length);

        //comparing the old and new hashes
        return compareHash(hashes, newHash);
    }

    private static byte[] convertFromHex(String s) {
        byte [] binary = new byte[s.length() / 2];
        for (int i = 0; i < binary.length; i++){
            binary[i] = (byte) Integer.parseInt( s.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    //Comparing hashes
    private static boolean compareHash(byte[] oldHash, byte[] newHash) {
        int difference = oldHash.length ^ newHash.length;
        for(int i = 0; i < oldHash.length && i < newHash.length; i++)
            difference |= oldHash[i] ^ newHash[i];

        return difference == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int hashBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {

        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, hashBytes * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return secretKeyFactory.generateSecret(spec).getEncoded();
    }

    private static String convertToHex(byte[] byteArray) {
        BigInteger bigInteger = new BigInteger(byteArray);
        String hex = bigInteger.toString(16);
        int paddingLength = (byteArray.length * 2) - hex.length();
        if(paddingLength > 0){
            return String.format("%0" + paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
}
