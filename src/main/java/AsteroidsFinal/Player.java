package AsteroidsFinal;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Player extends SpaceObject {
    private Pane pane;
    private boolean firing;
    public long lastFiringTime = 0;
    private SoundEffects soundEffects = new SoundEffects();
    private boolean invulnerable = false, nowhereToJump = false;
    private long invulnerableStartTime, nowhereToJumpTime;
    private List<Asteroid> asteroids = new ArrayList<>();
    private AlienShip alienShip;
    private List<Bullet> alienShipBullets;
    double currentSpeed;


    public Player(double x, double y, Pane pane, Scene scene) {
        super(x, y);
        this.pane = pane;
        this.speed = 2;
        this.angle = 0;

        // Add a listener to handle key presses
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                case A:
                    if(!Game.gameOver && !Game.isPaused) this.rotateLeft();
                    break;
                case RIGHT:
                case D:
                    if(!Game.gameOver && !Game.isPaused)  this.rotateRight();
                    break;
                case UP:
                case W:
                    if(!Game.gameOver && !Game.isPaused)  this.applyThrust();
                    break;
                case SPACE:
                    if(!Game.gameOver && !Game.isPaused)  {
                        setFiring(true);
                        soundEffects.playFireSound();
                        break;
                    }
                case H:
                    if(!Game.gameOver && !Game.isPaused)  {
                        hyperspaceJump(asteroids, alienShip, alienShipBullets);
                        soundEffects.playJumpSound();
                    }
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                setFiring(false);
            }
        });

        // Add the player's shape to the pane
        shape = generateShape(1.0);
        shape.setLayoutX(x);
        shape.setLayoutY(y);
        shape.setStroke(Color.WHITE);
        shape.setStrokeWidth(2);
    }


    public static Polygon generateShape(double ratio) {
        double[] points = {
                ratio * 0, ratio * -20,
                ratio * 12, ratio * 20,
                7 * ratio, 17 * ratio,
                -7 * ratio, 17 * ratio,
                -12 * ratio, 20 * ratio
        };
        return new Polygon(points);
    }

    private void rotateRight() {
        angle += 15;
        shape.setRotate(angle);
    }

    private void rotateLeft() {
        angle -= 15;
        shape.setRotate(angle);
    }

    private void applyThrust() {
        dx += speed * Math.sin(Math.toRadians(angle)) * 0.5;
        dy -= speed * Math.cos(Math.toRadians(angle)) * 0.5;

        // Limit the player's speed
        currentSpeed = Math.sqrt(dx * dx + dy * dy);
        if (currentSpeed > GameConstants.PLAYER_MAX_SPEED.getValue()) {
            dx = (dx / currentSpeed) * GameConstants.PLAYER_MAX_SPEED.getValue();
            dy = (dy / currentSpeed) * GameConstants.PLAYER_MAX_SPEED.getValue();
        }
    }

    public Bullet fire(Pane pane) {
        angle = this.getAngle();
        double bulletSpeed = currentSpeed + 5;
        Bullet bullet = new Bullet(x, y, bulletSpeed, angle);
        bullet.addToPane(pane);
        bullet.update();
        return bullet;
    }

    public boolean isFiring() {
        return firing;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public boolean canFire(int bulletCount) {
        return bulletCount < GameConstants.BULLET_LIMIT.getValue();
    }

    public void destroyed() {
        invulnerable = true;
        invulnerableStartTime = System.currentTimeMillis();
        shape.setVisible(false);
    }

    public void resetPosition() {
        x = GameConstants.PANE_WIDTH.getValue() / 2;
        y = GameConstants.PANE_HEIGHT.getValue() / 2;
        dx = 0;
        dy = 0;
        shape.setLayoutX(x);
        shape.setLayoutY(y);
        shape.setVisible(true);
    }

    public void updateInvulnerability() {
        if (invulnerable) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - invulnerableStartTime > 3000) { // 3000 milliseconds = 3 seconds
                invulnerable = false;
                shape.setVisible(true);
            } else {
                // Flicker the player's shape
                shape.setVisible(currentTime % 200 > 100);
            }
        }
    }

    public void updateJumpStatus() {
        if (nowhereToJump) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - nowhereToJumpTime > 1000) { // 1000 milliseconds = 1 second
                nowhereToJump = false;
                shape.setStroke(Color.WHITE);
            } else {
                shape.setStroke(Color.RED);
            }
        }
    }


    public boolean isInvulnerable() {
        return invulnerable;
    }

    private boolean safeAsteroid(double x, double y, List<Asteroid> asteroids, double safeDistance) {
        for (Asteroid asteroid : asteroids) {
            double distance = Math.sqrt(Math.pow(x - asteroid.getShape().getLayoutX(), 2) + Math.pow(y - asteroid.getShape().getLayoutY(), 2));
            if (distance <= safeDistance) {
                return false;
            }
        }
        return true;
    }

    public Pair<Double, Double> getSafeCoordinates(double safeDistance) {
        double x, y;
        do {
            x = Math.random() * GameConstants.PANE_WIDTH.getValue();
            y = Math.random() * GameConstants.PANE_HEIGHT.getValue();
        } while (!isSafe(x, y, this.getShape().getLayoutX(), this.getShape().getLayoutY(), safeDistance));
        return new Pair<>(x, y);
    }

    private boolean isSafe(double x, double y, double playerX, double playerY, double safeDistance) {
        return Math.sqrt(Math.pow(x - playerX, 2) + Math.pow(y - playerY, 2)) > safeDistance;
    }

    public void hyperspaceJump(List<Asteroid> asteroids, AlienShip alienShip, List<Bullet> alienShipBullets) {
        // Generate a random position for the player
        double a, b;
        boolean safeToJump;
        // cnt to make sure that the program will not crash
        int cnt = 0;
        do {
            cnt ++;
            a = Math.random() * GameConstants.PANE_WIDTH.getValue();
            b = Math.random() * GameConstants.PANE_HEIGHT.getValue();
            safeToJump = safeAsteroid(a, b, asteroids, GameConstants.SAFE_DISTANCE.getValue());
            if (!safeToJump) continue;

            if (alienShip != null) {
                double alienDistance = Math.sqrt(Math.pow(a - alienShip.getX(), 2) + Math.pow(b - alienShip.getY(), 2));
                if (alienDistance < GameConstants.SAFE_DISTANCE.getValue()) {
                    safeToJump = false;
                }
            }

            if (alienShipBullets != null) {
                for (Bullet bullet : alienShipBullets) {
                    double bulletDistance = Math.sqrt(Math.pow(a - bullet.getX(), 2) + Math.pow(b - bullet.getY(), 2));
                    if (bulletDistance < GameConstants.SAFE_DISTANCE.getValue()) {
                        safeToJump = false;
                        break;
                    }
                }
            }
        } while (!safeToJump && cnt < 5000);
        // If nowhere to jump, set the player to red for 1 second1
        if (cnt == 5000) {
            nowhereToJump = true;
            nowhereToJumpTime = System.currentTimeMillis();
        } else {
            // Set the position of the player to the new location
            x = a;
            y = b;
            shape.setLayoutX(a);
            shape.setLayoutY(b);
        }

        System.out.println("nowhereToJumpTime = " + nowhereToJumpTime);
        System.out.println("cnt = " + cnt);
    }

    public void setEnemies(List<Asteroid> asteroids, AlienShip alienShip, List<Bullet> alienShipBullets) {
        this.asteroids = asteroids;
        this.alienShip = alienShip;
        this.alienShipBullets = alienShipBullets;
    }

}

