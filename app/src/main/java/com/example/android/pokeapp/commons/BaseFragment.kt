package com.example.android.pokeapp.commons

import androidx.fragment.app.Fragment
import com.example.android.pokeapp.commons.uicomponents.ErrorDialog

abstract class BaseFragment: Fragment() {

    var errorDialog : ErrorDialog? = null

    override fun onDestroy() {
        super.onDestroy()
        errorDialog?.dismiss()
    }

}