package com.dbel.ui.payment.card

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MastercardTest {

    @Test
    fun `given a 5412945012562346 as card number then formatter should return 5412 9450 1256 2346`() {
        val cardNumber = mastercardFormatter(cardNumber = "5412945012562346")

        assertThat(cardNumber).isEqualTo("5412 9450 1256 2346")
    }

    @Test
    fun `given a 541294501256234 as card number then formatter should return 5412 9450 1256 234`() {
        val cardNumber = mastercardFormatter(cardNumber = "541294501256234")

        assertThat(cardNumber).isEqualTo("5412 9450 1256 234")
    }

    @Test
    fun `given a 541294501256 as card number then formatter should return 5412 9450 1256`() {
        val cardNumber = mastercardFormatter(cardNumber = "541294501256")

        assertThat(cardNumber).isEqualTo("5412 9450 1256")
    }

    @Test
    fun `given a 54129450 as card number then formatter should return 5412 9450`() {
        val cardNumber = mastercardFormatter(cardNumber = "54129450")

        assertThat(cardNumber).isEqualTo("5412 9450")
    }

    @Test
    fun `given a 5412 as card number then formatter should return 5412`() {
        val cardNumber = mastercardFormatter(cardNumber = "5412")

        assertThat(cardNumber).isEqualTo("5412")
    }

    @Test
    fun `given a 541 as card number then formatter should return 541`() {
        val cardNumber = mastercardFormatter(cardNumber = "541")

        assertThat(cardNumber).isEqualTo("541")
    }
}