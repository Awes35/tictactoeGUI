//author @Kollen Gruizenga
//class: Gameboard
// Creates the Tic-tac-toe board and handles the fundamentals of the game.
// (this is a lot more complex than I thought it would be)

package tictactoe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;



public class GameBoard extends JPanel implements ActionListener {
    Square[] squares = new Square[9];
    PanelListener[] listeners = new PanelListener[9];
    int gameType = 0;
    int turnType = 0;
    boolean winner = false;
    Random generator = new Random();
    int cpuChoice = 0;

    public GameBoard() {
        for (int i = 0; i < 9; i++) {
            squares[i] = new Square(i);
            add(squares[i]);
            listeners[i] = new PanelListener();
        }
        setLayout(new GridLayout(3, 3, 10, 10));
        setBorder(new EmptyBorder(100, 100, 62, 85));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Dialog", Font.BOLD, 30));
        // Draw the frame
        g.fillRect(233, 100, 10, 400);
        g.fillRect(366, 100, 10, 400);
        g.fillRect(100, 233, 400, 10);
        g.fillRect(100, 366, 400, 10);
        // First, check to see if we have a winner
        if (winner) {
            if (turnType == 1) {
                g.drawString("Player 1 wins!", 200, 50);
            } else if (turnType == 2) {
                g.drawString("Player 2 wins!", 200, 50);
            } else if (turnType == 3) {
                g.drawString("You win!", 240, 50);
            } else if (turnType == 4) {
                g.drawString("CPU wins!", 230, 50);
            }
            for (int i = 0; i < 9; i++) {
                MouseListener[] array = squares[i].getMouseListeners();
                if (array.length > 0) {
                    squares[i].removeMouseListener(listeners[i]);
                }
            }
        } else {
            // Nobody has won yet, display who's turn it is
            if (turnType == 1) {
                g.drawString("Player 1's turn!", 200, 50);
            } else if (turnType == 2) {
                g.drawString("Player 2's turn!", 200, 50);
            } else if (turnType == 3) {
                g.drawString("It's your turn!", 210, 50);
            } else if (turnType == 4) {
                g.drawString("CPU's turn!", 220, 50);
            }
        }
    }

    public void startGame(int type) {
        gameType = type;
        if (gameType == 1) {
            userTurn(1);
        } else if (gameType == 2) {
            userTurn(3);
        }
        repaint();
    }

    public void userTurn(int turn) {
        turnType = turn;
        for (int i = 0; i < 9; i++) {
            MouseListener[] array = squares[i].getMouseListeners();
            if (array.length == 0) {
                squares[i].addMouseListener(listeners[i]);
            }
        }
    }

    // Mind you, the CPU has no logic built into it at all; 
    // that's way too complex for me, so it's entirely based on RNG.
    public void cpuTurn() {
        turnType = 4;
        for (int i = 0; i < 9; i++) {
            squares[i].removeMouseListener(listeners[i]);
        }
        
        while(true) {
            cpuChoice = generator.nextInt(9);
            if (!squares[cpuChoice].hasX() && !squares[cpuChoice].hasO()) {
                Timer timer = new Timer(0, this);
                timer.setInitialDelay(2000);
                timer.setRepeats(false);
                timer.start();
                break;
            }
        }
    }

    public void checkWinner() {
        // Here's the tedious part. Run through EVERY possible winning pattern.
        if (squares[0].hasX() && squares[1].hasX() && squares[2].hasX())
            winner = true;
        else if (squares[3].hasX() && squares[4].hasX() && squares[5].hasX())
            winner = true;
        else if (squares[6].hasX() && squares[7].hasX() && squares[8].hasX())
            winner = true;
        else if (squares[0].hasX() && squares[3].hasX() && squares[6].hasX())
            winner = true;
        else if (squares[1].hasX() && squares[4].hasX() && squares[7].hasX())
            winner = true;
        else if (squares[2].hasX() && squares[5].hasX() && squares[8].hasX())
            winner = true;
        else if (squares[0].hasX() && squares[4].hasX() && squares[8].hasX())
            winner = true;
        else if (squares[2].hasX() && squares[4].hasX() && squares[6].hasX())
            winner = true;
        // this is ridiculous isn't it
        else if (squares[0].hasO() && squares[1].hasO() && squares[2].hasO())
            winner = true;
        else if (squares[3].hasO() && squares[4].hasO() && squares[5].hasO())
            winner = true;
        else if (squares[6].hasO() && squares[7].hasO() && squares[8].hasO())
            winner = true;
        else if (squares[0].hasO() && squares[3].hasO() && squares[6].hasO())
            winner = true;
        else if (squares[1].hasO() && squares[4].hasO() && squares[7].hasO())
            winner = true;
        else if (squares[2].hasO() && squares[5].hasO() && squares[8].hasO())
            winner = true;
        else if (squares[0].hasO() && squares[4].hasO() && squares[8].hasO())
            winner = true;
        else if (squares[2].hasO() && squares[4].hasO() && squares[6].hasO())
            winner = true;
        repaint();
    }

    private class PanelListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            Object source = e.getSource();
            int index = 0;
            for (Square s : squares) {
                if (source == s) {
                    index = s.index;
                    break;
                }
            }
            
            if (!squares[index].hasX() && !squares[index].hasO()) {
                if (turnType == 1) {
                    squares[index].fillX();
                    checkWinner();
                    if (!winner) {
                        userTurn(2);
                    }
                } else if (turnType == 2) {
                    squares[index].fillO();
                    checkWinner();
                    if (!winner) {
                        userTurn(1);
                    }
                } else if (turnType == 3) {
                    squares[index].fillX();
                    checkWinner();
                    if (!winner) {
                        cpuTurn();
                    }
                }
            }
            repaint();
        }
    }

    // For when the CPU performs an action
    @Override
    public void actionPerformed(ActionEvent e) {
        squares[cpuChoice].fillO();
        checkWinner();
        if (!winner) {
            userTurn(3);
        }
    }
}
