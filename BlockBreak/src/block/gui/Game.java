package block.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import block.gui.scene.GameParameter;
import block.gui.scene.GameScene;
import block.gui.scene.IOnSceneChangeListener;
import block.gui.scene.PlayGameScene;
import block.gui.scene.ResultGameScene;
import block.gui.scene.StartGameScene;
import block.gui.scene.eScene;

public class Game implements IOnSceneChangeListener {

    public static final int WIDTH = 500, HEIGHT = 700;
    public static final String GAME_TITLE = "ブロックくずし";

    private Stage stage;

    private GameScene nowGameScene;

    private Canvas canvas;
    private AnimationTimer at;


    public Game(Stage stage) {
        this.stage = stage;
        canvas = new Canvas(WIDTH, HEIGHT);

        at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (nowGameScene.isReady()) {
                    //更新処理
                    nowGameScene.update();
                }
                Platform.runLater(() -> {
                    //描画処理
                    nowGameScene.draw();
                });
            }
        };

        setGameScene(new StartGameScene(canvas, new GameParameter(), this));

        at.start();
//        Resource.getInstance().playBGM();
    }

    private void setGameScene(GameScene nextGameScene) {
        nowGameScene = nextGameScene;
        stage.setScene(new Scene(nextGameScene));
    }

    @Override
    public void OnSceneChange(eScene nextScene, GameParameter parameter) {
        switch (nextScene) {
            case START:
                setGameScene(new StartGameScene(canvas, new GameParameter(), this));
                break;

            case PLAY:
                setGameScene(new PlayGameScene(canvas, parameter, this));
                break;

            case RESULT:
                setGameScene(new ResultGameScene(canvas, parameter, this));
                break;
        }
    }
}
