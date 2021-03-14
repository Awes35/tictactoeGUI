//author @Kollen Gruizenga
//class: Square
// Represents an individual square on the game board

package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Square extends JPanel {
    Font font = new Font("Dialog", Font.PLAIN, 72);
    boolean xFilled = false;
    boolean oFilled = false;
    int index;

    public Square(int index) {
        this.index = index;
    }

    public void fillX() {
        xFilled = true;
        repaint();
    }

    public boolean hasX() {
        return xFilled;
    }

    public void fillO() {
        oFilled = true;
        repaint();
    }

    public boolean hasO() {
        return oFilled;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(font);
        if (xFilled) {
            g.drawString("X", 40, 90);
        } else if (oFilled) {
            g.drawString("O", 40, 90);
        }
    }
}
