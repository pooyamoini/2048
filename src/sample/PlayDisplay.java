package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class PlayDisplay {
    static Stage playDisplay = new Stage();
    private static Scene scenePlay;
    private static final float RECTANGLE_HEIGHT = 400 / 4.5f;
    private static final float RECTANGLE_WEIGHT = 400 / 4.5f;
    private static final Color COLOR_2 = new Color(238 / 255f, 228 / 255f, 218 / 255f, 1);
    private static final Color COLOR_4 = new Color(237 / 255f, 224 / 255f, 200 / 255f, 1);
    private static final Color COLOR_8 = new Color(245 / 255f, 199 / 255f, 99 / 255f, 1);
    private static final Color COLOR_16 = new Color(246 / 255f, 94 / 255f, 59 / 255f, 1);

    static void display() {

        Group root = new Group();
        Rectangle[] rectangles = new Rectangle[16];
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i] = new Rectangle(RECTANGLE_WEIGHT, RECTANGLE_HEIGHT);
            rectangles[i].setArcHeight(35);
            rectangles[i].setArcWidth(35);
            rectangles[i].setFill(COLOR_2);
            if (i % 4 == 0) {
                rectangles[i].setX(50);
                rectangles[i].setY((i / 4f) * RECTANGLE_WEIGHT + 50);
                if (i != 0)
                    rectangles[i].setY(rectangles[i - 4].getY() + RECTANGLE_WEIGHT + 10);
            } else {
                rectangles[i].setX(rectangles[i - 1].getX() + RECTANGLE_WEIGHT + 15);
                rectangles[i].setY(rectangles[i - 1].getY());
            }
            root.getChildren().add(rectangles[i]);
        }
        scenePlay = new Scene(root, 500, 500);
        scenePlay.setFill(Main.mainMenuColor);
        playDisplay.setScene(scenePlay);

        if (Main.window.getScene().equals(scenePlay))
            Main.window.setOnCloseRequest(e -> Main.window.setScene(Main.scene));

        playDisplay.show();
    }


    static Scene getScenePlay() {
        return scenePlay;
    }
}
