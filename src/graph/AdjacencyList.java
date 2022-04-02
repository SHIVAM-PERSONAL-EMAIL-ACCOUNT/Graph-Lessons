package graph;

import java.util.List;

public abstract class AdjacencyList implements Graph {
    public List<List<int[]>> adjList;
    public List<int[]> adjListOfEdges;
    public abstract void work();
}
