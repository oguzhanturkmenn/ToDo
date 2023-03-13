package com.oguzhanturkmen.mytodoreal.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "note_room")
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "note_id") val note_id: Int,
    @ColumnInfo(name = "note_title") val note_title: String,
    @ColumnInfo(name = "note_body") val note_body: String,
    @ColumnInfo(name = "note_date") val note_date: String
) : Serializable