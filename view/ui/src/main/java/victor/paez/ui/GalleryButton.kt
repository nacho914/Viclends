package victor.paez.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun GalleryButton(
    clearImage: Boolean,
    onImageSelected: (Uri?) -> Unit,
) {
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }

    if (clearImage) {
        selectedImageUri.value = null
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            selectedImageUri.value = uri
            onImageSelected(uri)
        },
    )

    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp)
            .clickable { launcher.launch("image/*") },
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_image_24),
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center).size(64.dp),
            )

            AsyncImage(
                model = selectedImageUri.value,
                contentDescription = "Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable
                    { launcher.launch("image/*") },
            )
        }
    }
}
