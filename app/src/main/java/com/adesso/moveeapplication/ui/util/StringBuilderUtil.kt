package com.adesso.moveeapplication.ui.util

import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import java.lang.StringBuilder

/**
 * Created by Burak Karakoca on 20.10.2020.
 */
object StringBuilderUtil {
    fun checkStringBuilder(_stringBuilder: StringBuilder) {
        if (_stringBuilder.isNotEmpty()) {
            _stringBuilder.append(UtilConstants.COMMA_SPACE_STRING)
        }
    }
}