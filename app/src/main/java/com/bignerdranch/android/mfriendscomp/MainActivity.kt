package com.bignerdranch.android.mfriendscomp

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.mfriendscomp.model.BEFriend
import com.bignerdranch.android.mfriendscomp.model.Friends

class MainActivity : AppCompatActivity() {

    val REQUEST_RESULT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = SimpleAdapter(
            this,
            asListMap(Friends.getAll()),
            R.layout.cell,
            arrayOf("name", "phone"),
            intArrayOf(R.id.name, R.id.phone)
        )

        val listOfFriends = findViewById<ListView>(R.id.lvFriends)
        listOfFriends.adapter = adapter

        listOfFriends.setOnItemClickListener { _, _, pos, _ -> onListItemClick(pos) }
    }

    private fun asListMap(src: MutableList<BEFriend>): List<Map<String, String?>> {
        return src.map { person -> hashMapOf("name" to person.name, "phone" to person.phone) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_close -> {
                finish()
                true
            }
            R.id.action_settings -> {
                val intent = Intent(this, UpdateActivity::class.java)
                startActivityForResult(intent, REQUEST_RESULT)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onListItemClick(position: Int) {
        ChosenFriend.setChosenFriend(Friends.getAll()[position])
        val intent = Intent(this, UpdateActivity::class.java)
        startActivityForResult(intent, REQUEST_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_RESULT) {
            if (resultCode == 1) {
                var adapter = SimpleAdapter(
                    this,
                    asListMap(Friends.getAll()),
                    R.layout.cell,
                    arrayOf("name", "phone"),
                    intArrayOf(R.id.name, R.id.phone)
                )
                val listOfFriends = findViewById<ListView>(R.id.lvFriends)
                listOfFriends.adapter = adapter
                }
            }
        ChosenFriend.setChosenFriend(null)
    }
}