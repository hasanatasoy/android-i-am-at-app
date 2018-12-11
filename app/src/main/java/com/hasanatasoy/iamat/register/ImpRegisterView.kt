package com.hasanatasoy.iamat.register

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.hasanatasoy.iamat.login.ImpLoginView
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.anim.CustomAnim
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.register_view.*

class ImpRegisterView : AppCompatActivity(), RegisterView {



    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_view)
        presenter = RegisterPresenter(this)
        registerSignUp!!.setOnClickListener {
            presenter.doRegister(RegisterDTO(
                    registerUsername!!.text.toString(),
                    registerPassword!!.text.toString(),
                    registerEmail!!.text.toString()))
        }
    }

    override fun showSuccess(message: String) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun goBackLoginScreen() {
        val registerIntent = Intent(this, ImpLoginView::class.java)
        startActivity(registerIntent)
        CustomAnim.animToLeft(this)
    }

    override fun finish() {
        super.finish()
        CustomAnim.animToLeft(this)
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun networkError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun timeoutError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
