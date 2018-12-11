package com.hasanatasoy.iamat.register

import com.google.gson.annotations.SerializedName

class RegisterDTO(@SerializedName("username")
                  var username: String? = null,
                  @SerializedName("password")
                  var password: String? = null,
                  @SerializedName("email")
                  var email: String? = null)
