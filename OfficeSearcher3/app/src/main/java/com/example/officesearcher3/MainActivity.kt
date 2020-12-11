package com.example.officesearcher3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val RC_SIGN_IN = 155
    val TAG = "Specifieke Logging: "
    private val default_web_client_id = "126475748460-brri7pjhiuoskenimums0561bg69lvvs.apps.googleusercontent.com"
    lateinit var mAuth: FirebaseAuth
    private var mGoogleSignInClient: GoogleSignInClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "on Create.....")
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        mAuth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(default_web_client_id)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<SignInButton>(R.id.sign_in_button).setOnClickListener(this)


    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            handleSignInResult(task)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "on Start.....")
//        val currentUser = mAuth.currentUser
//        updateUI(currentUser)

        // is de user al eerder geregistreerd?
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Test test test", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
//
//            val user = FirebaseAuth.getInstance().currentUser
//            //Even kijken of deze null of niet null moet zijn
//            if (user == null) {
////            startActivity(content_main.getLaunchIntent(this))
//                finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in_button -> signIn()
        }
    }


    private fun updateUI(account: GoogleSignInAccount?) {
        Log.i(TAG, "google User is signed in...")
//Moet nog wel geimplementeerd worden,
    }


}



