package graph.cycleDetection.list;

import graph.AdjacencyList;

import java.util.ArrayList;
import java.util.Arrays;

/*
    PSEUDO CODE:
    
    1. For each edge in the graph
        1.1. Get parents of both the vertices of the edge
        1.2. If the parent are same, cycle has been detected! Go to step 3
        1.3. Mark parents of both the vertices as same
    2. Repeat step 1 until all the edges have been exhausted
    3. Display result
*/
public class CycleDetectionInUndirectedGraphUsingUnionFind extends AdjacencyList {
    boolean cyclic;
    int [] setArr;

    public void performUnion(int parent, int child) {
        setArr[child] = parent;
    }

    public int getParent(int vertex) {
        if (setArr[vertex] == -1) return vertex;
        return getParent(setArr[vertex]);
    }

    public void initializeEdgeArray(int size) {
        setArr = new int [size];
        Arrays.fill(setArr, -1);
    }

    public void detectCycle(int size) {
        cyclic = false;
        initializeEdgeArray(size);
        for (int[] edge : adjListOfEdges) {
            int parent0 = getParent(edge[0]);
            int parent1 = getParent(edge[1]);
            if (parent0 == parent1) {
                cyclic = true;
                break;
            }
            performUnion(edge[0], edge[1]);
        }
        System.out.println(cyclic);
    }

    @Override
    public void work() {
        adjListOfEdges = new ArrayList<>();
        adjListOfEdges.add(new int [] {0,1,-1});
        adjListOfEdges.add(new int [] {1,2,-1});
        adjListOfEdges.add(new int [] {2,3,-1});
        detectCycle(4);
    }

    public static void main(String args[]) {
        new CycleDetectionInUndirectedGraphUsingUnionFind().work();
    }
}
