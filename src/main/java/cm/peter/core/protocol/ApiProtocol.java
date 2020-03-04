package cm.peter.core.protocol;

public interface ApiProtocol {

    /**
     * 加密请求内容
     * @param req 请求内容
     * @return
     */
    String encryptReq(String req);

    /**
     * 加密响应内容
     * @param resp 响应内容
     * @return
     */
    String encryptResp(String resp);

    /**
     * 解密请求内容
     * @param req 请求内容
     * @return
     */
    String decryptReq(String req);

    /**
     * 加密响应内容
     * @param resp 响应内容
     * @return
     */
    String decryptResp(String resp);

    /**
     * 设置加密密钥
     * @param key
     * @return
     */
    ApiProtocol setEncryptKey(String key);

    /**
     * 设置解密密钥
     *
     * @param key
     * @return
     */
    ApiProtocol setDecryptKey(String key);
}
