package com.hasanatasoy.iamat.profile.profileeditpage

interface EditProfileView {

    fun showSuccess(message: String)
    fun showError(message: String)
    fun setInformation(editProfileDTO: EditProfileDTO)
    fun goBackProfileView()
}