package com.hasanatasoy.iamat.api.api_camera;

import com.hasanatasoy.iamat.post.PostDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CameraRequest {

    @POST("post/save")
    Call<Boolean> addNewPost(@Body PostDTO postDTO);
}
