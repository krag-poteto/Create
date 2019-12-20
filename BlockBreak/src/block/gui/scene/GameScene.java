package block.gui.scene;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import block.resource.Resource;

public abstract class GameScene extends Group {
    protected Canvas canvas;
    protected GraphicsContext gc;
    protected Resource resource;
    protected IOnSceneChangeListener listener;

    protected GameParameter parameter;

    protected boolean ready = false;

    public GameScene(Canvas canvas, GameParameter parameter, IOnSceneChangeListener listener) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);

        resource = Resource.getInstance();
        this.listener = listener;

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(this::keyPressed);
        canvas.setOnKeyReleased(this::keyReleased);

        this.parameter = parameter;

        init();
        System.out.println(this.getClass().toString());
    }

    protected void init() {

    }

    public void update() {

    }

    public void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    protected void keyPressed(KeyEvent keyEvent) {

    }

    protected void keyReleased(KeyEvent keyEvent) {

    }

    public boolean isReady() {
        return ready;
    }

}
