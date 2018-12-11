package com.hasanatasoy.iamat.api;

import com.hasanatasoy.iamat.api.api_camera.CameraRequest;
import com.hasanatasoy.iamat.api.api_editprofile.EditProfileRequest;
import com.hasanatasoy.iamat.api.api_login.LoginRequest;
import com.hasanatasoy.iamat.api.api_.profile.ProfileRequest;
import com.hasanatasoy.iamat.api.api_post.PostRequest;
import com.hasanatasoy.iamat.api.api_register.RegisterRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    public static final String BASE_URL = "http://192.168.1.100:8080/";
    public static Retrofit myRetrofit = null;

    public static Retrofit getClient(){
        if(myRetrofit == null){
            myRetrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return myRetrofit;
    }


    public static LoginRequest getRequestApiLogin(){
        return getClient().create(LoginRequest.class);
    }
    public static CameraRequest getRequestApiCam(){
        return getClient().create(CameraRequest.class);
    }
    public static RegisterRequest getRequestApiRegister(){
        return getClient().create(RegisterRequest.class);
    }
    public static ProfileRequest getRequestApiProfile(){
        return getClient().create(ProfileRequest.class);
    }
    public static EditProfileRequest getRequestApiEditProfile(){
        return  getClient().create(EditProfileRequest.class);
    }
    public static PostRequest getRequestApiPost(){
        return getClient().create(PostRequest.class);
    }

}
