package com.example.chat_pr

import Model.User
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG: String = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        login_button_main.setOnClickListener() {
            val intent = Intent(this, LoginAcitivty::class.java)
            startActivity(intent)
        }


        join_button.setOnClickListener() {
            val email = email_area.text.toString() // 입력 데이터
            val password = password_area.text.toString()

            auth.createUserWithEmailAndPassword(email, password)  // 비밀번호 글자수 7자 이상
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        Log.d(TAG, "회원가입 성공")

                        val uid = FirebaseAuth.getInstance().uid ?: ""
                        val user = User(uid, username.text.toString())
                        //데이터베이스 넣음
                        val db = FirebaseFirestore.getInstance().collection("users")
                        db.document(uid)
                            .set(user)
                            .addOnSuccessListener {
                                Log.d(TAG, "데이터 베이스 성공")
                            }
                            .addOnFailureListener {
                                Log.d(TAG, "데이터 베이스 실패 $it")
                            }


                        Toast.makeText(this@MainActivity, "회원가입 성공!", Toast.LENGTH_SHORT).show()

                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    } else {
                        Log.d(TAG, "회원가입 실패", task.exception)
                        Toast.makeText(this@MainActivity, "회원가입 실패..", Toast.LENGTH_SHORT).show()
                    }

                }

        }
        login_button_main.setOnClickListener() {

            val intent = Intent(this, LoginAcitivty::class.java)
            startActivity(intent)
        }


    }
}





