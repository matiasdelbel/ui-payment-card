package com.dbel.ui.payment.card

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MastercardPreview() = MaterialTheme {
    Mastercard(
        cardHolderName = "Jose Luis Borges",
        cardNumber = "5412945012562346",
        expirationDate = "07/25",
        securityCode = "123",
        issuingBankLogo = painterResource(id = R.drawable.icon_citi),
    )
}

@Composable
fun Mastercard(
    cardHolderName: String,
    cardNumber: String,
    expirationDate: String,
    securityCode: String,
    issuingBankLogo: Painter,
    modifier: Modifier = Modifier,
) = PaymentCard(
    cardHolderName = cardHolderName,
    cardNumber = cardNumber,
    cardNumberFormatter = ::mastercardFormatter,
    expirationDate = expirationDate,
    networkLogo = painterResource(id = R.drawable.icon_mastercard),
    issuingBankLogo = issuingBankLogo,
    securityCode = securityCode,
    paymentCardColors = PaymentCardDefaults.colors(
        background = Color.Black,
        magneticStripe = Color.LightGray,
        text = Color.White
    ),
    modifier = modifier
)

internal fun mastercardFormatter(cardNumber: String): String = cardNumber
    .chunked(size = 4)
    .joinToString(separator = " ")
