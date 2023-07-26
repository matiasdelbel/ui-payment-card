package com.dbel.ui.payment.card

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AmericanExpressTest {

    @Test
    fun `given a 340213589323446 as card number then formatter should return 3402 135893 23446`() {
        val cardNumber = americanExpressFormatter(cardNumber = "340213589323446")

        assertThat(cardNumber).isEqualTo("3402 135893 23446")
    }

    @Test
    fun `given a 34021358932344 as card number then formatter should return 3402 1358932 3446`() {
        val cardNumber = americanExpressFormatter(cardNumber = "34021358932344")

        assertThat(cardNumber).isEqualTo("3402 135893 2344")
    }

    @Test
    fun `given a 3402135893 as card number then formatter should return 3402 135893`() {
        val cardNumber = americanExpressFormatter(cardNumber = "3402135893")

        assertThat(cardNumber).isEqualTo("3402 135893")
    }

    @Test
    fun `given a 340213589 as card number then formatter should return 3402 13589`() {
        val cardNumber = americanExpressFormatter(cardNumber = "340213589")

        assertThat(cardNumber).isEqualTo("3402 13589")
    }

    @Test
    fun `given a 3402 as card number then formatter should return 3402`() {
        val cardNumber = americanExpressFormatter(cardNumber = "3402")

        assertThat(cardNumber).isEqualTo("3402")
    }

    @Test
    fun `given a 340 as card number then formatter should return 340`() {
        val cardNumber = americanExpressFormatter(cardNumber = "340")

        assertThat(cardNumber).isEqualTo("340")
    }
}