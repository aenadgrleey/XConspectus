package com.example.xconspectus.data.repositories

import android.content.Context
import com.example.xconspectus.data.ConspectusDB
import com.example.xconspectus.data.XDatabase

class ConspectusRepository(context: Context, chapterId: Int) {

    private val conspectusDao = XDatabase.getDatabase(context).conspectusDao()
    val conspectuses = conspectusDao.getChapterConspectus(chapterId)

    fun addConspectus(conspectusDB: ConspectusDB){
        conspectusDao.addConspectus(conspectusDB)
    }
}