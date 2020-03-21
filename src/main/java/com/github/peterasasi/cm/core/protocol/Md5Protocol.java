package com.github.peterasasi.cm.core.protocol;

import com.github.peterasasi.cm.core.ApiReqParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;

public class Md5Protocol implements ApiProtocol {

    private ObjectMapper  objectMapper;

    public Md5Protocol() {
        this.objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }


    @Override
    public String encryptReq(String req) {
        if (this.objectMapper != null) {
            try {
                ApiReqParams params = this.objectMapper.readValue(req, ApiReqParams.class);
                return this.objectMapper.writeValueAsString(params);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public String encryptResp(String resp) {

//        if (this.objectMapper != null) {
//            try {
//                ApiResponse response = this.objectMapper.readValue(resp, ApiResponse.class);
//                return this.objectMapper.writeValueAsString(response);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//
//        }

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
//        String encryptKey = key;
        return this;
    }

    @Override
    public ApiProtocol setDecryptKey(String key) {
//        String decryptKey = key;
        return this;
    }
}
