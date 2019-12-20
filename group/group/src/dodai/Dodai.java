package dodai;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

/**
 * 枠がないウィンドウを作るクラスです。
 * ウィンドウには様々なGUI部品を貼り付けることができます。
 */
public class Dodai extends JFrame {
    private int clickedX;
    private int clickedY;

    /**
     * 枠がないウィンドウのクラスのインスタンスを作成します
     */
    public Dodai() {
        setUndecorated(true);
        addMouseListener(new Dodai.DodaiMouseListener());
        addMouseMotionListener(new Dodai.DodaiMouseMotionListener());
    }

    /**
     * ウィンドウにコンポーネントを貼り付けます
     * @param paramComponent 貼り付けるコンポーネント
     * @return 貼り付けたコンポーネント
     */
    public Component add(Component paramComponent) {
        Container localContainer = getContentPane();
        localContainer.add(paramComponent);
        return paramComponent;
    }

    /**
     * ウィンドウのサイズを指定します。
     * @param width 幅
     * @param height 高さ
     */
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    /**
     * ウィンドウの位置(x, y)を指定します
     * @param x x座標
     * @param y y座標
     */
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
    }

    /**
     * ウィンドウを表示もしくは非表示にします
     * @param flag 表示:true/ 非表示:false
     */
    public void setVisible(boolean flag) {
        super.setVisible(flag);
    }

    class DodaiMouseListener extends MouseAdapter {
        DodaiMouseListener() {
        }

        public void mousePressed(MouseEvent paramMouseEvent) {
            Dodai.this.clickedX = paramMouseEvent.getX();
            Dodai.this.clickedY = paramMouseEvent.getY();
        }
    }

    class DodaiMouseMotionListener extends MouseMotionAdapter {
        DodaiMouseMotionListener() {
        }

        public void mouseDragged(MouseEvent paramMouseEvent) {
            JFrame localJFrame = (JFrame) paramMouseEvent.getSource();
            int i = localJFrame.getX() - Dodai.this.clickedX + paramMouseEvent.getX();
            int j = localJFrame.getY() - Dodai.this.clickedY + paramMouseEvent.getY();
            localJFrame.setLocation(i, j);
        }
    }
}