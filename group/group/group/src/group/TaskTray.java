package group;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * タスクトレイにメニューアイコンを作成し、各種機能を実行するクラスです
 */
public class TaskTray {

    CustomizedDodai customizedDodai;

    /**
     * タスクトレイの常駐アプリを作成するクラスのインスタンスを作成します
     */
    public TaskTray() {

        customizedDodai = new CustomizedDodai();

        //タスクトレイアイコン
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("icon.png"));

        } catch (Exception e) {
            System.err.println("アイコン読み込み失敗");
            System.exit(1);
        }

        final TrayIcon icon = new TrayIcon(image);

        icon.addActionListener(e -> {
            icon.displayMessage(
                    "アイコンクリック",
                    "ダブルクリックされました",
                    MessageType.WARNING);
        });

        //ポップアップメニュー
        PopupMenu menu = new PopupMenu();

        //選択
        MenuItem search = new MenuItem("検索");
        search.addActionListener(e -> {
            String query = JOptionPane.showInputDialog(null, "検索したい言葉を入力してください", "");
            if (query != null) {
                FromDesktop.googleSearch(query);
            }
        });

        //検索と時計パネル表示
        MenuItem compositePanel = new MenuItem("検索と時計");
        compositePanel.addActionListener(e -> {
            customizedDodai.setVisible(true);
        });

        //終了メニュー
        MenuItem exitItem = new MenuItem("終了");
        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        //メニューにメニューアイテムを追加
        menu.add(search);
        menu.add(compositePanel);
        menu.add(exitItem);

        icon.setPopupMenu(menu);

        //タスクトレイに格納
        try {
            SystemTray.getSystemTray().add(icon);
        } catch (Exception e) {
            System.err.println("AWTException");
            System.exit(1);
        }
    }
}