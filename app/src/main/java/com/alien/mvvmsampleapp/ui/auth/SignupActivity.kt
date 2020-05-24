package com.alien.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alien.mvvmsampleapp.R
import com.alien.mvvmsampleapp.data.db.entities.User
import com.alien.mvvmsampleapp.databinding.ActivitySignupBinding
import com.alien.mvvmsampleapp.ui.home.HomeActivity
import com.alien.mvvmsampleapp.utils.hide
import com.alien.mvvmsampleapp.utils.show
import com.alien.mvvmsampleapp.utils.snackBar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), KodeinAware, AuthListener {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance<AuthViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewmodel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.signupViewModel = viewmodel
        viewmodel.authListener = this

        viewmodel.loggedInUser.observe(this, Observer { user->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }


    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User?) {
        progress_bar.hide()
        //root_layout.snackBar("${user?.name} is Logged In!!")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message)
    }
}
