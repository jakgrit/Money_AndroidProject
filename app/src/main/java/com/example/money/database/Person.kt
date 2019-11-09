package com.example.money.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "money_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    var personId: Long = 0L,

    @ColumnInfo(name = "firstName")
    val firstName: String,

    @ColumnInfo(name = "lastName")
    val lastName: String,

    @ColumnInfo(name = "amount")
    val amount: Double
)