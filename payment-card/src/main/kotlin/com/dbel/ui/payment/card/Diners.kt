package com.dbel.ui.payment.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview
fun DinersPreview() = MaterialTheme {
    Diners(
        cardHolderName = "Gabriel Garcia Marquez",
        cardNumber = "30021358933446",
        expirationDate = "07/25",
        securityCode = "123",
        issuingBankLogo = painterResource(id = R.drawable.icon_citi),
    )
}

/**
 * Render an Diners Club International card.
 */
@Composable
fun Diners(
    cardHolderName: String,
    cardNumber: String,
    expirationDate: String,
    securityCode: String,
    issuingBankLogo: Painter,
    modifier: Modifier = Modifier,
    paymentCardColors: PaymentCardColors = PaymentCardDefaults.colors(
        background = Color(color = 0xFFAAA9AE),
        magneticStripe = Color.Black
    ),
    paymentCardTexts: PaymentCardTexts = PaymentCardDefaults.texts(validThru = "Thru")
) = Column {
    DinersCardFront(
        cardHolderName,
        cardNumber,
        expirationDate,
        issuingBankLogo,
        paymentCardColors,
        paymentCardTexts,
        modifier
    )

    Spacer(modifier = Modifier.height(8.dp))

    PaymentCardBack(
        securityCode,
        modifier,
        paymentCardColors,
        paymentCardTexts
    )
}

@Composable
fun DinersCardFront(
    cardHolderName: String,
    cardNumber: String,
    expirationDate: String,
    issuingBankLogo: Painter,
    paymentCardColors: PaymentCardColors,
    paymentCardTexts: PaymentCardTexts,
    modifier: Modifier = Modifier
) = PaymentCardSide(
    containerColor = paymentCardColors.background,
    modifier = modifier
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(all = 16.dp)
    ) {
        val (networkingIconRef, issuingBankLogoRef, cardNumberRef, expirationDateRef, cardHolderNameRef) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.icon_diners),
            contentDescription = "networking logo",
            modifier = Modifier.constrainAs(networkingIconRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )

        Image(
            painter = issuingBankLogo,
            contentDescription = "issuing bank logo",
            modifier = Modifier.constrainAs(issuingBankLogoRef) {
                top.linkTo(networkingIconRef.top)
                bottom.linkTo(networkingIconRef.bottom)
                end.linkTo(parent.end)
            }
        )

        CardNumber(
            number = dinersFormatter(cardNumber),
            color = paymentCardColors.font,
            modifier = Modifier.constrainAs(cardNumberRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )

        CardHolderName(
            name = cardHolderName,
            color = paymentCardColors.font,
            modifier = Modifier.constrainAs(cardHolderNameRef) {
                bottom.linkTo(expirationDateRef.top)
                start.linkTo(parent.start)
            }
        )

        HorizontalExpirationDate(
            label = paymentCardTexts.validThru,
            date = expirationDate,
            color = paymentCardColors.font,
            modifier = Modifier.constrainAs(expirationDateRef) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, margin = 16.dp)
            }
        )
    }
}

internal fun dinersFormatter(cardNumber: String): String {
    require(value = cardNumber.length <= 14) { "Diners card should contain 14 numbers top." }

    val firstChuck = cardNumber.subStringOrDefault(
        range = IntRange(0, 3),
        default = cardNumber.subStringOrEmpty(startIndex = 0)
    )
    val secondChuck = cardNumber.subStringOrDefault(
        range = IntRange(4, 9),
        default = cardNumber.subStringOrEmpty(startIndex = 4)
    )
    val thirdChuck = cardNumber.subStringOrEmpty(startIndex = 10)

    return "$firstChuck $secondChuck $thirdChuck".trim()
}
