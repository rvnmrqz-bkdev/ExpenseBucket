package com.arvinmarquez.expensebucket.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.arvinmarquez.expensebucket.databinding.ActivityLoginBinding
import com.arvinmarquez.expensebucket.presentation.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binder: ActivityLoginBinding
    private lateinit var signInBtn: Button
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binder = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binder.root)


        binder.signInBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}