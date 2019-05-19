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
    public void start(Stage primaryStage) {
        window = primaryStage;
        primaryStage.setTitle("2048 Game");
        takeLength.relocate(167, 190);
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
            root.getChildren().add(takeLength);
        });


        takeLength.setOnAction(e -> {
            if (takeLength.getText().matches("\\d")) {
                if (Integer.parseInt(takeLength.getText()) >= 3) {
                    new Game(Integer.parseInt(takeLength.getText()));
                    PlayDisplay.display();
                }
            }
        });

        quitButton.setOnAction(e -> window.close());

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
