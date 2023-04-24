package com.example.blob

import android.os.Bundle
import android.view.KeyEvent.ACTION_DOWN
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.blob.ui.theme.BlobTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlobTheme {
                var offsetX = 0.0;
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Box(modifier = Modifier
                        .offset{ IntOffset(offsetX.roundToInt(),0)}
                        .draggable(
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState(onDelta = { delta ->
                                offsetX += delta
                            })
                        )
                        .background(Color.Blue)
                        .size(100f.dp))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlobTheme {
        var offsetX by remember{ mutableStateOf(0.0) };
        var offsetY by remember{ mutableStateOf(0.0) };
        // A surface container using the 'background' color from the theme
        Box(modifier = Modifier.size(500f.dp)) {
            Box(modifier = Modifier
                .offset{ IntOffset(offsetX.roundToInt(),offsetY.roundToInt())}
                .pointerInteropFilter { event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> true
                        MotionEvent.ACTION_MOVE -> {
                            event.changes.add
                        else -> false
                    }

                }
                .background(Color.Blue)
                .size(100f.dp))
        }
    }
}