package graph.topoSort.list;

import graph.AdjacencyList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TopologicalSort extends AdjacencyList {
    int [] res;
    int resPt;
    int [] visited;
    Deque<Integer> stack;

    public void display() {
        for (int vertex : res)
            System.out.print(vertex + " ");
    }

    public void traverse(int vertex) {
        visited[vertex] = 1;
        List<int[]> neighbours = adjList.get(vertex);
        for (int [] neighbour : neighbours)
            if (visited[neighbour[0]] == 0)
                traverse(neighbour[0]);
        stack.push(vertex);
    }

    public void sort() {
        res = new int [adjList.size()];
        resPt = 0;
        visited = new int [adjList.size()];
        for (int i = 0; i < adjList.size(); i++) {
            stack = new ArrayDeque<>();
            if (visited[i] == 0) traverse(i);
            while(!stack.isEmpty()) res[resPt++] = stack.pop();
        }
        display();
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
        adjList.add(neighbour6);

        sort();
    }

    public static void main(String args []) {
        new TopologicalSort().work();

    }
}
