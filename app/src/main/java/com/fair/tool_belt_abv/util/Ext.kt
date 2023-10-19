package com.fair.tool_belt_abv.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.fair.tool_belt_abv.BuildConfig

/**
 * Our application crashes when a user enters in ".<Number>" to avoid this crash,
 * we ensure that their entry has a "0" prefix if it has a leading decimal.
 */
fun String.isLeadingDecimal(): String {
    return if (this.startsWith('.')) "0$this" else this
}

const val RECEIVER_EMAIL = "infocerveapps@gmail.com"
const val EMAIL_SUBJECT_FEATURE = "Feature request"
const val EMAIL_SUBJECT_BUG = "Bug report"
const val EMAIL_SUBJECT_SUPPORT = "Support"

fun Context.sendEmail(
    receiver: String = RECEIVER_EMAIL,
    subject: String
) {
    Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(receiver))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(
            Intent.EXTRA_TEXT,
            """ 
               ### DO NOT CHANGE BELOW THIS LINE ###
               
               Manufacturer: ${Build.MANUFACTURER}
               Model: ${Build.MODEL}
               SDK version: ${Build.VERSION.SDK_INT}
               Android version: ${Build.VERSION.RELEASE}
               App version: ${BuildConfig.VERSION_NAME}
            """.trimIndent()
        )
    }.linkChooser(this)
}

fun Intent?.linkChooser(context: Context) {
    if (this != null) {
        try {
            context.startActivity(Intent.createChooser(this, "Select"))
        } catch (_: Exception) { }
    }
}
