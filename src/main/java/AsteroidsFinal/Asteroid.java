package AsteroidsFinal;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Asteroid extends SpaceObject {
    public Asteroid(double x, double y, double ratio) {
        super(x, y);
        this.ratio = ratio;

        Random rand = new Random();
        // Set a random angle for the asteroid
        this.angle = rand.nextDouble() * 360;

        // Set the velocity based on the size
        if (ratio == GameConstants.LARGE_RATIO.getValue()) {           // Large Asteroids
            speed = 1;
        } else if (ratio == GameConstants.MEDIUM_RATIO.getValue()) {      // Medium Asteroids
            speed = 2;
        } else {                      // Small Asteroids
            speed = 3;
        }

        dx = speed * Math.sin(Math.toRadians(angle));
        dy = -speed * Math.cos(Math.toRadians(angle));
        shape = generateShape(ratio);

        // Set the initial layout of the shape
        shape.setLayoutX(x);
        shape.setLayoutY(y);
    }

    private Shape generateShape(double ratio) {
        Polygon shape = new Polygon();
        Random rand = new Random();

        // Define four point arrays
        double[][] points = {
                {0 * ratio, -10 * ratio, 10 * ratio, -20 * ratio, 20 * ratio, -10 * ratio, 17 * ratio, 0 * ratio, 20 * ratio, 10 * ratio, 10 * ratio, 20 * ratio, -10 * ratio, 20 * ratio, -20 * ratio, 10 * ratio, -20* ratio, -10 * ratio, -20 * ratio, -10 * ratio, -10 * ratio, -20 * ratio},
                {0 * ratio, -15 * ratio, 10 * ratio, -20 * ratio, 20 * ratio, -15 * ratio, 10 * ratio, -7 * ratio, 20 * ratio, 10 * ratio, 12 * ratio, 20 * ratio, -6 * ratio, 17 * ratio, -10 * ratio, 20 * ratio, -20* ratio, 10 * ratio, -15 * ratio, 0 * ratio, -20* ratio, -10 * ratio, -20 * ratio},
                {-10 * ratio, -20 * ratio, 10 * ratio, -20 * ratio, 20 * ratio, -5 * ratio, 20 * ratio, 5 * ratio, 10 * ratio, 20 * ratio, 0 * ratio, 7 * ratio, -5 * ratio, 20 * ratio, -20* ratio, 5 * ratio, -10 * ratio, 0 * ratio, -20* ratio, -20 * ratio, -5 * ratio},
                {0 * ratio, -15 * ratio, -5 * ratio, -20 * ratio, 10 * ratio, -20 * ratio, 20 * ratio, -10 * ratio, 20 * ratio, -5 * ratio, 10 * ratio, 0 * ratio, 20 * ratio, 5 * ratio, 15 * ratio, 20 * ratio, 10* ratio, 15 * ratio, -10 * ratio, 20 * ratio, -20* ratio, 5 * ratio, -20 * ratio, -15 * ratio}
        };

        // Select a random point array and create a Polygon object using that array
        int randIndex = rand.nextInt(points.length);
        double[] randPoints = points[randIndex];
        shape = new Polygon(randPoints);

        shape.setStroke(Color.WHITE);
        shape.setStrokeWidth(3);
        return shape;
    }


    public List<Asteroid> destroyed(double ratio, Pane pane, double destroyedSpeed) {
        List<Asteroid> newAsteroids = new ArrayList<>();
        if (ratio == GameConstants.LARGE_RATIO.getValue()) {
            // Create two medium-sized asteroids
            for (int i = 0; i < 2; i++) {
                Asteroid asteroid = new Asteroid(super.x, super.y, GameConstants.MEDIUM_RATIO.getValue());
                asteroid.setSpeed(destroyedSpeed + Math.random());
                asteroid.addToPane(pane);
                newAsteroids.add(asteroid);
            }
        } else if (ratio == GameConstants.MEDIUM_RATIO.getValue()) {
            // Create two small-sized asteroids
            for (int i = 0; i < 2; i++) {
                Asteroid asteroid = new Asteroid(super.x, super.y, GameConstants.SMALL_RATIO.getValue());
                asteroid.setSpeed(destroyedSpeed + Math.random());
                asteroid.addToPane(pane);
                newAsteroids.add(asteroid);
            }
        }
        return newAsteroids;
    }
}

