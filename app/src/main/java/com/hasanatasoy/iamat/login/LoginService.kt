package com.hasanatasoy.iamat.login

import android.text.TextUtils


class LoginService (var loginDTO: LoginDTO?) {



    val isValidation: String
        get() = if (loginDTO!!.username == null)
            "UsernameEmpty"
        else if (loginDTO!!.username!!.length < 6)
            "UsernameLenght"
        else if (TextUtils.isEmpty(loginDTO!!.password) || loginDTO!!.password == null)
            "PasswordEmpty"
        else if (loginDTO!!.password!!.length < 6)
            "PasswordLenght"
        else
            "Success"
}
