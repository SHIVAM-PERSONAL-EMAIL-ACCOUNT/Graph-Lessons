package graph.shortestPath.floydWarshall.matrix;

import graph.AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;

public class AllVertexToAllVertexShortestPathWithPathTrace extends AdjacencyMatrix {
        Double [][] asps;
        int [][] next;

        public void displayPath(List<Integer> path) {
            for (int vertex : path)
                System.out.print(vertex + " ");
            System.out.println();
        }

        public void tracePath(int source, int destination) {
            System.out.print("From " + source + " to " + destination + " = ");
            List<Integer> path = new ArrayList<>();
            if (asps[source][destination] == Double.POSITIVE_INFINITY) { displayPath(path); return; }
            while(source != destination) {
                if (source == -1) {
                    System.out.print("No path exists");
                    System.out.println();
                    return;
                }
                path.add(source);
                source = next[source][destination];
            }
            path.add(source);
            displayPath(path);
        }

        public void displayResult() {
            for (Double [] row : asps) {
                for (double ele : row) {
                    System.out.print((int)ele + " ");
                }
                System.out.println();
            }

            System.out.println("All Shortest Paths: ");
            for (int i = 0; i < adjMatrix.length; i++)
                for (int j = 0; j < adjMatrix.length; j++)
                    tracePath(i, j);
        }

        public void detectCycle(int vertices) {
            for (int k = 0; k < vertices; k++)
                for (int i = 0; i < vertices; i++)
                    for (int j = 0; j < vertices; j++)
                        if (asps[i][k] + asps[k][j] < asps[i][j]) {
                            asps[i][j] = Double.NEGATIVE_INFINITY;
                            next[i][j] = -1;
                        }
        }

        public void runFloydWarshall(int vertices) {
            for (int k = 0; k < vertices; k++)
                for (int i = 0; i < vertices; i++)
                    for (int j = 0; j < vertices; j++)
                        if (asps[i][k] + asps[k][j] < asps[i][j]) {
                            asps[i][j] = asps[i][k] + asps[k][j];
                            next[i][j] = next[i][k];
                        }
        }

        public void initializeVariables() {
            asps = new Double [adjMatrix.length][adjMatrix.length];
            next = new int [adjMatrix.length][adjMatrix.length];
            for (int i = 0; i < adjMatrix.length; i++)
                for (int j = 0; j < adjMatrix.length; j++)
                    if (adjMatrix[i][j] == 10000000) {
                        asps[i][j] = Double.POSITIVE_INFINITY;
                    }
                    else {
                        asps[i][j] = (double) adjMatrix[i][j];
                        next[i][j] = j;
                    }
        }

        public void generateASPS() {
            initializeVariables();
            int vertices = adjMatrix.length;
            runFloydWarshall(vertices);
            detectCycle(vertices);
            displayResult();
        }

        @Override
        public void work() {
            adjMatrix = new int [4][4];

            adjMatrix[0][0] = 0;
            adjMatrix[0][1] = 4;
            adjMatrix[0][2] = 1;
            adjMatrix[0][3] = 10000000;

            adjMatrix[1][0] = 10000000;
            adjMatrix[1][1] = 0;
            adjMatrix[1][2] = 10000000;
            adjMatrix[1][3] = 4;

            adjMatrix[2][0] = 10000000;
            adjMatrix[2][1] = 5;
            adjMatrix[2][2] = 0;
            adjMatrix[2][3] = 10000000;

            adjMatrix[3][0] = 10000000;
            adjMatrix[3][1] = 10000000;
            adjMatrix[3][2] = 10000000;
            adjMatrix[3][3] = 0;

            generateASPS();
        }

        public static void main(String args []) {
            new AllVertexToAllVertexShortestPathWithPathTrace().work();
        }
}
