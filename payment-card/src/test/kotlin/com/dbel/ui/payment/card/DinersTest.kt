package com.dbel.ui.payment.card

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DinersTest {

    @Test
    fun `given a 34021358933446 as card number then formatter should return 3402 135893 3446`() {
        val cardNumber = dinersFormatter(cardNumber = "34021358933446")

        assertThat(cardNumber).isEqualTo("3402 135893 3446")
    }

    @Test
    fun `given a 3402135893344 as card number then formatter should return 3402 135893 344`() {
        val cardNumber = dinersFormatter(cardNumber = "3402135893344")

        assertThat(cardNumber).isEqualTo("3402 135893 344")
    }

    @Test
    fun `given a 3402135893 as card number then formatter should return 3402 135893`() {
        val cardNumber = dinersFormatter(cardNumber = "3402135893")

        assertThat(cardNumber).isEqualTo("3402 135893")
    }

    @Test
    fun `given a 340213589 as card number then formatter should return 3402 13589`() {
        val cardNumber = dinersFormatter(cardNumber = "340213589")

        assertThat(cardNumber).isEqualTo("3402 13589")
    }

    @Test
    fun `given a 3402 as card number then formatter should return 3402`() {
        val cardNumber = dinersFormatter(cardNumber = "3402")

        assertThat(cardNumber).isEqualTo("3402")
    }

    @Test
    fun `given a 340 as card number then formatter should return 340`() {
        val cardNumber = dinersFormatter(cardNumber = "340")

        assertThat(cardNumber).isEqualTo("340")
    }
}