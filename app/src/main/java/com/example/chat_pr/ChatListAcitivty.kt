package com.example.chat_pr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_pr.Adepter.UserItem
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_chatlist.*
import kotlinx.android.synthetic.main.activity_main.*

class ChatListAcitivty : AppCompatActivity() {
    private val TAG = ChatListAcitivty::class.java.simpleName
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlist)

        val adpater = GroupAdapter<GroupieViewHolder>()





        db.collection("users") //파이어베이스에있는 데이터 읽기
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    adpater.add(
                        UserItem(
                            document.get("username").toString(),
                            document.get("uid").toString()
                        )
                    )
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                recy_id.adapter = adpater
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        adpater.setOnItemClickListener { item, view ->

            val uid: String = ((item as UserItem).uid)
            val name:String=((item as UserItem).toString())

            val intent = Intent(this, ChatRommActivity::class.java)
            intent.putExtra("youruid",uid)
            intent.putExtra("name",name)
            startActivity(intent)
        }

    }
}