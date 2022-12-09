package com.example.xconspectus.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.xconspectus.data.SubjectDB
import com.example.xconspectus.data.repositories.SubjectsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SubjectsRepository(getApplication<Application>().applicationContext)

    val subjects: LiveData<List<SubjectDB>> = repository.subjects

    fun addSubject(subjectDB: SubjectDB) {
        if (checkAttributes(subjectDB)) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addSubject(subjectDB)
            }
        }
    }

    private fun checkAttributes(subjectDB: SubjectDB) = subjectDB.name != ""


}