package com.example.chat_pr.Adepter

import com.example.chat_pr.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.message_list_row.view.*

class UserItem(val name: String, val uid: String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.name.text = name //리사이클 뷰에 텍스트 가져오기
    }

    override fun getLayout(): Int {
        return R.layout.message_list_row
    }

}