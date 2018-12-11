package com.hasanatasoy.iamat.api.api_editprofile

import com.hasanatasoy.iamat.api.NetworkService
import com.hasanatasoy.iamat.profile.profileeditpage.EditProfileDTO
import com.hasanatasoy.iamat.profile.profileeditpage.EditProfileView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class EditProfileRequestService(var view: EditProfileView) {

    val request = NetworkService.getRequestApiEditProfile()

    fun sendToServerInformation(editProfileDTO: EditProfileDTO){
        request.sendToInformation(editProfileDTO).enqueue(object:  Callback, retrofit2.Callback<Boolean>{
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                view.showError("Bağlantı hatası")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.body()!!)
                    view.goBackProfileView()
                else
                    view.showError("Server hatası")
            }

        })
    }

    fun getInformationFromServer(username: String){
        request.getInformation(username).enqueue(object: Callback, retrofit2.Callback<EditProfileDTO>{
            override fun onFailure(call: Call<EditProfileDTO>, t: Throwable) {
                view.showError("Bağlantı hatası")
            }

            override fun onResponse(call: Call<EditProfileDTO>, response: Response<EditProfileDTO>) {
                var editProfileDTO = response.body()
                view.setInformation(editProfileDTO!!)
            }
        })
    }
}