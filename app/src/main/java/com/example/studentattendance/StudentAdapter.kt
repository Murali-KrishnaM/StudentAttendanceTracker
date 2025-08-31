package com.example.studentattendance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private var students: MutableList<Student>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtStudentName)
        val toggle: TextView = view.findViewById(R.id.txtAttendanceToggle)
        val icon: ImageView = view.findViewById(R.id.imgStudentIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.txtName.text = student.name
        holder.toggle.text = if (student.status == "Present") "✅" else "❌"

        holder.toggle.setOnClickListener {
            student.status = if (student.status == "Present") "Absent" else "Present"
            notifyItemChanged(position)
            DataRepository.saveAllStudents(holder.itemView.context, students)
        }
    }

    override fun getItemCount(): Int = students.size
}
