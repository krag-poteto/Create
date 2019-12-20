package block.resource;


import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class Resource {
    private static final Resource RESOURCE = new Resource();

    private MediaPlayer mp;

    private final Font pixelFont;

    private Resource() {
        this.pixelFont = Font.loadFont(getClass().getResourceAsStream("pixelfont.ttf"), 48);
        System.out.println(pixelFont);

        try {
            mp = new MediaPlayer(new Media(getClass().getResource("music.mp3").toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println("Musicファイルが読み込めませんでした。");
            System.exit(1);
        }
    }

    public void playBGM() {
        mp.setVolume(0.25);
        mp.play();
    }


    public Font getPixelFont() {
        return pixelFont;
    }

    public static Resource getInstance() {
        return RESOURCE;
    }
}
