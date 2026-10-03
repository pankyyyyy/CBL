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
            }
            int[] status = checkGuess(correctWord, guess);
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

    static int[] checkGuess(String correctWord, String guess) {
        int[] status = new int[correctWord.length()];
        boolean[] used = new boolean[correctWord.length()];
        for (int i = 0; i < correctWord.length(); i++) {
            if (guess.charAt(i) == correctWord.charAt(i)) {
                status[i] = 2;
                used[i] = true;
            }
        }
        for (int i = 0; i < correctWord.length(); i++) {
            if (status[i] == 2) {
                continue;
            }
            for (int j = 0; j < correctWord.length(); j++) {
                if (!used[j] && guess.charAt(i) == correctWord.charAt(j)) {
                    status[i] = 1;
                    used[j] = true;
                    break;
                }
            }
        }
        return status;
    }
}