package block.gui.scene;

public class GameParameter {
    private int stageId;
    private boolean clear;
    private long score;

    public GameParameter(int stageId, boolean clear, long score) {
        this.stageId = stageId;
        this.clear = clear;
        this.score = score;
    }

    public GameParameter() {
        stageId = 1;
        clear = false;
        score = 0;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
