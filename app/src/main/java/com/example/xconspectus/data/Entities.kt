package com.example.xconspectus.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subjects")
data class SubjectDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    val id: Int,
    @ColumnInfo(name = "name")
    var name: String
) : Parcelable {}

@Parcelize
@Entity(tableName = "themes")
data class ThemeDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowId")
    val id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name="subjectId")
    val subjectId: Int
) : Parcelable {}