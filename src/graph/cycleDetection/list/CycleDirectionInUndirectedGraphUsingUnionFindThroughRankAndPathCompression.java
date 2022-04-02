package graph.cycleDetection.list;

import graph.AdjacencyList;

import java.util.ArrayList;
import java.util.Arrays;

public class CycleDirectionInUndirectedGraphUsingUnionFindThroughRankAndPathCompression extends AdjacencyList {
    boolean cyclic;
    int [] setArr;

    public void performUnion(int vertex0, int vertex1) {
        int parent0 = getParent(vertex0);
        int parent1 = getParent(vertex1);
        if (setArr[parent0] <= setArr[parent1]) {
            setArr[parent0] += setArr[parent1];
            setArr[vertex1] = vertex0;
        }
        else {
            setArr[parent1] += setArr[parent0];
            setArr[vertex0] = vertex1;
        }
    }

    public int getParent(int vertex) {
        if (setArr[vertex] < 0) return vertex;
        setArr[vertex] = getParent(setArr[vertex]);
        return setArr[vertex];
    }

    public void initializeEdgeArray(int size) {
        setArr = new int [size];
        Arrays.fill(setArr, -1);
    }

    public void detectCycle(int size) {
        cyclic = false;
        initializeEdgeArray(size);
        for (int [] edge : adjListOfEdges) {
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
        adjListOfEdges.add(new int [] {0,1});
        adjListOfEdges.add(new int [] {1,2});
        adjListOfEdges.add(new int [] {2,3});
        adjListOfEdges.add(new int [] {1,4});
        adjListOfEdges.add(new int [] {4,3});
        detectCycle(5);
    }

    public static void main(String args[]) {
        new CycleDirectionInUndirectedGraphUsingUnionFindThroughRankAndPathCompression().work();
    }
}
