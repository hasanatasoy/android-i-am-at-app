package com.hasanatasoy.iamat.api.api_camera;

import android.util.Log;

import com.hasanatasoy.iamat.api.NetworkService;
import com.hasanatasoy.iamat.camera.PhotoEdit.PhotoEdit;
import com.hasanatasoy.iamat.post.PostDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraRequestService {

    private CameraRequest cameraRequest = NetworkService.getRequestApiCam();
    private PhotoEdit view;

    public CameraRequestService(PhotoEdit view){
        this.view = view;
    }

    public void addNewPostAndCheckStatus(PostDTO postDTO){
        try {
            cameraRequest.
                    addNewPost(postDTO)
                    .enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.body()){
                                view.showSuccess("Post Başarı ile Kaydedildi");
                                view.goPostScreen();
                            }
                            else
                                view.showError("Post Kaydedilemedi");
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            view.showError("Post Kaydedilemedi");
                        }
                    });
        }
        catch (Exception e){
        }
    }
}
