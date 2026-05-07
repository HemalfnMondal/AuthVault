package com.authvault.presentation.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aT\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00040\tH\u0003\u001a\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0003\u001a8\u0010\f\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001aF\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u0017H\u0007\u00a8\u0006\u001a"}, d2 = {"ChoiceRow", "", "T", "title", "", "value", "options", "", "onSelected", "Lkotlin/Function1;", "labelFor", "SectionHeader", "SettingSwitchRow", "subtitle", "checked", "", "onCheckedChange", "SettingsScreen", "viewModel", "Lcom/authvault/presentation/ui/settings/SettingsViewModel;", "updateViewModel", "Lcom/authvault/presentation/ui/update/UpdateViewModel;", "onNavigateBack", "Lkotlin/Function0;", "onExportBackup", "onImportBackup", "app_debug"})
public final class SettingsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    com.authvault.presentation.ui.settings.SettingsViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.authvault.presentation.ui.update.UpdateViewModel updateViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onExportBackup, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onImportBackup) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SectionHeader(java.lang.String title) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingSwitchRow(java.lang.String title, java.lang.String subtitle, boolean checked, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onCheckedChange) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final <T extends java.lang.Object>void ChoiceRow(java.lang.String title, java.lang.String value, java.util.List<? extends T> options, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> onSelected, kotlin.jvm.functions.Function1<? super T, java.lang.String> labelFor) {
    }
}