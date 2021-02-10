package br.com.rych.convidados.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.rych.convidados.service.model.GuestModel
import br.com.rych.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {


    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    fun saveGuest(id: Int, name: String, present: Boolean) {
        val guest = GuestModel(id = id,name = name, presence = present)

        if (id == 0) {
            mSaveGuest.value = mGuestRepository.saveGuest(guest)
        } else {
            mSaveGuest.value = mGuestRepository.updateGuest(guest)
        }

    }

    fun load(id: Int) {
        mGuest.value = mGuestRepository.getAGuest(id)
    }


}