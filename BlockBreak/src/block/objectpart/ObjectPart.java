package block.objectpart;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class ObjectPart {

    protected double x, y;
    protected double width, height;
    protected boolean alive;
    protected Color color;

    public ObjectPart(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alive = true;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isAlive() {
        return alive;
    }

    public void destroy() {
        alive = false;
    }

    public abstract void update(Canvas canvas);

    public void draw(Canvas canvas) {
        //Debug
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.strokeRect(x, y, width, height);
    }

    public Hit hitJudge(Ball target) {
        double centerX = target.x + (target.width / 2);
        double centerY = target.y + (target.height / 2);

        Hit hit = Hit.NOT;

        if (lineVsCircle(new Point2D(this.x, this.y), new Point2D(this.x + this.width, this.y), new Point2D(centerX, centerY), target.width / 2)) {
            hit = Hit.TOP;

        } else if (lineVsCircle(new Point2D(this.x, this.y + this.height), new Point2D(this.x + this.width, this.y + this.height), new Point2D(centerX, centerY), target.width / 2)) {
            hit = Hit.BOTTOM;

        } else if (lineVsCircle(new Point2D(this.x + this.width, this.y), new Point2D(this.x + this.width, this.y + this.height), new Point2D(centerX, centerY), target.width / 2)) {
            hit = Hit.RIGHT;

        } else if (lineVsCircle(new Point2D(this.x, this.y), new Point2D(this.x, this.y + this.height), new Point2D(centerX, centerY), target.width / 2)) {
            hit = Hit.LEFT;
        }
        return hit;
    }

    protected static boolean lineVsCircle(Point2D p1, Point2D p2, Point2D center, double radius) {
        Point2D lineDir = p2.subtract(p1);
        Point2D n = new Point2D(lineDir.getY(), -lineDir.getX());
        n = n.normalize();

        Point2D dir1 = center.subtract(p1);
        Point2D dir2 = center.subtract(p2);

        double dist = Math.abs(dir1.dotProduct(n));
        double a1 = dir1.dotProduct(lineDir);
        double a2 = dir2.dotProduct(lineDir);

        return a1 * a2 < 0 && dist < radius;
    }
}
