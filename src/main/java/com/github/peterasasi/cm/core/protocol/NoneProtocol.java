package com.github.peterasasi.cm.core.protocol;

public class NoneProtocol implements ApiProtocol {


    @Override
    public String encryptReq(String req) {
        return req;
    }

    @Override
    public String encryptResp(String resp) {
        return resp;
    }

    @Override
    public String decryptReq(String req) {
        return req;
    }

    @Override
    public String decryptResp(String resp) {
        return resp;
    }

    @Override
    public ApiProtocol setEncryptKey(String key) {
        return this;
    }

    @Override
    public ApiProtocol setDecryptKey(String key) {
        return this;
    }

    @Override
    public void setApiUri(String apiUri) {

    }

    @Override
    public String getApiUri() {
        return "";
    }
}
