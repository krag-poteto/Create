package block.gui.scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import block.objectpart.Ball;
import block.objectpart.Block;
import block.objectpart.Hit;
import block.objectpart.ObjectPart;
import block.objectpart.Player;
import block.resource.StageDataLoader;

public class PlayGameScene extends GameScene {
    private Random rand;

    private StageDataLoader.StageData stageData;

    private List<Ball> ballList;
    private List<Block> blockList;
    private Player player;

    private long score;

    public PlayGameScene(Canvas canvas, GameParameter parameter, IOnSceneChangeListener listener) {
        super(canvas, parameter, listener);
    }

    @Override
    protected void init() {
        super.init();
        rand = new Random();

        stageData = StageDataLoader.loadStage();
        blockList = new ArrayList<>();
        ballList = new ArrayList<>();

        score = 0;
        setStage();

        ready = true;
    }

    private void setStage() {
        int[][] stage = new int[StageDataLoader.STAGE_HEIGHT][StageDataLoader.STAGE_WIDTH];
        if (parameter.getStageId() == 1) {
            stage = stageData.stageData1;
        } else if (parameter.getStageId() == 2) {
            stage = stageData.stageData2;
        } else if (parameter.getStageId() == 3) {
            stage = stageData.stageData3;
        }

        for (int[] arr : stage) {
            System.out.println(Arrays.toString(arr));
        }

        for (int i = 0; i < stage.length; i++) {
            for (int j = 0; j < stage[i].length; j++) {
                if (stage[i][j] > 0) {
                    blockList.add(
                            new Block(
                                    canvas.getWidth() / 8 * (j),
                                    canvas.getHeight() / 16 * (i + 1),
                                    canvas.getWidth() / 8 - 2,
                                    canvas.getHeight() / 16 - 2,
                                    Color.hsb(360 / stage.length * i, 1.0, 1.0),
                                    stage[i][j]
                            )
                    );
                } else if (stage[i][j] < 0) {
                    blockList.add(
                            new Block(
                                    canvas.getWidth() / 8 * (j),
                                    canvas.getHeight() / 16 * (i + 1),
                                    canvas.getWidth() / 8 - 2,
                                    canvas.getHeight() / 16 - 2,
                                    Color.hsb(360 / stage.length * i, 1.0, 1.0),
                                    rand.nextInt(Math.abs(stage[i][j])) + 1
                            )
                    );
                }
            }//end j
        }//end i

        player = new Player(
                canvas.getWidth() / 2 - canvas.getWidth() / 16,
                canvas.getHeight() / 2 + canvas.getHeight() / 4 + canvas.getHeight() / 28,
                canvas.getWidth() / 8,
                canvas.getHeight() / 28,
                Color.WHITE
        );

        ballList.add(new Ball(
                (player.getX() + player.getWidth() / 2) - Ball.DIAMETER / 2,
                (player.getY() - Ball.DIAMETER - 2),
                Color.LAWNGREEN
        ));
    }

    @Override
    public void update() {
        super.update();
        //update
        ballList.forEach(ball -> ball.update(canvas));
        //blockList.forEach(block -> block.update(canvas));
        player.update(canvas);

        if (ballList.get(ballList.size() - 1).isWithPlayer()) {
            ballList.get(ballList.size() - 1).setChangeMoveX(player.getX() + player.getWidth() / 2 - Ball.DIAMETER / 2);
        }

        //当たり判定
        ballList.forEach(ball -> {
            blockList.stream().filter(ObjectPart::isAlive).forEach(block -> {
                Hit hit = block.hitJudge(ball);
                if (hit != Hit.NOT) {
                    score += 10;
                    block.hit();
                    if (hit == Hit.TOP || hit == Hit.BOTTOM) {
                        ball.reflectY();
                    }
                    if (hit == Hit.LEFT || hit == Hit.RIGHT) {
                        ball.reflectX();
                    }
                }
            });

            Hit hit = player.hitJudge(ball);
            if (hit != Hit.NOT) {
                if (hit == Hit.TOP || hit == Hit.BOTTOM) {
                    ball.reflectY();
                } else if (hit == Hit.LEFT || hit == Hit.RIGHT) {
                    ball.reflectX();
                }
            }
        });

        ballList.removeIf(ball -> !ball.isAlive());
        blockList.removeIf(block -> !block.isAlive());

        if (ballList.size() <= 0) {
            gameEnd(false);
        }
        if (blockList.size() <= 0) {
            gameEnd(true);
        }
    }

    @Override
    public void draw() {
        super.draw();
        //draw
        blockList.forEach(block -> block.draw(canvas));
        player.draw(canvas);
        ballList.forEach(ball -> ball.draw(canvas));

        showScore();
    }

    private void gameEnd(boolean clear) {
        parameter.setClear(clear);
        parameter.setScore(score);

        listener.OnSceneChange(eScene.RESULT, parameter);
    }

    @Override
    protected void keyPressed(KeyEvent keyEvent) {
        super.keyPressed(keyEvent);
        switch (keyEvent.getCode()) {
            case LEFT:
            case A:
                player.setLft(true);
                break;
            case RIGHT:
            case D:
                player.setRgt(true);
                break;

            case SPACE:
                pressedSpaceButton();
                break;
        }


    }

    public void pressedSpaceButton() {
        if (ballList.get(ballList.size() - 1).isWithPlayer()) {
            ballList.get(ballList.size() - 1).setWithPlayer(false);
        } else if (score >= parameter.getStageId() * 100 && ballList.size() <= 1) {
            addBall();
        }
    }

    public void addBall() {
        ballList.add(new Ball(
                (player.getX() + player.getWidth() / 2) - Ball.DIAMETER / 2,
                (player.getY() - Ball.DIAMETER),
                Color.LAWNGREEN
        ));
    }

    @Override
    protected void keyReleased(KeyEvent keyEvent) {
        super.keyReleased(keyEvent);
        switch (keyEvent.getCode()) {
            case LEFT:
            case A:
                player.setLft(false);
                break;
            case RIGHT:
            case D:
                player.setRgt(false);
                break;
        }
    }

    public void showScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(
                Font.font(resource.getPixelFont().getFamily(), 24)
        );
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setTextBaseline(VPos.BOTTOM);
        gc.fillText("score : " + score, canvas.getWidth(), canvas.getHeight());
        //for debug
//        gc.setTextAlign(TextAlignment.LEFT);
//        gc.setTextBaseline(VPos.TOP);
//        gc.fillText("x : " + ballList.get(0).getX() + "y : " + ballList.get(0).getY(), 0, 0);

    }
}
