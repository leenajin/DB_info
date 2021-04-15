package hs.ac.kr.db_info;

import hs.ac.kr.db_info.InfoData;
import hs.ac.kr.db_info.InfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {

        @POST("/user/info")
        Call<InfoResponse> userInfo(@Body InfoData data);

    }
