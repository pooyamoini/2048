package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class AccountMenu {
    private static Group root = new Group();
    private static Scene sceneAccount = new Scene(root, 500, 500);
    private static Button buttonLogin = new Button("Login");
    private static Button buttonSignUp = new Button("SignUp");
    private static Button highScore = new Button("HIGH SCORES");
    private static FileInputStream loginButtonFX;
    private static ImageView buttonImj;
    private static FileInputStream signUpButtonFile;
    private static ImageView signUpImj;
    private static FileInputStream highScoreFile;
    private static ImageView scoreImj;

    static {
        try {
            loginButtonFX = new FileInputStream("/Users/pooya/Desktop/Programms/third exercise 2048/src/sample/login1.png");
            signUpButtonFile = new FileInputStream("/Users/pooya/Desktop/Programms/third exercise 2048/src/sample/login2.png");
            highScoreFile = new FileInputStream("/Users/pooya/Desktop/Programms/third exercise 2048/src/sample/score.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        Image buttonImage = new Image(loginButtonFX);
        Image buttonImage2 = new Image(signUpButtonFile);
        Image buttonImage3 = new Image(highScoreFile);
        buttonImj = new ImageView(buttonImage);
        signUpImj = new ImageView(buttonImage2);
        scoreImj = new ImageView(buttonImage3);
    }

    static {
        buttonLogin.setTextFill(Color.BLACK);
        buttonSignUp.setTextFill(Color.BLACK);
        highScore.setTextFill(Color.BLACK);
        buttonLogin.relocate(170, 90);
        buttonLogin.setScaleX(0.4);
        buttonLogin.setScaleY(0.4);
        buttonSignUp.setScaleX(0.6);
        buttonSignUp.setScaleY(0.6);
        buttonSignUp.relocate(180, 210);
        highScore.relocate(160, 300);
        highScore.setScaleX(0.5);
        highScore.setScaleY(0.5);
        root.getChildren().addAll(buttonLogin, buttonSignUp, highScore);
        sceneAccount.setFill(COLORS.getColorMainMenu());
    }

    static {
        buttonLogin.setGraphic(buttonImj);
        buttonSignUp.setGraphic(signUpImj);
        highScore.setGraphic(scoreImj);
    }

    static void display() {
        Main.window.setScene(sceneAccount);

        buttonLogin.setOnAction(e -> LoginMenu.display());

        buttonSignUp.setOnAction(e -> SignUpMenu.display());

        highScore.setOnAction(e -> {
            new HighScoreMenu();
            HighScoreMenu.getCurrentHighScoreMenu().display();
        });
    }

    static Scene getSceneAccount() {
        return sceneAccount;
    }
}
