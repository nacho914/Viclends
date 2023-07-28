package victor.paez.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import victor.paez.util.LightPurple

@Composable
fun MainDebtCard(
    title: String,
    total: Int,
    debt: Int,
    revenue: Int,
    delay: Int,
    icon: @Composable () -> Unit = {},
) {
    Card(
        Modifier.fillMaxWidth().padding(16.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(containerColor = LightPurple),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                ),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Column(
                Modifier.weight(0.3f).padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                icon()
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth().padding(16.dp).weight(0.7f),
            ) {
                Text(
                    text = "Total prestado: $total",
                )
                Text(
                    text = "Deuda Original: $debt",
                )
                Text(
                    text = "Ganancia Original: $revenue",
                )
                Text(
                    text = "Retardo Original: $delay",
                )
            }
        }
    }
}
