package block.resource;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class StageDataLoader {
    public static final StageData STAGE_DATA = loadStage();

    public static final int STAGE_WIDTH = 8;
    public static final int STAGE_HEIGHT = 7;

    public static class StageData {
        public int[][] stageData1 = new int[STAGE_HEIGHT][STAGE_WIDTH];
        public int[][] stageData2 = new int[STAGE_HEIGHT][STAGE_WIDTH];
        public int[][] stageData3 = new int[STAGE_HEIGHT][STAGE_WIDTH];
    }

    public static StageData loadStage() {
        if (STAGE_DATA != null) {
            return STAGE_DATA;
        }
        Scanner sc = new Scanner(
                StageDataLoader.class.getResourceAsStream("blockdata.csv")
                , StandardCharsets.UTF_8.name()
        );
        StageData stageData = new StageData();

        for (int i = 0; i < STAGE_HEIGHT; i++) {
            String[] s = sc.nextLine().replaceAll(" ", "").split(",");
            for (int j = 0; j < STAGE_WIDTH; j++) {
                stageData.stageData1[i][j] = Integer.parseInt(s[j]);
            }
        }
        sc.nextLine();

        for (int i = 0; i < STAGE_HEIGHT; i++) {
            String[] s = sc.nextLine().replaceAll(" ", "").split(",");
            for (int j = 0; j < STAGE_WIDTH; j++) {
                stageData.stageData2[i][j] = Integer.parseInt(s[j]);
            }
        }
        sc.nextLine();

        for (int i = 0; i < STAGE_HEIGHT; i++) {
            String[] s = sc.nextLine().replaceAll(" ", "").split(",");
            for (int j = 0; j < STAGE_WIDTH; j++) {
                stageData.stageData3[i][j] = Integer.parseInt(s[j]);
            }
        }
        return stageData;
    }
}
