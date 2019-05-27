package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

class PlayDisplay {
    private static PlayDisplay currentPlayDisplay;

    PlayDisplay() {
        root = new Group();
        rectangles = new Rectangle[Game.getCurrentGame().getDimensions()][Game.getCurrentGame().getDimensions()];
        labels = new Text[Game.getCurrentGame().getDimensions()][Game.getCurrentGame().getDimensions()];
        score = new Text("0");
        currentPlayDisplay = this;
        initializeRectangles();
        initializeLabels();
        initializeGameOver();
        initializeScore();
    }

    private Group root;
    private Scene scenePlay;
    private Scene gameOverScene;
    private Group rootGameOver;
    private Text gameOverText;
    private final float RECTANGLE_HEIGHT = 400 / 4.5f;
    private final float RECTANGLE_WIDTH = 400 / 4.5f;
    private Rectangle[][] rectangles;
    private Text[][] labels;
    private Text score;
    private Button backToMainMenu = new Button("Back to Main Menu");

    private void initializeRectangles() {
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j] = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
                rectangles[i][j].setFill(COLORS.getColor0());
                if (j == 0) {
                    rectangles[i][j].setX(50);
                    rectangles[i][j].setY((i / 4f) * RECTANGLE_WIDTH + 50);
                    if (i != 0)
                        rectangles[i][j].setY(rectangles[i - 1][j].getY() + RECTANGLE_WIDTH + 10);
                } else {
                    rectangles[i][j].setX(rectangles[i][j - 1].getX() + RECTANGLE_WIDTH + 15);
                    rectangles[i][j].setY(rectangles[i][j - 1].getY());
                }
                root.getChildren().add(rectangles[i][j]);
            }
        }
    }

    private void initializeLabels() {
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new Text();
                labels[i][j].setX(rectangles[i][j].getX() + RECTANGLE_WIDTH / 2 - 3);
                labels[i][j].setY(rectangles[i][j].getY() + RECTANGLE_HEIGHT / 2 + 5);
                labels[i][j].setScaleX(2.3);
                labels[i][j].setScaleY(2.3);
                root.getChildren().add(labels[i][j]);
            }
        }
    }

    private void initializeGameOver() {
        rootGameOver = new Group();
        gameOverScene = new Scene(rootGameOver, 500, 500);
        gameOverText = new Text();
        gameOverText.setX(210);
        gameOverText.setY(260);
        gameOverText.setScaleX(3);
        gameOverText.setScaleY(3);
        gameOverScene.setFill(COLORS.getColorMainMenu());
        rootGameOver.getChildren().add(gameOverText);
    }

    private void initializeScore() {
        scenePlay = new Scene(root, 500 * Game.getCurrentGame().getDimensions() / 4f, 500 * Game.getCurrentGame().getDimensions() / 4f);
        score.setX(scenePlay.getWidth() / 2 + 20);
        score.setY(scenePlay.getHeight() - Game.getCurrentGame().getDimensions() * 10 + 10);
        if (Game.getCurrentGame().getDimensions() == 3)
            score.setY(scenePlay.getHeight() - 20);
        score.setScaleX(1.2);
        score.setScaleY(1.2);
        score.setFill(Color.BLACK);
        root.getChildren().add(score);
    }

    void display() {

        scenePlay.setFill(COLORS.getColorMainMenu());
        Main.window.setScene(scenePlay);

        Main.window.setOnCloseRequest(e -> Main.window.setScene(Main.scene));

        scenePlay.setOnKeyPressed(event -> {
            gameIsOver();
            if (Game.getCurrentGame() != null) {
                switch (event.getCode()) {
                    case LEFT:
                    case A:
                        Game.getCurrentGame().left();
                        break;
                    case UP:
                    case W:
                        Game.getCurrentGame().up();
                        break;
                    case RIGHT:
                    case D:
                        Game.getCurrentGame().right();
                        break;
                    case DOWN:
                    case S:
                        Game.getCurrentGame().down();
                        break;
                    case ESCAPE:
                        Game.setCurrentGameNull();
                        Main.window.setScene(Main.scene);
                        break;

                }
            }
        });

        showNumbers();

        gameIsOver();

        scenePlay.setOnKeyReleased(event -> showNumbers());
    }

    private void showNumbers() {
        for (int i = 0; i < Game.getCurrentGame().getBoard().length; i++) {
            for (int j = 0; j < Game.getCurrentGame().getBoard()[i].length; j++) {
                root.getChildren().remove(labels[i][j]);
                if (!Game.getCurrentGame().getBoard()[i][j].isEmpty()) {
                    labels[i][j].setText(Integer.toString(Game.getCurrentGame().getBoard()[i][j].getValue()));
                    if (labels[i][j].getText().length() == 3)
                        labels[i][j].setX(rectangles[i][j].getX() + RECTANGLE_WIDTH / 2 - 3 - 8);
                    else if (labels[i][j].getText().length() == 2)
                        labels[i][j].setX(rectangles[i][j].getX() + RECTANGLE_WIDTH / 2 - 3 - 5);
                    root.getChildren().add(labels[i][j]);
                    coloringRectangle(i, j);
                }
            }
        }
        score.setText("Score: ".concat(Integer.toString(Game.getCurrentGame().getScore())));
        initializeEmpty();
    }

    private void coloringRectangle(int i, int j) {
        switch (Game.getCurrentGame().getBoard()[i][j].getValue()) {
            case 2:
                rectangles[i][j].setFill(COLORS.getColor2());
                break;
            case 4:
                rectangles[i][j].setFill(COLORS.getColor4());
                break;
            case 8:
                rectangles[i][j].setFill(COLORS.getColor8());
                break;
            case 16:
                rectangles[i][j].setFill(COLORS.getColor16());
                break;
            case 32:
                rectangles[i][j].setFill(COLORS.getColor32());
                break;
            case 64:
                rectangles[i][j].setFill(COLORS.getColor64());
                break;
            case 128:
                rectangles[i][j].setFill(COLORS.getColor128());
                break;
            case 256:
                rectangles[i][j].setFill(COLORS.getColor256());
                break;
            case 512:
                rectangles[i][j].setFill(COLORS.getColor512());
                break;
            case 1024:
                rectangles[i][j].setFill(COLORS.getColor1024());
                break;
            case 2048:
                rectangles[i][j].setFill(COLORS.getColor2048());
        }
    }

    private void initializeEmpty() {
        for (int i = 0; i < Game.getCurrentGame().getBoard().length; i++) {
            for (int j = 0; j < Game.getCurrentGame().getBoard()[i].length; j++) {
                if (Game.getCurrentGame().getBoard()[i][j].isEmpty())
                    rectangles[i][j].setFill(COLORS.getColor0());
            }
        }
    }

    private void gameIsOver() {
        if (Game.getCurrentGame().gameIsOver()) {
            if (Game.getCurrentGame().getScore() > Account.getCurrentAccount().getHighScore())
                Account.getCurrentAccount().setHighScore(Game.getCurrentGame().getScore());
            Main.window.setScene(gameOverScene);
            gameOverText.setText("Game is Over\n\n\n");
            Text gameOverScoreText = new Text("Score is : ".concat(Integer.toString(Game.getCurrentGame().getScore())));
            gameOverScoreText.setX(gameOverScene.getWidth() / 2 - 40);
            gameOverScoreText.setY(gameOverScene.getHeight() / 2 + 100);
            gameOverScoreText.setScaleX(2.1);
            gameOverScoreText.setScaleY(2.1);
            backToMainMenu.relocate(180, 450);
            backToMainMenu.setOnAction(e -> Main.window.setScene(AccountMenu.getSceneAccount()));
            Game.setCurrentGameNull();
            rootGameOver.getChildren().addAll(gameOverScoreText, backToMainMenu);
        }
    }

    static PlayDisplay getCurrentPlayDisplay() {
        return currentPlayDisplay;
    }
}