package group;

import java.awt.Desktop;
import java.net.URI;

/**
 * google検索をするクラスです
 */
public class FromDesktop {
    private static final Desktop desk = Desktop.getDesktop();

    /**
     * google検索をするメソッドです
     * @param query 検索したい単語
     */
    public static void googleSearch(String query) {
        try {
            desk.browse(new URI("https://google.co.jp/search?q=" + query));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
