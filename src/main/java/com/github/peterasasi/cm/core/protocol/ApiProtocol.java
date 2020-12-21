package com.github.peterasasi.cm.core.protocol;

public interface ApiProtocol {

    void setApiUri(String apiUri);

    String getApiUri();

    /**
     * 加密请求内容
     * @param req 请求内容
     * @return String
     */
    String encryptReq(String req);

    /**
     * 加密响应内容
     * @param resp 响应内容
     * @return String
     */
    String encryptResp(String resp);

    /**
     * 解密请求内容
     * @param req 请求内容
     * @return String
     */
    String decryptReq(String req);

    /**
     * 加密响应内容
     * @param resp 响应内容
     * @return String
     */
    String decryptResp(String resp);

    /**
     * 设置加密密钥
     * @param key String
     * @return String
     */
    ApiProtocol setEncryptKey(String key);

    /**
     * 设置解密密钥
     *
     * @param key String
     * @return String
     */
    ApiProtocol setDecryptKey(String key);
}
