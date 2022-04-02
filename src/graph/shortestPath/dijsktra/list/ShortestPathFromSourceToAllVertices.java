package graph.shortestPath.dijsktra.list;

import graph.AdjacencyList;

import java.util.*;

public class ShortestPathFromSourceToAllVertices extends AdjacencyList {
    Queue<int[]> edges;
    int [] shortestDistances;
    int [] visited;

    public void displayResult() {
        for (int i = 0; i < shortestDistances.length; i++)
            System.out.println(i + " -> " + shortestDistances[i]);
    }

    public void runDijsktra() {
        while (!edges.isEmpty()) {
            int [] edge = edges.poll();
            visited[edge[0]] = 1;
            for (int [] neighbour : adjList.get(edge[0])) {
                if (visited[neighbour[0]] == 0 &&
                        edge[1] + neighbour[1] < shortestDistances[neighbour[0]]) {
                    shortestDistances[neighbour[0]] = edge[1] + neighbour[1];
                    edges.offer(new int[]{neighbour[0], shortestDistances[neighbour[0]]});
                }
            }
        }
    }

    public void initializeVariables() {
        edges = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        shortestDistances = new int [adjList.size()];
        visited = new int [adjList.size()];
        for (int i = 0; i < shortestDistances.length; i++)
            shortestDistances[i] = 10000000;
    }

    public void generateShortestPaths(int source) {
        initializeVariables();
        shortestDistances[source] = 0;
        edges.offer(new int [] {source, 0});
        runDijsktra();
        displayResult();
    }

    @Override
    public void work() {
        adjList = new ArrayList<>();

        // 0th vertex;
        List<int[]> neighboursOf0 = Arrays.asList(
                new int [] {1,18},
                new int [] {4,13}
        );
        adjList.add(neighboursOf0);

        // 1st vertex;
        List<int[]> neighboursOf1 = Arrays.asList(
                new int [] {0,18},
                new int [] {2,10}
        );
        adjList.add(neighboursOf1);

        // 2nd vertex;
        List<int[]> neighboursOf2 = Arrays.asList(
                new int [] {1,10},
                new int [] {3,6},
                new int [] {5,1}
        );
        adjList.add(neighboursOf2);

        // 3rd vertex;
        List<int[]> neighboursOf3 = Arrays.asList(
                new int [] {2,6},
                new int [] {4,5}
        );
        adjList.add(neighboursOf3);

        // 4th vertex;
        List<int[]> neighboursOf4 = Arrays.asList(
                new int [] {3,5},
                new int [] {0,13}
        );
        adjList.add(neighboursOf4);

        // 5th vertex;
        List<int[]> neighboursOf5 = Arrays.asList(
                new int [] {2,1},
                new int [] {6,11},
                new int [] {7,2}
        );
        adjList.add(neighboursOf5);

        // 6th vertex;
        List<int[]> neighboursOf6 = Arrays.asList(
                new int [] {5,11},
                new int [] {7,1}
        );
        adjList.add(neighboursOf6);

        // 7th vertex;
        List<int[]> neighboursOf7 = Arrays.asList(
                new int [] {5,2},
                new int [] {6,1}
        );
        adjList.add(neighboursOf7);

        generateShortestPaths(0);
    }

    public static void main(String args []) {
        new ShortestPathFromSourceToAllVertices().work();
    }
}
