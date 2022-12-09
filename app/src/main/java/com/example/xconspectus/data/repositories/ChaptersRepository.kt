package com.example.xconspectus.data.repositories

import android.content.Context
import com.example.xconspectus.data.ChapterDB
import com.example.xconspectus.data.XDatabase

class ChaptersRepository(context: Context, themeId: Int) {
    private val chapterDao = XDatabase.getDatabase(context).chapterDao()
    val chapters = chapterDao.getThemeChapter(themeId)

    fun addChapter(chapterDB: ChapterDB) {
        chapterDao.addChapter(chapterDB)
    }
}