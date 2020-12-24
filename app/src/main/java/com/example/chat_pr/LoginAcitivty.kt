package com.example.chat_pr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginAcitivty : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG = LoginAcitivty::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()


        login_button.setOnClickListener() {
            val email = login_email.text.toString()
            val password = login_pwd.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->


                    if (task.isSuccessful) {
                        Log.d(TAG, "success")
                        Toast.makeText(this@LoginAcitivty, "로그인성공!", Toast.LENGTH_SHORT).show()

                        //데이터베이스에 유저 정보 입력


                       val intent = Intent(this, ChatListAcitivty::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        Log.d(TAG, "fallure", task.exception)
                        Toast.makeText(this@LoginAcitivty, "로그인 실패..", Toast.LENGTH_SHORT).show()
                    }
                }


        }

    }


}

