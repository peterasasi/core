package cm.peter.core;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse {

    private Object data;
    private String msg;


    private ApiResponseCode code;

    public ApiResponse() {
        this.code = ApiResponseCode.INITIAL;
        this.msg = "unknown";
        this.data = null;
    }

    public ApiResponse success(Object data) {
        this.code = ApiResponseCode.SUCCESS;
        this.data = data;
        this.msg = "success";

        return this;
    }

    public ApiResponse success(Object data, String msg) {
        this.code = ApiResponseCode.SUCCESS;
        this.data = data;
        this.msg = msg;

        return this;
    }


    public ApiResponse fail(String msg) {
        this.code = ApiResponseCode.ERROR;
        this.msg = msg;
        return this;
    }


    public ApiResponse fail(ApiResponseCode code) {
        this.code = code;
        this.msg = code.getDescription();
        return this;
    }

    public ApiResponse fail(String msg, ApiResponseCode code) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ApiResponse fail(String msg, Object data, ApiResponseCode code) {
        this.code = code;
        this.data = data;
        this.msg = msg;

        return this;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ApiResponseCode getCode() {
        return code;
    }

    public void setCode(ApiResponseCode code) {
        this.code = code;
    }
}
