package hs.ac.kr.db_info;

import com.google.gson.annotations.SerializedName;

public class InfoResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
