package com.authvault;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = AuthVaultApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface AuthVaultApp_GeneratedInjector {
  void injectAuthVaultApp(AuthVaultApp authVaultApp);
}
