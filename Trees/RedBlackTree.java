public class RedBlackTree {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private static class Node {
        int key;
        Node left, right, parent;
        boolean color;

        Node(int key, boolean color, Node parent) {
            this.key = key;
            this.color = color;
            this.parent = parent;
        }
    }

    public void add(int key) {
        Node inserted = bstInsert(key);
        if (inserted != null)
            fixAfterInsert(inserted);
    }

    /*
     * RB insertion fix (loop + helper)
      */
    private void fixAfterInsert(Node x) {
        while (x != root && colorOf(parentOf(x)) == RED) {
            Node p = parentOf(x); // parent
            Node g = parentOf(p); // grand-parent

            // If parent is red and not root, grandparent must exist
            if (g == null) break;   // <-- minimal fix: avoid infinite loop

            // If parent is on a left child
            if (p == leftOf(g)) {
                Node u = rightOf(g); // uncle
                x = repairStepLeftSide(x, p, g, u);
            }
            // If parent is on a right child
            else {
                Node u = leftOf(g); // uncle
                x = repairStepRightSide(x, p, g, u);
            }
        }
        setColor(root, BLACK);
    }

    /**
     * Parent p is LEFT child of grandparent g.
     * Handles ONE repair step and returns the next x to continue from.
     */
    private Node repairStepLeftSide(Node x, Node p, Node g, Node u) {
        // Case 1A: uncle is RED -> recolor and "bubble up" to g
        if (colorOf(u) == RED) {
            redUncleColoringRepair(p, g, u);
            return g; // continue fixing from grandparent
        }

        // Uncle is BLACK:
        // Case 2A: x is a right child (Triangle case) -> rotate p left
        if (x == rightOf(p)) {
            x = rotateLeft(p);
        }

        // Recompute parent after potential rotation
        p = parentOf(x);
        g = parentOf(p);

        // Case 3A: Line case -> rotate g right, recolor
        Node oldG = g;
        x = rotateRight(oldG);
        blackUncleColoringRepair(x, oldG);

        return x;
    }

    /**
     * Parent p is RIGHT child of grandparent g. (Mirror of left side.)
     */
    private Node repairStepRightSide(Node x, Node p, Node g, Node u) {
        // Case 1B: uncle is RED -> recolor and "bubble up" to g
        if (colorOf(u) == RED) {
            redUncleColoringRepair(p, g, u);
            return g;
        }

        // Uncle is BLACK:
        // Case 2B: x is a left child (Triangle case) -> rotate p right
        if (x == leftOf(p)) {
            x = rotateRight(p);
        }

        // Recompute parent after potential rotation
        p = parentOf(x);
        g = parentOf(p);

        // Case 3B: Line case -> rotate g left, recolor
        Node oldG = g;
        x = rotateLeft(oldG);
        blackUncleColoringRepair(x, oldG);

        return x;
    }

    // -----------------------
    // BST insert
    // -----------------------

    private Node bstInsert(int key) {
        if (root == null) {
            root = new Node(key, BLACK, null);
            return root;
        }

        Node cur = root, parent = null;
        while (cur != null) {
            parent = cur;
            if (key < cur.key) cur = cur.left;
            else if (key > cur.key) cur = cur.right;
            else return null; // ignore duplicates
        }

        Node n = new Node(key, RED, parent);
        if (key < parent.key) parent.left = n;
        else parent.right = n;
        return n;
    }

    // -----------------------
    // Coloring
    // -----------------------

    private void redUncleColoringRepair(Node p, Node g, Node u) {
        // Parent and Uncle are set to Black and Grandparent becomes RED.
        setColor(p, BLACK);
        setColor(u, BLACK);
        setColor(g, RED);
    }

    
    private void blackUncleColoringRepair(Node newTop, Node oldG) {
        // new subtree root becomes BLACK; old grandparent becomes RED.
        setColor(newTop, BLACK);
        setColor(oldG, RED);
    }

    // -----------------------
    // Rotations
    // -----------------------

    private Node rotateLeft(Node x) {
        return x;
    }

    private Node rotateRight(Node x) {
        return x;
    }

    // -----------------------
    // Helpers
    // -----------------------

    private static Node parentOf(Node n) { return n == null ? null : n.parent; }
    private static Node leftOf(Node n)   { return n == null ? null : n.left; }
    private static Node rightOf(Node n)  { return n == null ? null : n.right; }

    private static boolean colorOf(Node n) { return n == null ? BLACK : n.color; }
    private static void setColor(Node n, boolean c) { if (n != null) n.color = c; }
}
