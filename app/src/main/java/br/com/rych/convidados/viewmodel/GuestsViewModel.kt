package br.com.rych.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rych.convidados.service.constants.GuestsConstants
import br.com.rych.convidados.service.model.GuestModel
import br.com.rych.convidados.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList


    fun load(filter: Int) {

        if (filter == GuestsConstants.FILTER.EMPTY) {
            mGuestList.value = mGuestRepository.getAllGuests()
        } else if (filter == GuestsConstants.FILTER.PRESENT) {
            mGuestList.value = mGuestRepository.getPresents()
        } else {
            mGuestList.value = mGuestRepository.getAbsents()
        }


    }

    fun delete(id: Int) {
        mGuestRepository.deleteGuest(id)
    }
}