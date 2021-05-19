package com.example.imageupload

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun login(view: View) {
        auth = Firebase.auth
        val ee:EditText = findViewById(R.id.email)
        val email = ee.text.toString()
        val ep:EditText = findViewById(R.id.password)
        val password = ep.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("abcd", "signInWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(this,"Sign In Successful",Toast.LENGTH_LONG).show()
                    startUserActivity(auth)
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("abcd", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                    // [START_EXCLUDE]
                    //TODO: checkForMultiFactorFailure(task.exception!!)
                }

                // [START_EXCLUDE]
                //if (!task.isSuccessful) {
                //    binding.status.setText(R.string.auth_failed)
                //}
                //hideProgressBar()
                // [END_EXCLUDE]
            }

    }

    private fun startUserActivity(auth:FirebaseAuth) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("auth",auth)
        startActivity(intent)
    }


    fun signup(view: View) {
        auth = Firebase.auth
        val ee:EditText = findViewById(R.id.email)
        val email = ee.text.toString()
        val ep:EditText = findViewById(R.id.password)
        val password = ep.text.toString()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("abcd", "createUserWithEmail:success")
                val user = auth.currentUser
                Toast.makeText(this,"User Creation Successful",Toast.LENGTH_LONG).show()
                //updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("abcd", "createUserWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                //updateUI(null)
            }

            // [START_EXCLUDE]
            //hideProgressBar()
            // [END_EXCLUDE]
        }

    }


}