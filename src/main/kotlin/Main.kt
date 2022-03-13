package ru.netology.kotlin032

import java.lang.Exception

enum class Cards {Mastercard, Maestro, Visa, Mir, VK_Pay}

const val VKPAY_DAY_LIMIT = 15_000
const val VKPAY_MONTH_LIMIT = 40_000

const val CARDS_DAY_LIMIT = 150_000
const val CARDS_MONTH_LIMIT = 600_000


fun main() {

    println("Программа Расчёт Комиссии при платеже через сообщения ВКонтакте")

    while(true){
        var userInput: String?
        print("Введите номер типа карты, Mastercard - 1, Maestro - 2, Visa - 3, Мир - 4, VK_Pay - 5 или end для выхода:  ")
        userInput = readLine()
        if (userInput == "end")
            break

        var cardType: Int = 0
        try {
            cardType = userInput?.toInt()!!
        }catch (e: Exception){
            println("Прошу ввести корректное значение!")
            continue
        }
        if ( !(cardType  in 1..5) ){
            println("Прошу ввести корректное значение!")
            continue
        }

        val card = when(cardType){
            1 -> Cards.Mastercard
            2 -> Cards.Maestro
            3 -> Cards.Visa
            4 -> Cards.Mir
            else -> Cards.VK_Pay
        }

        print("Введите cумму предыдущих переводов в этом месяце:  ")
        userInput = readLine()

        var monthPaymentsSum: Int = 0
        try {
            monthPaymentsSum = userInput?.toInt()!!
        }catch (e: Exception){
            println("Прошу ввести корректное значение!")
            continue
        }
        if ( monthPaymentsSum < 0 ){
            println("Прошу ввести корректное значение!")
            continue
        }


        print("Введите cумму совершаемого перевода:  ")
        userInput = readLine()

        var currentPayment: Int = 0
        try {
            currentPayment = userInput?.toInt()!!
        }catch (e: Exception){
            println("Прошу ввести корректное значение!")
            continue
        }
        if ( currentPayment < 0 ){
            println("Прошу ввести корректное значение!")
            continue
        }

        if ( !checkDaylyLimit(card, currentPayment) ){
            println("Превышен дневной лимит оплат по карте $card!")
            continue
        }
        if ( !checkMonthlyLimit(card, monthPaymentsSum) ){
            println("Превышен месячный лимит оплат по карте $card!")
            continue
        }

        val commission: Int = calcCommission(currentPayment, card, monthPaymentsSum)

        println("Комиссия, копейки: $commission")
    }

}

fun calcCommission(currentPayment: Int, card: Cards = Cards.VK_Pay, monthPaymentsSum: Int = 0): Int {
    val commission: Int = when(card){
        Cards.VK_Pay -> {
            0
        }
        Cards.Mastercard, Cards.Maestro -> {
            if (monthPaymentsSum < 75_000)
                0
            else
                (currentPayment * 100 * 0.006).toInt() + 2_000
        }
        Cards.Visa, Cards.Mir -> {
            val commValue: Int = (currentPayment * 100 * 0.075).toInt()
            if(commValue < 3500)
                3500
            else
                commValue
        }
        else -> 0
    }

    return commission
}

fun checkMonthlyLimit(card: Cards, monthPaymentsSum: Int): Boolean {
    val checkOk: Boolean = when(card){
        Cards.VK_Pay -> {
            monthPaymentsSum <= VKPAY_MONTH_LIMIT
        }
        else -> {
            monthPaymentsSum <= CARDS_MONTH_LIMIT
        }
    }

    return checkOk
}

fun checkDaylyLimit(card: Cards, currentPayment: Int): Boolean {
    val checkOk: Boolean = when(card){
        Cards.VK_Pay -> {
            currentPayment <= VKPAY_DAY_LIMIT
        }
        else -> {
            currentPayment <= CARDS_DAY_LIMIT
        }
    }

    return checkOk
}


