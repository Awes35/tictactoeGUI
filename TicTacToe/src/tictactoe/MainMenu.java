//author @Kollen Gruizenga
//class: MainMenu
// The main menu. User will choose either CPU or 2-player mode.

package tictactoe;

import javax.swing.*;
import java.awt.*;


public class MainMenu extends JPanel {
    Font font = new Font("Bauhaus 93", Font.PLAIN, 72);
    Font font2 = new Font("Bauhaus 93", Font.PLAIN, 15);
    JPanel buttonPanel = new JPanel();
    Button button1 = new Button("2-Player");
    Button button2 = new Button("Play with CPU");

    public MainMenu() {
        setBackground(new Color(200, 75, 75));
        // I'm a bit fed up with trying to place things exactly where I want them
        // using BorderLayouts and GridLayouts, so I'm explicitly placing/sizing them via coordinates
        setLayout(null);
        button1.setBounds(250, 325, 120, 50);
        button2.setBounds(250, 400, 120, 50);
        add(button1);
        add(button2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Logo design
        g.setFont(font);
        g.drawString("TicTacToe", 120, 200);
        g.fillRect(100, 80, 7, 200);
        g.fillRect(500, 80, 7, 200);
        g.fillRect(25, 125, 550, 7);
        g.fillRect(25, 225, 550, 7);
        g.setFont(font2);
        g.drawString("by Kollen Gruizenga", 110, 250);
        
    }
}
