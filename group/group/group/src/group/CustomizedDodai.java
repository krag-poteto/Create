package group;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import dodai.Dodai;

/**
 * Dodaiクラスを拡張した3つのパネルを一つのパネルで表示するクラスです
 */
public class CustomizedDodai extends Dodai {

    private DigitalClock digitalClock;
    private SearchPanel searchPanel;

    /**
     * 複合パネルを表示するクラスのインスタンスを作成します
     */
    public CustomizedDodai() {

        digitalClock = new DigitalClock();
        searchPanel = new SearchPanel();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton endButton = new JButton("終了");
        JButton minimalizeButton = new JButton("最小化");

        endButton.addActionListener(e -> {
            System.exit(0);
        });

        minimalizeButton.addActionListener(e -> {
            setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(minimalizeButton);
        buttonPanel.add(endButton);
        buttonPanel.setPreferredSize(new Dimension(200, 30));


        add(digitalClock);
        add(searchPanel);
        add(buttonPanel);
        pack();

        //パネル表示位置
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getMaximumWindowBounds();
        int x = (int) rec.getWidth() - getWidth() - 10;
        int y = (int) rec.getHeight() - getHeight();

        setLocation(x, y);
        setAlwaysOnTop(true);
    }
}
