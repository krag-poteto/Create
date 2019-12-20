package block;

import javafx.application.Application;
import javafx.stage.Stage;

import block.gui.Game;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new Game(primaryStage);
        primaryStage.setTitle(Game.GAME_TITLE);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
