import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToeSwing extends JFrame {
    private static final int SIZE = 3;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private char currentPlayer = 'X';

    public TicTacToeSwing() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        initializeBoard();

        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("-")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                if (isWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetBoard();
                } else if (isDraw()) {
                    JOptionPane.showMessageDialog(null, "The game is a draw!");
                    resetBoard();
                } else {
                    switchPlayer();
                }
            }
        }
    }

    private boolean isWin() {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((buttons[i][0].getText().charAt(0) == currentPlayer && buttons[i][1].getText().charAt(0) == currentPlayer && buttons[i][2].getText().charAt(0) == currentPlayer) ||
                    (buttons[0][i].getText().charAt(0) == currentPlayer && buttons[1][i].getText().charAt(0) == currentPlayer && buttons[2][i].getText().charAt(0) == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        if ((buttons[0][0].getText().charAt(0) == currentPlayer && buttons[1][1].getText().charAt(0) == currentPlayer && buttons[2][2].getText().charAt(0) == currentPlayer) ||
                (buttons[0][2].getText().charAt(0) == currentPlayer && buttons[1][1].getText().charAt(0) == currentPlayer && buttons[2][0].getText().charAt(0) == currentPlayer)) {
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("-");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeSwing());
    }
}
