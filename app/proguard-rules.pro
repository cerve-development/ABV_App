# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep `INSTANCE.serializer()` of serializable objects.
#-if @kotlinx.serialization.Serializable class **
#-keep, allowshrinking, allowoptimization, allowobfuscation, allowaccessmodification class <1>

#noinspection ShrinkerUnresolvedReference

-dontwarn kotlinx.serialization.Serializable
-dontwarn kotlinx.serialization.KSerializer
-dontwarn kotlinx.serialization.internal.AbstractPolymorphicSerializer

-keep,includedescriptorclasses class com.cerve.abv.shared.**$$serializer { *; }
#-keepclassmembers class com.cerve.abv.shared** {
#    *** Companion;
#}

#-keepclasseswithmembers class com.cerve.abv.shared.* {
#     kotlinx.serialization.KSerializer serializer(...);
#}
