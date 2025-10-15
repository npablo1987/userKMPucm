package org.example.pvproyecto.vistas.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import pvproyecto.composeapp.generated.resources.Res

@Composable
fun AnimationControl(
    fileName: String,                        // ejemplo: "Login.json"
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(220.dp, 220.dp),
    isPlaying: Boolean = true,
    speed: Float = 1f,
    iterations: Int = Compottie.IterateForever
) {
    // Estado para guardar el contenido del JSON
    var jsonText by remember(fileName) { mutableStateOf<String?>(null) }

    // Carga el archivo JSON desde los recursos
    LaunchedEffect(fileName) {
        jsonText = loadJsonFromResources("files/$fileName")
    }

    val text = jsonText ?: return

    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(text)
    }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
        speed = speed,
        iterations = iterations
    )

    Image(
        painter = rememberLottiePainter(
            composition = composition,
            progress = { progress }
        ),
        contentDescription = null,
        modifier = modifier.size(size)
    )
}

/**
 * Carga un archivo JSON desde composeResources/files/
 * usando APIs multiplataforma de Compose Resources.
 */
@OptIn(ExperimentalResourceApi::class)
suspend fun loadJsonFromResources(path: String): String? {
    return try {
        // Cargar desde composeResources usando la API multiplataforma
        Res.readBytes(path).decodeToString()
    } catch (e: Exception) {
        println("Error loading resource: ${e.message}")
        null
    }
}