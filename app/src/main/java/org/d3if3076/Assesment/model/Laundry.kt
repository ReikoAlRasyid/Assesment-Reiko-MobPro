package org.d3if3076.Assesment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "laundry")
data class Laundry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val berat: String,
    val total: Float,
    val jenis: String,
    val tanggal: String
)

