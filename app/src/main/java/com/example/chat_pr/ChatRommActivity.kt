package com.example.chat_pr

import com.example.chat_pr.ChatModel.ChatModel
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_pr.Adepter.Chat_RightMe
import com.example.chat_pr.Adepter.Chat_leftYout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.chat_room.*

class ChatRommActivity : AppCompatActivity() {
    private val TAG = ChatRommActivity::class.java.simpleName

    private lateinit
    var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.chat_room)

        auth = FirebaseAuth.getInstance()  //uid값
        val myUid = auth.uid
        val youruid = intent.getStringExtra("youruid")
        val name = intent.getStringExtra("name")

        val adpater = GroupAdapter<GroupieViewHolder>()


        val db = FirebaseFirestore.getInstance()
        //데이터 불러오기
        db.collection("message")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) { //uid,msg가져오기
                    val senderUid = document.get("myuid")
                    val msg = document.get("message")
                    //내가 보낸 메세지일때
                    if (senderUid!!.equals(myUid)) {
                        adpater.add(Chat_RightMe())
                    } else { //아닐때
                        adpater.add(Chat_leftYout())
                    }
                }
                recyclerView_chat.adapter = adpater
            }


        button.setOnClickListener {
            val message = editText.text.toString()
            editText.setText("")

            val chat = ChatModel(myUid.toString(), youruid.toString(), message)
            db.collection("message")
                .add(chat)
                .addOnSuccessListener {
                    Log.d(TAG, "성공")
                }
                .addOnFailureListener {
                    Log.d(TAG, "실패")
                }
        }

    }

}
