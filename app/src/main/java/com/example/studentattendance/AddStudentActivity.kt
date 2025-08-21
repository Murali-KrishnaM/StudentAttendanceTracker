package com.example.studentattendance

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val edtName = findViewById<EditText>(R.id.edtStudentName)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupStatus)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = edtName.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Enter student name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val status = when (radioGroup.checkedRadioButtonId) {
                R.id.radioPresent -> "Present"
                R.id.radioAbsent -> "Absent"
                else -> "Unknown"
            }

            val student = Student(name, status)
            DataRepository.saveStudent(this, student)

            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
