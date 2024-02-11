package com.example.googlesignf

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.googlesignf.databinding.ActivitySignOutBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class SignOutActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

           val go = GoogleSignIn.getClient(this,gso)
            go.signOut()

            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}