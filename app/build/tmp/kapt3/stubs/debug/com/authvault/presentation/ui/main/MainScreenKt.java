package com.authvault.presentation.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0016\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001aP\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001aH\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u00a8\u0006\u0019"}, d2 = {"DeleteAccountSheet", "", "account", "Lcom/authvault/presentation/model/AccountUiModel;", "onCancel", "Lkotlin/Function0;", "onDelete", "EmptyState", "onAddAccount", "MainScreen", "viewModel", "Lcom/authvault/presentation/ui/main/MainViewModel;", "onOpenSettings", "onOpenDetail", "Lkotlin/Function1;", "", "onNavigateToScan", "TopBar", "searchExpanded", "", "searchQuery", "", "onSearchClicked", "onSearchQueryChanged", "onSettingsClicked", "app_debug"})
public final class MainScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void MainScreen(@org.jetbrains.annotations.NotNull()
    com.authvault.presentation.ui.main.MainViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenSettings, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddAccount, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onOpenDetail, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToScan) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TopBar(boolean searchExpanded, java.lang.String searchQuery, kotlin.jvm.functions.Function0<kotlin.Unit> onSearchClicked, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSearchQueryChanged, kotlin.jvm.functions.Function0<kotlin.Unit> onSettingsClicked) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyState(kotlin.jvm.functions.Function0<kotlin.Unit> onAddAccount) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void DeleteAccountSheet(com.authvault.presentation.model.AccountUiModel account, kotlin.jvm.functions.Function0<kotlin.Unit> onCancel, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
}