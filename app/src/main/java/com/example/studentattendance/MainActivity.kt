package com.example.studentattendance

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerStudents: RecyclerView
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerStudents = findViewById(R.id.recyclerStudents)
        recyclerStudents.layoutManager = LinearLayoutManager(this)

        studentAdapter = StudentAdapter(DataRepository.getStudents(this).toMutableList())
        recyclerStudents.adapter = studentAdapter

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_students -> true
                R.id.nav_add -> {
                    startActivity(Intent(this, AddStudentActivity::class.java))
                    true
                }
                R.id.nav_summary -> {
                    startActivity(Intent(this, SummaryActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val updatedStudents = DataRepository.getStudents(this).toMutableList()
        studentAdapter = StudentAdapter(updatedStudents)
        recyclerStudents.adapter = studentAdapter
    }
}
