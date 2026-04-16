import java.util.*;

class Vertex {
    String label;
    int index;
    String status;   // for DFS: UNEXPLORED / VISITED

    public Vertex(String label, int index) {
        this.label = label;
        this.index = index;
        this.status = "UNEXPLORED";
    }
}

class Edge {
    String label;
    Vertex from;
    Vertex to;
    String status;   // for DFS: UNEXPLORED / DISCOVERY / BACK

    public Edge(String label, Vertex from, Vertex to) {
        this.label = label;
        this.from = from;
        this.to = to;
        this.status = "UNEXPLORED";
    }
}

class GraphAdjList {
    List<Vertex> vertices;
    List<Edge> edges;

    // for each vertex index, list of incident edges
    List<List<Edge>> incidence;

    public GraphAdjList() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        incidence = new ArrayList<>();
    }

    //****************************************************************

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
        incidence.get(v.index).add(e);   // undirected
        return e;
    }

    //****************************************************************

    public void removeEdge(Edge e) {
        edges.remove(e);

        Vertex u = e.from;
        Vertex v = e.to;

        incidence.get(u.index).remove(e);
        incidence.get(v.index).remove(e);
    }

    public void removeVertex(Vertex v) {
        List<Edge> incidentEdges = new ArrayList<>(incidence.get(v.index));
        for (Edge e : incidentEdges) {
            removeEdge(e);
        }

        int idx = v.index;
        vertices.remove(idx);
        incidence.remove(idx);

        for (int i = idx; i < vertices.size(); i++) {
            vertices.get(i).index = i;
        }
    }

    //****************************************************************

    public boolean areAdjacent(Vertex v, Vertex w) {
        for (Edge e : incidence.get(v.index)) {
            Vertex other = opposite(v, e);
            if (other == w) {
                return true;
            }
        }
        return false;
    }

    public Iterable<Edge> incidentEdges(Vertex v) {
        return incidence.get(v.index);
    }

    public Vertex opposite(Vertex v, Edge e) {
        if (e.from == v) {
            return e.to;
        } else {
            return e.from;
        }
    }

    //****************************************************************
    // DFS LABEL INITIALIZATION
    //****************************************************************

    public void resetStatus() {
        for (Vertex v : vertices) {
            v.status = "UNEXPLORED";
        }
        for (Edge e : edges) {
            e.status = "UNEXPLORED";
        }
    }

    //****************************************************************
    // RECURSIVE DFS — whole graph
    //****************************************************************

    public void dfsRecursive() {
        resetStatus();

        for (Vertex v : vertices) {
            if (v.status.equals("UNEXPLORED")) {
                dfsRecursive(v);
            }
        }
    }

    // recursive DFS from one start vertex
    public void dfsRecursive(Vertex v) {
        v.status = "VISITED";

        for (Edge e : incidence.get(v.index)) {
            if (e.status.equals("UNEXPLORED")) {
                Vertex w = opposite(v, e);

                if (w.status.equals("UNEXPLORED")) {
                    e.status = "DISCOVERY";
                    dfsRecursive(w);
                } else {
                    e.status = "BACK";
                }
            }
        }
    }

    //****************************************************************
    // ITERATIVE DFS — whole graph
    //****************************************************************

    public void dfsIterative() {
        resetStatus();

        for (Vertex v : vertices) {
            if (v.status.equals("UNEXPLORED")) {
                dfsIterative(v);
            }
        }
    }

    // iterative DFS from one start vertex
    public void dfsIterative(Vertex start) {
        Stack<Vertex> stack = new Stack<>();
        
        start.status = "VISITED";
        stack.push(start);

        while (!stack.isEmpty()) {
            Vertex v = stack.pop();

            for (Edge e : incidence.get(v.index)) {
                if (e.status.equals("UNEXPLORED")) {
                    Vertex w = opposite(v, e);

                    if (w.status.equals("UNEXPLORED")) {
                        e.status = "DISCOVERY";
                        w.status = "VISITED";
                        stack.push(w);
                    } else {
                        e.status = "BACK";
                    }
                }
            }
        }
    }
}
