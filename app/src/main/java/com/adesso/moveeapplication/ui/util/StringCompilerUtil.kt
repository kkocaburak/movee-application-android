package com.adesso.moveeapplication.ui.util

import com.adesso.moveeapplication.R
import com.adesso.moveeapplication.application.MoveeApplication

/**
 * Created by Burak Karakoca on 9.10.2020.
 */
object StringCompilerUtil {
    fun compileString(string1: Any, string2: Any) : String {
        return string1.toString() + string2.toString()
    }
    fun compileString(string1: Any, string2: Any, string3: Any) : String {
        return string1.toString() + string2.toString() + string3.toString()
    }
    fun compileWithComma(string1: Any) : String {
        return string1.toString() + UtilConstants.COMMA_STRING
    }
    fun compileWithDot(string1: Any) : String {
        return string1.toString() + UtilConstants.DOT_STRING
    }
}