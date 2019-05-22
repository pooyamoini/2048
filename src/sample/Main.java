package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    private static FileInputStream playButtonFile;
    private static ImageView playImj;
    private static FileInputStream menuFile;
    private static ImageView menuImj;
    private static FileInputStream quitFile;
    private static ImageView quitImj;
    private static FileInputStream userFile;
    private static ImageView userImj;

    static {
        try {
            playButtonFile = new FileInputStream("/Users/pooya/Desktop/Programms/third exercise 2048/src/sample/play.png");
            menuFile = new FileInputStream("/Users/pooya/Desktop/Programms/third exercise 2048/src/sample/home.png");
            quitFile = new FileInputStream("/Users/pooya/Desktop/Programms/third exercise 2048/src/sample/quit.png");
            userFile = new FileInputStream("/Users/pooya/Desktop/Programms/third exercise 2048/src/sample/username.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        Image image1 = new Image(playButtonFile);
        Image image2 = new Image(menuFile);
        Image image3 = new Image(quitFile);
        Image image4 = new Image(userFile);
        playImj = new ImageView(image1);
        menuImj = new ImageView(image2);
        quitImj = new ImageView(image3);
        userImj = new ImageView(image4);
    }

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
        playButton.setOnAction(e -> root.getChildren().add(takeLength));

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
                    root.getChildren().removeAll(takeLength, invalidText);
                    takeLength.setText("");
                } else root.getChildren().add(invalidText);
            } else root.getChildren().add(invalidText);
        });

        quitButton.setOnAction(e -> window.close());

        mainMenu.setOnAction(e -> window.setScene(AccountMenu.getSceneAccount()));
    }

    private void secondInitialize() {
        takeLength.relocate(167, 120);
        takeLength.setPromptText("enter dimensions:");

        invalidText.setFill(Color.RED);
        invalidText.relocate(57, 20);

        changeUserName.relocate(playButton.getLayoutX() - 40, playButton.getLayoutY() + 173);
        changeUserName.setScaleX(0.6);
        changeUserName.setScaleY(0.6);
        changeUserName.setGraphic(userImj);

        changingUserName.setPromptText("username");
        changingUserName.relocate(147, changeUserName.getLayoutY() + 65);
    }

    private void initializeButton() {
        playButton.setTextFill(Color.BLACK);
        playButton.relocate(180, 50);
        playButton.setScaleX(0.6);
        playButton.setScaleY(0.6);
        playButton.setGraphic(playImj);

        quitButton.relocate(playButton.getLayoutX() + 8, playButton.getLayoutY() + 100);
        quitButton.setTextFill(Color.BLACK);
        quitButton.setScaleX(0.6);
        quitButton.setScaleY(0.6);
        quitButton.setGraphic(quitImj);

        mainMenu.relocate(playButton.getLayoutX() - 10, playButton.getLayoutY() + 290);
        mainMenu.setScaleX(0.7);
        mainMenu.setScaleY(0.7);
        mainMenu.setGraphic(menuImj);
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(KeyEvent event) {

    }
}
