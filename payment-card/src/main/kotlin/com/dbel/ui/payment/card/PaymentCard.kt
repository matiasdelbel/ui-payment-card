package com.dbel.ui.payment.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun PaymentCardPreview() = MaterialTheme {
    PaymentCard(
        cardHolderName = "Edgar Allan Poe",
        cardNumber = "5403431312352346",
        cardNumberFormatter = { it.chunked(size = 4).joinToString(separator = " ") },
        expirationDate = "07/25",
        securityCode = "123",
        networkLogo = painterResource(id = R.drawable.icon_mastercard),
        issuingBankLogo = painterResource(id = R.drawable.icon_citi),
    )
}

@Composable
fun PaymentCard(
    cardHolderName: String,
    cardNumber: String,
    expirationDate: String,
    securityCode: String,
    networkLogo: Painter,
    issuingBankLogo: Painter,
    cardNumberFormatter: (String) -> String,
    modifier: Modifier = Modifier,
    paymentCardColors: PaymentCardColors = PaymentCardDefaults.colors(),
    paymentCardTexts: PaymentCardTexts = PaymentCardDefaults.texts()
) = Column {
    PaymentCardFront(
        cardHolderName,
        cardNumber,
        expirationDate,
        networkLogo,
        issuingBankLogo,
        cardNumberFormatter,
        modifier,
        paymentCardColors,
        paymentCardTexts
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
fun PaymentCardSide(
    containerColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) = Card(
    colors = CardDefaults.cardColors(containerColor = containerColor),
    modifier = modifier.aspectRatio(PaymentCardDefaults.AspectRatio),
    content = { content() }
)

@Composable
fun PaymentCardFront(
    cardHolderName: String,
    cardNumber: String,
    expirationDate: String,
    networkLogo: Painter,
    issuingBankLogo: Painter,
    cardNumberFormatter: (String) -> String,
    modifier: Modifier = Modifier,
    paymentCardColors: PaymentCardColors = PaymentCardDefaults.colors(),
    paymentCardTexts: PaymentCardTexts = PaymentCardDefaults.texts()
) = PaymentCardSide(containerColor = paymentCardColors.background, modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxHeight(fraction = 0.4f)
        ) {
            IssuingBankLogo(logo = issuingBankLogo)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        ) {
            CardNumber(
                number = cardNumberFormatter(cardNumber),
                color = paymentCardColors.text
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                Spacer(modifier = Modifier.fillMaxWidth(fraction = 0.5f))
                HorizontalExpirationDate(
                    label = paymentCardTexts.validThru,
                    date = expirationDate,
                    color = paymentCardColors.text,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            CardHolderName(
                name = cardHolderName,
                color = paymentCardColors.text,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .weight(1f)
            )
            NetworkLogo(logo = networkLogo)
        }
    }
}

@Composable
fun PaymentCardBack(
    securityCode: String,
    modifier: Modifier = Modifier,
    paymentCardColors: PaymentCardColors = PaymentCardDefaults.colors(),
    paymentCardTexts: PaymentCardTexts = PaymentCardDefaults.texts()
) = PaymentCardSide(containerColor = paymentCardColors.background, modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        MagneticStripe(
            color = paymentCardColors.magneticStripe,
            modifier = Modifier.padding(top = 24.dp)
        )

        Row(modifier = Modifier.padding(top = 8.dp)) {
            Spacer(modifier = Modifier.fillMaxWidth(fraction = 0.7f))
            SecurityCode(label = paymentCardTexts.cvv, code = securityCode, color = paymentCardColors.text)
        }
    }
}

@Composable
fun CardNumber(
    number: String,
    color: Color,
    modifier: Modifier = Modifier
) = Text(
    text = number,
    color = color,
    maxLines = 1,
    letterSpacing = 6.sp,
    fontSize = 22.sp,
    modifier = modifier
)

@Composable
fun CardHolderName(
    name: String,
    color: Color,
    modifier: Modifier = Modifier
) = Text(
    text = name.uppercase(),
    color = color,
    modifier = modifier
)

@Composable
fun HorizontalExpirationDate(
    label: String,
    date: String,
    color: Color,
    modifier: Modifier = Modifier,
) = Row(
    verticalAlignment = Alignment.Bottom,
    modifier = modifier
) {
    Text(
        text = label.replace(oldValue = " ", newValue = "\n").uppercase(),
        color = color,
        style = MaterialTheme.typography.labelSmall
    )
    Text(
        text = date,
        color = color,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
fun VerticalExpirationDate(
    label: String,
    date: String,
    color: Color,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.Start
) {
    Text(
        text = label.uppercase(),
        color = color,
        style = MaterialTheme.typography.labelSmall
    )
    Text(
        text = date,
        color = color,
    )
}

@Composable
fun MagneticStripe(
    color: Color,
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .height(48.dp)
        .background(color)
)

@Composable
fun NetworkLogo(
    logo: Painter,
    modifier: Modifier = Modifier
) = Image(
    painter = logo,
    contentDescription = "network logo",
    modifier = modifier
)

@Composable
fun IssuingBankLogo(logo: Painter) = Image(
    painter = logo,
    contentDescription = "issuing bank logo"
)

@Composable
fun SecurityCode(
    label: String,
    code: String,
    color: Color,
    modifier: Modifier = Modifier
) = Row(
    verticalAlignment = Alignment.Bottom,
    modifier = modifier
) {
    Text(
        text = label.replace(oldValue = " ", newValue = "\n").uppercase(),
        color = color,
        style = MaterialTheme.typography.labelSmall
    )
    Text(
        text = code,
        color = color,
        modifier = Modifier.padding(start = 8.dp)
    )
}

object PaymentCardDefaults {

    internal const val AspectRatio = 1.586f

    @Composable
    fun colors(
        background: Color = MaterialTheme.colorScheme.primaryContainer,
        magneticStripe: Color = MaterialTheme.colorScheme.primary,
        text: Color = MaterialTheme.colorScheme.onPrimaryContainer
    ) = PaymentCardColors(background, magneticStripe, text)

    @Composable
    fun texts(
        cvv: String = LocalContext.current.getString(R.string.payment_card_cvv),
        validThru: String = LocalContext.current.getString(R.string.payment_card_valid_thru),
    ) = PaymentCardTexts(validThru, cvv)
}

@Immutable
data class PaymentCardColors internal constructor(
    val background: Color,
    val magneticStripe: Color,
    val text: Color,
)

@Immutable
data class PaymentCardTexts internal constructor(
    val validThru: String,
    val cvv: String,
)