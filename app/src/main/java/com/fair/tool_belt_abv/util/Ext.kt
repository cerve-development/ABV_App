package com.fair.tool_belt_abv.util

import android.content.Context
import android.content.Intent
import android.os.Build
import com.fair.tool_belt_abv.R


/**
     * Our application crashes when a user enters in ".<Number>" to avoid this crash,
     * we ensure that their entry has a "0" prefix if it has a leading decimal.
     */
fun String.isLeadingDecimal(): String {
    return if (this.startsWith('.')) "0$this" else this
}

const val RECEIVER_EMAIL = "cerve.v55@gmail.com"
const val EMAIL_SUBJECT_FEATURE = "Feature request"
const val EMAIL_SUBJECT_BUG = "Bug report"
const val EMAIL_SUBJECT_SUPPORT = "Support"

fun Context.sendEmail(
    receiver: String = RECEIVER_EMAIL,
    subject: String
) {
    Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf(receiver))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(
            Intent.EXTRA_TEXT,
            """  
               Id: ${Build.ID}
               Model: ${Build.MODEL}
               Manufacture: ${Build.MANUFACTURER}
               Android version: ${Build.VERSION.RELEASE}
            """.trimIndent()
        )
        type = "message/rfc822"
    }.linkChooser(this, this.getString(R.string.SUBTITLE_email))
}

fun Intent?.linkChooser(context: Context, title: String) {
    if (this != null) {
        try {
            context.startActivity(Intent.createChooser(this, title))
        } catch (_: Exception) { }
    }
}
