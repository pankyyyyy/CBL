public class WordleGame {
    private GameState state = GameState.IN_PROGRESS;
    private int attemptsLeft = 6;
    private String correctWord; 
    private int attempts = 0;

    public WordleGame(String correctWord) {
        this.correctWord = correctWord;
    }

    public int[] submitGuess(String guess) {
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

    public boolean isValidGuess(String guess) {
        if (guess.length() == correctWord.length()) {
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
}
