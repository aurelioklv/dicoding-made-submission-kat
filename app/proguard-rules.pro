# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
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
# Preserve classes from the core module that are required
#-keep class com.aurelioklv.kat.core.data.** { *; }
#-keep class com.aurelioklv.kat.core.domain.** { *; }
#-keep class com.aurelioklv.kat.core.data.local.preferences.** { *; }

# Preserve classes used with reflection
#-keep class * { @org.koin.core.annotation.KoinComponent *; }
#-keep class * { @javax.inject.Inject *; }

# Preserve annotations
-keepattributes *Annotation*

-dontwarn com.aurelioklv.kat.core.data.Resource$Error
-dontwarn com.aurelioklv.kat.core.data.Resource$Loading
-dontwarn com.aurelioklv.kat.core.data.Resource$Success
-dontwarn com.aurelioklv.kat.core.data.Resource
-dontwarn com.aurelioklv.kat.core.data.local.preferences.AppTheme$Companion
-dontwarn com.aurelioklv.kat.core.data.local.preferences.AppTheme
-dontwarn com.aurelioklv.kat.core.data.local.preferences.ThemeManager
-dontwarn com.aurelioklv.kat.core.data.local.preferences.UserPreferences
-dontwarn com.aurelioklv.kat.core.di.CoreModuleKt
-dontwarn com.aurelioklv.kat.core.domain.model.Breed
-dontwarn com.aurelioklv.kat.core.domain.repository.IBreedRepository
-dontwarn com.aurelioklv.kat.core.domain.usecase.BreedInteractor
-dontwarn com.aurelioklv.kat.core.domain.usecase.BreedUseCase
-dontwarn com.aurelioklv.kat.core.ui.BreedAdapter
-dontwarn com.aurelioklv.kat.core.ui.DefaultItemDecoration