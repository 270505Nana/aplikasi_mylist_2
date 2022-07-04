package com.example.aplikasi_mylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list)

        //update
        val update : ImageView = findViewById(R.id.update_btn)
        update.setOnClickListener{view_update()}


        //update
        val delete : ImageView = findViewById(R.id.delete_btn)
        delete.setOnClickListener{view_delete()}

    }

    private fun view_update() {
        val intent = Intent(this, UpdateActivity::class.java)
        startActivity(intent)
    }
    private fun view_delete() {
        val intent = Intent(this, DeleteActivity::class.java)
        startActivity(intent)
    }


}