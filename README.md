# AuthVault

AuthVault is an Android two-factor authentication app for managing TOTP and HOTP accounts locally.

It is built with Kotlin, Jetpack Compose, Room, Hilt, CameraX, ML Kit, ZXing, Coil, DataStore, and SQLCipher.

## Features

- Add accounts by scanning a QR code with the camera
- Add accounts by uploading a QR image from the gallery
- Add accounts manually
- View live TOTP/HOTP codes with countdown rings
- Copy codes to the clipboard
- Edit saved accounts
- View account details and generated transfer QR codes
- Reorder and delete accounts
- Optional biometric app lock
- Security settings for app lock and screenshot control
- In-app update notifications powered by a GitHub-hosted JSON file
- Manual update checks from Settings with automatic once-per-day checks
- Encrypted local database with SQLCipher
- Backup and restore support
- Custom launcher icon and polished UI

## Screenshots

<div align="center">
  <img width="250" alt="Screenshot 1" src="https://github.com/user-attachments/assets/767ff1ce-d06d-4923-9db3-ba9d60d1f56b" />
  <img width="250" alt="Screenshot 2" src="https://github.com/user-attachments/assets/c414b1c8-e7ae-4ea1-a9a5-e6772b9bbd84" />
  <img width="250" alt="Screenshot 3" src="https://github.com/user-attachments/assets/0146c698-0f5a-4037-8f68-88ffc4f1d344" />
</div>

![Visitors](https://visitor-badge.laobi.icu/badge?page_id=HemalfnMondal.AuthVault)
![Downloads](https://img.shields.io/github/downloads/HemalfnMondal/AuthVault/total)

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Room
- Hilt
- CameraX
- ML Kit Barcode Scanning
- ZXing
- Coil
- Coil SVG
- DataStore
- SQLCipher
- Biometric Prompt

## Security And Privacy

- App lock can be enabled from Settings
- Screenshot and screen recording behavior can be controlled from Security settings
- Sensitive secret values are protected in the edit and detail flows
- Account data is stored locally on the device
- Update checks only read public release metadata and open the release page in your browser when a newer version is available

## Requirements

- Android 8.0+ (minSdk 26)
- Android Studio or a local Android build environment
- Gradle installed locally
- An Android device or emulator

## Build and Run

This repository currently uses a local Gradle installation instead of the Gradle wrapper.

From the project root:

```bash
gradle assembleDebug
```

Install the debug APK on a connected device:

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

Launch the app:

```bash
adb shell am start -n com.authvault/.MainActivity
```

## Project Structure

- `app/src/main/java/com/authvault` - app entry point and application class
- `app/src/main/java/com/authvault/presentation` - Compose UI, navigation, and view models
- `app/src/main/java/com/authvault/data` - Room database, repository, and crypto/data storage
- `app/src/main/java/com/authvault/domain` - domain models and use cases
- `version.json` - GitHub-hosted update metadata consumed by the in-app update checker

## Permissions

AuthVault uses these Android permissions:

- `INTERNET` for loading service icons
- `CAMERA` for QR scanning
- `USE_BIOMETRIC` and `USE_FINGERPRINT` for app lock
- `FOREGROUND_SERVICE` and `FOREGROUND_SERVICE_MEDIA_PROJECTION` for screen sharing support
- `VIBRATE` for haptic feedback

## Notes

- Account data is stored locally on the device.
- QR icons are loaded from Simple Icons with SVG support enabled in Coil.
- The app checks `version.json` for newer releases and opens the release page when an update is available.
- If you plan to publish this publicly on GitHub, consider adding a license file such as MIT, Apache 2.0, or GPL depending on your intent.
- The app includes a custom launcher icon and adaptive icon assets.
- Build output is generated with the local Gradle installation in this workspace.

## License

No license file is included yet.
