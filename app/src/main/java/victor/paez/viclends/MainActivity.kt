package victor.paez.viclends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.AndroidEntryPoint
import victor.paez.navigation.NavigationHost
import victor.paez.ui.MainScaffold
import victor.paez.viclends.ui.theme.ViclendsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var title by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViclendsTheme {
                MainScaffold(
                    title = title,
                ) { padding ->
                    NavigationHost(
                        padding,
                        ::changeTitle,
                    )
                }
            }
        }
    }

    private fun changeTitle(titleChange: String) {
        title = titleChange
    }
}
