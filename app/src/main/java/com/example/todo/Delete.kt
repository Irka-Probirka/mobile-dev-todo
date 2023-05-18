package com.example.todo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val db = DataBase.getDataBase(this)
        val btn_back = findViewById<Button>(R.id.btn_back)
        val btn_delete = findViewById<Button>(R.id.btn_delete)
        val title = findViewById<TextView>(R.id.add_title)
        val about = findViewById<TextView>(R.id.add_about)

        val intent = Intent(this, MainActivity::class.java)

        var position = this.intent.getIntExtra("id", -1)
        if (position == -1) return startActivity(intent)

        val cardTitle = this.intent.getStringExtra("title").toString()
        val cardAbout = this.intent.getStringExtra("about").toString()

        title.text = cardTitle
        about.text = cardAbout

        position += 1
        btn_delete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                db.getDao().deleteTask(TableTask(position, cardTitle, cardAbout))
                startActivity(intent)
            }
        }

        btn_back.setOnClickListener {
            startActivity(intent)
        }

    }
}