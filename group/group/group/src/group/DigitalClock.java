package group;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * デジタル時計とカレンダーのデジタル表示を作成するクラスです
 */
public class DigitalClock extends JPanel implements ActionListener {

    private final SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    private final SimpleDateFormat dateSdf = new SimpleDateFormat("yy/MMM/dd EEE", Locale.ENGLISH);

    private JLabel timeLabel;
    private JLabel dateLabel;
    private Timer timer;

    /**
     * デジタル時計と日付表示をするクラスのインスタンスを作成します
     */
    public DigitalClock() {
        timeLabel = new JLabel();
        dateLabel = new JLabel();

        timeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        dateLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        setTime();

        add(timeLabel, BorderLayout.CENTER);
        add(dateLabel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(200, 90));

        timer = new Timer(1000, this);
        timer.start();
    }


    /**
     * アクションが発生すると呼び出されます。
     * @param e アクションイベント
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setTime();
    }

    /**
     * 時間と日付をセットします
     */
    public void setTime() {
        Calendar c = Calendar.getInstance();
        timeLabel.setText(timeSdf.format(c.getTime()));
        dateLabel.setText(dateSdf.format(c.getTime()));
    }
}
