package testButton;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Move extends MovingObj implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==e.VK_W){
            moveUP();
        }
        if(e.getKeyCode()==e.VK_S){
            moveDOWN();
        }
        if(e.getKeyCode()==e.VK_A){
            moveLEFT();
        }
        if(e.getKeyCode()==e.VK_D){
            moveRIGHT();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
