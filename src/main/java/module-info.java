module com.example.asteroidsfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    exports AsteroidsFinal;
    opens AsteroidsFinal to javafx.fxml;
}