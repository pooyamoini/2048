package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;


class HighScoreMenu {
    private Group root;
    private Scene sceneHighScores;
    private Text emptyUser;
    private Button backToMainMenu;
    private static HighScoreMenu currentHighScoreMenu;

    HighScoreMenu() {
        root = new Group();
        emptyUser = new Text("number of user : 0");
        backToMainMenu = new Button("main menu");
        firstInitialize();
        currentHighScoreMenu = this;
    }

    private void firstInitialize() {
        emptyUser.setFill(Color.RED);
        backToMainMenu.relocate(emptyUser.getX(), emptyUser.getY() + 30);
        root.getChildren().add(backToMainMenu);
        emptyUser.relocate(35, 30);
        sceneHighScores = new Scene(root, 500, 100 + (Account.accountsSize() * 60));
        backToMainMenu.relocate(215, sceneHighScores.getHeight() - 30);
    }

    void display() {
        sceneHighScores.setFill(COLORS.getColorMainMenu());

        Main.window.setScene(sceneHighScores);
        Text[] scores = new Text[Account.accountsSize()];
        Rectangle[] rectangles = new Rectangle[Account.accountsSize()];
        if (Account.accountsSize() == 0) {
            if (!root.getChildren().contains(emptyUser))
                root.getChildren().addAll(emptyUser);
        }

        backToMainMenu.setOnAction(e -> Main.window.setScene(AccountMenu.getSceneAccount()));

        if (Account.accountsSize() != 0) {
            root.getChildren().remove(emptyUser);
            for (int i = 0; i < scores.length; i++) {
                root.getChildren().removeAll(scores[i], rectangles[i]);
                rectangles[i] = new Rectangle(450, 35);
                switch (new Random().nextInt(4)){
                    case 0:
                        rectangles[i].setFill(COLORS.getColor0());
                        break;
                    case 1:
                        rectangles[i].setFill(COLORS.getColor8());
                        break;
                    case 2:
                        rectangles[i].setFill(COLORS.getColor512());
                        break;
                    case 3:
                        rectangles[i].setFill(COLORS.getColor2048());
                }
                rectangles[i].setX(30);
                rectangles[i].setY(20);
                scores[i] = new Text(Account.getAccounts().get(i).getUserName().concat(" -> ").concat(Integer.toString(Account.getAccounts().get(i).getHighScore())));
                scores[i].setFill(COLORS.getColor4());
                if (i != 0) {
                    rectangles[i].setX(30);
                    rectangles[i].setY(rectangles[i - 1].getY() + 60);
                }
                scores[i].relocate(rectangles[i].getX() + 200, rectangles[i].getY() + 12);
                if (!root.getChildren().contains(rectangles[i]))
                    root.getChildren().add(rectangles[i]);
                if (!root.getChildren().contains(scores[i]))
                    root.getChildren().add(scores[i]);
            }
        }
    }

    static HighScoreMenu getCurrentHighScoreMenu() {
        return currentHighScoreMenu;
    }
}