public class GuessChecker {
    public static int[] checkGuess(String correctWord, String guess){
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
