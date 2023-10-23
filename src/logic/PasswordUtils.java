package logic;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
	private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    public static byte[] createSalt() {
    	SecureRandom random = new SecureRandom();
    	byte[] salt = new byte[16];
    	random.nextBytes(salt);
        return salt;
    }

    public static byte[] createHash(String password, byte[] salt) {
    	PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password.toCharArray(), Character.MIN_VALUE);
        try {
          SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
          return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
          throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
          spec.clearPassword();
        }
    }

    // Función auxiliar para convertir bytes a una representación hexadecimal
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
