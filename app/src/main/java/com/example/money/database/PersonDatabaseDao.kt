package com.example.money.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDatabaseDao {

    @Insert
    fun insert(person: Person)

    @Update
    fun update(person: Person)

    @Query("Select * from money_table Where personId = :key")
    fun get(key: Long): Person?

    @Query("DELETE FROM money_table")
    fun clear()

    @Query("Select * from money_table Order By personId Desc Limit 1")
    fun getPerson(): Person?

    @Query("SELECT * FROM money_table ORDER BY personId DESC")
    fun getAllPersons(): LiveData<List<Person>>
}