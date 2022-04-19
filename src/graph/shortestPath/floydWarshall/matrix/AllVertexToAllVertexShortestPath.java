package graph.shortestPath.floydWarshall.matrix;

import graph.AdjacencyMatrix;

public class AllVertexToAllVertexShortestPath extends AdjacencyMatrix {
    Double [][] asps;

    public void displayResult() {
        for (Double [] row : asps) {
            for (double ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }

    public void detectCycle(int vertices) {
        for (int k = 0; k < vertices; k++)
            for (int i = 0; i < vertices; i++)
                for (int j = 0; j < vertices; j++)
                    if (asps[i][k] + asps[k][j] < asps[i][j])
                        asps[i][j] = Double.NEGATIVE_INFINITY;
    }

    public void runFloydWarshall(int vertices) {
        for (int k = 0; k < vertices; k++)
            for (int i = 0; i < vertices; i++)
                for (int j = 0; j < vertices; j++)
                    if (asps[i][k] + asps[k][j] < asps[i][j])
                        asps[i][j] = asps[i][k] + asps[k][j];
    }

    public void initializeVariables() {
        asps = new Double [adjMatrix.length][adjMatrix.length];
        for (int i = 0; i < adjMatrix.length; i++)
            for (int j = 0; j < adjMatrix.length; j++)
                if (adjMatrix[i][j] == 10000000)
                    asps[i][j] = Double.POSITIVE_INFINITY;
                else
                    asps[i][j] = (double) adjMatrix[i][j];
    }

    public void generateASPS() {
        initializeVariables();
        int vertices = adjMatrix.length;
        runFloydWarshall(vertices);
        displayResult();
        detectCycle(vertices);
        displayResult();
    }

    @Override
    public void work() {
        adjMatrix = new int [4][4];

        adjMatrix[0][0] = 0;
        adjMatrix[0][1] = 1;
        adjMatrix[0][2] = 10000000;
        adjMatrix[0][3] = 10000000;

        adjMatrix[1][0] = 10000000;
        adjMatrix[1][1] = 0;
        adjMatrix[1][2] = 2;
        adjMatrix[1][3] = 10000000;

        adjMatrix[2][0] = -4;
        adjMatrix[2][1] = 10000000;
        adjMatrix[2][2] = 0;
        adjMatrix[2][3] = 10000000;

        adjMatrix[3][0] = 1;
        adjMatrix[3][1] = 10000000;
        adjMatrix[3][2] = 10000000;
        adjMatrix[3][3] = 0;

        generateASPS();
    }

    public static void main(String args []) {
        new AllVertexToAllVertexShortestPath().work();
    }
}
