package hs.ac.kr.db_info;

import com.google.gson.annotations.SerializedName;

public class InfoData {
    @SerializedName("userId")
    private String userId;

    @SerializedName("userSex")
    private String userSex;

    @SerializedName("userBirth")
    private String userBirth;

    @SerializedName("userJob")
    private String userJob;

    public InfoData(String userId, String userSex, String userBirth, String userJob) {
        this.userId = userId;
        this.userSex = userSex;
        this.userBirth = userBirth;
        this.userJob = userJob;
    }
}
