package org.d3if3076.Assesment.navigation

import org.d3if3076.Assesment.ui.screen.KEY_ID_LAUNDRY

sealed class Screen(val route: String){
    data object Home: Screen("mainscreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_LAUNDRY}") {
        fun withId(id:Long) = "detailScreen/$id"
    }
}