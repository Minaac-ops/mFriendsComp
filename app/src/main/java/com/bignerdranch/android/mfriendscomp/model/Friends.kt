package com.bignerdranch.android.mfriendscomp.model

object Friends {
    private val mFriends = mutableListOf<BEFriend>(
        BEFriend("Onkel", "123"),
        BEFriend("Tante", "1234")
    )

    fun getAll(): MutableList<BEFriend> {
        return mFriends
    }

    fun addToList(friendToAdd: BEFriend){
        mFriends.add(friendToAdd)
    }
}