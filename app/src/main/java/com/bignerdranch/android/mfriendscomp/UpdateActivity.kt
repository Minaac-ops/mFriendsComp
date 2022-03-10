package com.bignerdranch.android.mfriendscomp

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.mfriendscomp.model.BEFriend
import com.bignerdranch.android.mfriendscomp.model.Friends

class UpdateActivity : AppCompatActivity() {

    private lateinit var etNo: EditText
    private lateinit var etName: EditText
    private lateinit var tvHeader: TextView

    private var RESULT_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_contact)

        tvHeader = findViewById<TextView>(R.id.tvHeader)
        etName = findViewById<EditText>(R.id.etName)
        etNo = findViewById<EditText>(R.id.etNo)

        setContactOnPage()
    }

    fun returnResult(view: View) {
        setResult(RESULT_CODE, intent)
        finish()
    }

    private fun setContactOnPage() {
        if (ChosenFriend.getChosenFriend() == null) tvHeader.text = "Create new contact."
        tvHeader.text = ChosenFriend.getChosenFriend()?.name
        etName.setText(ChosenFriend.getChosenFriend()?.name)
        etNo.setText(ChosenFriend.getChosenFriend()?.phone)
    }

    private fun createContact() {
        RESULT_CODE = 1
        Friends.addToList(
            BEFriend(
                etName.text.toString(),
                etNo.text.toString()
            )
        )
    }

    fun onClickUpdate(view: View) {
        var chosenFriend = ChosenFriend.getChosenFriend()
        if (chosenFriend != null) {
            updateContact()
            Toast.makeText(this, "Contact has been updated", Toast.LENGTH_LONG)
                .show()
        } else if (chosenFriend == null) {
            createContact()
            Toast.makeText(this, "Contact has been created", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun updateContact() {
        RESULT_CODE = 1
        ChosenFriend.getChosenFriend()?.name = etName.text.toString()
        ChosenFriend.getChosenFriend()?.phone = etNo.text.toString()

    }
}