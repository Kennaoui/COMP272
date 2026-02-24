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
            this.left = null;
            this.right = null;
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
            if (g == null) break;  

            //Compute Uncle of x
            Node u = (p == leftOf(g)) ? rightOf(g) : leftOf(g);

            
            // Case 1: uncle is RED -> recolor and "bubble up" to g
            if (colorOf(u) == RED) {
                redUncleColoringRepair(p, g, u);
                x = g; // continue fixing from grandparent
            }

            
            // Case 2: uncle is BLACK -> rotations + recolor
            else {
                // Parent is a left child
                if (p == leftOf(g)) {

                    // Case 2B: triangle -> rotate parent left
                    if (x == rightOf(p)) {
                        rotateLeft(p);
                        // after rotation, x and p shift
                        x = leftOf(parentOf(x)); // keep x pointing at the former parent side
                        p = parentOf(x);
                    }

                    // Case 2A: line -> rotate grandparent right
                    rotateRight(g);
                    // After rotation, former parent becomes top of that subtree
                    Node newTop = parentOf(g);
                    blackUncleColoringRepair(newTop, g);

                    // Once we fix Case 2, we're done at this level
                    break;
                }
                // Parent is a right child
                else {

                    // Case 2B: triangle -> rotate parent right
                    if (x == leftOf(p)) {
                        rotateRight(p);
                        x = rightOf(parentOf(x));
                        p = parentOf(x);
                    }

                    // Case 2A: line -> rotate grandparent left
                    rotateLeft(g);
                    Node newTop = parentOf(g);
                    blackUncleColoringRepair(newTop, g);

                    break;
                }
            }
                
            
            }
        setColor(root, BLACK);
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
        if (key < parent.key) 
            parent.left = n;
        else 
            parent.right = n;
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
        return x; //Placeholder so code compiles
    }

    private Node rotateRight(Node x) {
        return x; //Placeholder so code compiles
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
