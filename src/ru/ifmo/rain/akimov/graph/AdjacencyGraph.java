package ru.ifmo.rain.akimov.graph;

import ru.ifmo.rain.akimov.drawing.DrawingApi;

import java.util.List;

public class AdjacencyGraph extends Graph {
    final List<List<Integer>> adjacencyList;

    public AdjacencyGraph(List<List<Integer>> adjacencyList, DrawingApi drawingApi) {
        super(drawingApi, adjacencyList.size());

        int n = adjacencyList.size();
        for (List<Integer> edgesFromI : adjacencyList) {
            assert edgesFromI != null && edgesFromI.size() < n;
            for (Integer edge : edgesFromI) {
                assert edge >= 0 && edge < n;
            }
        }

        this.adjacencyList = adjacencyList;
    }

    @Override
    public void drawGraph() {
        drawVertices();
        for (int i = 0; i < adjacencyList.size(); i++) {
            for (Integer j : adjacencyList.get(i)) {
                drawEdge(i, j);
            }
        }
    }
}
