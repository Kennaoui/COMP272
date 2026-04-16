import java.util.*;

class Vertex {
    String label;
    int index;

    public Vertex(String label, int index) {
        this.label = label;
        this.index = index;
    }
}

class Edge {
    String label;
    Vertex from; 
    Vertex to; 
    //int weight;

    public Edge(String label; Vertex from, Vertex to) {
        this.label = label;
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

    //************************************************************************************
    //************************************************************************************
    
    public Vertex addVertex(String label) {
        Vertex v = new Vertex(label, vertices.size());
        vertices.add(v);
        incidence.add(new ArrayList<>());
        return v;
    }

    public Edge addEdge(Vertex u, Vertex v, String label) {
        Edge e = new Edge(label, u, v);
        edges.add(e);
        incidence.get(u.index).add(e);
        incidence.get(v.index).add(e); // undirected
        return e;
    }
    
    //*************************************************************************
    
    public void removeEdge(Edge e) {
        edges.remove(e);
    
        Vertex u = e.from;
        Vertex v = e.to;
    
        incidence.get(u.index).remove(e);
        incidence.get(v.index).remove(e);
    }

    public void removeVertex(Vertex v) {
        // 1. Remove all incident edges
        List<Edge> incidentEdges = new ArrayList<>(incidence.get(v.index));
        for (Edge e : incidentEdges) {
            removeEdge(e);
        }
    
        // 2. Remove vertex and its adjacency list
        int idx = v.index;
        vertices.remove(idx);
        incidence.remove(idx);
    
        // 3. Update indices of remaining vertices
        for (int i = idx; i < vertices.size(); i++) {
            vertices.get(i).index = i;
        }
    }
    
    //********************************************************************************************
    //********************************************************************************************

    public boolean areAdjacent(Vertex v, Vertex w) {
        // look only at v's incidence list
        for (Edge e : incidence.get(v.index)) {
            Vertex other = (e.u == v) ? e.v : e.u;
            if (other == w) {
                return true;
            }
        }
        return false;
    }
    
    public Iterable<Edge> incidentEdges(Vertex v) {
        // Return the stored list directly (could wrap in Collections.unmodifiableList)
        return incidence.get(v.index);
    }
}
