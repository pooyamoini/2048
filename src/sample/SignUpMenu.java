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
    private static TextField enterPassWord = new TextField();
    private static Button signUpButton = new Button("Sign Up");
    private static Text signUpText = new Text("enter user name:");
    private static Text inputPassWord = new Text("enter password:");
    private static Text userNameAlreadyExist = new Text("this username already exist");
    private static Button backButton = new Button("Back");

    static {
        enterUserName.setPromptText("user name");
        enterPassWord.setPromptText("enter password");
        enterUserName.relocate(170, 180);
        enterPassWord.relocate(170, 220);
        signUpButton.relocate(215, 265);
        signUpText.relocate(55, 185);
        inputPassWord.relocate(55, 225);
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

        enterUserName.setOnAction(e -> {
            if (!root.getChildren().contains(enterPassWord) && !enterUserName.getText().equals("")) {
                root.getChildren().addAll(enterPassWord);
                if (!root.getChildren().contains(inputPassWord))
                    root.getChildren().add(inputPassWord);
            }
        });

        enterPassWord.setOnAction(e -> {
            if (!enterPassWord.getText().equals("") && !enterUserName.getText().equals(""))
                signUpLogic();
        });

        signUpButton.setOnAction(e -> signUpLogic());

        backButton.setOnAction(e -> Main.window.setScene(AccountMenu.getSceneAccount()));
    }

    private static void signUpLogic() {
        if (!enterUserName.getText().equals("") && !enterPassWord.getText().equals("")) {
            String userName = enterUserName.getText().trim();
            String passWord = enterPassWord.getText().trim();
            if (Account.addAccount(userName, passWord)) {
                Main.window.setScene(AccountMenu.getSceneAccount());
                root.getChildren().removeAll(userNameAlreadyExist, inputPassWord, enterPassWord);
                enterUserName.setText("");
                enterPassWord.setText("");
            } else {
                if (!root.getChildren().contains(userNameAlreadyExist))
                    root.getChildren().add(userNameAlreadyExist);
            }
        }
    }
}
