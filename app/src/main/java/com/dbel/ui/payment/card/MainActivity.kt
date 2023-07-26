package com.dbel.ui.payment.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dbel.ui.payment.card.ui.theme.UiPaymentCardTheme

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiPaymentCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        val pagerState = rememberPagerState()

                        PaymentCardExamples(
                            pagerState,
                            modifier = Modifier.weight(1f)
                        )
                        PageIndicator(
                            pagerState,
                            modifier = Modifier.height(50.dp).fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PaymentCardExamples(state: PagerState, modifier: Modifier = Modifier) = HorizontalPager(
    pageCount = 4,
    state = state,
    modifier = modifier
) { page ->
    when (page) {
        0 -> AmericanExpress(
            cardHolderName = "Mario Vargas Llosa",
            cardNumber = "340213589323446",
            expirationDate = "07/25",
            securityCode = "1233",
            modifier = Modifier.padding(all = 16.dp)
        )
        1 -> Diners(
            cardHolderName = "Gabriel Garcia Marquez",
            cardNumber = "30021358933446",
            expirationDate = "07/25",
            securityCode = "123",
            issuingBankLogo = painterResource(id = R.drawable.icon_citi),
            modifier = Modifier.padding(all = 16.dp)
        )
        2 -> Mastercard(
            cardHolderName = "Jose Luis Borges",
            cardNumber = "5412945012562346",
            expirationDate = "07/25",
            securityCode = "123",
            issuingBankLogo = painterResource(id = R.drawable.icon_citi),
            modifier = Modifier.padding(all = 16.dp)
        )
        3 -> Visa(
            cardHolderName = "Roberto Bolagno",
            cardNumber = "4056453265402053",
            expirationDate = "07/25",
            securityCode = "123",
            issuingBankLogo = painterResource(id = R.drawable.icon_citi),
            modifier = Modifier.padding(all = 16.dp)
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PageIndicator(pagerState: PagerState, modifier: Modifier = Modifier) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.Center
) {
    repeat(4) { iteration ->
        val color =
            if (pagerState.currentPage == iteration)
                MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.primaryContainer
        Box(
            modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .background(color)
                .size(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalFoundationApi::class)
fun GreetingPreview() {
    UiPaymentCardTheme {
        PaymentCardExamples(state = rememberPagerState())
    }
}