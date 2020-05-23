package com.alien.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alien.mvvmsampleapp.R
import com.alien.mvvmsampleapp.data.db.entities.User
import com.alien.mvvmsampleapp.databinding.ActivityLoginBinding
import com.alien.mvvmsampleapp.utils.hide
import com.alien.mvvmsampleapp.utils.show
import com.alien.mvvmsampleapp.utils.showToast
import com.alien.mvvmsampleapp.utils.snackBar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this;
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User?) {
        progress_bar.hide()
        root_layout.snackBar("${user?.name} is Logged In!!")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message)
    }
}
