package com.example.xconspectus.ui.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.xconspectus.data.ThemeDB
import com.example.xconspectus.data.repositories.ThemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectFragmentViewModel(private val repository: ThemesRepository) : ViewModel() {
    var themes = repository.themes

    fun addTheme(themeDB: ThemeDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTheme(themeDB)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: ThemesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SubjectFragmentViewModel(repository) as T
        }
    }
}