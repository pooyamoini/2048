package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayDisplay {
    private static Group root = new Group();
    static Stage playDisplay = new Stage();
    private static Scene scenePlay;
    private static final float RECTANGLE_HEIGHT = 400 / 4.5f;
    private static final float RECTANGLE_WEIGHT = 400 / 4.5f;
    private static Rectangle[][] rectangles = new Rectangle[4][4];
    private static Text[][] labels = new Text[4][4];

    static {
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j] = new Rectangle(RECTANGLE_WEIGHT, RECTANGLE_HEIGHT);
                rectangles[i][j].setArcHeight(35);
                rectangles[i][j].setArcWidth(35);
                rectangles[i][j].setFill(COLORS.getColor0());
                if (j == 0) {
                    rectangles[i][j].setX(50);
                    rectangles[i][j].setY((i / 4f) * RECTANGLE_WEIGHT + 50);
                    if (i != 0)
                        rectangles[i][j].setY(rectangles[i - 1][j].getY() + RECTANGLE_WEIGHT + 10);
                } else {
                    rectangles[i][j].setX(rectangles[i][j - 1].getX() + RECTANGLE_WEIGHT + 15);
                    rectangles[i][j].setY(rectangles[i][j - 1].getY());
                }
                root.getChildren().add(rectangles[i][j]);
            }
        }
    }

    static {
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new Text();
                labels[i][j].setX(rectangles[i][j].getX() + RECTANGLE_WEIGHT / 2 - 3);
                labels[i][j].setY(rectangles[i][j].getY() + RECTANGLE_HEIGHT / 2 + 5);
                labels[i][j].setScaleX(2.3);
                labels[i][j].setScaleY(2.3);
                root.getChildren().add(labels[i][j]);
            }
        }
    }

    static void display() {

        scenePlay = new Scene(root, 500, 500);
        scenePlay.setFill(Main.mainMenuColor);
        playDisplay.setScene(scenePlay);

        Main.window.setOnCloseRequest(e -> {
            Game.setCurrentGameNull();
            Main.window.setScene(Main.scene);
        });

        scenePlay.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    Game.getCurrentGame().left();
                    showNumbers();
                    break;
                case UP:
                    break;
                case RIGHT:
                    break;
                case DOWN:
                    break;
            }
        });

        scenePlay.setOnKeyReleased(event -> showNumbers());

        showNumbers();

        playDisplay.show();
    }


    static Scene getScenePlay() {
        return scenePlay;
    }

    private static void showNumbers() {
        for (int i = 0; i < Game.getCurrentGame().getBoard().length; i++) {
            for (int j = 0; j < Game.getCurrentGame().getBoard()[i].length; j++) {
                root.getChildren().remove(labels[i][j]);
                if (!Game.getCurrentGame().getBoard()[i][j].isEmpty()) {
                    labels[i][j].setText(Integer.toString(Game.getCurrentGame().getBoard()[i][j].getValue()));
                    root.getChildren().add(labels[i][j]);
                    switch (Game.getCurrentGame().getBoard()[i][j].getValue()) {
                        case 2:
                            rectangles[i][j].setFill(COLORS.getColor2());
                            break;
                        case 4:
                            rectangles[i][j].setFill(COLORS.getColor4());
                            break;
                        case 8:
                            rectangles[i][j].setFill(COLORS.getColor8());
                            break;
                        case 16:
                            rectangles[i][j].setFill(COLORS.getColor16());
                            break;
                        case 32:
                            rectangles[i][j].setFill(COLORS.getColor32());
                            break;
                        case 64:
                            rectangles[i][j].setFill(COLORS.getColor64());
                            break;
                        case 128:
                            rectangles[i][j].setFill(COLORS.getColor128());
                            break;
                        case 256:
                            rectangles[i][j].setFill(COLORS.getColor256());
                            break;
                        case 512:
                            rectangles[i][j].setFill(COLORS.getColor512());
                            break;
                    }
                }
            }
        }
        initializeEmpty();
    }

    private static void initializeEmpty(){
        for (int i = 0; i < Game.getCurrentGame().getBoard().length; i++) {
            for (int j = 0; j < Game.getCurrentGame().getBoard()[i].length; j++) {
                if (Game.getCurrentGame().getBoard()[i][j].isEmpty())
                    rectangles[i][j].setFill(COLORS.getColor0());
            }
        }
    }
}
