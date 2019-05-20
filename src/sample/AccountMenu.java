package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class AccountMenu {
    private static Group root = new Group();
    private static Scene sceneAccount = new Scene(root, 500, 500);
    private static Button buttonLogin = new Button("Login");
    private static Button buttonSignUp =  new Button("SignUp");
    static {
        buttonLogin.setTextFill(Color.BLACK);
        buttonSignUp.setTextFill(Color.BLACK);
        buttonLogin.relocate(225, 140);
        buttonLogin.setScaleX(2.5);
        buttonLogin.setScaleY(2);
        buttonSignUp.setScaleX(2.5);
        buttonSignUp.setScaleY(2);
        buttonSignUp.relocate(220, 240);
        root.getChildren().addAll(buttonLogin, buttonSignUp);
        sceneAccount.setFill(COLORS.getColorMainMenu());
    }

    static void display(){
        Main.window.setScene(sceneAccount);
        buttonLogin.setOnAction(e -> {
            LoginMenu.display();
        });
        buttonSignUp.setOnAction(e -> SignUpMenu.display());
    }

    static Scene getSceneAccount() {
        return sceneAccount;
    }
}
