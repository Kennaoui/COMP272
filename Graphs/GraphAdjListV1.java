import java.util.*;

class Vertex {
    String label;

    public Vertex(String label) {
        this.label = label;
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

class GraphAdjList implements Graph {
    List<Vertex> vertices;
    List<Edge> edges;

    // adjacency structure: for each vertex index, list of incident edges
    List<List<Edge>> adjacency;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        adjacency = new ArrayList<>();
    }
}
