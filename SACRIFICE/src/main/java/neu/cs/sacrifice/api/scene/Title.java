package neu.cs.sacrifice.api.scene;

import com.almasb.fxgl.dsl.FXGL;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Title {

    private String title = "", subTitle = "";
    private Font titleFont = Font.font("Times New Roman", FontWeight.BOLD, 64),
            subTitleFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
    private Color titleColor = Color.WHITE, subTitleColor = Color.WHITE;

    private double fadeIn = 1, stay = 3, fadeOut = 1;

    public Font getTitleFont() {
        return titleFont;
    }

    public Title setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
        return this;
    }

    public Font getSubTitleFont() {
        return subTitleFont;
    }

    public Title setSubTitleFont(Font subTitleFont) {
        this.subTitleFont = subTitleFont;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Title setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Title setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public Color getTitleColor() {
        return titleColor;
    }

    public Title setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public Color getSubTitleColor() {
        return subTitleColor;
    }

    public Title setSubTitleColor(Color subTitleColor) {
        this.subTitleColor = subTitleColor;
        return this;
    }

    public double getFadeIn() {
        return fadeIn;
    }

    public Title setFadeIn(double fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public double getStay() {
        return stay;
    }

    public Title setStay(double stay) {
        this.stay = stay;
        return this;
    }

    public double getFadeOut() {
        return fadeOut;
    }

    public Title setFadeOut(double fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    public void show() {
        // Title
        Text titleNode = new Text(getTitle());
        titleNode.setFont(getTitleFont());
        titleNode.setFill(getTitleColor());
        titleNode.setTextAlignment(TextAlignment.CENTER);

        // Subtitle
        Text subtitleNode = new Text(getSubTitle());
        subtitleNode.setFont(getSubTitleFont());
        subtitleNode.setFill(getSubTitleColor());
        subtitleNode.setTextAlignment(TextAlignment.CENTER);

        // Group
        VBox vBox = new VBox();
        vBox.setSpacing(8);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(titleNode, subtitleNode);
        vBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(1.0), new BorderWidths(1))));
        vBox.setTranslateX(0);
        vBox.setTranslateY(0);

        StackPane panel = new StackPane(vBox);

        // Add to root
        FXGL.getGameScene().getContentRoot().getChildren().add(panel);

        // Animation
        FadeTransition fadeInAnimation = new FadeTransition(Duration.seconds(getFadeIn()), vBox);
        fadeInAnimation.setFromValue(0.0);
        fadeInAnimation.setToValue(1.0);

        fadeInAnimation.play();
        fadeInAnimation.setOnFinished(e -> {
            FXGL.runOnce(() -> {
                FadeTransition fadeOutAnimation = new FadeTransition(Duration.seconds(getFadeOut()), vBox);
                fadeOutAnimation.setFromValue(1.0);
                fadeOutAnimation.setToValue(0.0);
                fadeOutAnimation.play();

            }, Duration.seconds(getStay()));
        });
    }
}
