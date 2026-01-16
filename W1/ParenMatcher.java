import java.util.EmptyStackException;

public class ParenMatcher {

    // Returns true iff all grouping symbols in X match correctly.
    // Tokens that are not grouping symbols are ignored.
    public static boolean parenMatch(String[] X, int n) {
        ArrayStack<Character> S = new ArrayStack<>(n); // worst case: all tokens are openings

        for (int i = 0; i < n; i++) {
            if (X[i] == null || X[i].isEmpty()) continue;

            char c = X[i].charAt(0);

            if (isOpening(c)) {
                S.push(c);
            } else if (isClosing(c)) {
                if (S.isEmpty()) {
                    return false; // nothing to match with
                }

                char open;
                try {
                    open = S.pop();
                } catch (EmptyStackException e) {
                    return false; // defensive (shouldn't happen given isEmpty() check)
                }

                if (!matches(open, c)) {
                    return false; // wrong type
                }
            }
            // else: ignore non-grouping tokens (variables, operators, numbers, etc.)
        }

        return S.isEmpty(); // true iff every opening symbol was matched
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
