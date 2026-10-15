import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordleGame {
    private GameState state = GameState.IN_PROGRESS;
    private int attemptsLeft = 6;
    private String correctWord; 
    private int attempts = 0;
    public static Set<String> guesses = new HashSet<>();
    public static List<String> answers = new ArrayList<>();
    public WordleGame(String correctWord) {
        this.correctWord = correctWord;
    }

    public int[] submitGuess(String guess) throws IOException {
        if (state == GameState.IN_PROGRESS) {
            if (isValidGuess(guess)) {
                int[] status = GuessChecker.checkGuess(correctWord, guess);
                attemptsLeft--;
                if (correctWord.equals(guess)) {
                    state = GameState.WON;
                } else if (attemptsLeft == 0) {
                    state = GameState.LOST;
                }
                attempts++;
                return status;
            }
        }   
        return null;
    }
    public boolean check(String guess) throws IOException {
        loadGuesses("words.txt");
        if (guesses.contains(guess)) {
            return true;
        }
        return false;
    }

    public boolean isValidGuess(String guess) throws IOException {
        if (guess.length() == correctWord.length() && isAllLetters(guess) && check(guess)) {
            return true;
        }
        return false;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public GameState getState() {
        return state;
    }

    public int NumberOfAttempts() {
        return attempts;
    } 

    public boolean isAllLetters(String guess) {
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (c < 'a' || c > 'z') {
                return false;
            }
        }
        return true;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public static void load(String answer) throws IOException {
        try {
        for (String line : Files.readAllLines(Path.of(answer))) {
            if (!line.isEmpty()) {
                answers.add(line);
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadGuesses(String guess) throws IOException {
        try {
            for (String line : Files.readAllLines(Path.of(guess))) {
              if (!line.isEmpty()) {
                guesses.add(line);
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
