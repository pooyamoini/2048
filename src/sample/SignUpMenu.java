package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

class SignUpMenu {
    private static Group root = new Group();
    private static Scene sceneSignUp = new Scene(root, 500, 500);
    private static TextField enterUserName = new TextField();
    private static Button signUpButton = new Button("Sign Up");
    private static Text signUpText = new Text("enter user name:");
    private static Text userNameAlreadyExist = new Text("this username already exist");
    private static Button backButton = new Button("Back");

    static {
        enterUserName.setPromptText("user name");
        enterUserName.relocate(170, 180);
        signUpButton.relocate(215, 210);
        signUpText.relocate(55, 185);
        sceneSignUp.setFill(COLORS.getColorMainMenu());
        userNameAlreadyExist.relocate(170, 310);
        userNameAlreadyExist.setFill(Color.RED);
        backButton.relocate(225, 385);
        backButton.setTextFill(Color.BLACK);
    }

    static {
        root.getChildren().addAll(enterUserName, signUpButton, signUpText, backButton);
    }

    static void display() {
        Main.window.setScene(sceneSignUp);

        enterUserName.setOnAction(e -> signUpLogic());

        signUpButton.setOnAction(e -> signUpLogic());

        backButton.setOnAction(e -> Main.window.setScene(AccountMenu.getSceneAccount()));
    }

    private static void signUpLogic() {
        if (!enterUserName.getText().equals("")) {
            String userName = enterUserName.getText();
            if (Account.addAccount(userName))
                Main.window.setScene(AccountMenu.getSceneAccount());
            else root.getChildren().add(userNameAlreadyExist);
        }
    }
}
