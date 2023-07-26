package com.dbel.ui.payment.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview
fun AmericanExpressPreview() = MaterialTheme {
    AmericanExpress(
        cardHolderName = "Mario Vargas Llosa",
        cardNumber = "340213589323446",
        expirationDate = "07/25",
        securityCode = "1233",
    )
}

/**
 * Render an American Express card.
 */
@Composable
fun AmericanExpress(
    cardHolderName: String,
    cardNumber: String,
    expirationDate: String,
    securityCode: String,
    modifier: Modifier = Modifier,
    paymentCardColors: PaymentCardColors = PaymentCardDefaults.colors(background = BackgroundColor),
    paymentCardTexts: PaymentCardTexts = PaymentCardDefaults.texts(cvv = NoText)
) = PaymentCardSide(
    containerColor = paymentCardColors.background,
    modifier = modifier
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        val (networkingNameRef, networkingIconRef, cardNumberRef, securityCodeRef, expirationDateRef, cardHolderNameRef) = createRefs()

        AmericanExpressName(
            modifier = Modifier.constrainAs(networkingNameRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        AmericanExpressLogo(
            modifier = Modifier.constrainAs(networkingIconRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )

        SecurityCode(
            label = paymentCardTexts.cvv,
            color = paymentCardColors.font,
            code = securityCode,
            modifier = Modifier.constrainAs(securityCodeRef) {
                bottom.linkTo(cardNumberRef.top, margin = 8.dp)
                end.linkTo(parent.end)
            }
        )

        CardNumber(
            number = americanExpressFormatter(cardNumber),
            color = paymentCardColors.font,
            modifier = Modifier.constrainAs(cardNumberRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )

        ExpirationDate(
            label = paymentCardTexts.validThru,
            date = expirationDate,
            color = paymentCardColors.font,
            orientation = ExpirationDate.Vertical,
            modifier = Modifier.constrainAs(expirationDateRef) {
                bottom.linkTo(cardHolderNameRef.top, margin = 4.dp)
                start.linkTo(parent.start)
            }
        )

        CardHolderName(
            name = cardHolderName,
            color = paymentCardColors.font,
            modifier = Modifier.constrainAs(cardHolderNameRef) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        )
    }
}

@Composable
private fun AmericanExpressName(modifier: Modifier = Modifier) = Text(
    text = LocalContext
        .current
        .getString(R.string.payment_card_american_express)
        .uppercase(),
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.onPrimary,
    style = TextStyle(
        fontSize = 22.sp,
        letterSpacing = 4.sp,
        shadow = Shadow(
            color = Color.Black, offset = Offset(4f, 4f), blurRadius = 3f
        )
    ),
    modifier = modifier
)

@Composable
private fun AmericanExpressLogo(modifier: Modifier = Modifier) = Image(
    painter = painterResource(id = R.drawable.icon_american_express),
    contentDescription = "American Express logo",
    alpha = 0.28f,
    modifier = modifier
)

internal fun americanExpressFormatter(cardNumber: String): String {
    require(value = cardNumber.length <= 15) { "American Express card should contain 15 numbers top." }

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

private val BackgroundColor = Color(color = 0xFFCAE9D9)
private const val NoText = ""
