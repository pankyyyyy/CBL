import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class WordleGameTest {
    @Test
    void attemptsLeftAtStart() {
        WordleGame game = new WordleGame("crane");
        int expected = 6;
        assertEquals(expected, game.getAttemptsLeft());
    }

    @Test
    void decreaseAttemptsLeft() {
        WordleGame game = new WordleGame("crane");
        game.submitGuess("plane");
        int expected = 5;
        assertEquals(expected, game.getAttemptsLeft());
    }

    @Test
    void dontCountInvalidGuesses() {
        WordleGame game = new WordleGame("plane");
        game.submitGuess("rock");
        int expected = 6;
        assertEquals(expected, game.getAttemptsLeft());
    }

    @Test
    void gameWon() {
        WordleGame game = new WordleGame("plane");
        game.submitGuess("plane");
        assertEquals(GameState.WON, game.getState());
    }

    @Test
    void gameLost() {
        WordleGame game = new WordleGame("plane");
        for (int i = 0; i < 6; i++) {
            game.submitGuess("audio");
        }
        assertEquals(GameState.LOST, game.getState());
    }
}
