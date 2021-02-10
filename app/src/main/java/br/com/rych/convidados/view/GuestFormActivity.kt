package br.com.rych.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.rych.convidados.viewmodel.GuestFormViewModel
import br.com.rych.convidados.R
import br.com.rych.convidados.service.constants.GuestsConstants
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        presentRadioButton.isChecked = true
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.saveButtom) {

            val name = nameEditText.text.toString()
            val presence = presentRadioButton.isChecked

            mViewModel.saveGuest(mGuestId, name, presence)

        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestsConstants.GUESTID)
            mViewModel.load(mGuestId)
        }
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso!!", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(applicationContext, "Falha!!", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, Observer {
            nameEditText.setText(it.name)
            if (it.presence) {
                presentRadioButton.isChecked = true
            } else {
                absentRadioButton.isChecked = true
            }
        })
    }

    private fun setListeners() {
        saveButtom.setOnClickListener(this)
    }


}