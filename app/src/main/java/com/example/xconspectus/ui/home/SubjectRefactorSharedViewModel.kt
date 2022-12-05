package com.example.xconspectus.ui.home

import androidx.lifecycle.ViewModel
import com.example.xconspectus.data.SubjectDB

class SubjectRefactorSharedViewModel : ViewModel() {
    private var _refactored : Boolean = false
    val refactored : Boolean get() = _refactored
    private var subjectDB : SubjectDB? = null

    fun subjectRefactored(subjectDB: SubjectDB){
        this.subjectDB = subjectDB
        _refactored = true
    }

    fun setSubjectToRefactor(subjectDB: SubjectDB){
        this.subjectDB = subjectDB
    }

    fun getSubject(): SubjectDB? {
        _refactored = false
        return subjectDB
    }
}