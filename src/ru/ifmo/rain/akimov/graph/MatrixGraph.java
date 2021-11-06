package ru.ifmo.rain.akimov.graph;

import ru.ifmo.rain.akimov.drawing.DrawingApi;

import java.util.List;

public class MatrixGraph extends Graph {
    private final List<List<Integer>> matrix;

    public MatrixGraph(List<List<Integer>> matrix, DrawingApi drawingApi) {
        super(drawingApi, matrix.size());

        int n = matrix.size();
        for (List<Integer> edgesFromI : matrix) {
            assert edgesFromI != null && edgesFromI.size() == n;
            for (Integer edge : edgesFromI) {
                assert edge == 0 || edge == 1;
            }
        }

        this.matrix = matrix;
    }

    @Override
    public void drawGraph() {
        drawVertices();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j) == 1) {
                    drawEdge(i, j);
                }
            }
        }
    }
}
