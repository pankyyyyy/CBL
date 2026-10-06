import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class GuessCheckerTest {
    @Test
    void allCorrect() {
        int[] expected = {2, 2, 2, 2, 2};
        assertArrayEquals(expected, GuessChecker.checkGuess("crane", "crane"));
    }

    @Test
    void noLettersMatch() {
        int[] expected = {0, 0, 0, 0, 0};
        assertArrayEquals(expected, GuessChecker.checkGuess("crane", "plump"));
    }

    @Test
    void mixedGreenAndGray() {
        int[] expected = {0, 0, 2, 0, 2};
        assertArrayEquals(expected, GuessChecker.checkGuess("crane", "slate"));
    }

    @Test
    void duplicateLetterOnlyMarkedOnce() {
        int[] expected = {0, 0, 1, 0, 1};
        assertArrayEquals(expected, GuessChecker.checkGuess("abide", "speed"));
    }
}
