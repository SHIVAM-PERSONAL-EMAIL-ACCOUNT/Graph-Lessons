package graph.topoSort.list;

import graph.AdjacencyList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AllTopologicalSorts extends AdjacencyList {
    List<int[]> combinations;
    Deque<Integer> sortOrder;
    int [] visited;
    int [] indegrees;
    int vertices;

    public void display() {
        for (int [] combination : combinations) {
            for (int i = combination.length - 1; i >= 0; i--) {
                System.out.print(combination[i] + " ");
            }
            System.out.println();
        }
    }

    public void saveOrder() {
        int [] order = new int [vertices];
        int i = 0;
        for (int vertex : sortOrder)
            order[i++] = vertex;
        combinations.add(order);
    }

    public void computeCombinations() {
        boolean hasAllVertices = true;
        for (int i = 0; i < vertices; i++) {
            if (visited[i] == 0 && indegrees[i] == 0) {
                visited[i] = 1;
                sortOrder.push(i);
                for (int [] neighbour : adjList.get(i))
                    indegrees[neighbour[0]]--;
                computeCombinations();
                visited[i] = 0;
                sortOrder.pop();
                for (int [] neighbour : adjList.get(i))
                    indegrees[neighbour[0]]++;
                hasAllVertices = false;
            }
        }
        if (hasAllVertices)
            saveOrder();
    }

    public void computeIndegree() {
        indegrees = new int [adjList.size()];
        for (List<int[]> neighbours : adjList)
            for (int [] neighbour : neighbours)
                indegrees[neighbour[0]]++;
    }

    public void sort() {
        vertices = adjList.size();
        indegrees = new int [vertices];
        visited = new int [vertices];
        combinations = new ArrayList<>();
        sortOrder = new ArrayDeque<>();
        computeIndegree();
        computeCombinations();
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
        neighbour2.add(new int [] {3,-1});
        adjList.add(neighbour2);

        List<int[]> neighbour3 = new ArrayList<>();
        adjList.add(neighbour3);

        List<int[]> neighbour4 = new ArrayList<>();
        adjList.add(neighbour4);

        List<int[]> neighbour5 = new ArrayList<>();
        adjList.add(neighbour5);

        sort();
    }

    public static void main(String args []) {
        new AllTopologicalSorts().work();
    }

}
