package com.dbel.ui.payment.card

internal fun String.subStringOrDefault(range: IntRange, default: String) =
    runCatching { substring(range) }.getOrDefault(defaultValue = default)

internal fun String.subStringOrEmpty(startIndex: Int) =
    runCatching { substring(startIndex) }.getOrDefault(defaultValue = "")