package com.example.xconspectus.data.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.xconspectus.data.SubjectDB
import com.example.xconspectus.data.XDatabase
import java.sql.RowId

class SubjectsRepository(context: Context) {

    private val subjectDao = XDatabase.getDatabase(context)!!.subjectDao()
    val subjects: LiveData<List<SubjectDB>> = subjectDao.loadSubjects()

    fun addSubject(subjectDB: SubjectDB) {
        subjectDao.addSubject(subjectDB)
    }
    fun getSubjectById(subjectId : Int) : LiveData<List<SubjectDB>>{
        return subjectDao.getSubjectById(subjectId)
    }
    fun updateSubject(subjectDB: SubjectDB){
        subjectDao.updateSubject(subjectDB)
    }

}