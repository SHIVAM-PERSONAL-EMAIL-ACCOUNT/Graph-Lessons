package graph.mst.list;

import graph.AdjacencyList;

import java.util.*;

public class PrimMinimumSpanningTreeLazyVersion extends AdjacencyList {
    Queue<int[]> pq;
    int [][] mstEdges;
    int visited [];

    public void displayMST() {
        for (int [] edge : mstEdges)
            System.out.println(edge[0] + " " + edge[1] + " " + edge[2]);
    }

    public void runPrim() {
        int i = 0;
        while (i < adjList.size() - 1) {
            int [] edge = pq.poll();
            mstEdges[i++] = edge;
            visited[edge[1]] = 1;
            for (int [] neighbour : adjList.get(edge[1]))
                if (visited[neighbour[0]] != 1)
                    pq.offer(new int [] {edge[1], neighbour[0], neighbour[1]});
        }
    }

    public void initializePriorityQueue() {
        visited[0] = 1;
        for (int [] neighbour : adjList.get(0))
            pq.offer(new int [] {0, neighbour[0], neighbour[1]});
    }

    public void generateMST() {
        pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        mstEdges = new int [adjList.size() - 1][3];
        visited = new int [adjList.size()];
        initializePriorityQueue();
        runPrim();
        displayMST();
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

        generateMST();
    }

    public static void main(String args []) {
        new PrimMinimumSpanningTreeLazyVersion().work();
    }
}
