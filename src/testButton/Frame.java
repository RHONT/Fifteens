package testButton;

import javax.swing.*;

public class Frame extends JFrame {

    public void initComponents(){
        initFrame();
        initObj();
    }

    private void initObj() {
        MovingObj mo = new MovingObj();
        mo.initObj();
        add(MovingObj.b);
    }

    private void initFrame() {

        this.setSize(300, 300);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);

    }

}
