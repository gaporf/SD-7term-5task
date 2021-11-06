package ru.ifmo.rain.akimov.graph;

import ru.ifmo.rain.akimov.drawing.DrawingApi;

import java.util.List;

public abstract class Graph {
    private final DrawingApi drawingApi;
    private final int n;


    public Graph(DrawingApi drawingApi, int n) {
        this.drawingApi = drawingApi;
        this.n = n;
    }

    public abstract void drawGraph();

    private List<Long> getCoordinates(int v) {
        long w = drawingApi.getDrawingAreaWidth();
        long h = drawingApi.getDrawingAreaHeight();
        double R = Math.min(w, h) / 2.0;
        double angle = 2 * Math.PI / n;
        double c = Math.sqrt(2 * R * R - 2 * R * R * Math.cos(angle));
        double S = 0.5 * R * R * Math.sin(angle);
        double r = 2 * S / (R + R + c);
        double x0 = w / 2.0;
        double y0 = h / 2.0;
        double x1 = x0 + Math.cos(angle * v) * R;
        double y1 = y0 + Math.sin(angle * v) * R;
        double x2 = x0 + Math.cos(angle * (v + 1)) * R;
        double y2 = y0 + Math.sin(angle * (v + 1)) * R;
        double xc = (x1 + x2) / 2;
        double yc = (y1 + y2) / 2;
        double bisectorLen = Math.sqrt(Math.pow(xc - x0, 2) + Math.pow(yc - y0, 2));
        double dx = (xc - x0) / bisectorLen;
        double dy = (yc - y0) / bisectorLen;
        double len = bisectorLen * 2 * R / (2 * R + c);
        return List.of((long) (x0 + dx * len), (long) (y0 + dy * len), (long) r);
    }

    public void drawVertices() {
        for (int i = 0; i < n; i++) {
            List<Long> coordinates = getCoordinates(i);
            drawingApi.drawCircle(coordinates.get(0), coordinates.get(1), coordinates.get(2));
        }
    }

    public void drawEdge(int v1, int v2) {
        List<Long> v1Coordinates = getCoordinates(v1);
        List<Long> v2Coordinates = getCoordinates(v2);
        drawingApi.drawLine(v1Coordinates.get(0), v1Coordinates.get(1), v2Coordinates.get(0), v2Coordinates.get(1));
    }
}
