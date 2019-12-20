package block.objectpart;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Block extends ObjectPart {
    int nowLife;
    final int LIFEMAX_VALUE;

    public Block(double x, double y, double width, double height, Color color, int lifeMax) {
        super(x, y, width, height, color);
        this.LIFEMAX_VALUE = lifeMax;
        nowLife = LIFEMAX_VALUE;
    }

    public void hit() {
        if (nowLife > 0) {
            nowLife--;
            color = color.deriveColor(0, 1, 1 / (double) LIFEMAX_VALUE * nowLife, 1);
        }
        if (nowLife <= 0) destroy();
    }


    @Override
    public void update(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (alive) {
            gc.setFill(color);
            gc.fillRect(x, y, width, height);
        }
        //デバッグ
//        gc.setFill(Color.WHITESMOKE);
//        gc.setFont(Font.font(24));
//        gc.setTextAlign(TextAlignment.LEFT);
//        gc.setTextBaseline(VPos.TOP);
//        gc.fillText(String.valueOf(nowLife), x, y);
    }
}
