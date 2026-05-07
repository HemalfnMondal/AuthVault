package com.authvault.data.update;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J=\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0005H\u00d6\u0001R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lcom/authvault/data/update/VersionResponse;", "", "versionCode", "", "versionName", "", "releaseNotes", "downloadUrl", "forceUpdate", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getDownloadUrl", "()Ljava/lang/String;", "getForceUpdate", "()Z", "getReleaseNotes", "getVersionCode", "()I", "getVersionName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
final class VersionResponse {
    @com.google.gson.annotations.SerializedName(value = "version_code")
    private final int versionCode = 0;
    @com.google.gson.annotations.SerializedName(value = "version_name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String versionName = null;
    @com.google.gson.annotations.SerializedName(value = "release_notes")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String releaseNotes = null;
    @com.google.gson.annotations.SerializedName(value = "download_url")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String downloadUrl = null;
    @com.google.gson.annotations.SerializedName(value = "force_update")
    private final boolean forceUpdate = false;
    
    public VersionResponse(int versionCode, @org.jetbrains.annotations.NotNull()
    java.lang.String versionName, @org.jetbrains.annotations.NotNull()
    java.lang.String releaseNotes, @org.jetbrains.annotations.Nullable()
    java.lang.String downloadUrl, boolean forceUpdate) {
        super();
    }
    
    public final int getVersionCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVersionName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReleaseNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDownloadUrl() {
        return null;
    }
    
    public final boolean getForceUpdate() {
        return false;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.authvault.data.update.VersionResponse copy(int versionCode, @org.jetbrains.annotations.NotNull()
    java.lang.String versionName, @org.jetbrains.annotations.NotNull()
    java.lang.String releaseNotes, @org.jetbrains.annotations.Nullable()
    java.lang.String downloadUrl, boolean forceUpdate) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}