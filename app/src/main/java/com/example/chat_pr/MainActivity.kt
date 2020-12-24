package com.example.chat_pr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG: String = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val join_button: Button = findViewById(R.id.join_button)
        val email_area: EditText = findViewById(R.id.email_area)
        val password_area: EditText = findViewById(R.id.password_area)
        join_button.setOnClickListener() {
            val email = email_area.text.toString() // 입력 데이터
            val password = password_area.text.toString()

            auth.createUserWithEmailAndPassword(email, password)  // 비밀번호 글자수 7자 이상
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        Log.d(TAG, "로그인성공")
                    } else {
                        Log.d(TAG, "로그인 실패", task.exception)
                    }

//                    try {
//
//                        if (task.isSuccessful) {
//                            Log.d(TAG, "로그인성공")
//                            Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
//                        } else {
//                            Log.d(TAG, "로그인 실패", task.exception)
//                            Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
//                        }
//
//                    } catch (e: Exception) { //만약 null 값이라면 화면갱신
//
//                        if (task.isSuccessful) {
//                            Log.d(TAG, "로그인성공")
//                            Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
//                        } else {
//                            Log.d(TAG, "로그인 실패", task.exception)
//                            Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
//                            val intent = Intent(this, LoginAcitivty::class.java)
//                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                            startActivity(intent)
//                        }
//
//
//                    }
                }

        }


        val join: Button = findViewById(R.id.join_button)
        join.setOnClickListener() {
            val intent = Intent(this, LoginAcitivty::class.java)
            startActivity(intent)


        }
        val login_button_main: Button = findViewById(R.id.login_button_main)

        login_button_main.setOnClickListener() {
            val intent = Intent(this, LoginAcitivty::class.java)
            startActivity(intent)
        }

    }
}





