package com.authvault.presentation.ui.add

import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.authvault.presentation.ui.main.MainViewModel
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanQrScreen(
    viewModel: MainViewModel,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var isProcessed by remember { mutableStateOf(false) }
    var statusMessage by remember { mutableStateOf("") }
    var previewViewRef by remember { mutableStateOf<PreviewView?>(null) }

    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    LaunchedEffect(previewViewRef, isProcessed) {
        val previewView = previewViewRef
        if (previewView != null && !isProcessed) {
            setupCamera(
                context = context,
                lifecycleOwner = lifecycleOwner,
                previewView = previewView,
                cameraProviderFuture = cameraProviderFuture
            ) { rawUri ->
                if (!isProcessed) {
                    isProcessed = true
                    viewModel.decodeAndSaveFromQr(rawUri) { success, message ->
                        if (success) {
                            onNavigateHome()
                        } else {
                            isProcessed = false
                            statusMessage = message
                        }
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        AndroidView(
            factory = { ctx ->
                PreviewView(ctx).apply {
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    scaleType = PreviewView.ScaleType.FILL_CENTER
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { previewView ->
                previewViewRef = previewView
            }
        )

        // Top dark gradient + back button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Transparent))
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                text = "Scan QR Code",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        // Viewfinder overlay
        ViewfinderOverlay(modifier = Modifier.align(Alignment.Center))

        // Bottom instructions / status
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (statusMessage.isNotEmpty()) {
                Text(
                    text = statusMessage,
                    color = Color(0xFFEF5350),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .background(Color.Black.copy(alpha = 0.5f))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            Text(
                text = "Point camera at a 2FA QR code",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "The code will be scanned automatically",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ViewfinderOverlay(modifier: Modifier = Modifier) {
    val boxSize = 260.dp
    val cornerLength = 36.dp
    val cornerStroke = 4.dp
    val cornerColor = Color(0xFF00BCD4)

    Box(modifier = modifier.size(boxSize), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(boxSize)) {
            val stroke = cornerStroke.toPx()
            val len = cornerLength.toPx()
            val w = size.width
            val h = size.height
            // Top-left
            drawLine(cornerColor, androidx.compose.ui.geometry.Offset(0f, 0f), androidx.compose.ui.geometry.Offset(len, 0f), strokeWidth = stroke, cap = StrokeCap.Round)
            drawLine(cornerColor, androidx.compose.ui.geometry.Offset(0f, 0f), androidx.compose.ui.geometry.Offset(0f, len), strokeWidth = stroke, cap = StrokeCap.Round)
            // Top-right
            drawLine(cornerColor, androidx.compose.ui.geometry.Offset(w - len, 0f), androidx.compose.ui.geometry.Offset(w, 0f), strokeWidth = stroke, cap = StrokeCap.Round)
            drawLine(cornerColor, androidx.compose.ui.geometry.Offset(w, 0f), androidx.compose.ui.geometry.Offset(w, len), strokeWidth = stroke, cap = StrokeCap.Round)
            // Bottom-left
            drawLine(cornerColor, androidx.compose.ui.geometry.Offset(0f, h - len), androidx.compose.ui.geometry.Offset(0f, h), strokeWidth = stroke, cap = StrokeCap.Round)
            drawLine(cornerColor, androidx.compose.ui.geometry.Offset(0f, h), androidx.compose.ui.geometry.Offset(len, h), strokeWidth = stroke, cap = StrokeCap.Round)
            // Bottom-right
            drawLine(cornerColor, androidx.compose.ui.geometry.Offset(w - len, h), androidx.compose.ui.geometry.Offset(w, h), strokeWidth = stroke, cap = StrokeCap.Round)
            drawLine(cornerColor, androidx.compose.ui.geometry.Offset(w, h - len), androidx.compose.ui.geometry.Offset(w, h), strokeWidth = stroke, cap = StrokeCap.Round)
        }
    }
}

// Camera setup and processing
fun setupCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    onQrDetected: (String) -> Unit
) {
    val mainExecutor = androidx.core.content.ContextCompat.getMainExecutor(context)
    val analysisExecutor = Executors.newSingleThreadExecutor()

    cameraProviderFuture.addListener({
        try {
            val cameraProvider = cameraProviderFuture.get()

            val preview = androidx.camera.core.Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(previewView.surfaceProvider) }

            val scanner = BarcodeScanning.getClient(
                BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_QR_CODE).build()
            )

            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(android.util.Size(1280, 720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            imageAnalysis.setAnalyzer(analysisExecutor) { imageProxy ->
                processImageProxy(scanner, imageProxy) { rawValue ->
                    android.util.Log.d("CameraSetup", "QR detected: ${rawValue.take(200)}")
                    try { imageAnalysis.clearAnalyzer() } catch (e: Exception) { android.util.Log.e("CameraSetup", "clearAnalyzer failed", e) }
                    analysisExecutor.shutdown()
                    onQrDetected(rawValue)
                }
            }

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, preview, imageAnalysis)

        } catch (e: Exception) {
            Log.e("CameraSetup", "Camera setup failed", e)
        }
    }, mainExecutor)
}

fun processImageProxy(
    scanner: com.google.mlkit.vision.barcode.BarcodeScanner,
    imageProxy: androidx.camera.core.ImageProxy,
    onQrDetected: (String) -> Unit
) {
    val mediaImage = imageProxy.image
    if (mediaImage == null) {
        imageProxy.close()
        return
    }
    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
    scanner.process(image)
        .addOnSuccessListener { barcodes ->
            for (barcode in barcodes) {
                val raw = barcode.rawValue
                if (raw != null && raw.startsWith("otpauth://", ignoreCase = true)) {
                    onQrDetected(raw)
                    return@addOnSuccessListener
                }
            }
        }
        .addOnFailureListener { e -> Log.e("QrScan", "Barcode scan failed", e) }
        .addOnCompleteListener { imageProxy.close() }
}
