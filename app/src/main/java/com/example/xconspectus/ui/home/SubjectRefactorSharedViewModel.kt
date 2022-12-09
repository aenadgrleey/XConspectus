package com.example.xconspectus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xconspectus.data.SubjectDB

class SubjectRefactorSharedViewModel : ViewModel() {

    private var _subjectDB: MutableLiveData<SubjectDB> = MutableLiveData()
    val subjectDB: LiveData<SubjectDB> get() = _subjectDB

    //set subject that will be sent to dialog to be edited
    fun setSubjectToRefactor(subjectDB: SubjectDB) {
        this._subjectDB.value = subjectDB
    }

    //set empty subject to create new in database
    fun setNewSubject() {
        _subjectDB.value = SubjectDB(0, "")
    }

    //refactor or set new subject attributes
    fun updateSubject(name: String) {
        _subjectDB.value!!.name = name

        //mock observer to call it method
        _subjectDB.value = _subjectDB.value
    }


}