package com.hasanatasoy.iamat.api.api_login;

import com.hasanatasoy.iamat.login.LoginDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginRequest {

    @POST("login/check")
    Call<Boolean> doLoginCheck(@Body LoginDTO loginDTO);
}
