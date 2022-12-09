package com.example.xconspectus.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xconspectus.data.SubjectDB

class SubjectRefactorSharedViewModel : ViewModel() {
    private var _refactored: MutableLiveData<Boolean> = MutableLiveData(false)
    val refactored get() = _refactored

    private var _subjectDB: SubjectDB? = null
    val subjectDB: SubjectDB? get() = _subjectDB

    //set subject that will be sent to dialog to be edited
    fun setSubjectToRefactor(subjectDB: SubjectDB) {
        this._subjectDB = subjectDB
    }

    //set empty subject to create new in database
    fun setNewSubject() {
        _subjectDB = SubjectDB(null, "")
    }

    //refactor or set new subject attributes
    fun updateSubject(name: String) {
        _subjectDB!!.name = name
        _refactored.value = true
    }

    fun onAdd() {
        _refactored.value = false
    }


}