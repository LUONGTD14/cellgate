# CellGate

CellGate is an engaging arcade game for Android where you control a plane navigating through randomly generated obstacles. Challenge your reflexes to achieve the highest score!

## 🎮 Key Features

- **Addictive Gameplay:** Navigate the plane through narrow gaps to score points.
- **Smooth Controls:** Precise touch-based horizontal movement.
- **Procedural Map Generation:** Every run is unique thanks to the `MapGenerator` logic.
- **High Score System:** Keep track of your personal records using `PreferenceUtil`.
- **Visual Effects:** Integrated Particle System and vibrant graphics.
- **Dynamic Sound:** Immersive sound effects for scoring and collisions.

## 🛠️ Tech Stack

- **Language:** Java
- **Android SDK:** Compile SDK 36, Min SDK 34
- **Game Engine:** Custom Game Loop implemented via `SurfaceView`
- **UI Components:** Material Components, ConstraintLayout
- **Build System:** Gradle (Kotlin DSL)

## 🚀 Getting Started

1. Clone this repository:
   ```bash
   git clone https://github.com/LUONGTD14/cellgate.git
   ```
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Wait for Gradle to sync.
4. Connect an Android device or launch an Emulator (API 34+).
5. Click **Run** to start playing.

## 📂 Detailed File Structure

### 🏗️ Activities (`com.ltd14.cellgate.activities`)
- **`MainActivity.java`**: The entry screen displaying the best score and the "Play" button.
- **`GameActivity.java`**: The main container for the game session, hosting the `GameView`.
- **`GameOverActivity.java`**: Displays the final score and provides options to restart or exit.

### 🕹️ Game Logic (`com.ltd14.cellgate.game`)
- **`GameView.java`**: The core component that handles rendering, user input, and collision detection.
- **`GameLoop.java`**: Manages the consistent update and draw cycles (FPS control).
- **`GameState.java`**: Defines the different states of the game (Ready, Playing, Game Over).
- **`ScoreManager.java`**: Tracks the current score during a session.

### 📦 Models (`com.ltd14.cellgate.model`)
- **`Plane.java`**: Defines properties and movement logic for the player's aircraft.
- **`Wall.java`**: Represents the obstacles/walls the player must avoid.
- **`MapData.java`**: Data structure holding the collection of walls for a map segment.

### 🎨 UI & Rendering (`com.ltd14.cellgate.ui`)
- **`BackgroundRenderer.java`**: Handles the drawing of the scrolling or static background.
- **`HudRenderer.java`**: Renders the Heads-Up Display (current score overlay).
- **`ParticleSystem.java`**: Manages visual effects like stars or debris using particles.

### 🗺️ Map Generation (`com.ltd14.cellgate.generator`)
- **`MapGenerator.java`**: Core algorithm for procedural generation. It dynamically calculates wall positions and gap sizes to ensure the game remains challenging and unique every time you play.

### 🔊 Sound Management (`com.ltd14.cellgate.sound`)
- **`SoundManager.java`**: Responsible for the game's audio experience. It handles the initialization of the `SoundPool`, loads audio assets, and triggers sound effects for specific events like scoring or crashing.

### 🛠️ Utilities (`com.ltd14.cellgate.util`)
- **`PreferenceUtil.java`**: Manages persistent data storage. Specifically used to save and retrieve the user's high score from Android's `SharedPreferences` so that progress is saved across sessions.
- **`Constants.java`**: Centralized configuration file. Contains all hardcoded values like plane dimensions, scroll speeds, wall thickness, and color hex codes to make the code easier to maintain and tweak.

## 📝 License

This project is developed for educational and entertainment purposes.

---
*Developed by ltd14*
