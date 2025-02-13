# Flight Journey Tracker with Jetpack method.

## Overview
This is an Android application developed using Jetpack Compose. The app allows users to track their journey across multiple stops, check visa requirements, and view distances covered. It also includes a unit toggle between kilometers and miles.

## Implementation Details
### 1. **Project Structure**
The project follows a clean structure:
- `MainActivity.kt`: The entry point of the app that sets up the Compose UI.
- `MainScreen.kt`: Contains the main UI components including the list of stops, progress tracking, and unit conversion.
- `StopItem.kt`: A composable function that defines how each stop is displayed(Created but did not used so I deleted this.).
- `Theme`: Uses Material 3 for styling.

### 2. **Jetpack Compose Features Used**
- **State Management**: `remember { mutableStateOf() }` is used to track the current stop, distance covered, and unit conversion.
- **LazyColumn**: Efficiently renders a scrollable list of stops.
- **Material 3 Components**: `Card`, `Button`, and `Text` are used for UI elements.

### 3. **Functionality**
- Displays a list of stops with visa information and distance.
- Allows toggling between kilometers and miles.
- Tracks progress through stops and updates distance covered.

### 4. **How It Works**
1. The list of stops is predefined in `MainScreen.kt`.
2. When a user clicks "Next Stop Reached", the distance covered increases and updates the UI.
3. The "Switch to Miles" button converts the displayed distances between km and miles.

### 5. **Dependencies Used**
- Jetpack Compose UI
- Material 3 Components
- Kotlin State Management

## How to Run
1. Clone the repository.
2. Open in Android Studio (latest version recommended).
3. Sync Gradle dependencies.
4. Run the app on an emulator or a physical device.



