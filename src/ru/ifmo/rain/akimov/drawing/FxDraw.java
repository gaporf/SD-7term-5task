package ru.ifmo.rain.akimov.drawing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FxDraw extends Application implements DrawingApi {
    private final int width = 500;
    private final int height = 500;
    private static GraphicsContext graphicsContext;

    public FxDraw() {
        if (graphicsContext == null) {
            Canvas canvas = new Canvas(width, height);
            graphicsContext = canvas.getGraphicsContext2D();
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
        graphicsContext.fillOval(x - (double) r / 2, y - (double) r / 2, r, r);
    }

    @Override
    public void drawLine(long x1, long y1, long x2, long y2) {
        graphicsContext.strokeLine(x1, y1, x2, y2);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing circle");
        Group root = new Group();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setStroke(Color.BLACK);
        root.getChildren().add(graphicsContext.getCanvas());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
