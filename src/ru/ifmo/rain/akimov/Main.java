package ru.ifmo.rain.akimov;

import javafx.application.Application;
import ru.ifmo.rain.akimov.drawing.AwtDraw;
import ru.ifmo.rain.akimov.drawing.DrawingApi;
import ru.ifmo.rain.akimov.drawing.FxDraw;
import ru.ifmo.rain.akimov.graph.AdjacencyGraph;
import ru.ifmo.rain.akimov.graph.Graph;
import ru.ifmo.rain.akimov.graph.MatrixGraph;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args == null || args.length != 3 ||
                args[0] == null || !(args[0].equals("matrix") || args[0].equals("adjacency")) ||
                args[1] == null || !(args[1].equals("awt") || args[1].equals("fx")) ||
                args[2] == null) {
            System.err.println("Expected input format: <matrix or adjacency> <awt or fx>");
            return;
        }

        List<List<Integer>> graphList = new ArrayList<>();
        final int W = 500;
        final int H = 500;

        try {
            Scanner scanner = new Scanner(new File(args[2]));
            if (args[0].equals("matrix")) {
                int n = scanner.nextInt();
                for (int i = 0; i < n; i++) {
                    graphList.add(new ArrayList<>());
                    for (int j = 0; j < n; j++) {
                        graphList.get(i).add(scanner.nextInt());
                    }
                }
            } else if (args[0].equals("adjacency")) {
                int n = scanner.nextInt();
                int m = scanner.nextInt();
                for (int i = 0; i < n; i++) {
                    graphList.add(new ArrayList<>());
                }
                for (int i = 0; i < m; i++) {
                    int a = scanner.nextInt();
                    int b = scanner.nextInt();
                    graphList.get(a).add(b);
                    graphList.get(b).add(a);
                }
            } else {
                assert false;
            }
        } catch (IOException e) {
            System.err.println("Couldn't read from file: " + args[2] + ". Reason: " + e.getMessage());
            return;
        }

        DrawingApi drawingApi = args[1].equals("awt") ? new AwtDraw(W, H) : new FxDraw();
        Graph graph = args[0].equals("matrix") ? new MatrixGraph(graphList, drawingApi) : new AdjacencyGraph(graphList, drawingApi);
        graph.drawGraph();
        if (args[1].equals("awt")) {
            assert drawingApi instanceof AwtDraw;
            Frame frame = (AwtDraw) drawingApi;
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });
            frame.setSize(W, H);
            frame.setVisible(true);
        } else if (args[1].equals("fx")) {
            assert drawingApi instanceof FxDraw;
            Application.launch(((FxDraw) drawingApi).getClass(), "");
        } else {
            assert false;
        }
    }
}
