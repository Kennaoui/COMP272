package ds.stack;
import java.util.EmptyStackException;

public class ParenMatcher {

    // Returns true iff all grouping symbols in X match correctly.
    // Tokens that are not grouping symbols are ignored.
   public static boolean parenMatch(String[] X) {
        int n = X.length;
        ArrayStack<Character> S = new ArrayStack<>(n);
        boolean valid = true;
        int i = 0;

        while (i < n && valid) {
            if (X[i] != null && !X[i].isEmpty()) {
                char c = X[i].charAt(0);
    
                if (isOpening(c)) {
                    S.push(c);
                } 
                else if (isClosing(c)) {
                    if (S.isEmpty()) {
                        valid = false; // nothing to match with
                    } 
                    else {
                        char open = S.pop(); // safe now
                        if (!matches(open, c)) {
                            valid = false; // wrong type
                        }
                    }
                }
                // else: ignore non-grouping tokens
            }
            i++;
        }

        return valid && S.isEmpty();
    }

    private static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }
}
