package com.hasanatasoy.iamat.profile.profileeditpage

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.hasanatasoy.iamat.R
import com.hasanatasoy.iamat.anim.CustomAnim
import com.hasanatasoy.iamat.profile.profilepage.EditProfileInformation
import com.hasanatasoy.iamat.profile.profilepage.ImpProfileView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.profile_edit_view.*

class ImpEditProfile : AppCompatActivity() , EditProfileView {


    private lateinit var presenter:EditProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit_view)
        presenter = EditProfilePresenter(this)
        editprofile_done.setOnClickListener {
            var sex = false
            if(editprofile_sexE.text.toString().equals("Erkek"))
                sex = false
            else if(editprofile_sexE.text.toString().equals("Kadın"))
                sex = true
            else
                System.out.print("do something")
            // server side
            val editProfileDTO = EditProfileDTO(
                    editprofile_usernameE.text.toString(),
                    editprofile_nameE.text.toString(),
                    editprofile_informationE.text.toString(),
                    editprofile_bioE.text.toString(),
                    editprofile_emailE.text.toString(),
                    editprofile_telE.text.toString(),
                    sex)
            presenter.sendToServerProfileInformation(editProfileDTO)
        }
        editprofile_cancel.setOnClickListener {
            var profileIntent= Intent(this,ImpProfileView::class.java)
            startActivity(profileIntent)
            CustomAnim.animToLeft(this)
        }
        presenter.getInformationFromServer()
    }

    override fun goBackProfileView(){
        var profileIntent = Intent(this,ImpProfileView::class.java)
        startActivity(profileIntent)
    }

    override fun setInformation(editProfileDTO: EditProfileDTO) {
        editprofile_usernameE.setText(editProfileDTO.username)
        editprofile_bioE.setText(editProfileDTO.bio)
        editprofile_emailE.setText(editProfileDTO.email)
        editprofile_informationE.setText(editProfileDTO.information)
        editprofile_nameE.setText(editProfileDTO.name)
        editprofile_telE.setText(editProfileDTO.telNo)
        if(editProfileDTO.sex)
            editprofile_sexE.setText("Kadın")
        else
            editprofile_sexE.setText("Erkek")
    }

    override fun showSuccess(message: String) {
        Toasty.success(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toasty.error(this,message,Toast.LENGTH_SHORT).show()
    }
}