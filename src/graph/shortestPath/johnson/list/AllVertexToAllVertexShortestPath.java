package graph.shortestPath.johnson.list;

import graph.AdjacencyList;

import java.util.*;

public class AllVertexToAllVertexShortestPath extends AdjacencyList {
    int dummySource;
    int [] shortestDistanceFromDummySource;
    boolean hasCycle;

    int [][] asps;

    Queue<int[]> edges;
    int [] visited;

    public void displayResult() {
        for (int [] row : asps) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public void revertEdgeChanges() {
        for (int i = 0; i < adjList.size(); i++)
            for (int [] neighbours : adjList.get(i))
                neighbours[1] -= shortestDistanceFromDummySource[i] - shortestDistanceFromDummySource[neighbours[0]];
    }

    public void initializeVariables() {
        edges = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        visited = new int [adjList.size()];
    }

    public void runDijsktra(int source) {
        initializeVariables();
        edges.offer(new int [] {source, 0});
        while (!edges.isEmpty()) {
            int [] edge = edges.poll();
            visited[edge[0]] = 1;
            for (int [] neighbour : adjList.get(edge[0])) {
                if (visited[neighbour[0]] == 0 &&
                        edge[1] + neighbour[1] < asps[source][neighbour[0]]) {
                    asps[source][neighbour[0]] = edge[1] + neighbour[1];
                    edges.offer(new int[]{neighbour[0], asps[source][neighbour[0]]});
                }
            }
        }
    }

    public void initializeAspsMatrix() {
        asps = new int [adjList.size()][adjList.size()];
        for (int i = 0; i < asps.length; i++)
            for (int j = 0; j < asps.length; j++)
                asps[i][j] = i == j ? 0 : 10000000;
    }

    public void getAsps() {
        initializeAspsMatrix();
        for (int i = 0; i < adjList.size(); i++)
            runDijsktra(i);
    }

    public void removeDummyNode() {
        adjList.remove(adjList.size() - 1);
    }

    public void detectCycle() {
        outer: for (int i = 0; i < adjList.size(); i++)
            for (int [] edge : adjListOfEdges)
                if (edge[2] + shortestDistanceFromDummySource[edge[0]] < shortestDistanceFromDummySource[edge[1]]) {
                    hasCycle = true;
                    break outer;
                }
    }

    public void runBellmanFord() {
        for (int i = 0; i < adjList.size(); i++)
            for (int [] edge : adjListOfEdges)
                if (edge[2] + shortestDistanceFromDummySource[edge[0]] < shortestDistanceFromDummySource[edge[1]])
                    shortestDistanceFromDummySource[edge[1]] = edge[2] + shortestDistanceFromDummySource[edge[0]];
    }

    public void initializeShortestDistanceVariables() {
        dummySource = adjList.size() - 1;
        shortestDistanceFromDummySource = new int [adjList.size()];
        for (int i = 0; i < shortestDistanceFromDummySource.length; i++)
            shortestDistanceFromDummySource[i] = 10000000;
        shortestDistanceFromDummySource[dummySource] = 0;
    }

    public void initializeEdgeList() {
        adjListOfEdges = new ArrayList<>();
        for (int i = 0; i < adjList.size(); i++)
            for (int [] neighbour : adjList.get(i))
                adjListOfEdges.add(new int [] {i, neighbour[0], neighbour[1]});
    }

    public void getShortestDistances() {
        initializeEdgeList();
        initializeShortestDistanceVariables();
        runBellmanFord();
        detectCycle();
    }

    public void addDummyVertex() {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < adjList.size(); i++)
            neighbours.add(new int [] {i, 0});
        adjList.add(neighbours);
    }

    public void readjustEdges() {
        addDummyVertex();
        getShortestDistances();
        if (hasCycle) {
            System.out.println("Cycle was detected");
            return;
        }
        for (int i = 0; i < adjList.size() - 1; i++)
            for (int [] neighbours : adjList.get(i))
                neighbours[1] += shortestDistanceFromDummySource[i] - shortestDistanceFromDummySource[neighbours[0]];
        removeDummyNode();
    }

    public void generateShortestPaths() {
        readjustEdges();
        getAsps();
        revertEdgeChanges();
        displayResult();
    }

    @Override
    public void work() {
        adjList = new ArrayList<>();

        // Vertex 0
        List<int[]> neighbour0 = Arrays.asList(
                new int [] {1,-5},
                new int [] {2,2},
                new int [] {3,3}
        );
        adjList.add(neighbour0);

        // Vertex 1
        List<int[]> neighbour1 = Arrays.asList(
                new int [] {2,4}
        );
        adjList.add(neighbour1);

        // Vertex 2
        List<int[]> neighbour2 = Arrays.asList(
                new int [] {3,1}
        );
        adjList.add(neighbour2);

        // Vertex 3
        List<int[]> neighbour3 = Arrays.asList();
        adjList.add(neighbour3);

        generateShortestPaths();
    }

    public static void main(String args []) {
        new AllVertexToAllVertexShortestPath().work();
    }
}
