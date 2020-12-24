package com.example.chat_pr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginAcitivty : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG = LoginAcitivty::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val login_pwd: EditText = findViewById(R.id.login_pwd)
        val login_email: EditText = findViewById(R.id.email_area)
        val login_button: EditText = findViewById(R.id.login_email)

        login_button.setOnClickListener() {
            val email = login_email.text.toString()
            val password = login_pwd.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->


                    if (task.isSuccessful) {
                        Log.d(TAG, "로그인성공")
                    } else {
                        Log.d(TAG, "로그인 실패", task.exception)
                    }
//                    try {
//                        if (task.isSuccessful) {
//                            Log.d(TAG, "로그인성공")
//                        } else {
//                            Log.d(TAG, "로그인 실패", task.exception)
//                        }
//                    } catch (e: Exception) { //만약 null 값이 들어간다면 화면을 갱신해 다시 로그인 할 수 있게 해준다.
//                        if (email == null || password == null || email == null && password == null) {
//                            val intent = Intent(this, LoginAcitivty::class.java)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                            startActivity(intent)
//                        }
                }


        }

    }


}

