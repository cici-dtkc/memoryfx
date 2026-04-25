package com.game.memory.view;

import com.game.memory.model.Difficulty;
import java.util.EnumMap;
import java.util.Map;
import javafx.animation.ScaleTransition;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class HomeScreen extends StackPane {

    private static final PseudoClass SELECTED = PseudoClass.getPseudoClass("selected");

    private final Map<Difficulty, VBox> difficultyCards = new EnumMap<>(Difficulty.class);
    private Difficulty selectedDifficulty;

    public HomeScreen() {
        String stylesheet = getClass().getResource("/css/style.css").toExternalForm();
        getStylesheets().add(stylesheet);
        getStyleClass().add("home-screen");
        setAlignment(Pos.CENTER);
        setPadding(new Insets(28));

        BorderPane shell = new BorderPane();
        shell.getStyleClass().add("home-shell");
        shell.setMaxWidth(1080);

        VBox content = new VBox(26);
        content.getStyleClass().add("home-content");
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(42, 40, 36, 40));

        content.getChildren().addAll(
            createTitle(),
            createCardIllustration(),
            createDifficultySection(),
            createStartButton()
        );

        shell.setCenter(content);
        getChildren().add(shell);
    }

    private Node createTitle() {
        Label title = new Label("MEMORY CARD FLIP");
        title.getStyleClass().add("home-title");
        title.setWrapText(true);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setAlignment(Pos.CENTER);

        Label subtitle = new Label("Chọn chế độ phù hợp rồi bắt đầu trò chơi nào.");
        subtitle.getStyleClass().add("home-subtitle");
        subtitle.setWrapText(true);
        subtitle.setTextAlignment(TextAlignment.CENTER);
        subtitle.setAlignment(Pos.CENTER);
        subtitle.setMaxWidth(540);

        VBox titleBox = new VBox(12, title, subtitle);
        titleBox.getStyleClass().add("home-title-box");
        titleBox.setAlignment(Pos.CENTER);
        return titleBox;
    }

    private Node createCardIllustration() {
        StackPane illustration = new StackPane();
        illustration.getStyleClass().add("card-illustration");
        illustration.setPrefSize(300, 220);
        illustration.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        Circle blobLeft = new Circle(48);
        blobLeft.getStyleClass().addAll("illustration-blob", "illustration-blob-left");
        blobLeft.setTranslateX(-90);
        blobLeft.setTranslateY(18);

        Circle blobRight = new Circle(42);
        blobRight.getStyleClass().addAll("illustration-blob", "illustration-blob-right");
        blobRight.setTranslateX(102);
        blobRight.setTranslateY(-24);

        Rectangle backLeft = createRoundedCard("card-back-left");
        backLeft.setRotate(-16);
        backLeft.setTranslateX(-62);
        backLeft.setTranslateY(22);

        Rectangle backRight = createRoundedCard("card-back-right");
        backRight.setRotate(14);
        backRight.setTranslateX(62);
        backRight.setTranslateY(18);

        StackPane centerCard = new StackPane();
        centerCard.getStyleClass().add("center-illustration-card");

        Rectangle frontCard = createRoundedCard("card-front");
        frontCard.setRotate(-2);

        Label icon = new Label("★");
        icon.getStyleClass().add("card-illustration-icon");

        Label flipText = new Label("FLIP!");
        flipText.getStyleClass().add("card-illustration-text");
        flipText.setTranslateY(42);

        centerCard.getChildren().addAll(frontCard, icon, flipText);
        illustration.getChildren().addAll(blobLeft, blobRight, backLeft, backRight, centerCard);
        return illustration;
    }

    private Rectangle createRoundedCard(String styleClass) {
        Rectangle card = new Rectangle(104, 136);
        card.setArcWidth(28);
        card.setArcHeight(28);
        card.getStyleClass().addAll("illustration-card", styleClass);
        return card;
    }

    private Node createDifficultySection() {
        Label sectionTitle = new Label("CHỌN ĐỘ KHÓ");
        sectionTitle.getStyleClass().add("difficulty-section-title");

        HBox options = new HBox(18);
        options.getStyleClass().add("difficulty-options");
        options.setAlignment(Pos.CENTER);

        for (Difficulty difficulty : Difficulty.values()) {
            VBox card = createDifficultyButton(difficulty);
            difficultyCards.put(difficulty, card);
            HBox.setHgrow(card, Priority.ALWAYS);
            options.getChildren().add(card);
        }

        VBox section = new VBox(18, sectionTitle, options);
        section.getStyleClass().add("difficulty-section");
        section.setAlignment(Pos.CENTER);
        return section;
    }

    private VBox createDifficultyButton(Difficulty difficulty) {
        Label viLabel = new Label(difficulty.getDisplayVietnamese());
        viLabel.getStyleClass().add("difficulty-vi-label");

        Label enLabel = new Label(difficulty.getDisplayEnglish());
        enLabel.getStyleClass().add("difficulty-en-label");

        Label sizeLabel = new Label(difficulty.getGridSize() + "x" + difficulty.getGridSize());
        sizeLabel.getStyleClass().add("difficulty-size-label");

        VBox card = new VBox(8, viLabel, enLabel, sizeLabel);
        card.getStyleClass().addAll("difficulty-card", difficultyStyleClass(difficulty));
        card.setAlignment(Pos.CENTER);
        card.setMinWidth(210);
        card.setPrefWidth(230);
        card.setCursor(Cursor.HAND);
        card.setOnMouseClicked(event -> selectDifficulty(difficulty));

        DropShadow hoverShadow = new DropShadow(22, Color.rgb(100, 120, 170, 0.22));
        ScaleTransition hoverIn = new ScaleTransition(Duration.millis(160), card);
        hoverIn.setToX(1.04);
        hoverIn.setToY(1.04);

        ScaleTransition hoverOut = new ScaleTransition(Duration.millis(160), card);
        hoverOut.setToX(1.0);
        hoverOut.setToY(1.0);

        card.setOnMouseEntered(event -> {
            hoverOut.stop();
            hoverIn.playFromStart();
            if (selectedDifficulty != difficulty) {
                card.setEffect(hoverShadow);
            }
        });

        card.setOnMouseExited(event -> {
            hoverIn.stop();
            hoverOut.playFromStart();
            if (selectedDifficulty != difficulty) {
                card.setEffect(null);
            }
        });

        return card;
    }

    private String difficultyStyleClass(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> "difficulty-easy";
            case MEDIUM -> "difficulty-medium";
            case HARD -> "difficulty-hard";
        };
    }

    private Button createStartButton() {
        Button startButton = new Button("Bắt đầu chơi");
        startButton.getStyleClass().add("start-game-button");
        startButton.setCursor(Cursor.HAND);
        startButton.setPrefWidth(260);
        startButton.setPrefHeight(54);

        ScaleTransition hoverIn = new ScaleTransition(Duration.millis(160), startButton);
        hoverIn.setToX(1.03);
        hoverIn.setToY(1.03);

        ScaleTransition hoverOut = new ScaleTransition(Duration.millis(160), startButton);
        hoverOut.setToX(1.0);
        hoverOut.setToY(1.0);

        startButton.setOnMouseEntered(event -> hoverIn.playFromStart());
        startButton.setOnMouseExited(event -> hoverOut.playFromStart());

        return startButton;
    }

    private void selectDifficulty(Difficulty difficulty) {
        selectedDifficulty = difficulty;

        difficultyCards.forEach((value, card) -> {
            boolean isSelected = value == difficulty;
            card.pseudoClassStateChanged(SELECTED, isSelected);
            card.setEffect(isSelected ? new DropShadow(28, Color.rgb(89, 106, 146, 0.28)) : null);
            card.setScaleX(isSelected ? 1.03 : 1.0);
            card.setScaleY(isSelected ? 1.03 : 1.0);
        });
    }
}
