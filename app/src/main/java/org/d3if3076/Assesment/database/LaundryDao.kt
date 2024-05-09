package org.d3if3076.Assesment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3076.Assesment.model.Laundry

@Dao
interface LaundryDao {
    @Insert
    suspend fun insert(laundry: Laundry)

    @Update
    suspend fun update(laundry: Laundry)

    @Query("SELECT * FROM laundry ORDER BY tanggal DESC")
    fun getLaundry(): Flow<List<Laundry>>

    @Query("SELECT * FROM laundry WHERE id = :id")
    suspend fun getLaundryById(id: Long): Laundry?

    @Query("DELETE FROM laundry WHERE id = :id")
    suspend fun deleteById(id: Long)
}