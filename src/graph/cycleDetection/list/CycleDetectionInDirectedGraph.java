package graph.cycleDetection.list;

import graph.AdjacencyList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    PSEUDO CODE:
    
    1. For each node that is not safe (not yet checked for cyclicity) node:
        1.1. Mark it as unsafe
        1.2. If the node has already been marked unsafe, cycle has been detected! Go to step 3
        1.3. For each neighbour of the node, go to step 1
        1.4. If no cycle detected after exhaustion of all the neighbours, mark the node as safe
    2. Repeat step 1 until all the nodes have been marked as safe
    3. Display result
*/
public class CycleDetectionInDirectedGraph extends AdjacencyList {
    boolean cyclic;
    Set<Integer> safeNodes;
    Set<Integer> unsafeNodes;

    public void detectCycle() {
        cyclic = false;
        safeNodes = new HashSet<>();
        unsafeNodes = new HashSet<>();
        for (int i = 0; i < adjList.size(); i++)
            if (hasCycle(i))
                break;
        System.out.println(cyclic);
    }

    public boolean hasCycle(int source) {
        if (safeNodes.contains(source)) return false;
        if (unsafeNodes.contains(source)) return true;
        unsafeNodes.add(source);
        List<int[]> neighbours = adjList.get(source);
        for (int [] neighbour : neighbours) {
            if (hasCycle(neighbour[0])) {
                cyclic = true;
                return true;
            }
        }
        unsafeNodes.remove(source);
        safeNodes.add(source);
        return false;
    }

    public static void main(String args []) {
        new CycleDetectionInDirectedGraph().work();
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

        List<int[]> neighbour3 = new ArrayList<>();
        neighbour3.add(new int [] {3,-1});
        adjList.add(neighbour3);

        List<int[]> neighbour4 = new ArrayList<>();
        //neighbour4.add(new int [] {0,-1});
        adjList.add(neighbour4);

        detectCycle();
    }
}
