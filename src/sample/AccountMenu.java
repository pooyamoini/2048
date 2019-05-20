package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

class AccountMenu {
    private static Group root = new Group();
    private static Scene sceneAccount = new Scene(root, 500, 500);
    private static Button buttonLogin = new Button("Login");
    private static Button buttonSignUp =  new Button("SignUp");
    private static Button highScore = new Button("HIGH SCORES");
    static {
        buttonLogin.setTextFill(Color.BLACK);
        buttonSignUp.setTextFill(Color.BLACK);
        buttonLogin.relocate(225, 140);
        buttonLogin.setScaleX(1.6);
        buttonLogin.setScaleY(1.3);
        buttonSignUp.setScaleX(1.6);
        buttonSignUp.setScaleY(1.3);
        buttonSignUp.relocate(220, 220);
        highScore.setTextFill(Color.BLACK);
        highScore.relocate(195, 300);
        highScore.setScaleX(1.6);
        highScore.setScaleY(1.3);
        root.getChildren().addAll(buttonLogin, buttonSignUp, highScore);
        sceneAccount.setFill(COLORS.getColorMainMenu());
    }

    static void display(){
        Main.window.setScene(sceneAccount);
        buttonLogin.setOnAction(e -> LoginMenu.display());
        buttonSignUp.setOnAction(e -> SignUpMenu.display());
        highScore.setOnAction(e -> HighScoreMenu.display());
    }

    static Scene getSceneAccount() {
        return sceneAccount;
    }
}
