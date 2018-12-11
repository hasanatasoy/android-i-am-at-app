package com.hasanatasoy.iamat.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hasanatasoy.iamat.CurrentUsername
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.anim.CustomAnim

import com.hasanatasoy.iamat.post.ImpPostView
import com.hasanatasoy.iamat.register.ImpRegisterView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.login_view.*

class ImpLoginView : AppCompatActivity(), LoginView, View.OnClickListener {



    var loginPresenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        loginPresenter = LoginPresenter(this)
        loginButton.setOnClickListener(this)
        registerButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.loginButton -> loginClick()
            R.id.registerButton -> registerClick()
        }
    }

    private fun loginClick() {
        loginPresenter!!.isLogin(loginUsername.text.toString(), loginPassword.text.toString())
    }

    //api classlarını kotline çevirince düzeltilecek bu kısım çok sıkıntılı
    override fun assignCurrentUsername(username: String){
        CurrentUsername.username = username
    }

    private fun registerClick() {
        goRegisterPage()
    }

    override fun showSuccess(message: String) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun doLogin() {
        val loginIntent = Intent(this, ImpPostView::class.java)
        startActivity(loginIntent)
        CustomAnim.animToRight(this)
    }

    override fun goRegisterPage() {
        val loginIntent = Intent(this, ImpRegisterView::class.java)
        startActivity(loginIntent)
        CustomAnim.animToRight(this)
    }

    override fun finish() {
        super.finish()
        CustomAnim.animToRight(this)
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
