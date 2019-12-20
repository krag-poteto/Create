package block.objectpart;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends ObjectPart {
    private double dx = 4;
    private boolean rgt, lft;

    public Player(double x, double y, double width, double height, Color color) {
        super(x, y, 50, 20, color);
        rgt = false;
        lft = false;
    }

    public void move() {
        if (rgt) {
            x += dx;
        }
        if (lft) {
            x -= dx;
        }
    }

    public void setLft(boolean lft) {
        this.lft = lft;
    }

    public void setRgt(boolean rgt) {
        this.rgt = rgt;
    }

    @Override
    public void update(Canvas canvas) {
        move();
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x + width >= canvas.getWidth()) {
            this.x = canvas.getWidth() - width;
        }
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRoundRect(x, y, width, height, 2, 2);
    }
}
