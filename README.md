# AuthVault

AuthVault is an Android two-factor authentication app built with Kotlin, Jetpack Compose, Room, Hilt, CameraX, ML Kit, ZXing, Coil, and SQLCipher.

It is designed to store TOTP/HOTP accounts locally, scan QR codes from the camera or from images, and protect access with biometric app lock.

## Features

- Add accounts by scanning a QR code with the camera
- Add accounts by uploading a QR image from the gallery
- Add accounts manually
- View live TOTP/HOTP codes with countdown rings
- Copy codes to the clipboard
- Edit saved accounts
- Reorder and delete accounts
- Generate and show account QR codes for transfer
- Optional biometric app lock
- Encrypted local database with SQLCipher
- Backup and restore support

## Screenshots

Add screenshots here if you want to show the UI on GitHub.

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

## Permissions

AuthVault uses these Android permissions:

- `INTERNET` for loading service icons
- `CAMERA` for QR scanning
- `USE_BIOMETRIC` and `USE_FINGERPRINT` for app lock
- `VIBRATE` for haptic feedback

## Notes

- Account data is stored locally on the device.
- QR icons are loaded from Simple Icons with SVG support enabled in Coil.
- If you plan to publish this publicly on GitHub, consider adding a license file such as MIT, Apache 2.0, or GPL depending on your intent.

## License

No license file is included yet.
