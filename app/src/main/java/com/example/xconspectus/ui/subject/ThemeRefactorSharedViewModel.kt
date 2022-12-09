package com.example.xconspectus.ui.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xconspectus.data.ThemeDB

class ThemeRefactorSharedViewModel : ViewModel() {
    private var _themeDB: MutableLiveData<ThemeDB> = MutableLiveData()
    val themeDB: LiveData<ThemeDB> get() = _themeDB

    //set theme that will be sent to dialog to be edited
    fun setThemeToRefactor(themeDB: ThemeDB){
        _themeDB.value = themeDB
    }

    //set empty theme to create new in database
    fun setNewTheme(subjectId: Int){
        _themeDB.value = ThemeDB(0, "", subjectId)
    }

    //refactor or set new subject attributes
    fun updateTheme(name: String){
        _themeDB.value!!.name = name
        //mock theme to call observer
        _themeDB.value = themeDB.value
    }
}