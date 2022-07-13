package com.arvinmarquez.expensebucket.utils

import java.text.DecimalFormat
import java.text.NumberFormat

class NumberUtils {

    fun toMoneyFormat(currencySymbol: String?, number: Number) : String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        return if(currencySymbol!=null){
            "$currencySymbol${formatter.format(number)}"
        } else{
            formatter.format(number)
        }
    }
}