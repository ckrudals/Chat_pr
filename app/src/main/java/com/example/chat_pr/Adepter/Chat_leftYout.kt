package com.example.chat_pr.Adepter

import com.example.chat_pr.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class Chat_leftYout() : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }

    override fun getLayout(): Int {
        return R.layout.chat_left_you
    }
}