package com.example.googlesignf

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.googlesignf.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        auth = Firebase.auth

        binding.button.setOnClickListener {  }

        binding.google.setOnClickListener {
             val signInClient = googleSignInClient.signInIntent
              launcher.launch(signInClient)
        }

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.
    StartActivityForResult()){
        result ->

        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            if (task.isSuccessful){
                val account:GoogleSignInAccount?=task.result
                val credential = GoogleAuthProvider.getCredential(account?.idToken,null)
                auth.signInWithCredential(credential).addOnCompleteListener{
                    if (it.isSuccessful){
//                        Toast.makeText(this,"done",Toast.LENGTH_LONG).show()
                          startActivity(Intent(this,SignOutActivity::class.java))

                    }else{
                        Toast.makeText(this,"Failed F",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }else{
            Toast.makeText(this , "Failed M" , Toast.LENGTH_LONG).show()
        }

    }

//    override fun onStart() {
//        super.onStart()
//
//      if (auth.currentUser!=null){
//          startActivity(Intent(this,SignOutActivity::class.java))
//      }
//    }
}