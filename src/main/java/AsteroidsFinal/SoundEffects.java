package AsteroidsFinal;

import javafx.scene.media.AudioClip;

public class SoundEffects {
    private static final AudioClip FIRE_SOUND = new AudioClip(SoundEffects.class.getResource("/AsteroidsFinal/fire.wav").toExternalForm());
    private static final AudioClip BANG_SOUND = new AudioClip(SoundEffects.class.getResource("/AsteroidsFinal/bang.wav").toExternalForm());
    private static final AudioClip BONUS_SOUND = new AudioClip(SoundEffects.class.getResource("/AsteroidsFinal/bonus.mp3").toExternalForm());
    private static final AudioClip JUMP_SOUND = new AudioClip(SoundEffects.class.getResource("/AsteroidsFinal/jump.mp3").toExternalForm());
    private static final AudioClip LEVEL_SOUND = new AudioClip(SoundEffects.class.getResource("/AsteroidsFinal/level.wav").toExternalForm());
    private static final AudioClip KEY_SOUND = new AudioClip(SoundEffects.class.getResource("/AsteroidsFinal/key.wav").toExternalForm());
    private static final AudioClip GAMEOVER_SOUND = new AudioClip(SoundEffects.class.getResource("/AsteroidsFinal/gameover.mp3").toExternalForm());

    public void playFireSound() {
        FIRE_SOUND.play();
    }
    public void playBangSound() {
        BANG_SOUND.play();
    }
    public void playBonusSound() {
        BONUS_SOUND.play();
    }
    public void playLevelSound() {
        LEVEL_SOUND.play();
    }
    public void playJumpSound() {
        JUMP_SOUND.play();
    }
    public void playKeySound() {
        KEY_SOUND.play();
    }
    public void playGameOverSound() {
        GAMEOVER_SOUND.play();
    }
}