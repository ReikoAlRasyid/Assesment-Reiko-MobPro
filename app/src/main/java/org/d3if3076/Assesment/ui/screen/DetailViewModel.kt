package org.d3if3076.Assesment.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3076.Assesment.database.LaundryDao
import org.d3if3076.Assesment.model.Laundry
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DetailViewModel(private val dao: LaundryDao) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    private fun calculateTotal(weight: String, type: String): Float {
        val weightValue = weight.toFloatOrNull()?: 0f
        return when (type) {
            "Normal" -> weightValue * 5000
            "Premium" -> weightValue * 8000
            else -> 0f
        }
    }
    fun insert(nama: String, berat: String, jenis: String) {
        val laundry = Laundry(
            nama = nama,
            berat = berat,
            total = calculateTotal(berat, jenis),
            jenis = jenis,
            tanggal = formatter.format(Date())
            )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(laundry)
        }
    }

    suspend fun getLaundry(id: Long): Laundry? {
        return dao.getLaundryById(id)
    }

    fun update(id: Long, nama: String, berat: String, jenis: String) {

        val mahasiswa = Laundry(
            id = id,
            nama = nama,
            berat = berat,
            total = calculateTotal(berat, jenis),
            jenis = jenis,
            tanggal = formatter.format(Date())
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(mahasiswa)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}
