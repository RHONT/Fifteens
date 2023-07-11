package testButton;

import javax.swing.*;

public class MovingObj {

    public static JButton b = new JButton();

    private static int x = 10;
    private static int y = 10;
    private final int width = 10;
    private final int height = 10;

    public void initObj(){

        b.setBounds(x, y, width, height);
        b.addKeyListener(new Move());

    }

    public void moveUP(){

        b.setBounds(x, y-=10, width, height);
        b.repaint();

    }
    public void moveDOWN(){

        b.setBounds(x, y+=10, width, height);
        b.repaint();

    }
    public void moveLEFT(){

        b.setBounds(x-=10, y, width, height);
        b.repaint();

    }
    public void moveRIGHT(){

        b.setBounds(x+=10, y, width, height);
        b.repaint();

    }

}
