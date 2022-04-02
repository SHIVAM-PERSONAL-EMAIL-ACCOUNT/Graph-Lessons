package graph.topoSort.list;

import graph.AdjacencyList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class KahnTopologicalSort extends AdjacencyList {
    int [] res;
    int resPt;
    int [] indegrees;
    Deque<Integer> independentVertices;

    public void display() {
        for (int vertex : res)
            System.out.print(vertex + " ");
    }

    public void computeIndegree() {
        indegrees = new int [adjList.size()];
        for (List<int[]> neighbours : adjList)
            for (int [] neighbour : neighbours)
                indegrees[neighbour[0]]++;
    }

    public void sort() {
        computeIndegree();
        independentVertices = new ArrayDeque<>();
        for (int i = 0; i < indegrees.length; i++)
            if (indegrees[i] == 0)
                independentVertices.add(i);
        res = new int [adjList.size()];
        resPt = res.length - 1;
        while(!independentVertices.isEmpty()) {
            int vertex = independentVertices.poll();
            res[resPt--] = vertex;
            for (int [] neighbour : adjList.get(vertex)) {
                indegrees[neighbour[0]]--;
                if (indegrees[neighbour[0]] == 0) independentVertices.add(neighbour[0]);
            }
        }
        if (resPt >= 0) System.out.println("Cycle Detected");
        else display();
    }

    @Override
    public void work() {
        adjList = new ArrayList<>();

        List<int[]> neighbour1 = new ArrayList<>();
        neighbour1.add(new int [] {1,-1});
        adjList.add(neighbour1);

        List<int[]> neighbour2 = new ArrayList<>();
        neighbour2.add(new int [] {2,-1});
        neighbour2.add(new int [] {4,-1});
        adjList.add(neighbour2);

        List<int[]> neighbour3 = new ArrayList<>();
        neighbour3.add(new int [] {3,-1});
        adjList.add(neighbour3);

        List<int[]> neighbour4 = new ArrayList<>();
        adjList.add(neighbour4);

        List<int[]> neighbour5 = new ArrayList<>();
        neighbour5.add(new int [] {5,-1});
        adjList.add(neighbour5);

        List<int[]> neighbour6 = new ArrayList<>();
        neighbour6.add(new int [] {0,-1});
        adjList.add(neighbour6);

        sort();
    }

    public static void main(String args []) {
        new KahnTopologicalSort().work();
    }
}

