package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main extends Application {

    static Stage window;
    static Color mainMenuColor = new Color(187 / 255f, 173 / 255f, 160 / 255f, 1);
    static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        primaryStage.setTitle("2048 Game");
        Group root = new Group();
        Button playButton = new Button("PLAY");
        playButton.setTextFill(Color.BLACK);
        playButton.relocate(225, 140);
        playButton.setScaleX(2.5);
        playButton.setScaleY(2);
        playButton.setWrapText(true);

        Button quitButton = new Button("QUIT");
        quitButton.relocate(playButton.getLayoutX(), playButton.getLayoutY() + 100);
        quitButton.setTextFill(Color.BLACK);
        quitButton.setScaleX(2.5);
        quitButton.setScaleY(2);
        quitButton.setWrapText(true);

        scene = new Scene(root, 500, 500);
        scene.setFill(mainMenuColor);
        window.setScene(scene);


        playButton.setOnAction(e -> {
            PlayDisplay.display();
            window = PlayDisplay.playDisplay;
        });

        quitButton.setOnAction(e -> {
            if (window.getScene() == PlayDisplay.getScenePlay()){
                window.close();
                window.setScene(scene);
            }
            else
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

}
