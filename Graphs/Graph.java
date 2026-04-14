public interface Graph {

    // ------------------------------------------------
    // Vertices and edges (storing elements)
    // ------------------------------------------------

    void init(int n);
    // Initialize the graph with n vertices

    int vertexCount();
    // Return the number of vertices in the graph

    int edgeCount();
    // Return the number of edges in the graph


    // ------------------------------------------------
    // Accessor methods
    // ------------------------------------------------

    int[] endVertices(int e);
    // Return the two endpoints (u, v) of edge e

    int opposite(int v, int e);
    // Given vertex v and edge e, return the other endpoint of e

    boolean areAdjacent(int v, int w);
    // Return true if vertices v and w are connected by an edge

    Object replaceVertex(int v, Object x);
    // Replace and return the element stored at vertex v

    Object replaceEdge(int e, Object x);
    // Replace and return the element stored at edge e

    Object getVertexValue(int v);
    // Return the element stored at vertex v

    Object getEdgeValue(int e);
    // Return the element stored at edge e


    // ------------------------------------------------
    // Update methods
    // ------------------------------------------------

    int insertVertex(Object o);
    // Insert a new vertex storing element o, return its index

    int insertEdge(int v, int w, Object o);
    // Insert a new edge (v, w) storing element o, return its ID

    void removeVertex(int v);
    // Remove vertex v and all its incident edges

    void removeEdge(int e);
    // Remove edge e from the graph


    // ------------------------------------------------
    // Iterable collection methods
    // ------------------------------------------------

    int[] incidentEdges(int v);
    // Return all edges incident to vertex v

    int[] vertices();
    // Return all vertex indices in the graph

    int[] edges();
    // Return all edge IDs in the graph
}
