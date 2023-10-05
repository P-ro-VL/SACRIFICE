package neu.cs.sacrifice.screen;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;

import com.almasb.fxgl.audio.Audio;
import javafx.animation.FadeTransition;
import javafx.scene.Cursor;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Duration;

import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.utils.assets_loader.FontLoader;
import neu.cs.sacrifice.api.utils.assets_loader.SoundPlayer;
import neu.cs.sacrifice.api.utils.assets_loader.TextureLoader;

import javax.sound.sampled.Clip;
import java.io.File;

public class MainMenu extends FXGLMenu {

    public MainMenu() {
        super(MenuType.MAIN_MENU);

        init();
    }

    public void init() {
        StackPane mainMenuPane = new StackPane();

        Clip backgroundAmbience = SoundPlayer.playGlobalSound(SoundPlayer.SOUND_DIR + "main_menu_ambience.wav", 50);

        Media startVideo = new Media(new File("assets/ui/main_menu.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer((startVideo));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(SACRIFICE.WINDOW_WIDTH);
        mediaView.setFitHeight(SACRIFICE.WINDOW_HEIGHT);

        Image gameTitle = TextureLoader.loadImage(TextureLoader.TEXTURE_DIR + "game_title.png");
        ImageView gameTitleView = new ImageView(gameTitle);

        Font startBtnFont = FontLoader.loadFont(FontLoader.FONT_DIR + "girassol.ttf", 60);
        Text startBtn = new Text("khai môn");
        startBtn.setTranslateY(287);
        startBtn.setFont(startBtnFont);
        startBtn.setFill(Color.rgb(144,144,144));
        startBtn.setOnMouseEntered(e -> {
            startBtn.setFill(Color.WHITE);
            startBtn.setEffect(new Glow(1.9));
            startBtn.setCursor(Cursor.HAND);
        });
        startBtn.setOnMouseExited(e -> {
            startBtn.setFill(Color.rgb(144,144,144));
            startBtn.setEffect(new Glow(0));
        });

        startBtn.setOnMouseClicked((e) -> {
            Media startMedia = new Media(new File("assets/ui/main_menu_start.mp4").toURI().toString());
            MediaPlayer startMediaPlayer = new MediaPlayer(startMedia);
            startMediaPlayer.seek(Duration.seconds(0));
            startMediaPlayer.setAutoPlay(false);

            mediaView.setMediaPlayer(startMediaPlayer);

            start(backgroundAmbience, gameTitleView, startBtn, startMediaPlayer);
        });

        mainMenuPane.getChildren().addAll(mediaView, gameTitleView, startBtn);

        getContentRoot().getChildren().add(mainMenuPane);
    }

    public void start(Clip backgroundAmbience, ImageView gameTitleView, Text startBtn, MediaPlayer mediaPlayer) {
        FadeTransition gameTitleFade = new FadeTransition(Duration.seconds(1), gameTitleView);
        gameTitleFade.setFromValue(1.0);
        gameTitleFade.setToValue(0.0);
        gameTitleFade.play();

        FadeTransition startBtnFade = new FadeTransition(Duration.seconds(1), startBtn);
        startBtnFade.setFromValue(1.0);
        startBtnFade.setToValue(0.0);
        startBtnFade.play();

        startBtnFade.setOnFinished(e -> {
            backgroundAmbience.stop();

            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(this::fireNewGame);
        });
    }

}
