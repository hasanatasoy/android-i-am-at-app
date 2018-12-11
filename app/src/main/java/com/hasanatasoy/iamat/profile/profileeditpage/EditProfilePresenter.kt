package com.hasanatasoy.iamat.profile.profileeditpage

import com.hasanatasoy.iamat.CurrentUsername
import com.hasanatasoy.iamat.api.api_editprofile.EditProfileRequestService

class EditProfilePresenter(view: EditProfileView) {

    val requestService = EditProfileRequestService(view)

    fun sendToServerProfileInformation(editProfileDTO: EditProfileDTO){
        requestService.sendToServerInformation(editProfileDTO)
    }

    fun getInformationFromServer(){
        requestService.getInformationFromServer(CurrentUsername.username!!)
    }
}