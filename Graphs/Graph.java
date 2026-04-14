interface Graph {   // Graph ADT

    // ------------------------------------------------
    // Vertices and edges (storing elements)
    // ------------------------------------------------

    void init(int n);          // initialize graph with n vertices
    int vertexCount();         // number of vertices
    int edgeCount();           // number of edges


    // ------------------------------------------------
    // Accessor methods
    // ------------------------------------------------

    int[] endVertices(int e);          // return the two end vertices of edge e
    int opposite(int v, int e);        // return the vertex opposite v on edge e
    boolean areAdjacent(int v, int w); // true if v and w are adjacent

    Object replace(int v, Object x);   // replace element at vertex v with x
    Object replace(int e, Object x);   // replace element at edge e with x

    Object getVertexValue(int v);      // return element stored at vertex v
    Object getEdgeValue(int e);        // return element stored at edge e
    int weight(int v, int w);          // optional: return weight of edge (v, w)


    // ------------------------------------------------
    // Update methods
    // ------------------------------------------------

    void addVertex(Object o);              // insert a vertex storing element o
    void addEdge(int v, int w, Object o);  // insert edge (v, w) storing element o

    void removeVertex(int v);              // remove vertex v and its incident edges
    void removeEdge(int e);                // remove edge e


    // ------------------------------------------------
    // Iterable collection methods
    // ------------------------------------------------

    int[] incidentEdges(int v);   // all edges incident to v
    int[] vertices();             // all vertices in the graph
    int[] edges();                // all edges in the graph
}
