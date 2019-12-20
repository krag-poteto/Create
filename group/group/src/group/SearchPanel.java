package group;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 検索パネルを作成するクラスです
 */
class SearchPanel extends JPanel {
    JTextField tf;

    /**
     * 検索パネルを作成するクラスのインスタンスを作成します
     */
    public SearchPanel() {
        tf = new JTextField(1);

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    search();
                }
            }
        });

        JButton button = new JButton("検索");
        button.addActionListener(e -> {
            search();
        });

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(tf);
        add(button);

    }

    /**
     * 検索バーに入力された単語をFromDesktopクラスのgooglesearchメソッドで検索します
     */
    public void search() {
        if (!tf.getText().equals("")) {
            FromDesktop.googleSearch(tf.getText());
        }
    }
}