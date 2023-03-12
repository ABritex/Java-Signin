package SignUp_ABELEDA;

import javax.swing.*;

public class Loading extends JFrame {
    JProgressBar Loading;
    int i = 0, num = 0;

    Loading() {
        Loading = new JProgressBar(0, 2000);
        Loading.setBounds(233, 250, 233, 40);
        Loading.setValue(0);
        Loading.setStringPainted(true);
        add(Loading);
        setSize(700, 600);
        setLayout(null);
    }

    public void iterate() {
        while (i <= 2000) {
            Loading.setValue(i);
            i = i + 20;
            try {
                Thread.sleep(150);
            } catch (Exception e) {
            }
        }
    }
}