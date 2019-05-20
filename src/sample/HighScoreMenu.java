package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


class HighScoreMenu {
    private static Group root = new Group();
    private static Scene sceneHighScores;
    private static Text emptyUser = new Text("no users!");
    private static Button backToMainMenu = new Button("main menu");

    static {
        emptyUser.setFill(Color.RED);
        backToMainMenu.relocate(emptyUser.getX(), emptyUser.getY() + 30);
        root.getChildren().add(backToMainMenu);
        emptyUser.relocate(50, 50);
        backToMainMenu.relocate(50, 600);
        sceneHighScores = new Scene(root, 180, 650);
    }

    static void display() {
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
                rectangles[i] = new Rectangle(130, 35);
                rectangles[i].setFill(COLORS.getColor0());
                rectangles[i].setX(20);
                rectangles[i].setY(20);
                rectangles[i].setArcWidth(30);
                rectangles[i].setArcHeight(30);
                scores[i] = new Text(Account.getAccounts().get(i).getUserName().concat(" Score : ").concat(Integer.toString(Account.getAccounts().get(i).getHighScore())));
                scores[i].setFill(COLORS.getColor4());
                if (i != 0) {
                    rectangles[i].setX(20);
                    rectangles[i].setY(rectangles[i - 1].getY() + 60);
                }
                scores[i].relocate(rectangles[i].getX() + 20, rectangles[i].getY() + 12);
                if (!root.getChildren().contains(rectangles[i]))
                    root.getChildren().add(rectangles[i]);
                if (!root.getChildren().contains(scores[i]))
                    root.getChildren().add(scores[i]);
            }
        }

    }
}