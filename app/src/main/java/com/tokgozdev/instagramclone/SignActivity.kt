package com.tokgozdev.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser


        if(currentUser!= null){
            //do something
        }

    }


    fun signInClick(view: View){
        val email = emailLoginEditText.text.toString()
        val password = passwordLoginEditText.text.toString()

        if(emailLoginEditText != null && passwordLoginEditText != null){
            signInUser(email, password)
        }else{
            Toast.makeText(applicationContext, "Fill the blanks", Toast.LENGTH_LONG).show()
        }
    }


    fun signInUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()

                }else {
                        Toast.makeText(applicationContext, task.exception?.localizedMessage,
                        Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener(this) {task ->
                Toast.makeText(applicationContext, task.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }

     fun notAccClick(view : View){
         val intent = Intent(this, SignUpActivity :: class.java)
         startActivity(intent)
         finish()
     }
}