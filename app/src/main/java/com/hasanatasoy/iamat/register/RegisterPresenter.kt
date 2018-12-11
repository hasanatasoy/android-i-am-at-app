package com.hasanatasoy.iamat.register;

import android.util.Log
import com.hasanatasoy.iamat.api.api_register.RegisterRequest
import com.hasanatasoy.iamat.api.api_register.RegisterRequestService;


class RegisterPresenter (var view : RegisterView?, private var registerRequestService: RegisterRequestService = RegisterRequestService(view)){

    fun doRegister(registerDTO: RegisterDTO){
        registerRequestService.doRegister(registerDTO)
    }
}
