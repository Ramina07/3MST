package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputPath = "src/main/resources/input/assign_3_input.json";
        String outputPath = "src/main/resources/output/assign_3_output.json";

        System.out.println("Starting Assignment 3: Minimum Spanning Tree Optimization");
        System.out.println("Reading graphs from: " + inputPath);

        // Читаем графы из JSON
        List<JsonHandler.GraphData> graphs = JsonHandler.readGraphsFromJson(inputPath);
        List<JsonObject> results = new ArrayList<>();

        for (JsonHandler.GraphData graphData : graphs) {
            System.out.println("\nProcessing Graph ID: " + graphData.id + " — " + graphData.description);

            // Создаём объект Graph
            Graph graph = new Graph(graphData.nodes);
            for (JsonHandler.EdgeData e : graphData.edges) {
                graph.addEdge(e.from, e.to, e.weight);
            }

            // Запуск Prim
            long primStart = System.nanoTime();
            PrimAlgorithm.Result primResult = PrimAlgorithm.run(graph, graphData.nodes.get(0));
            long primEnd = System.nanoTime();
            double primTimeMs = (primEnd - primStart) / 1_000_000.0;

            // Запуск Kruskal
            long kruskalStart = System.nanoTime();
            KruskalAlgorithm.Result kruskalResult = KruskalAlgorithm.run(graph);
            long kruskalEnd = System.nanoTime();
            double kruskalTimeMs = (kruskalEnd - kruskalStart) / 1_000_000.0;

            // Формируем JSON-объект результата
            JsonObject graphResult = new JsonObject();
            graphResult.addProperty("graph_id", graphData.id);

            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", graphData.nodes.size());
            inputStats.addProperty("edges", graphData.edges.size());
            graphResult.add("input_stats", inputStats);

            // Prim
            JsonObject primJson = new JsonObject();
            primJson.addProperty("total_cost", primResult.totalCost);
            primJson.addProperty("operations_count", primResult.operationsCount);
            primJson.addProperty("execution_time_ms", primTimeMs);

            JsonArray primEdges = new JsonArray();
            for (Edge e : primResult.mstEdges) {
                JsonObject edgeJson = new JsonObject();
                edgeJson.addProperty("from", e.getFrom());
                edgeJson.addProperty("to", e.getTo());
                edgeJson.addProperty("weight", e.getWeight());
                primEdges.add(edgeJson);
            }
            primJson.add("mst_edges", primEdges);
            graphResult.add("prim", primJson);

            // Kruskal
            JsonObject kruskalJson = new JsonObject();
            kruskalJson.addProperty("total_cost", kruskalResult.totalCost);
            kruskalJson.addProperty("operations_count", kruskalResult.operationsCount);
            kruskalJson.addProperty("execution_time_ms", kruskalTimeMs);

            JsonArray kruskalEdges = new JsonArray();
            for (Edge e : kruskalResult.mstEdges) {
                JsonObject edgeJson = new JsonObject();
                edgeJson.addProperty("from", e.getFrom());
                edgeJson.addProperty("to", e.getTo());
                edgeJson.addProperty("weight", e.getWeight());
                kruskalEdges.add(edgeJson);
            }
            kruskalJson.add("mst_edges", kruskalEdges);
            graphResult.add("kruskal", kruskalJson);

            results.add(graphResult);

            // Выводим краткий отчёт в консоль
            System.out.println("Prim total cost: " + primResult.totalCost + " | time: " + primTimeMs + " ms");
            System.out.println("Kruskal total cost: " + kruskalResult.totalCost + " | time: " + kruskalTimeMs + " ms");
        }

        // Сохраняем результаты в output.json
        JsonHandler.writeResultsToJson(outputPath, results);

        System.out.println("Done! Results saved to " + outputPath);
    }
}

