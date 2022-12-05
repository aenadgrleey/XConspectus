package com.example.xconspectus.ui.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xconspectus.data.ThemeDB
import com.example.xconspectus.data.repositories.ThemesRepository

class SubjectFragmentViewModel(private val repository: ThemesRepository) : ViewModel() {
    var themes = repository.themes

    fun addTheme(themeDB: ThemeDB){
        repository.addTheme(themeDB)
    }

    class Factory(private val repository: ThemesRepository)  : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SubjectFragmentViewModel(repository) as T
        }
    }
}