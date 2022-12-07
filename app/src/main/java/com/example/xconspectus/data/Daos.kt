package com.example.xconspectus.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubjectDao {

    @Query("SELECT *, `rowId` FROM subjects")
    fun loadSubjects(): LiveData<List<SubjectDB>>

    @Query("SELECT *, `rowId` FROM subjects WHERE rowId = :subjectId")
    fun getSubjectById(subjectId: Int) : LiveData<List<SubjectDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSubject(subject: SubjectDB)

    @Update()
    fun updateSubject(subject: SubjectDB)

}

@Dao
interface ThemeDao {

    @Query("SELECT *, `rowId` FROM themes WHERE subjectId=:subjectId")
    fun getSubjectThemes(subjectId: Int): LiveData<List<ThemeDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTheme(theme: ThemeDB)
}