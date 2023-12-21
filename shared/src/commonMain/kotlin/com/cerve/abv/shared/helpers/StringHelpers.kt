package com.cerve.abv.shared.helpers

fun String.isLeadingDecimal(): String {
    return if (this.startsWith('.')) "0$this" else this
}