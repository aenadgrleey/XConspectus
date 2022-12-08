package com.example.xconspectus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xconspectus.data.SubjectDB

class SubjectRefactorSharedViewModel : ViewModel() {

    private var _subjectDB: MutableLiveData<SubjectDB> = MutableLiveData()
    val subjectDB : LiveData<SubjectDB> get() = _subjectDB

    fun setSubjectToRefactor(subjectDB: SubjectDB) {
        this._subjectDB.value = subjectDB
    }

    fun setNewSubject(){
        _subjectDB.value = SubjectDB(0, "")
    }

    fun updateSubject(name: String){
        _subjectDB.value!!.name = name

        //mock observer to call it method
        _subjectDB.value = _subjectDB.value
    }


}