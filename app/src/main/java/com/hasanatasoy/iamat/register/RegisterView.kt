package com.hasanatasoy.iamat.register

import com.hasanatasoy.iamat.BaseView

interface RegisterView : BaseView{

    fun showSuccess(message: String)
    fun goBackLoginScreen()
}
