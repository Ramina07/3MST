package org.example;

import java.util.*;

public class PrimAlgorithm {

    public static class Result {
        public List<Edge> mstEdges;
        public double totalCost;
        public int operationsCount;

        public Result(List<Edge> mstEdges, double totalCost, int operationsCount) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operationsCount = operationsCount;
        }
    }

    public static Result run(Graph graph, String startVertex) {
        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        int operations = 0;
        double totalCost = 0;

        visited.add(startVertex);
        pq.addAll(graph.getNeighbors(startVertex));
        operations += pq.size();

        while (!pq.isEmpty() && visited.size() < graph.getVertices().size()) {
            Edge edge = pq.poll();
            operations++;

            String nextVertex = !visited.contains(edge.getFrom()) ? edge.getFrom() : edge.getTo();
            if (visited.contains(nextVertex)) continue;

            visited.add(nextVertex);
            mstEdges.add(edge);
            totalCost += edge.getWeight();

            for (Edge neighbor : graph.getNeighbors(nextVertex)) {
                String other = neighbor.getFrom().equals(nextVertex) ? neighbor.getTo() : neighbor.getFrom();
                if (!visited.contains(other)) {
                    pq.add(neighbor);
                    operations++;
                }
            }
        }

        return new Result(mstEdges, totalCost, operations);
    }
}
