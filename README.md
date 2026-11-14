# Wild Atlantic GPS Guide V8 — Android source project

This is an **initial working prototype**, not a compiled APK or the previously requested full word-for-word commentary. GPS coordinates and scripts are preliminary and must be road-tested and editorially approved before commercial use.

## Build an APK
1. Install Android Studio on a Windows/Mac/Linux computer.
2. Open this extracted project folder; let Android Studio install the required Android SDK and Gradle dependencies.
3. Select **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
4. Copy `app/build/outputs/apk/debug/app-debug.apk` to the Samsung phone and open it to install. Samsung may ask you to allow installation from this source. Only install APKs you built yourself or trust.
5. Grant precise location and notification permissions, enable Location, pair your Bluetooth earbud, and select **TEST EARPIECE AUDIO**.
6. Select the tour and **START GPS GUIDE**. A persistent notification appears while GPS tracking is active.

## Current capabilities
- Native Android app with three tour selectors
- Foreground GPS service and distance-triggered commentary
- Android text-to-speech output, which can route to Bluetooth earphones
- Scripts and GPS points stored locally in `app/src/main/assets/stops.json`
- No internet permission; audio synthesis works offline **only if an offline TTS voice is installed**

## Known limitations
- Prototype scripts are short, not the full V8 commentary.
- Stops and routes require testing; GPS coordinates are approximate.
- TTS Irish accent depends on the device's available voices; it is not a recorded natural Irish male voice.
- No manual replay/skip, driver dashboard, or automatic audio ducking yet.
- The guide does not monitor ferry position on water.
- It has not been compiled or tested on a device in this environment.
- Do not operate phone controls while driving.
