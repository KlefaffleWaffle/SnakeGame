package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class testSnakeNoApplet extends JPanel implements KeyListener, MouseListener {
    ArrayList<Coord> body = new ArrayList<>();
    boolean started = false;
    boolean gameOver = false;
    int currentScore = 0;
    int numOfMoves = 0;

    final int GRAPH_X_LEFT = 100;
    final int GRAPH_Y_TOP = 100;
    final int GRAPH_WIDTH = 300;
    final int GRAPH_HEIGHT = 300;
    int ratX = 100, ratY = 100;
    boolean setNewRatLocation = true;

    enum Direction { UP, DOWN, LEFT, RIGHT }
    Direction d = Direction.DOWN;
    boolean upDownLocked = true, leftRightLocked = false;

    Timer timer;

    public testSnakeNoApplet() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.WHITE);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);

        // Initialize snake
        body.add(new Coord(370, 160));
        body.add(new Coord(370, 130));
        body.add(new Coord(370, 100));

        timer = new Timer(500, e -> gameLoop());
        timer.start();
    }

    private void gameLoop() {
        if (!started || gameOver) return;

        updateSnake();
        updateRat();
        repaint();
    }

    private void updateSnake() {
        Coord head = body.get(0);
        Coord newHead = new Coord(head.getX(), head.getY());

        switch (d) {
            case LEFT -> newHead.setX(newHead.getX() - 30);
            case RIGHT -> newHead.setX(newHead.getX() + 30);
            case UP -> newHead.setY(newHead.getY() - 30);
            case DOWN -> newHead.setY(newHead.getY() + 30);
        }

        body.add(0, newHead);

        if (newHead.getX() == ratX && newHead.getY() == ratY) {
            currentScore++;
            setNewRatLocation = true;
        } else {
            body.remove(body.size() - 1);
        }

        // Check collision with walls
        if (newHead.getX() > 370 || newHead.getX() < 100 || newHead.getY() > 370 || newHead.getY() < 100) {
            gameOver = true;
        }

        // Check collision with self
        for (int i = 1; i < body.size(); i++) {
            if (newHead.getX() == body.get(i).getX() && newHead.getY() == body.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    private void updateRat() {
        while (setNewRatLocation) {
            Random r = new Random();
            ratX = 100 + r.nextInt(10) * 30;
            ratY = 100 + r.nextInt(10) * 30;

            setNewRatLocation = false;
            for (Coord c : body) {
                if (c.getX() == ratX && c.getY() == ratY) {
                    setNewRatLocation = true;
                    break;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            g.drawString("Game Over", 250, 250);
            g.drawString("Number of Moves = " + numOfMoves, 250, 270);
            g.drawString("Current Score = " + currentScore, 250, 290);
            return;
        }

        if (!started) {
            g.drawString("Click Screen to start", 250, 250);
            return;
        }

        g.drawString("Current Score = " + currentScore, 10, 20);

        // Draw snake
        g.setColor(Color.GREEN);
        for (Coord c : body) {
            g.fillRect(c.getX(), c.getY(), 30, 30);
        }

        // Draw rat
        g.setColor(Color.RED);
        g.fillRect(ratX, ratY, 30, 30);

        // Draw grid
        g.setColor(Color.BLACK);
        g.drawRect(GRAPH_X_LEFT, GRAPH_Y_TOP, GRAPH_WIDTH, GRAPH_HEIGHT);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        numOfMoves++;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> { if (!leftRightLocked) { d = Direction.LEFT; upDownLocked = false; leftRightLocked = true; } }
            case KeyEvent.VK_RIGHT -> { if (!leftRightLocked) { d = Direction.RIGHT; upDownLocked = false; leftRightLocked = true; } }
            case KeyEvent.VK_UP -> { if (!upDownLocked) { d = Direction.UP; upDownLocked = true; leftRightLocked = false; } }
            case KeyEvent.VK_DOWN -> { if (!upDownLocked) { d = Direction.DOWN; upDownLocked = true; leftRightLocked = false; } }
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) { started = true; }
    @Override public void mousePressed(MouseEvent e) { started = true; }
    @Override public void mouseReleased(MouseEvent e) { started = true; }
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public static class Coord {
        private int x, y;
        public Coord(int x, int y) { this.x = x; this.y = y; }
        public int getX() { return x; }
        public int getY() { return y; }
        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        testSnakeNoApplet game = new testSnakeNoApplet();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}