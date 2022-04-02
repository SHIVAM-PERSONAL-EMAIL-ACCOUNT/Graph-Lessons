package graph.shortestPath.bellmanFord.list;

import graph.AdjacencyList;

import java.util.ArrayList;

public class SourceToAllShortestPath extends AdjacencyList {
    int [] shortestDistances;

    public void displayResult() {
        for (int i = 0; i < shortestDistances.length; i++)
            System.out.println(i + " -> " + shortestDistances[i]);
    }

    public void detectNegativeCycle(int vertices) {
        for (int i = 0; i < vertices; i++)
            for (int [] edge : adjListOfEdges)
                if (edge[2] + shortestDistances[edge[0]] < shortestDistances[edge[1]])
                    shortestDistances[edge[1]] = -10000000;

    }

    public void runBellmanFord(int vertices) {
        for (int i = 0; i < vertices; i++)
            for (int [] edge : adjListOfEdges)
                if (edge[2] + shortestDistances[edge[0]] < shortestDistances[edge[1]])
                    shortestDistances[edge[1]] = shortestDistances[edge[0]] + edge[2];

    }

    public void initializeVariables(int vertices) {
        shortestDistances = new int [vertices];
        for (int i = 0; i < vertices; i++)
            shortestDistances[i] = 10000000;
    }

    public void generateShortestPaths(int vertices, int source) {
        initializeVariables(vertices);
        shortestDistances[source] = 0;
        runBellmanFord(vertices);
        detectNegativeCycle(vertices);
        displayResult();
    }

    @Override
    public void work() {
        adjListOfEdges = new ArrayList<>();
        adjListOfEdges.add(new int [] {0,1,6});
        adjListOfEdges.add(new int [] {0,2,1});
        adjListOfEdges.add(new int [] {2,1,4});
        adjListOfEdges.add(new int [] {1,3,8});
        adjListOfEdges.add(new int [] {3,2,-16});

        generateShortestPaths(4, 0);
    }

    public static void main(String args []) {
        new SourceToAllShortestPath().work();
    }
}
