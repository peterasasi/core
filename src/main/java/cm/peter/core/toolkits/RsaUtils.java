package cm.peter.core.toolkits;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaUtils {
    public static final String RSA_ALGORITHM = "RSA";
    public static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * 生成2048钥匙
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public static String decrypt(String privateKeyPem, String message) throws Exception {
        return decrypt(loadPrivateKeyFromPem(privateKeyPem), message);
    }

    /**
     * 私钥解密
     *
     * @param privateKey PrivateKey
     * @param message    String
     * @return String
     * @throws Exception
     */
    public static String decrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        ByteArrayBuilder crypto = new ByteArrayBuilder();
        int chunkLen = 256;
        byte[] base64bytes = base64Decode(message);
        StringBuilder decrypt = new StringBuilder();

        for (int i = 0; i < base64bytes.length; i += chunkLen) {
            crypto.reset();
            if (i + chunkLen > base64bytes.length) {
                chunkLen = base64bytes.length - i;
            }
            for (int j = 0; j < chunkLen; j++) {
                crypto.append(base64bytes[j]);
            }
//            System.out.println("length = " + crypto.toByteArray().length);
            byte[] chunkDecrypt = cipher.doFinal(crypto.toByteArray());
//            System.out.println("chunk = " + new String(chunkDecrypt));
            decrypt.append(new String(chunkDecrypt));
        }
        return decrypt.toString();
    }

    public static String encrypt(String publicKeyPem, String message) throws Exception {
        return encrypt(loadPublicKeyFromPem(publicKeyPem), message);
    }

    /**
     * 公钥加密
     *
     * @param publicKey PublicKey
     * @param message   String
     * @return String
     * @throws Exception
     */
    public static String encrypt(PublicKey publicKey, String message) throws Exception {
        return base64Encode(encryptBytes(publicKey, message));
    }

    public static byte[] encryptBytes(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        ByteArrayBuilder crypto = new ByteArrayBuilder();
        int chunkLen = 245;
        for (int i = 0; i < message.length(); i += chunkLen) {
            if (i + chunkLen > message.length()) {
                chunkLen = message.length() - i;
            }
            String chunk = message.substring(i, i + chunkLen);

            byte[] bytes = cipher.doFinal(chunk.getBytes(UTF8));
            for (int j = 0; j < bytes.length; j++) {
                crypto.append(bytes[j]);
            }
        }
        return crypto.toByteArray();
    }

    /**
     * 生成签名 sha256
     *
     * @param plainText 明文
     * @return
     * @throws Exception
     */
    public static String signSha256(String plainText, PrivateKey privateKey) throws Exception {
        try {
            Signature signet = Signature.getInstance("SHA256withRSA");
            signet.initSign(privateKey);
            signet.update(plainText.getBytes());
            return Base64Utils.encode(signet.sign());
        } catch (Exception e) {
            throw e;
        }
    }

    public static String signSha256(String plainText, String privateKeyPem) throws Exception {
        return signSha256(plainText, loadPrivateKeyFromPem(privateKeyPem));
    }

    /**
     * 验签 SHA256withRSA
     *
     * @param plainText
     * @param signText
     * @return
     */
    public static boolean verify(String plainText, String signText, PublicKey publicKey) {
        try {
            byte[] signature = Base64Utils.decodeBuffer(signText);
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initVerify(publicKey);
            sign.update(plainText.getBytes());
            return sign.verify(signature);
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean verify(String plainText, String signText, String publicKeyPem) throws Exception {
        return verify(plainText, signText, loadPublicKeyFromPem(publicKeyPem));
    }

    public static RSAPublicKey loadPublicKeyFromPem(String publicKeyPem) throws Exception {
        try {
            String publicKeyPemFormat = publicKeyPem.replace("-----BEGIN PUBLIC KEY-----\n", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replace("\n", "");

            byte[] buffer = base64Decode(publicKeyPemFormat);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static RSAPrivateKey loadPrivateKeyFromPem(String privateKeyStr) throws Exception {
        try {
            String privateKeyPemFormat = privateKeyStr.replace("-----BEGIN PRIVATE KEY-----\n", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replace("\n", "");

            byte[] buffer = base64Decode(privateKeyPemFormat);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String base64Encode(byte[] data) {
        return Base64Utils.encode(data);
    }

    public static byte[] base64Decode(String data) {
        return Base64Utils.decodeBuffer(data);
    }
}
