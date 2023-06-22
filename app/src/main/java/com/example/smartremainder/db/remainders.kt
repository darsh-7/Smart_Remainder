package com.example.smartremainder.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "remainders")
data class remainders(
    @PrimaryKey(autoGenerate = true)
    var remainderId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "calendar")
    var calendar: Calendar = Calendar.getInstance(),

    @ColumnInfo(name = "discription")
    var discription: String = ""
)