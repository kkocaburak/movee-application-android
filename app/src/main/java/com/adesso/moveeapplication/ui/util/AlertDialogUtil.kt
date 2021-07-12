package com.adesso.moveeapplication.ui.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication

/**
 * Created by Burak Karakoca on 23.10.2020.
 */
object AlertDialogUtil {

    fun showAlert(alert: String, context: Context?) {
        AlertDialog.Builder(context)
            .setMessage(alert)
            .setCancelable(true)
            .show()
    }

    fun showAlertWithButtons(alert: String, context: Context?) : AlertDialog.Builder {
         return AlertDialog.Builder(context)
                .setMessage(alert)
                .setCancelable(true)

    }

}