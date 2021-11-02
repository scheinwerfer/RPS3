import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Hmac {

    private static final String HMAC_ALGO = "HmacSHA512";

    byte[] seed = new byte[16];


    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length*2);
        for(byte b: bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String getKey() throws IllegalStateException {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(seed);

        return bytesToHex(seed);
    }

    public String hmac(String computerMove) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac signer = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(seed, HMAC_ALGO);
        signer.init(keySpec);

        byte[] digest = signer.doFinal(computerMove.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(digest);
    }
}