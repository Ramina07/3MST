package org.example;

import java.util.*;

public class KruskalAlgorithm {

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

    private static class UnionFind {
        private final Map<String, String> parent = new HashMap<>();

        public UnionFind(List<String> vertices) {
            for (String v : vertices) {
                parent.put(v, v);
            }
        }

        public String find(String v) {
            if (!parent.get(v).equals(v)) {
                parent.put(v, find(parent.get(v)));
            }
            return parent.get(v);
        }

        public boolean union(String a, String b) {
            String rootA = find(a);
            String rootB = find(b);
            if (rootA.equals(rootB)) return false;
            parent.put(rootB, rootA);
            return true;
        }
    }

    public static Result run(Graph graph) {
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);

        UnionFind uf = new UnionFind(graph.getVertices());
        List<Edge> mstEdges = new ArrayList<>();

        double totalCost = 0;
        int operations = 0;

        for (Edge e : edges) {
            operations++;
            if (uf.union(e.getFrom(), e.getTo())) {
                mstEdges.add(e);
                totalCost += e.getWeight();
            }
            if (mstEdges.size() == graph.getVertices().size() - 1) break;
        }

        return new Result(mstEdges, totalCost, operations);
    }
}

