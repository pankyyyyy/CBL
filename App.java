import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the correct word:");
        String correctWord = scanner.next().toLowerCase();
        System.out.println("Start");
        String guess = scanner.next().toLowerCase();
        while (!guess.equals(correctWord)) {
            if (guess.length() != correctWord.length()) {
                System.out.println("Incorrect length. Please try again.");
                guess = scanner.next().toLowerCase();
                continue;
            }
            int[] status = GuessChecker.checkGuess(correctWord, guess);
            String result = "";
            for (int i = 0; i < status.length; i++) {
                if (status[i] == 2) {
                    result += guess.charAt(i);  
                } else if (status[i] == 1) {
                    result += "*";               
                } else {
                    result += "-";               
                }
            }
            System.out.println(result);

            guess = scanner.next().toLowerCase();
        }
        System.out.println("Congratulations! You guessed the word correctly.");
    }
}
