package com.example.aplikasi_mylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val my_profile : ImageView = findViewById(R.id.vitur_profile)
        my_profile.setOnClickListener{view_profile()}
//
//        val add_list : ImageView = findViewById(R.id.vitur_addlist)
//        add_list.setOnClickListener{view_addlist()}
//
//        val my_list : ImageView = findViewById(R.id.vitur_mylist)
//        my_list.setOnClickListener{view_mylist()}




        //RecylerView Read from database (Firebase)
        userRecyclerView = findViewById(R.id.transaction_list)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<List>()
        getTransactionData()


        //add float button
        val addflt_btn = findViewById<FloatingActionButton>(R.id.create_btn)
        addflt_btn.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun view_profile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

//    private fun view_addlist() {
//        val intent = Intent(this, AddActivity::class.java)
//        startActivity(intent)
//    }
//
//    private fun view_mylist() {
//        val intent = Intent(this, ShowActivity::class.java)
//        startActivity(intent)
//    }

    private fun getTransactionData() {
        dbref = FirebaseDatabase.getInstance().getReference("Transaction")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    for (transactionSnapshot in snapshot.children) {

                        val transaction = transactionSnapshot.getValue(List::class.java)
                        userArrayList.add(transaction!!)
                    }

                    userRecyclerView.adapter = Adapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}

