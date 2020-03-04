package cm.peter.core;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiResponseCode {
    INITIAL("1234", "initial"),
    SUCCESS("0", "success"),
    NOT_LOGIN("1111", "not login"),
    NOT_FOUND("404", "not found"),
    ERROR("-1", "error")
    ;

    private String code;
    private String description;

    ApiResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return code;
    }
}
