package victor.paez.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailClientCharacteristics(
    text: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif,
            ),
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 1.dp, start = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = value,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
            ),
        )
    }
}
