package cm.peter.core.toolkits;

import cm.peter.core.exception.CryptException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 注意:
 * 为了避免\u0000影响，这里会将明文先base64加密，再通过aes对该base64字符串进行加密,比如要加密和解密下面的明文
 * "4234/23423423.4/3243/24/23     \u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000
 */
public class AesUtils {

    public final static String ALG = "AES/CBC/pkcs5padding";

    /**
     * 默认
     */
    public final static String DEFAULT_IV = "1234567890123456";


    /**
     * AES加密
     *
     * @param data string 需要加密的数据
     * @param key string 加密需要的KEY
     * @return string 加密
     * @throws CryptException 异常
     */
    public static String encrypt(String data, String key) throws CryptException {
        // 使用base64 加密 避免乱码
        return encrypt(Base64Utils.encodeBuffer(data), key, DEFAULT_IV);
    }


    /**
     * AES加密
     * @param dataBytes 需要加密的数据
     * @param key  加密需要的KEY
     * @param iv   加密需要的向量
     * @return string base64加密后的数据
     * @throws  CryptException 异常
     */
    public static String encrypt(byte[] dataBytes, String key, String iv) throws CryptException {
        byte[] enCodeFormat = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");

        try {
            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes()));

            int blockSize = cipher.getBlockSize();

            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            byte[] encrypted = cipher.doFinal(plaintext);

            return Base64Utils.encode(encrypted);
        } catch (InvalidKeyException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (NoSuchAlgorithmException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (InvalidAlgorithmParameterException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (NoSuchPaddingException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (BadPaddingException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (IllegalBlockSizeException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        }
    }


    /**
     * @param data string aes加密返回的字符串
     * @param key  string key
     * @return string  解密后
     * @throws CryptException 异常
     */
    public static String decrypt(String data, String key) throws CryptException {
        return decrypt(Base64Utils.decodeBuffer(data), key, DEFAULT_IV);
    }

    /**
     * @param data 需要解密的数据
     * @param key  解密需要的KEY 同加密
     * @param iv   解密需要的向量 同加密
     * @return String 解密后
     * @throws  CryptException 异常
     */
    public static String decrypt(byte[] data, String key, String iv) throws CryptException {

        byte[] enCodeFormat = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
        try {
            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes()));// 初始化

            byte[] result = cipher.doFinal(data);
            int i = 0;
            for (i = result.length - 1; i > 0; ) {
                if (result[i] > 0) {
                    break;
                }
                i--;
            }
            i = i + 1;
            byte[] trimResult = new byte[i];
            System.arraycopy(result, 0, trimResult, 0, i);
            return Base64Utils.decode(trimResult);
        } catch (InvalidKeyException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (NoSuchAlgorithmException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (InvalidAlgorithmParameterException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (NoSuchPaddingException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (BadPaddingException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        } catch (IllegalBlockSizeException e) {
            throw new CryptException(e.getMessage(), e.getCause());
        }
    }
}
