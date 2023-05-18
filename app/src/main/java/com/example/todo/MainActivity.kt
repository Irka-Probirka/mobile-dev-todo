package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, Create::class.java)
        val db = DataBase.getDataBase(this)

        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                tasksList.adapter = TaskAdapter(db.getDao().getAllTasks())
            }
            tasksList.hasPendingAdapterUpdates()
            tasksList.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.add.setOnClickListener {
            startActivity(intent)
        }
    }
}