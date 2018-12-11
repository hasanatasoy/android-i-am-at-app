package com.hasanatasoy.iamat.login

import com.hasanatasoy.iamat.BaseView

interface LoginView : BaseView{

    fun showSuccess(message: String)
    fun doLogin()
    fun goRegisterPage()
    //sorry for this
    fun assignCurrentUsername(username: String)

}
