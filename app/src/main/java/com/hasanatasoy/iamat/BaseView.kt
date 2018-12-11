package com.hasanatasoy.iamat

interface BaseView {

    fun showProgress()

    fun dismissProgress()

    fun showError(message: String)

    fun networkError()

    fun timeoutError()
}