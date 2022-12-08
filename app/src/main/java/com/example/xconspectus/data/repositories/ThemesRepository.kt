package com.example.xconspectus.data.repositories

import android.content.Context
import com.example.xconspectus.data.ThemeDB
import com.example.xconspectus.data.XDatabase

class ThemesRepository(context: Context, subjectId : Int) {
    private val themeDao = XDatabase.getDatabase(context).themeDao()
    val themes = themeDao.getSubjectThemes(subjectId)

    fun addTheme(theme: ThemeDB){
        themeDao.addTheme(theme)
    }
}