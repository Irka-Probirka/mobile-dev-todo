package com.example.todo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Create : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val title = findViewById<TextView>(R.id.add_title)
        val about = findViewById<TextView>(R.id.add_about)

        val btn_add = findViewById<Button>(R.id.btn_add)
        val btn_back = findViewById<Button>(R.id.btn_back)

        val db = DataBase.getDataBase(this)

        btn_add.setOnClickListener {
            if (!title.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                val text = "Введите название!"
                val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                toast.show()
            } else {
                val tableTask = TableTask(
                    null,
                    title.text.toString(),
                    about.text.toString()
                )
                lifecycleScope.launch(Dispatchers.IO) {
                    db.getDao().insertTask(tableTask)
                }
                val transition = Intent(this, MainActivity::class.java)
                startActivity(transition)
            }
        }

        btn_back.setOnClickListener{
            val transition = Intent(this, MainActivity::class.java)
            startActivity(transition)
        }

    }
}