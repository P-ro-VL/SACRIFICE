package neu.cs.sacrifice.screen;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;

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
import neu.cs.sacrifice.api.utils.assets_loader.TextureLoader;

import java.io.File;

public class MainMenu extends FXGLMenu {

    public MainMenu() {
        super(MenuType.MAIN_MENU);

        init();
    }

    public void init() {
        StackPane mainMenuPane = new StackPane();

        Media startVideo = new Media(new File("assets/ui/start.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer((startVideo));
        mediaPlayer.setAutoPlay(false);
        mediaPlayer.seek(Duration.seconds(1));

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(SACRIFICE.WINDOW_WIDTH);
        mediaView.setFitHeight(SACRIFICE.WINDOW_HEIGHT);

        Font gameTitleFont = FontLoader.loadFont("boston_algel_bold.ttf", 130);
        Text gameTitle = new Text("S A C R I F I C E");
        gameTitle.setTranslateY(-138 -84);
        gameTitle.setEffect(new Glow(2.9));
        gameTitle.setFill(Color.rgb(242,242,242));
        gameTitle.setFont(gameTitleFont);
        gameTitle.setOnMouseClicked(e -> fireNewGame());

        Font startBtnFont = FontLoader.loadFont("girassol.ttf", 60);
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
            start(gameTitle, startBtn, mediaPlayer);
        });

        mainMenuPane.getChildren().addAll(mediaView, gameTitle, startBtn);

        getContentRoot().getChildren().add(mainMenuPane);
    }

    public void start(Text gameTitle, Text startBtn, MediaPlayer mediaPlayer) {
        FadeTransition gameTitleFade = new FadeTransition(Duration.seconds(1), gameTitle);
        gameTitleFade.setFromValue(1.0);
        gameTitleFade.setToValue(0.0);
        gameTitleFade.play();

        FadeTransition startBtnFade = new FadeTransition(Duration.seconds(1), startBtn);
        startBtnFade.setFromValue(1.0);
        startBtnFade.setToValue(0.0);
        startBtnFade.play();

        startBtnFade.setOnFinished(e -> {
            mediaPlayer.seek(Duration.seconds(0));
            mediaPlayer.play();

            mediaPlayer.setOnEndOfMedia(this::fireNewGame);
        });
    }

}
