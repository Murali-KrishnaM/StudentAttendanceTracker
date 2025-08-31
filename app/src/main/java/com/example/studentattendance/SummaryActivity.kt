package com.example.studentattendance

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val txtSummary = findViewById<TextView>(R.id.txtSummary)
        val students = DataRepository.getStudents(this).toMutableList()

        val presentCount = students.count { it.status == "Present" }
        val absentCount = students.count { it.status == "Absent" }

        txtSummary.text = "Present: $presentCount\nAbsent: $absentCount\nTotal: ${students.size}"
    }
}
