package com.bignerdranch.android.mfriendscomp

import com.bignerdranch.android.mfriendscomp.model.BEFriend

object ChosenFriend {

    private var chosenFriend: BEFriend? = null

    fun setChosenFriend(friend: BEFriend?){
        this.chosenFriend = friend
    }
    fun getChosenFriend(): BEFriend? {
        return chosenFriend
    }
}