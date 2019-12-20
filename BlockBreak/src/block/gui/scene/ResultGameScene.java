package block.gui.scene;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ResultGameScene extends GameScene {

    public ResultGameScene(Canvas canvas, GameParameter parameter, IOnSceneChangeListener listener) {
        super(canvas, parameter, listener);
    }

    @Override
    protected void init() {
        super.init();
        ready = true;
    }

    @Override
    public void draw() {
        super.draw();
        showResultText();
    }

    @Override
    protected void keyPressed(KeyEvent keyEvent) {
        super.keyPressed(keyEvent);
        switch (keyEvent.getCode()) {
            case SPACE:
                listener.OnSceneChange(eScene.START, parameter);
                break;
        }
    }

    private void showResultText() {
        gc.setFont(resource.getPixelFont());
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        if (parameter.isClear()) {
            gc.fillText("ゲームクリア！", canvas.getWidth() / 2, canvas.getHeight() / 2 - canvas.getHeight() / 4 + canvas.getHeight() / 6);
        } else {
            gc.fillText("ゲームオーバー・・・", canvas.getWidth() / 2, canvas.getHeight() / 2 - canvas.getHeight() / 4 + canvas.getHeight() / 6);
        }
        gc.setFont(
                Font.font(resource.getPixelFont().getFamily(), 24)
        );
        gc.fillText("score : " + parameter.getScore(), canvas.getWidth() / 2, canvas.getHeight() / 2 + canvas.getHeight() / 6);
    }
}
