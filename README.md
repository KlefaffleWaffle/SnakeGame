# Snake Game
A classic Snake game built in Java using Swing.

## About
This project was originally developed as a Java Applet and later modernized to run as a standalone Swing application, moving away from the deprecated Applet framework.

## How to Run
**Requirements:** Java 21 or later

**Option 1 — Command line:**
```
java -jar SnakeGame.jar
```

**Option 2 — Double-click:** Right-click the JAR file → Open with → Java Platform Binary

## Controls
| Key | Action |
|-----|--------|
| ↑ | Move Up |
| ↓ | Move Down |
| ← | Move Left |
| → | Move Right |

## Technical Implementation

### Real-Time Keyboard Input
The game uses a `KeyListener` to capture keyboard input directly, providing immediate, real-time response the instant a key is pressed. Input is not buffered or character-based — there is no need to press Enter, and there is no input delay between keypress and action.

### Threading & Game Loop
The game runs on a dedicated thread separate from the Swing Event Dispatch Thread (EDT), ensuring the UI remains responsive while the game loop runs continuously. This prevents the game logic from blocking rendering or user input.

### Timing & Animation
Frame timing is handled programmatically to produce consistent, smooth animation regardless of system load. The game loop controls the speed of the snake and the rate at which the board is repainted, giving the game a predictable and stable feel.

### Window Management
The game is rendered inside a `JFrame` window managed by Swing. The game board is drawn on a custom `JPanel` using Java2D graphics, with `repaint()` calls triggered by the game loop to update the display each frame.

## Built With
- Java 21
- Java Swing
- Java2D
