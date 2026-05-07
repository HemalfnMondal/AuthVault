package com.authvault.presentation.ui.add;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u0012\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010\u001a@\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010\u00a8\u0006\u001c"}, d2 = {"ScanQrScreen", "", "viewModel", "Lcom/authvault/presentation/ui/main/MainViewModel;", "onNavigateBack", "Lkotlin/Function0;", "onNavigateHome", "ViewfinderOverlay", "modifier", "Landroidx/compose/ui/Modifier;", "processImageProxy", "scanner", "Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "imageProxy", "Landroidx/camera/core/ImageProxy;", "onQrDetected", "Lkotlin/Function1;", "", "setupCamera", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "previewView", "Landroidx/camera/view/PreviewView;", "cameraProviderFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "app_debug"})
public final class ScanQrScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ScanQrScreen(@org.jetbrains.annotations.NotNull()
    com.authvault.presentation.ui.main.MainViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateHome) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ViewfinderOverlay(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    public static final void setupCamera(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner lifecycleOwner, @org.jetbrains.annotations.NotNull()
    androidx.camera.view.PreviewView previewView, @org.jetbrains.annotations.NotNull()
    com.google.common.util.concurrent.ListenableFuture<androidx.camera.lifecycle.ProcessCameraProvider> cameraProviderFuture, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQrDetected) {
    }
    
    public static final void processImageProxy(@org.jetbrains.annotations.NotNull()
    com.google.mlkit.vision.barcode.BarcodeScanner scanner, @org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy imageProxy, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQrDetected) {
    }
}