package graph.mst.list;

import graph.AdjacencyList;

import java.util.ArrayList;
import java.util.Comparator;

public class KruskalMinimumSpanningTree extends AdjacencyList {

    static class UnionFindNode {
        int parent;
        int rank;

        public UnionFindNode(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    int [][] mstEdges;
    UnionFindNode [] unionArr;

    public void displayMST() {
        for (int [] edge : mstEdges)
            System.out.println(edge[0] + " " + edge[1] + " " + edge[2]);
    }

    public void unionize(int vertex0, int vertex1) {
        int parent0 = getParent(vertex0);
        int parent1 = getParent(vertex1);
        if (unionArr[parent0].rank < unionArr[parent1].rank) unionArr[parent0].parent = parent1;
        else if (unionArr[parent0].rank > unionArr[parent1].rank) unionArr[parent1].parent = parent0;
        else { unionArr[parent0].parent = parent1; unionArr[parent0].rank++; };
    }

    public int getParent(int vertex) {
        if (unionArr[vertex].parent == vertex) return vertex;
        unionArr[vertex].parent = getParent(unionArr[vertex].parent);
        return unionArr[vertex].parent;
    }

    public void initializeUnionFindArray(int size) {
        unionArr = new UnionFindNode [size];
        for (int i = 0; i < size; i++)
            unionArr[i] = new UnionFindNode(i, 0);
    }

    public void runKruskal(int size) {
        initializeUnionFindArray(size);
        int i = 0;
        int edges = 0;
        while (edges < size - 1) {
            int [] edge = adjListOfEdges.get(i++);
            int parent0 = getParent(edge[0]);
            int parent1 = getParent(edge[1]);
            if (parent0 != parent1) {
                mstEdges[edges++] = edge;
                unionize(edge[0], edge[1]);
            }
        }
    }

    public void sortEdgesByWeight() {
        adjListOfEdges.sort(Comparator.comparingInt(e -> e[2]));
    }

    public void generateMST(int size) {
        mstEdges = new int [size - 1][3];
        unionArr = new UnionFindNode [size];
        sortEdgesByWeight();
        runKruskal(size);
        displayMST();
    }

    @Override
    public void work() {
        adjListOfEdges = new ArrayList<>();
        adjListOfEdges.add(new int [] {0,1,0});
        adjListOfEdges.add(new int [] {2,3,2});
        adjListOfEdges.add(new int [] {1,2,1});
        adjListOfEdges.add(new int [] {4,6,7});
        adjListOfEdges.add(new int [] {5,6,8});
        adjListOfEdges.add(new int [] {1,3,3});
        adjListOfEdges.add(new int [] {0,3,4});
        adjListOfEdges.add(new int [] {3,4,5});
        adjListOfEdges.add(new int [] {4,5,6});

        generateMST(7);
    }

    public static void main(String args []) {
        new KruskalMinimumSpanningTree().work();
    }
}
