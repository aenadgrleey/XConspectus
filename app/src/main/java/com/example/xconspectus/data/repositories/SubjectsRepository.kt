package com.example.xconspectus.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.xconspectus.data.SubjectDB
import com.example.xconspectus.data.XDatabase

class SubjectsRepository(context: Context) {

    private val subjectDao = XDatabase.getDatabase(context)!!.subjectDao()
    val subjects: LiveData<List<SubjectDB>> = subjectDao.loadSubjects()

    suspend fun addSubject(subjectDB: SubjectDB) {
        subjectDao.addSubject(subjectDB)
    }

}