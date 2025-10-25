package org.example;

import java.util.*;

public class Graph {
    private final List<String> vertices;
    private final List<Edge> edges;
    private final Map<String, List<Edge>> adjacencyList;

    public Graph(List<String> vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
        for (String v : vertices) {
            adjacencyList.put(v, new ArrayList<>());
        }
    }

    public void addEdge(String from, String to, double weight) {
        Edge edge = new Edge(from, to, weight);
        edges.add(edge);
        adjacencyList.get(from).add(edge);
        adjacencyList.get(to).add(edge); // неориентированный граф
    }

    public List<String> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getNeighbors(String vertex) {
        return adjacencyList.get(vertex);
    }

    public void printGraph() {
        System.out.println("Graph:");
        for (String v : vertices) {
            System.out.print(v + " -> ");
            for (Edge e : adjacencyList.get(v)) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }
}

