package ind.wl2d.trueshop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ind.wl2d.trueshop.R

val newPeninimMtFontFamily =
    FontFamily(
        Font(R.font.new_peninim_mt),
    )

val AppTypography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = newPeninimMtFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = newPeninimMtFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            ),
    )
