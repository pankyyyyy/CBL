import java.util.Scanner;
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the correct word:");
        String correctWord = scanner.nextLine();
        String[] letters = new String[correctWord.length()];
        for(int i = 0; i < correctWord.length(); i++) {
            String c = String.valueOf(correctWord.charAt(i));
            letters[i] = c;
        }
        System.out.println("Correct word: " + correctWord);
        System.out.println("Start");
        String guess = scanner.next();
        while (!guess.equals(correctWord)) {
            if (guess.length() != correctWord.length()) {
                System.out.println("Incorrect length. Please try again.");
                guess = scanner.next();
                continue;
            }
            String[] characters = new String[guess.length()];
            for (int i = 0; i < guess.length(); i++) {
                String c = String.valueOf(guess.charAt(i));
                characters[i] = c;
            }
            String result = "";
            for (int i = 0; i < correctWord.length(); i++) {
                if (letters[i].equals(characters[i])) {
                    result += characters[i];      
                    continue;                    
                }
                boolean found = false;
                for (int j = 0; j < correctWord.length(); j++) {
                    if (letters[j].equals(characters[i])) {
                        found = true;             
                        break;                    
                    }
                }
                if (found) {
                    result += "*";
                    found = false;
                } else {
                    result += "-";               
                }
            }
            System.out.println(result);
            guess = scanner.next();
        }
        System.out.println("Congratulations! You guessed the word correctly.");

    }
}