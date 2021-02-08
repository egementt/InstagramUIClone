package com.tokgozdev.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.view.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser



    }

    fun accTextOnClick(view: View){
        val intent = Intent(this@SignUpActivity, SignActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun signUpOnClick(view: View){

        if(emailSignUpEditText != null && passwordSignUpEditText != null && userNameEditText != null){
            val email = emailSignUpEditText.text.toString()
            val password = passwordSignUpEditText.text.toString()
            val userName = userNameEditText.text.toString()

            createUser(email, password)
        }


    }


    fun createUser(email: String, password : String ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful) {
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                    val user = auth.currentUser
                }else {
                    Toast.makeText(applicationContext, task.exception?.localizedMessage,
                    Toast.LENGTH_LONG).show()
                }
            }
    }

}