package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

class LoginMenu {
    private static Group root = new Group();
    private static Scene sceneLoginMenu = new Scene(root, 500, 500);
    private static TextField loginTF = new TextField();
    private static Text invalidInput = new Text("Invalid User Name");
    private static Text invalidPassword = new Text("pass word is wrong");
    private static Button backToMainMenu = new Button("Back to Menu");
    private static Button login = new Button("Login");
    private static TextField enterPassword = new TextField();

    static {
        Text text = new Text("Enter User Name:");
        Text text1 = new Text("Enter Pass word:");
        loginTF.setPromptText("enter username");
        enterPassword.setPromptText("enter password");
        loginTF.relocate(170, 180);
        text1.relocate(55, 225);
        enterPassword.relocate(170, 220);
        invalidInput.setFill(Color.RED);
        invalidInput.relocate(185, 400);
        invalidPassword.relocate(185, 410);
        invalidPassword.setFill(Color.RED);
        sceneLoginMenu.setFill(COLORS.getColorMainMenu());
        text.relocate(55, 185);
        backToMainMenu.relocate(195, 430);
        root.getChildren().addAll(loginTF, text, backToMainMenu, login, enterPassword, text1);
        login.relocate(backToMainMenu.getLayoutX() + 20, 270);
        login.setWrapText(true);
    }

    static void display() {
        Main.window.setScene(sceneLoginMenu);

        login.setOnAction(e -> loginLogic());

        loginTF.setOnAction(e -> {
            if (!enterPassword.getText().equals("") && !loginTF.getText().equals(""))
                loginLogic();
        });

        enterPassword.setOnAction(e -> {
            if (!enterPassword.getText().equals("") && !loginTF.getText().equals(""))
                loginLogic();
        });

        backToMainMenu.setOnAction(e -> Main.window.setScene(AccountMenu.getSceneAccount()));
    }

    private static void loginLogic() {
        String userName = loginTF.getText().trim();
        root.getChildren().removeAll(invalidPassword, invalidInput);
        if (!enterPassword.getText().equals("")) {
            String passWord = enterPassword.getText().trim();
            if (Account.login(userName, passWord)) {
                Main.window.setScene(Main.scene);
                root.getChildren().removeAll(invalidInput, invalidPassword);
                loginTF.setText("");
                enterPassword.setText("");
            } else if (!Account.checkCorrectPassWord(userName, passWord))
                root.getChildren().add(invalidPassword);
            else root.getChildren().add(invalidInput);
        }
    }
}
