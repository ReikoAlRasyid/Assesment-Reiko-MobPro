package org.d3if3076.Assesment.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3076.Assesment.database.LaundryDao
import org.d3if3076.Assesment.model.Laundry

class MainViewModel(dao: LaundryDao) : ViewModel() {

    val data: StateFlow<List<Laundry>> = dao.getLaundry().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}