package com.gdscug.mooflix.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.model.User
import com.gdscug.mooflix.databinding.ActivityRegisterBinding
import com.gdscug.mooflix.ui.login.LoginActivity
import com.gdscug.mooflix.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RegisterviewModel()
        setUpAction()

    }

    private fun RegisterviewModel(){
    val factory = ViewModelFactory.getInstace(this)
        viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]
    }

    private fun setUpData(){
        binding.apply {
            val name = edtUsername.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            when {
                name.isEmpty() -> {
                    tvUsername.error = getString(R.string.cant_empty)
                }

                email.isEmpty() -> {
                    tvEmail.error = getString(R.string.cant_empty)
                }

                name.isEmpty() -> {
                    tvPassword.error = getString(R.string.cant_empty)
                }
                else -> {
                    viewModel.saveUser(User(name,email,password, false))
                    Toast.makeText(this@RegisterActivity,R.string.register_success,Toast.LENGTH_SHORT).show()
               moveActivity()
                }
            }
        }
    }

    private fun setUpAction(){
        binding.apply {

            btnRegister.setOnClickListener{setUpData()}
            tvLogin.setOnClickListener {moveActivity()}
        }
    }
    private fun moveActivity(){
        startActivity(Intent(this,LoginActivity::class.java ))
        finish()
    }

}