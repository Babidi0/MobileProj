package com.example.progetto.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.progetto.data.database.Booking
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.database.Register
import kotlinx.coroutines.launch

class BookingViewModel(private val dao: ProjDAO) : ViewModel() {

    fun addBooking(booking: Booking) {
        viewModelScope.launch {
            dao.addBooking(booking)
        }
    }

    suspend fun getRegister(userId: Int): Register? {
        return dao.getRegisterByUserId(userId)
    }
}



