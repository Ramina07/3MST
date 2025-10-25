Assignment 3: Optimization of a City Transportation Network (Minimum Spanning Tree)
1. Objective

The primary objective of this assignment is to optimize a city’s transportation network by identifying the Minimum Spanning Tree (MST) of a weighted undirected graph.
The MST ensures that all districts (vertices) are connected with the minimum total road construction cost (edge weights).
Two classic greedy algorithms — Prim’s Algorithm and Kruskal’s Algorithm — were implemented and compared in terms of accuracy, efficiency, and performance.

2. Background and Motivation

In urban planning, constructing road networks efficiently is a critical task. The MST model determines the minimal set of roads that connects all city districts while minimizing total construction cost.
Using MST algorithms helps reduce redundancy, minimize expenses, and ensure full connectivity between districts.

Both Prim’s and Kruskal’s algorithms are greedy and progressively select the smallest edges until all districts are connected without cycles.

3. Input Data Description

The dataset was manually created in JSON format to simulate a city’s road network.
It includes three graphs with different complexities to test algorithm scalability.

Graph ID	Description	Vertices	Edges
1	Small city network	5	6
2	Medium transportation network	12	14
3	Large inter-district network	25	28

Files

src/main/resources/input/assign_3_input.json     # Input dataset
src/main/resources/output/assign_3_output.json   # Output results


Example JSON structure

{
  "id": 1,
  "nodes": ["A", "B", "C", "D", "E"],
  "edges": [
    {"from": "A", "to": "B", "weight": 2},
    {"from": "A", "to": "C", "weight": 4},
    {"from": "C", "to": "D", "weight": 1},
    {"from": "D", "to": "E", "weight": 3}
  ]
}

4. System Implementation
4.1 Tools and Framework

Language: Java 17

Build System: Maven

Library: Gson (for JSON parsing)

IDE: IntelliJ IDEA

4.2 Project Structure
Assignment3MST/
 ├── pom.xml
 ├── src/
 │   ├── main/
 │   │   ├── java/org/example/
 │   │   │    ├── Main.java
 │   │   │    ├── Graph.java
 │   │   │    ├── Edge.java
 │   │   │    ├── PrimAlgorithm.java
 │   │   │    ├── KruskalAlgorithm.java
 │   │   │    └── JsonHandler.java
 │   │   └── resources/
 │   │        ├── input/assign_3_input.json
 │   │        └── output/assign_3_output.json
 │   └── test/
 └── README.md

4.3 Class Summary
Class	Description
Graph.java	Defines the graph structure and manages vertices and edges.
Edge.java	Represents an edge with from, to, and weight.
PrimAlgorithm.java	Implements Prim’s MST algorithm using a priority queue.
KruskalAlgorithm.java	Implements Kruskal’s MST algorithm using Union-Find.
JsonHandler.java	Reads and writes JSON data using Gson.
Main.java	Runs both algorithms, measures time, and writes results to output JSON.
5. Algorithms
5.1 Prim’s Algorithm

Prim’s algorithm starts from one vertex and repeatedly adds the smallest edge that connects visited and unvisited vertices.
Best suited for dense graphs.

Time Complexity: O(E log V)

Steps:

Start from an arbitrary vertex.

Use a priority queue to select the smallest edge.

Add the connected vertex to the MST.

Repeat until all vertices are included.

5.2 Kruskal’s Algorithm

Kruskal’s algorithm sorts all edges by weight and adds them one by one, avoiding cycles using Union-Find.
Best suited for sparse graphs.

Time Complexity: O(E log E)

Steps:

Sort all edges by weight.

Use Union-Find to avoid cycles.

Add edges until MST is complete.

6. Experimental Results
Graph ID	Vertices	Edges	MST Cost	Prim Ops	Kruskal Ops	Prim Time (ms)	Kruskal Time (ms)
1	5	6	10	10	4	1.94	8.63
2	12	14	43	28	14	0.06	0.04
3	25	28	82	53	25	0.08	0.07

Observations:

Both algorithms produced identical MST total costs (correctness confirmed).

Kruskal’s algorithm required fewer operations on smaller graphs.

Prim’s algorithm scales better for large, dense graphs.

7. Analysis

Both algorithms are correct and efficient.

Prim’s Algorithm performs better on dense graphs.

Kruskal’s Algorithm is simpler and more effective on sparse graphs.

Time differences are minimal on small datasets.

Implementation complexity is higher for Prim but runtime is more stable.

8. Conclusions

Both algorithms produced identical MST costs.

Kruskal’s Algorithm performs better for sparse networks.

Prim’s Algorithm performs better for dense and large networks.

MST modeling helps reduce overall road construction costs in real-world city planning.

9. References

GeeksForGeeks — Prim’s MST Algorithm

GeeksForGeeks — Kruskal’s MST Algorithm

Astana IT University — Assignment 3 Guidelines, 2025

10. How to Run
# Compile the project
mvn clean compile

# Run the main class
mvn exec:java -Dexec.mainClass="org.example.Main"


After execution, results are saved automatically in:

src/main/resources/output/assign_3_output.json
