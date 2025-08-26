package com.example.studentattendance

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataRepository {

    private const val PREFS_NAME = "StudentDataPrefs"
    private const val KEY_STUDENTS = "students"

    fun getStudents(context: Context): List<Student> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_STUDENTS, null)
        return if (json != null) {
            val type = object : TypeToken<List<Student>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun saveAllStudents(context: Context, students: List<Student>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(students)
        editor.putString(KEY_STUDENTS, json)
        editor.apply()
    }

    fun saveStudent(context: Context, student: Student) {
        val students = getStudents(context).toMutableList()
        students.add(student)
        saveAllStudents(context, students)
    }

    fun clearAll(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}
