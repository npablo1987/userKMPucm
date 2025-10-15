package org.example.pvproyecto.vistas.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.resource

@Composable
fun AnimationControl(
    fileName: String,                        // p.ej: "Login.json"
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(220.dp, 220.dp),
    isPlaying: Boolean = true,
    speed: Float = 1f,
    iterations: Int = Compottie.IterateForever
) {
    // lee el JSON desde composeResources/files/<fileName>
    val jsonText = remember(fileName) {
        resource("files/$fileName").readBytes().decodeToString()
    }

    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(jsonText)
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