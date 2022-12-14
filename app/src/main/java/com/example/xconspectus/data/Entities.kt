package com.example.xconspectus.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
)

@Entity(tableName = "themes")
data class ThemeDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "subjectId")
    val subjectId: Int,
)

@Entity(tableName = "chapters")
data class ChapterDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "themeId")
    val themeId: Int
)

@Entity(tableName = "conspectuses")
data class ConspectusDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "chapterId")
    val chapterId: Int,
)