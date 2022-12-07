package com.example.xconspectus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xconspectus.data.SubjectDB

class SubjectRefactorSharedViewModel : ViewModel() {
    private val _refactored = MutableLiveData(false)
    val refactored: LiveData<Boolean> get() = _refactored
    private var _newlyAdded = false
    val newlyAdded get() = _newlyAdded
    private var _subjectDB: SubjectDB = SubjectDB(0, "")
    val subjectDB get() = _subjectDB
    private var _position = 0
    val position get() = _position

    fun subjectRefactored() {
        _refactored.value = true
    }

    fun setSubjectToRefactor(subjectDB: SubjectDB, position: Int) {
        this._subjectDB = subjectDB
        _position = position
    }

    fun actionsDone() {
        _refactored.value = false
        _newlyAdded = false
    }

    fun refactorSubjectName(name: String) {
        _subjectDB.name = name
    }

    fun newSubject() {
        _subjectDB = SubjectDB(0, "")
        _newlyAdded = true
    }
}