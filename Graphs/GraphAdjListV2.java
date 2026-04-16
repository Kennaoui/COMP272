import java.util.*;

class Vertex {
    String label;
    List<Edge> adjacentEdges;

    public Vertex(String label) {
        this.label = label;
        this.adjacentEdges = new ArrayList<>();
    }
}

class Edge {
    Vertex from;
    Vertex to;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }
}

class GraphAdjList{
    List<Vertex> vertices;
    List<Edge> edges; // optional, but often kept

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
