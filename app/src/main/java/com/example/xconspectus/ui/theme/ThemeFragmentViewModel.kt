package com.example.xconspectus.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.xconspectus.data.ChapterDB
import com.example.xconspectus.data.repositories.ChaptersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThemeFragmentViewModel(private val repository: ChaptersRepository) : ViewModel() {
    val chapters = repository.chapters

    fun addChapter(chapterDB: ChapterDB) {
        if (attributesCheck(chapterDB)) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addChapter(chapterDB)
            }

        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: ChaptersRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ThemeFragmentViewModel(repository) as T
        }
    }

    private fun attributesCheck(chapterDB: ChapterDB): Boolean = chapterDB.name != ""

}