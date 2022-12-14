package com.example.xconspectus.ui.theme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xconspectus.data.ChapterDB

class ChapterRefactorSharedViewModel : ViewModel() {
    private var _refactored: MutableLiveData<Boolean> = MutableLiveData(false)
    val refactored get() = _refactored

    private var _chapterDB : ChapterDB? = null
    val chapterDB : ChapterDB? get() = _chapterDB

    //set chapter that will be sent to dialog to be edited
    fun setChapterToRefactor(chapterDB: ChapterDB){
        _chapterDB = chapterDB
    }

    //set empty chapter to create new in database
    fun setNewChapter(themeId: Int, subjectId: Int){
        _chapterDB = ChapterDB(null, "", themeId)
    }

    //refactor or set new subject attributes
    fun updateChapter(name: String){
        _chapterDB!!.name = name
        _refactored.value = true
    }

    fun onAdd(){
        _refactored.value = false
    }

}