package com.insightapp.nocontrole.utils

object MonthNameById {
    fun get(month: Int): String? {
        if (month == 0)
            return "Janeiro"
        else if (month == 1)
            return "Fevereiro"
        else if (month == 2)
            return "Março"
        else if (month == 3)
            return "Abril"
        else if (month == 4)
            return "Maio"
        else if (month == 5)
            return "Junho"
        else if (month == 6)
            return "Julho"
        else if (month == 7)
            return "Agosto"
        else if (month == 8)
            return "Setembro"
        else if (month == 9)
            return "Outubro"
        else if (month == 10)
            return "Novembro"
        else return "Dezembro"
    }
}