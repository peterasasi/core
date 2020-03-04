package com.github.peterasasi.cm.core.protocol;

public class FactoryProtocol {

    public static final String None = "none";
    public static final String Md5 = "md5";
    public static final String Rsa = "rsa";

    public static ApiProtocol getProtocol(String type) {
        switch (type) {
            case FactoryProtocol.Md5:
                return new Md5Protocol();
            case FactoryProtocol.None:
            default:
                return new NoneProtocol();
        }
    }
}
