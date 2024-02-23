package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private static final long serialVersionUID = 1L;
	private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';
    private TicTacToe game;
    private JButton[][] buttons;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        setSize(300, 300);

        game = new TicTacToe();
        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];

        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(100, 100));
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                add(button);
               
              
            }
        }

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (game.getBoard()[row][col] == EMPTY_CELL && !game.isGameOver()) {
                game.play(row, col);
                buttons[row][col].setText(String.valueOf(game.getCurrentPlayer()));

                char winner = game.checkWinner();
                if (winner != EMPTY_CELL) {
                    if (winner == TicTacToe.PLAYER_X) {
                        JOptionPane.showMessageDialog(TicTacToeGUI.this, "Player O wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    } else if (winner == TicTacToe.PLAYER_O) {
                        JOptionPane.showMessageDialog(TicTacToeGUI.this, "Player X wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(TicTacToeGUI.this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(TicTacToeGUI.this, "Cell already occupied or game over!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }
}
