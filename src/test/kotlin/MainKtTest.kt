import org.junit.Test

import org.junit.Assert.*
import ru.netology.kotlin032.*

class MainKtTest {

    @Test
    fun calcCommission_VK() {
        // Arrange
        val testPayment = 60000

        // Act
        val result = calcCommission(currentPayment = testPayment)

        // Assert
        assertEquals(0, result)
    }



    @Test
    fun calcCommission_MasMae_LessActionLimit() {
        // Arrange
        val testPayment = 10_000
        val testCard: Cards = Cards.Maestro
        val testMonthPayment = 70_000

        // Act
        val result = calcCommission(currentPayment = testPayment, card = testCard, monthPaymentsSum = testMonthPayment)

        // Assert
        assertEquals(0, result)
    }

    @Test
    fun calcCommission_MasMae_MoreActionLimit() {
        // Arrange
        val testPayment = 10_000
        val testCard: Cards = Cards.Maestro
        val testMonthPayment = 80_000

        // Act
        val result = calcCommission(currentPayment = testPayment, card = testCard, monthPaymentsSum = testMonthPayment)

        // Assert
        assertEquals(8_000, result)
    }

    @Test
    fun calcCommission_Visa_lessMin() {
        // Arrange
        val testPayment = 1_000
        val testCard: Cards = Cards.Visa
        val testMonthPayment = 80_000

        // Act
        val result = calcCommission(currentPayment = testPayment, card = testCard, monthPaymentsSum = testMonthPayment)

        // Assert
            assertEquals(3_500, result)
    }

    @Test
    fun calcCommission_Visa_moreMin() {
        // Arrange
        val testPayment = 10_000
        val testCard: Cards = Cards.Visa
        val testMonthPayment = 80_000

        // Act
        val result = calcCommission(currentPayment = testPayment, card = testCard, monthPaymentsSum = testMonthPayment)

        // Assert
        assertEquals(7_500, result)
    }

    @Test
    fun calcCommission_Mir_lessMin() {
        // Arrange
        val testPayment = 1_000
        val testCard: Cards = Cards.Mir
        val testMonthPayment = 80_000

        // Act
        val result = calcCommission(currentPayment = testPayment, card = testCard, monthPaymentsSum = testMonthPayment)

        // Assert
        assertEquals(3_500, result)
    }

    @Test
    fun calcCommission_Mir_moreMin() {
        // Arrange
        val testPayment = 10_000
        val testCard: Cards = Cards.Mir
        val testMonthPayment = 80_000

        // Act
        val result = calcCommission(currentPayment = testPayment, card = testCard, monthPaymentsSum = testMonthPayment)

        // Assert
        assertEquals(7_500, result)
    }




    @Test
    fun checkMonthlyLimit_VK_less10() {
        // Arrange
        val testCard: Cards = Cards.VK_Pay
        val testMonthSum = VKPAY_MONTH_LIMIT - 10

        // Act
        val result = ru.netology.kotlin032.checkMonthlyLimit(card = testCard, monthPaymentsSum = testMonthSum)

        // Assert
        assertEquals(true, result)
    }

    @Test
    fun checkMonthlyLimit_VK_more10() {
        // Arrange
        val testCard: Cards = Cards.VK_Pay
        val testMonthSum = VKPAY_MONTH_LIMIT + 10

        // Act
        val result = ru.netology.kotlin032.checkMonthlyLimit(card = testCard, monthPaymentsSum = testMonthSum)

        // Assert
        assertEquals(false, result)
    }

    @Test
    fun checkMonthlyLimit_VK_exact() {
        // Arrange
        val testCard: Cards = Cards.VK_Pay
        val testMonthSum = VKPAY_MONTH_LIMIT

        // Act
        val result = ru.netology.kotlin032.checkMonthlyLimit(card = testCard, monthPaymentsSum = testMonthSum)

        // Assert
        assertEquals(true, result)
    }

    @Test
    fun checkMonthlyLimit_Cards_less10() {
        // Arrange
        val testCard: Cards = Cards.Mastercard
        val testMonthSum = CARDS_MONTH_LIMIT - 10

        // Act
        val result = ru.netology.kotlin032.checkMonthlyLimit(card = testCard, monthPaymentsSum = testMonthSum)

        // Assert
        assertEquals(true, result)
    }

    @Test
    fun checkMonthlyLimit_Cards_more10() {
        // Arrange
        val testCard: Cards = Cards.Visa
        val testMonthSum = CARDS_MONTH_LIMIT + 10

        // Act
        val result = ru.netology.kotlin032.checkMonthlyLimit(card = testCard, monthPaymentsSum = testMonthSum)

        // Assert
        assertEquals(false, result)
    }

    @Test
    fun checkMonthlyLimit_Cards_exact() {
        // Arrange
        val testCard: Cards = Cards.Mir
        val testMonthSum = CARDS_MONTH_LIMIT

        // Act
        val result = ru.netology.kotlin032.checkMonthlyLimit(card = testCard, monthPaymentsSum = testMonthSum)

        // Assert
        assertEquals(true, result)
    }





    @Test
    fun checkDaylyLimit_VK_less10() {
        // Arrange
        val testCard: Cards = Cards.VK_Pay
        val testDaySum = VKPAY_DAY_LIMIT - 10

        // Act
        val result = checkDaylyLimit(card = testCard, currentPayment = testDaySum)

        // Assert
        assertEquals(true, result)
    }

    @Test
    fun checkDaylyLimit_VK_more10() {
        // Arrange
        val testCard: Cards = Cards.VK_Pay
        val testDaySum = VKPAY_DAY_LIMIT + 10

        // Act
        val result = checkDaylyLimit(card = testCard, currentPayment = testDaySum)

        // Assert
        assertEquals(false, result)
    }

    @Test
    fun checkDaylyLimit_VK_exact() {
        // Arrange
        val testCard: Cards = Cards.VK_Pay
        val testDaySum = VKPAY_DAY_LIMIT

        // Act
        val result = checkDaylyLimit(card = testCard, currentPayment = testDaySum)

        // Assert
        assertEquals(true, result)
    }

    @Test
    fun checkDaylyLimit_Cards_less10() {
        // Arrange
        val testCard: Cards = Cards.Mastercard
        val testDaySum = CARDS_DAY_LIMIT - 10

        // Act
        val result = checkDaylyLimit(card = testCard, currentPayment = testDaySum)

        // Assert
        assertEquals(true, result)
    }

    @Test
    fun checkDaylyLimit_Cards_more10() {
        // Arrange
        val testCard: Cards = Cards.Mir
        val testDaySum = CARDS_DAY_LIMIT + 10

        // Act
        val result = checkDaylyLimit(card = testCard, currentPayment = testDaySum)

        // Assert
        assertEquals(false, result)
    }

    @Test
    fun checkDaylyLimit_Cards_exact() {
        // Arrange
        val testCard: Cards = Cards.Visa
        val testDaySum = CARDS_DAY_LIMIT

        // Act
        val result = checkDaylyLimit(card = testCard, currentPayment = testDaySum)

        // Assert
        assertEquals(true, result)
    }


}