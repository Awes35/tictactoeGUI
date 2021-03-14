//author @Kollen Gruizenga
//class: TicTacToe
// All transitions between the main menu and game board are handled here.
// This class acts as the "control center" and receives all button events.

package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    MainMenu mainMenu = new MainMenu();
    GameBoard game = new GameBoard();
    
    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setBounds(500, 150, 600, 600);
        setResizable(false);
        setVisible(true);
        enterMainMenu();
        mainMenu.button1.addActionListener(this);
        mainMenu.button2.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        TicTacToe gui = new TicTacToe();
    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mainMenu.button1) {
            exitMainMenu();
            enterGame(1);
        } else if (source == mainMenu.button2) {
            exitMainMenu();
            enterGame(2);
        }
    }

    public void enterMainMenu() {
        add(mainMenu);
        getContentPane().invalidate();
        getContentPane().validate();
    }

    public void exitMainMenu() {
        remove(mainMenu);
    }

    public void enterGame(int gameType) {
        add(game);
        getContentPane().invalidate();
        getContentPane().validate();
        game.startGame(gameType);
    }

    public void exitGame() {
        remove(game);
    }
}
