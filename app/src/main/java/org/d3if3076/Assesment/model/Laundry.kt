package org.d3if3076.Assesment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "laundry")
data class Laundry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val berat: String,
    val jenis: String,
    val imageId: String,
)

