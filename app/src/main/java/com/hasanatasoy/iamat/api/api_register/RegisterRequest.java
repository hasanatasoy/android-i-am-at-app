package com.hasanatasoy.iamat.api.api_register;

import com.hasanatasoy.iamat.register.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterRequest {

    @POST("register/add")
    Call<Boolean> doRegister(@Body RegisterDTO registerDTO);
}
