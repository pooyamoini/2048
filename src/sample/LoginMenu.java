package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LoginMenu {
    private static Group root = new Group();
    private static Scene sceneLoginMenu = new Scene(root, 500, 500);
    private static TextField loginTF = new TextField();
    private static Text invalidInput = new Text("Invalid User Name");
    private static Button backToMainMenu = new Button("Back to Menu");

    static {
        Text text = new Text("Enter User Name:");
        loginTF.setPromptText("enter username");
        loginTF.relocate(170,180);
        invalidInput.setFill(Color.RED);
        invalidInput.relocate(185, 400);
        sceneLoginMenu.setFill(COLORS.getColorMainMenu());
        text.relocate(55, 185);
        backToMainMenu.relocate(55, 230);
        root.getChildren().addAll(loginTF, text, backToMainMenu);
    }

    static void display(){
        Main.window.setScene(sceneLoginMenu);
        loginTF.setOnAction(e -> {
            String userName = loginTF.getText();
            if (Account.login(userName)){
                Main.window.setScene(Main.scene);
            }
            else root.getChildren().add(invalidInput);
        });

        backToMainMenu.setOnAction(e -> Main.window.setScene(AccountMenu.getSceneAccount()));
    }

    static Scene getSceneLoginMenu() {
        return sceneLoginMenu;
    }
}
