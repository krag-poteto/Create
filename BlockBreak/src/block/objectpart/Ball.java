package block.objectpart;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends ObjectPart {

    private double dx, dy;
    private double prevX, prevY;
    private final double BALL_SPEED_X, BALL_SPEED_Y;
    public static final int DIAMETER = 16;
    private boolean withPlayer = true;


    public Ball(double x, double y, Color color) {
        super(x, y, DIAMETER, DIAMETER, color);
        dx = BALL_SPEED_X = 2;
        dy = BALL_SPEED_Y = -2;
    }

    public void move() {
        prevX = x;
        prevY = y;

        if (!withPlayer) {
            x += dx;
            y += dy;
        }
    }

    public void reflectX() {
        dx = (-1) * dx;
    }

    public void reflectY() {
        dy = (-1) * dy;
    }

    public boolean isWithPlayer() {
        return withPlayer;
    }

    public void setChangeMoveX(double x) {
        this.x = x;
    }

    public void setWithPlayer(boolean withPlayer) {
        this.withPlayer = withPlayer;
    }

    @Override
    public void update(Canvas canvas) {
        move();
        if (this.x < 0 || this.x + this.width > canvas.getWidth()) {
            dx = (-1) * dx;
        }
        if (this.y < 0) {
            dy = (-1) * dy;
        } else if (this.y > canvas.getHeight()) {
            this.alive = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillOval(x, y, DIAMETER, DIAMETER);
    }
}
