package com.github.peterasasi.cm.core;


import com.github.peterasasi.cm.core.exception.ParameterException;

import java.util.ArrayList;

/**
 * 接口请求通用参数
 */
public class ApiReqParams {

    public static final String ALG = "alg";
    public static final String CLIENT_ID = "client_id";
    public static final String SERVICE_TYPE = "service_type";
    public static final String SERVICE_VERSION = "service_version";
    public static final String UID = "uid";
    public static final String SID = "sid";
    public static final String NOTIFY_ID = "notify_id";
    public static final String APP_VERSION = "app_version";
    public static final String APP_TYPE = "app_type";
    public static final String LANG = "lang";
    public static final String SIGN = "sign";
    public static final String BUSS_DATA = "buss_data";

    public static final ArrayList<String> AllParams = new ArrayList<>(
    );

    private String notifyId;
    private String serviceType;
    private String serviceVersion;
    private String appVersion;
    private String appType;
    private String appRequestTime;
    private String lang;
    private String clientId;
    private String sign;
    private String bussData;
    private String uid;
    private String sid;

    static  {
        ApiReqParams.AllParams.add(ApiReqParams.ALG);
        ApiReqParams.AllParams.add(ApiReqParams.CLIENT_ID);
        ApiReqParams.AllParams.add(ApiReqParams.SERVICE_TYPE);
        ApiReqParams.AllParams.add(ApiReqParams.SERVICE_VERSION);
        ApiReqParams.AllParams.add(ApiReqParams.UID);
        ApiReqParams.AllParams.add(ApiReqParams.SID);
        ApiReqParams.AllParams.add(ApiReqParams.NOTIFY_ID);
        ApiReqParams.AllParams.add(ApiReqParams.APP_VERSION);
        ApiReqParams.AllParams.add(ApiReqParams.APP_TYPE);
        ApiReqParams.AllParams.add(ApiReqParams.LANG);
        ApiReqParams.AllParams.add(ApiReqParams.SIGN);
        ApiReqParams.AllParams.add(ApiReqParams.BUSS_DATA);
    }

    public ApiReqParams() {
        this.clientId = "";
        this.notifyId = "";
        this.uid = "0";
        this.sid = "";
        this.sign = "";
        this.serviceType = "";
        this.serviceVersion = "v1";
        this.bussData = "";
    }

    public void check() throws ParameterException {
        if (this.serviceType.isEmpty()) {
            throw new ParameterException("serviceType is required");
        }
        if (this.clientId.isEmpty()) {
            throw new ParameterException("clientId is required");
        }
        if (this.appVersion.isEmpty()) {
            throw new ParameterException("appVersion is required");
        }
        if (this.appType.isEmpty()) {
            throw new ParameterException("appType is required");
        }
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppRequestTime() {
        return appRequestTime;
    }

    public void setAppRequestTime(String appRequestTime) {
        this.appRequestTime = appRequestTime;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getBussData() {
        return bussData;
    }

    public void setBussData(String bussData) {
        this.bussData = bussData;
    }

    @Override
    public String toString() {
        return "ApiReqParams{" +
                "notifyId='" + notifyId + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appType='" + appType + '\'' +
                ", appRequestTime='" + appRequestTime + '\'' +
                ", lang='" + lang + '\'' +
                ", clientId='" + clientId + '\'' +
                ", sign='" + sign + '\'' +
                ", bussData='" + bussData + '\'' +
                ", uid='" + uid + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}
