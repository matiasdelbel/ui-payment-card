# Payment Card

## American Express
![Alt text](.docs/american_express.png?raw=true "American Express")

```kotlin
AmericanExpress(
    cardHolderName = "Mario Vargas Llosa",
    cardNumber = "340213589323446",
    expirationDate = "07/25",
    securityCode = "1233",
)
```

## Diners
![Alt text](.docs/diners.png?raw=true "Diners Club")

```kotlin
Diners(
    cardHolderName = "Gabriel Garcia Marquez",
    cardNumber = "30021358933446",
    expirationDate = "07/25",
    securityCode = "123",
    issuingBankLogo = painterResource(id = R.drawable.icon_citi),
)
```

## Mastercard
![Alt text](.docs/mastercard.png?raw=true "Mastercard")

```kotlin
Mastercard(
    cardHolderName = "Jose Luis Borges",
    cardNumber = "5412945012562346",
    expirationDate = "07/25",
    securityCode = "123",
    issuingBankLogo = painterResource(id = R.drawable.icon_citi),
)
```

## Visa
![Alt text](.docs/visa.png?raw=true "Mastercard")

```kotlin
Visa(
    cardHolderName = "Roberto Bolagno",
    cardNumber = "4056453265402053",
    expirationDate = "07/25",
    securityCode = "123",
    issuingBankLogo = painterResource(id = R.drawable.icon_citi),
)
```