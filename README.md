# Snake Game
The classic video game "Snake" built in Java using Swing.

## About
This project was originally developed as a Java Applet and later modernized to run as a standalone Swing application, moving away from the deprecated Applet framework.

## How to Run
**Requirements:** Java 21 or later

in the command line type:
```
"java -jar [FilePath]\SnakeGame.jar"
```

## Controls
| Input | Action |
|-------|--------|
| Click screen | Start game |
| ↑ | Move Up |
| ↓ | Move Down |
| ← | Move Left |
| → | Move Right |

Note: The snake cannot reverse direction — e.g. pressing ↓ while moving ↑ has no effect.

## Technical Implementation

### Real-Time Keyboard Input
The game uses a `KeyListener` to capture keyboard input directly, providing immediate, real-time response the instant a key is pressed. Input is not buffered or character-based — there is no need to press Enter, and there is no input delay between keypress and action.

### Threading & Game Loop
The game loop is driven by a javax.swing.Timer, which fires every 500ms to update game state and trigger a repaint. This keeps the game logic simple while remaining compatible with Swing's rendering model.

### Timing & Animation
Frame timing is handled programmatically to produce consistent, smooth animation regardless of system load. The game loop controls the speed of the snake and the rate at which the board is repainted, giving the game a predictable and stable feel.

### Window Management
The game is rendered inside a `JFrame` window managed by Swing. The game board is drawn on a custom `JPanel` using Java2D graphics, with `repaint()` calls triggered by the game loop to update the display each frame.

## Built With
- Java 21
- Java Swing
- Java2D



https://github.com/user-attachments/assets/b3b38fae-c680-424b-8483-1de97b073753



(Please keep in mind the recording frame rate was off from the game's frame rate causing the animation to look jumpy)
