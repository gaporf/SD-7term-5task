package ru.ifmo.rain.akimov.drawing;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class AwtDraw extends Frame implements DrawingApi {
    private final long width;
    private final long height;
    private final java.util.List<java.util.List<Long>> circles = new ArrayList<>();
    private final java.util.List<java.util.List<Long>> lines = new ArrayList<>();

    public AwtDraw(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D ga = (Graphics2D) g;
        ga.setPaint(Color.black);
        for (java.util.List<Long> circle : circles) {
            double x0 = (double) circle.get(0);
            double y0 = (double) circle.get(1);
            double r = (double) circle.get(2);
            ga.fill(new Ellipse2D.Double(x0 - r / 2, y0 - r / 2, r, r));
        }
        for (java.util.List<Long> line : lines) {
            ga.draw(new Line2D.Double((double) line.get(0), (double) line.get(1), (double) line.get(2), (double) line.get(3)));
        }
    }

    @Override
    public long getDrawingAreaWidth() {
        return width;
    }

    @Override
    public long getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(long x, long y, long r) {
        circles.add(java.util.List.of(x, y, r));
    }

    @Override
    public void drawLine(long x1, long y1, long x2, long y2) {
        lines.add(java.util.List.of(x1, y1, x2, y2));
    }
}
