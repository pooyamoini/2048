package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<KeyEvent> {

    static Stage window;
    static Scene scene;
    private Button playButton = new Button("PLAY");
    private Button quitButton = new Button("QUIT");
    private TextField takeLength = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("2048 Game");
        takeLength.relocate(225, 190);
        takeLength.setScaleX(2.5);
        takeLength.setScaleY(2.5);
        takeLength.setPromptText("enter dimensions:");
        Group root = new Group();

        playButton.setTextFill(Color.BLACK);
        playButton.relocate(225, 140);
        playButton.setScaleX(2.5);
        playButton.setScaleY(2);
        playButton.setWrapText(true);

        quitButton.relocate(playButton.getLayoutX(), playButton.getLayoutY() + 100);
        quitButton.setTextFill(Color.BLACK);
        quitButton.setScaleX(2.5);
        quitButton.setScaleY(2);
        quitButton.setWrapText(true);

        scene = new Scene(root, 500, 500);
        scene.setFill(COLORS.getColorMainMenu());
        window.setScene(scene);


        playButton.setOnAction(e -> {
            new Game(4);
//            root.getChildren().add(takeLength);
            PlayDisplay.display();
        });

        quitButton.setOnAction(e -> {
            if (window.getScene() != scene)
                window.setScene(scene);
            window.close();
        });

        if (window.getScene().equals(PlayDisplay.getScenePlay()))
            window.setOnCloseRequest(e -> window.setScene(scene));


        root.getChildren().addAll(playButton, quitButton);

        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(KeyEvent event) {

    }
}
