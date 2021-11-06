package com.nowiwr01.domain.utils.extensions

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.nowiwr01.domain.model.user.User

fun createUserEventListener(callback: (user: User) -> Unit) = object : ValueEventListener {
    override fun onCancelled(p0: DatabaseError) {}
    override fun onDataChange(snapshot: DataSnapshot) {
        val user = snapshot.getUser()
        callback.invoke(user)
    }
}