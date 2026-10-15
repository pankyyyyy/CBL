import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CBLApp {
    private int rows = 6;
    private int cols;
    private WordleGame game;
    private JLabel[][] cells;
    private JFrame frame;
    private int row = 0;              
    private int col = 0;

    public CBLApp() throws IOException {
        WordleGame.load("answers.txt");
        String word = WordleGame.answers.get((int) (Math.random() * WordleGame.answers.size()));
        game = new WordleGame(word);
        cols = word.length();
        cells = new JLabel[rows][cols];
        frame = new JFrame("CBLdle");
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel board = new JPanel(new GridLayout(rows, cols, 8, 8));
        board.setBorder(BorderFactory.createEmptyBorder(50, 50, 250, 50));
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JLabel cell = new JLabel("", SwingConstants.CENTER);
                cell.setOpaque(true);
                cell.setBackground(Color.WHITE);
                cell.setFont(new Font("SansSerif", Font.BOLD, 32));
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                cells[r][c] = cell;
                board.add(cell);
            }
        }
        frame.add(board, BorderLayout.CENTER);

        board.setFocusable(true);
        board.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e)  {
                if (game.getState() != GameState.IN_PROGRESS) return;
                char ch = e.getKeyChar();
                if (Character.isLetter(ch) && col < cols) {
                    cells[row][col++].setText(String.valueOf(ch).toUpperCase());
                } else if (ch == '\b' && col > 0) {
                    cells[row][--col].setText("");
                } else if (ch == '\n') {
                    try {
                        submit();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    private void submit() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (JLabel c : cells[row]) sb.append(c.getText());

        int[] status = game.submitGuess(sb.toString().toLowerCase());
        if (status == null) {
            JOptionPane.showMessageDialog(frame, "Not a valid guess.");
            return;
        }

        for (int i = 0; i < cols; i++) {
            Color color = status[i] == 2 ? Color.GREEN  
                        : status[i] == 1 ? Color.YELLOW
                        : Color.GRAY;
            cells[row][i].setBackground(color);
            cells[row][i].setForeground(Color.WHITE);
        }

        if (game.getState() == GameState.WON) {
            JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the word correctly.");
        } else if (game.getState() == GameState.LOST) {
            JOptionPane.showMessageDialog(frame, "Out of guesses! The word was: " + game.getCorrectWord());
        }
        row++;
        col = 0;
    }

    public static void main(String[] args) throws IOException {
            try {
                new CBLApp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
