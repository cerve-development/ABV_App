package com.fair.tool_belt_abv.util

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import com.fair.tool_belt_abv.R

/**
 * Our application crashes when a user enters in ".<Number>" to avoid this crash,
 * we ensure that their entry has a "0" prefix if it has a leading decimal.
 */
fun String.isLeadingDecimal(): String {
    return if (this.startsWith('.')) "0$this" else this
}

const val RECEIVER_EMAIL = "infocerveapps@gmail.com"
const val STORE_LINK_ANDROID = "https://play.google.com/store/apps/details?id=com.fair.tool_belt_abv"
const val DEFAULT_INTENT_TYPE = "text/plain"
const val EMAIL_SUBJECT_FEATURE = "Feature request"
const val EMAIL_SUBJECT_BUG = "Bug report"
const val EMAIL_SUBJECT_SUPPORT = "Support"

fun Context.getVersion(): String = try {
    val info = packageManager.getPackageInfo(packageName, 0)
    info.versionName
} catch (e: PackageManager.NameNotFoundException) {
    getString(R.string.LABEL_DEFAULT_APP_VERSION)
}

fun Context.sendEmail(
    subject: String,
    receiver: String = RECEIVER_EMAIL,
    title: String
) {
    Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(receiver))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(
            EXTRA_TEXT,
            """ 
               ### DO NOT CHANGE BELOW THIS LINE ###
               
               Manufacturer: ${Build.MANUFACTURER}
               Model: ${Build.MODEL}
               SDK version: ${Build.VERSION.SDK_INT}
               Android version: ${Build.VERSION.RELEASE}
               App version: ${getVersion()}
            """.trimIndent()
        )
    }.linkChooser(this, title)
}

fun Context.shared(
    value: String,
    title: String
) {
    Intent(Intent.ACTION_SEND).apply {
        putExtra(EXTRA_TEXT, value)
        type = DEFAULT_INTENT_TYPE
    }.linkChooser(this, title)
}

fun Intent?.linkChooser(context: Context, title: String) {
    if (this != null) {
        try {
            context.startActivity(Intent.createChooser(this, title))
        } catch (_: Exception) { }
    }
}
