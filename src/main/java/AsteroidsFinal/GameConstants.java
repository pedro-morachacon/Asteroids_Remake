package AsteroidsFinal;

public enum GameConstants {
    LARGE_ASTEROID(900),
    MEDIUM_ASTEROID(600),
    SMALL_ASTEROID(300),
    ALIEN_SHIP(1000),
    BONUS_LIFE(10000),
    PANE_WIDTH(1200),
    PANE_HEIGHT(900),
    ALIEN_SHIP_FIRING_INTERVAL(2),
    ALIEN_SHIP_INTERVAL(20000),
    INITIAL_LIVES(5),
    BULLET_LIFESPAN(2000),
    LARGE_RATIO(5),
    MEDIUM_RATIO(3),
    SMALL_RATIO(1),
    BULLET_LIMIT(50),
    PLAYER_MAX_SPEED(35),
    PLAYER_FIRING_INTERVAL(200),
    SAFE_DISTANCE(300);
    private int value;

    GameConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}