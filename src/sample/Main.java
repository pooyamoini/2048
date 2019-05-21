package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<KeyEvent> {

    static Stage window;
    static Scene scene;
    private static Button playButton = new Button("PLAY");
    private static Button quitButton = new Button("QUIT");
    private static Button mainMenu = new Button("Main Menu");
    private static Button changeUserName = new Button("Change username");
    private static TextField takeLength = new TextField();
    private static Text invalidText = new Text("invalid input\ninput must be number\nand bigger than 2\nlower than 10");
    private static TextField changingUserName = new TextField();
    private static ProgressBar progressBar = new ProgressBar();

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        primaryStage.setTitle("2048 Game");
        Group root = new Group();

        initializeButton();

        secondInitialize();

        scene = new Scene(root, 500, 500);
        scene.setFill(COLORS.getColorMainMenu());
        AccountMenu.display();

        setButtonsAction(root);

        root.getChildren().addAll(playButton, quitButton, mainMenu, changeUserName);

        window.show();
    }

    private void setButtonsAction(Group root) {
        playButton.setOnAction(e -> {
            root.getChildren().add(takeLength);
            root.getChildren().add(progressBar);
            if (!root.getChildren().contains(progressBar)) {
                progressBar.setProgress(0);
            }
        });

        changeUserName.setOnAction(e -> root.getChildren().add(changingUserName));

        changingUserName.setOnAction(e -> {
            if (!changingUserName.getText().equals("")) {
                if (Account.getCurrentAccount().setUserName(changingUserName.getText()))
                    root.getChildren().remove(changingUserName);
            }
        });

        takeLength.setOnAction(e -> {
            if (takeLength.getText().matches("\\d")) {
                if (Integer.parseInt(takeLength.getText()) >= 3) {
                    new Game(Integer.parseInt(takeLength.getText()));
                    PlayDisplay.getCurrentPlayDisplay().display();
                    root.getChildren().removeAll(takeLength, progressBar, invalidText);
                    takeLength.setText("");
                } else root.getChildren().add(invalidText);
            } else root.getChildren().add(invalidText);
        });

        quitButton.setOnAction(e -> window.close());

        mainMenu.setOnAction(e -> window.setScene(AccountMenu.getSceneAccount()));
    }

    private void secondInitialize() {
        progressBar.relocate(mainMenu.getLayoutX() - 5, mainMenu.getLayoutY() + 100);

        takeLength.relocate(167, 90);
        takeLength.setPromptText("enter dimensions:");

        invalidText.setFill(Color.RED);
        invalidText.relocate(57, 20);

        changeUserName.relocate(playButton.getLayoutX() - 50, playButton.getLayoutY() + 200);
        changeUserName.setScaleX(1.4);
        changeUserName.setScaleY(1.2);
        changeUserName.setWrapText(true);

        changingUserName.setPromptText("username");
        changingUserName.relocate(167, changeUserName.getLayoutY() + 48);
    }

    private void initializeButton() {
        playButton.setTextFill(Color.BLACK);
        playButton.relocate(225, 50);
        playButton.setScaleX(1.4);
        playButton.setScaleY(1.2);
        playButton.setWrapText(true);

        quitButton.relocate(playButton.getLayoutX(), playButton.getLayoutY() + 100);
        quitButton.setTextFill(Color.BLACK);
        quitButton.setScaleX(1.4);
        quitButton.setScaleY(1.2);
        quitButton.setWrapText(true);

        mainMenu.relocate(playButton.getLayoutX() - 20, playButton.getLayoutY() + 300);
        mainMenu.setScaleX(1.4);
        mainMenu.setScaleY(1.2);
        mainMenu.setWrapText(true);
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(KeyEvent event) {

    }
}
