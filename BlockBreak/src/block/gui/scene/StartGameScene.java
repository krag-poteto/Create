package block.gui.scene;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class StartGameScene extends GameScene {

    public StartGameScene(Canvas canvas, GameParameter parameter, IOnSceneChangeListener listener) {
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
        showTitleText();
    }

    @Override
    protected void keyPressed(KeyEvent keyEvent) {
        super.keyPressed(keyEvent);
        switch (keyEvent.getCode()) {
            case DIGIT1:
            case NUMPAD1:
                parameter.setStageId(1);
                listener.OnSceneChange(eScene.PLAY, parameter);
                break;

            case DIGIT2:
            case NUMPAD2:
                parameter.setStageId(2);
                listener.OnSceneChange(eScene.PLAY, parameter);
                break;

            case DIGIT3:
            case NUMPAD3:
                parameter.setStageId(3);
                listener.OnSceneChange(eScene.PLAY, parameter);
                break;
        }
    }

    private void showTitleText() {
        gc.setFont(resource.getPixelFont());
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText("ブロックくずし", canvas.getWidth() / 2, canvas.getHeight() / 2 - canvas.getHeight() / 4 + canvas.getHeight() / 6);

        gc.setFont(
                Font.font(resource.getPixelFont().getFamily(), 24)
        );
        gc.fillText("キーボードの1～3でレベルを\n選んでください", canvas.getWidth() / 2, canvas.getHeight() / 2 + canvas.getHeight() / 6);
    }
}
