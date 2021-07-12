package com.adesso.moveeapplication.ui.util

import android.os.Build
import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication
import com.adesso.moveeapplication.application.MoveeConstants
import com.adesso.moveeapplication.ui.base.BaseUIConstants
import java.time.format.DateTimeFormatter

/**
 * Created by Burak Karakoca on 14.10.2020.
 */
object DateFormatterUtil {

    fun formatDate(birthday: String, placeOfBirth: String) : String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(MoveeApplication.appContext.getString(R.string.date_ymd_format_string))
            val finalDate = DateTimeFormatter.ofPattern(
                MoveeApplication.appContext.getString(R.string.date_mdy_format_string)
            ).format(formatter.parse(birthday))

            getBornPlaceText(StringCompilerUtil.compileString(
                finalDate,
                MoveeApplication.appContext.getString(R.string.in_string)),
                placeOfBirth
            )
        } else {
            getBornPlaceText(birthday, placeOfBirth)
        }
    }

    private fun getBornPlaceText(date: String, placeOfBirth: String) : String {
        val partsNonRegex = StringSplitterUtil.getStringBySplitParameter(
            placeOfBirth,
            UtilConstants.COMMA_STRING
        )
        if (partsNonRegex.size < MoveeConstants.INT_TWO) {
            return placeOfBirth
        }
        if (partsNonRegex[partsNonRegex.size - MoveeConstants.INT_TWO].length > MoveeConstants.INT_TEN) {
            return StringCompilerUtil.compileString(
                date,
                partsNonRegex[partsNonRegex.size - MoveeConstants.INT_ONE],
                UtilConstants.DOT_STRING
            )
        }
        return StringCompilerUtil.compileString(
            date,
            StringCompilerUtil.compileWithComma(partsNonRegex[partsNonRegex.size - MoveeConstants.INT_TWO]),
            StringCompilerUtil.compileWithDot(partsNonRegex[partsNonRegex.size - MoveeConstants.INT_ONE])
        )
    }
}