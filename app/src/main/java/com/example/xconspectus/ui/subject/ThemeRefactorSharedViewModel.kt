package com.example.xconspectus.ui.subject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xconspectus.data.ThemeDB

class ThemeRefactorSharedViewModel : ViewModel() {
    private var _refactored: MutableLiveData<Boolean> = MutableLiveData(false)
    val refactored get() = _refactored

    private var _themeDB: ThemeDB? = null
    val themeDB: ThemeDB? get() = _themeDB

    //set theme that will be sent to dialog to be edited
    fun setThemeToRefactor(themeDB: ThemeDB){
        _themeDB = themeDB
    }

    //set empty theme to create new in database
    fun setNewTheme(subjectId: Int){
        _themeDB = ThemeDB(null, "", subjectId)
    }

    //refactor or set new subject attributes
    fun updateTheme(name: String){
        _themeDB!!.name = name
        _refactored.value = true
    }

    fun onAdd(){
        _refactored.value = false
    }
}