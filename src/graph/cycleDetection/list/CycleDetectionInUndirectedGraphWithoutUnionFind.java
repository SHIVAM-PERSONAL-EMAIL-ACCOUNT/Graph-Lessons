package graph.cycleDetection.list;

import graph.AdjacencyList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CycleDetectionInUndirectedGraphWithoutUnionFind extends AdjacencyList {
    Set<Integer> safeNodes;
    Set<Integer> unsafeNodes;
    boolean cyclic;

    public void detectCycle() {
        cyclic = false;
        safeNodes = new HashSet<>();
        unsafeNodes = new HashSet<>();
        for (int i = 0; i < adjList.size(); i++)
            if (hasCycle(i, -1))
                break;
        System.out.println(cyclic);
    }

    public boolean hasCycle(int source, int parent) {
        if (safeNodes.contains(source)) return false;
        if (unsafeNodes.contains(source)) return true;
        unsafeNodes.add(source);
        List<int[]> neighbours = adjList.get(source);
        for (int [] neighbour : neighbours) {
            if (neighbour[0] != parent && hasCycle(neighbour[0], source)) {
                cyclic = true;
                return true;
            }
        }
        unsafeNodes.remove(source);
        safeNodes.add(source);
        return false;
    }

    public static void main(String args []) {
        new CycleDetectionInUndirectedGraphWithoutUnionFind().work();
    }

    @Override
    public void work() {
        adjList = new ArrayList<>();

        List<int[]> neighbour1 = new ArrayList<>();
        neighbour1.add(new int [] {1,-1});
        adjList.add(neighbour1);

        List<int[]> neighbour2 = new ArrayList<>();
        neighbour2.add(new int [] {2,-1});
        adjList.add(neighbour2);
        adjList.get(1).add(new int [] {0,-1});

        List<int[]> neighbour3 = new ArrayList<>();
        neighbour3.add(new int [] {3,-1});
        adjList.add(neighbour3);
        adjList.get(2).add(new int [] {1,-1});

        List<int[]> neighbour4 = new ArrayList<>();
        neighbour4.add(new int [] {0,-1});
        adjList.add(neighbour4);
        adjList.get(3).add(new int [] {2,-1});
    }
}
