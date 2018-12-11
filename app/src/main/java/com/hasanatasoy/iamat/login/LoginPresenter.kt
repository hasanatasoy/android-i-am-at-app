package com.hasanatasoy.iamat.login

import com.hasanatasoy.iamat.CurrentUsername
import com.hasanatasoy.iamat.api.api_login.LoginRequestService


class LoginPresenter(var view: LoginView , var loginRequestService: LoginRequestService = LoginRequestService(view)) {
    var service: LoginService? = null

    fun isLogin(username: String, password: String) {
        val loginDTO = LoginDTO(username, password)
        service = LoginService(loginDTO)
        val responseValidation = service!!.isValidation
        if (responseValidation == "UsernameEmpty")
            view.showError("You must enter your username")
        else if (responseValidation == "UsernameLenght")
            view.showError("Username lenght must be greater than 6 character")
        else if (responseValidation == "PasswordEmpty")
            view.showError("You must enter your password")
        else if (responseValidation == "PasswordLenght")
            view.showError("Password lenght must be greater than 6 character")
        else {
            view.showSuccess("Giriş Başarılı")
            view.doLogin()
            view.assignCurrentUsername(username)
            //loginRequestService.doLoginCheck(LoginDTO(username,password))
        }
    }

}
