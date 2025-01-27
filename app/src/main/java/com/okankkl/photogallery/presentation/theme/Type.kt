package com.okankkl.photogallery.presentation.theme

import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.okankkl.photogallery.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.raw.google_font_certs
)

val InterFont = GoogleFont("Inter")

val InterFontFamily = FontFamily(
    Font(
        googleFont = InterFont,
        fontProvider = provider,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = InterFont,
        fontProvider = provider,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = InterFont,
        fontProvider = provider,
        weight = FontWeight.Thin,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = InterFont,
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = InterFont,
        fontProvider = provider,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = InterFont,
        fontProvider = provider,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        googleFont = InterFont,
        fontProvider = provider,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    )
)