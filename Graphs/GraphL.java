public class GraphAdjList implements Graph {

    private class Node {
        int vertex;
        int edgeID;
        Node prev, next;

        Node(int v, int e, Node p, Node n) {
            vertex = v;
            edgeID = e;
            prev = p;
            next = n;
        }
    }

    private class Edge {
        int u, v;
        Object value;

        Edge(int u, int v, Object value) {
            this.u = u;
            this.v = v;
            this.value = value;
        }
    }

    private Node[] nodeArray;
    private Object[] nodeValues;
    private Edge[] edgeArray;
    private int numEdge;

    GraphAdjList() {}

    public void init(int n) {
        nodeArray = new Node[n];
        for (int i = 0; i < n; i++) {
            nodeArray[i] = new Node(-1, -1, null, null); // header
        }
        nodeValues = new Object[n];
        edgeArray = new Edge[0];
        numEdge = 0;
    }

    public int vertexCount() {
        return nodeArray.length;
    }

    public int edgeCount() {
        return numEdge;
    }

    public boolean areAdjacent(int v, int w) {
        Node curr = nodeArray[v].next;
        while (curr != null) {
            if (curr.vertex == w) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public int insertVertex(Object o) {
        int n = nodeArray.length;

        Node[] newNodeArray = new Node[n + 1];
        Object[] newNodeValues = new Object[n + 1];

        for (int i = 0; i < n; i++) {
            newNodeArray[i] = nodeArray[i];
            newNodeValues[i] = nodeValues[i];
        }

        newNodeArray[n] = new Node(-1, -1, null, null);
        newNodeValues[n] = o;

        nodeArray = newNodeArray;
        nodeValues = newNodeValues;

        return n;
    }

    public int insertEdge(int v, int w, Object o) {
        if (v == w) {
            throw new IllegalArgumentException("No self-loops allowed.");
        }
        if (areAdjacent(v, w)) {
            throw new IllegalArgumentException("No parallel edges allowed.");
        }

        int e = numEdge;

        Edge[] newEdgeArray = new Edge[numEdge + 1];
        for (int i = 0; i < numEdge; i++) {
            newEdgeArray[i] = edgeArray[i];
        }
        newEdgeArray[numEdge] = new Edge(v, w, o);
        edgeArray = newEdgeArray;

        addToList(v, w, e);
        addToList(w, v, e);

        numEdge++;
        return e;
    }

    public void removeVertex(int v) {
        int[] inc = incidentEdges(v);

        while (inc.length > 0) {
            removeEdge(inc[0]);
            inc = incidentEdges(v);
        }

        int n = nodeArray.length;

        Node[] newNodeArray = new Node[n - 1];
        Object[] newNodeValues = new Object[n - 1];

        for (int i = 0; i < v; i++) {
            newNodeArray[i] = nodeArray[i];
            newNodeValues[i] = nodeValues[i];
        }
        for (int i = v + 1; i < n; i++) {
            newNodeArray[i - 1] = nodeArray[i];
            newNodeValues[i - 1] = nodeValues[i];
        }

        nodeArray = newNodeArray;
        nodeValues = newNodeValues;

        adjustVertexIndicesAfterRemoval(v);
    }

    public void removeEdge(int e) {
        int u = edgeArray[e].u;
        int v = edgeArray[e].v;

        removeFromList(u, e);
        removeFromList(v, e);

        Edge[] newEdgeArray = new Edge[numEdge - 1];
        for (int i = 0; i < e; i++) {
            newEdgeArray[i] = edgeArray[i];
        }
        for (int i = e + 1; i < numEdge; i++) {
            newEdgeArray[i - 1] = edgeArray[i];
        }

        edgeArray = newEdgeArray;
        numEdge--;

        adjustEdgeIndicesAfterRemoval(e);
    }

    public int[] incidentEdges(int v) {
        int cnt = 0;
        Node curr;

        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            cnt++;
        }

        int[] temp = new int[cnt];
        cnt = 0;
        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            temp[cnt++] = curr.edgeID;
        }

        return temp;
    }

    private void addToList(int u, int v, int e) {
        Node curr = nodeArray[u];
        while (curr.next != null && curr.next.vertex < v) {
            curr = curr.next;
        }
        curr.next = new Node(v, e, curr, curr.next);
        if (curr.next.next != null) {
            curr.next.next.prev = curr.next;
        }
    }

    private void removeFromList(int u, int e) {
        Node curr = nodeArray[u].next;
        while (curr != null) {
            if (curr.edgeID == e) {
                curr.prev.next = curr.next;
                if (curr.next != null) {
                    curr.next.prev = curr.prev;
                }
                return;
            }
            curr = curr.next;
        }
    }

    private void adjustEdgeIndicesAfterRemoval(int removedEdge) {
        for (int i = 0; i < nodeArray.length; i++) {
            Node curr = nodeArray[i].next;
            while (curr != null) {
                if (curr.edgeID > removedEdge) {
                    curr.edgeID--;
                }
                curr = curr.next;
            }
        }
    }

    private void adjustVertexIndicesAfterRemoval(int removedVertex) {
        for (int i = 0; i < nodeArray.length; i++) {
            Node curr = nodeArray[i].next;
            while (curr != null) {
                if (curr.vertex > removedVertex) {
                    curr.vertex--;
                }
                curr = curr.next;
            }
        }

        for (int e = 0; e < numEdge; e++) {
            if (edgeArray[e].u > removedVertex) {
                edgeArray[e].u--;
            }
            if (edgeArray[e].v > removedVertex) {
                edgeArray[e].v--;
            }
        }
    }

    // Not implemented (not needed for this slide)
    public int[] endVertices(int e) { throw new UnsupportedOperationException(); }
    public int opposite(int v, int e) { throw new UnsupportedOperationException(); }
    public Object replaceVertex(int v, Object x) { throw new UnsupportedOperationException(); }
    public Object replaceEdge(int e, Object x) { throw new UnsupportedOperationException(); }
    public Object getVertexValue(int v) { throw new UnsupportedOperationException(); }
    public Object getEdgeValue(int e) { throw new UnsupportedOperationException(); }
    public int[] vertices() { throw new UnsupportedOperationException(); }
    public int[] edges() { throw new UnsupportedOperationException(); }
}
