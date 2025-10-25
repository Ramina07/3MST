# Assignment 3: Optimization of a City Transportation Network (Minimum Spanning Tree)

## 1. Objective

The primary objective of this assignment is to optimize a city’s transportation network by identifying the **Minimum Spanning Tree (MST)** of a weighted undirected graph.  
The MST ensures that all districts (vertices) are connected with the minimum total road construction cost (edge weights).

Two classic greedy algorithms — **Prim’s Algorithm** and **Kruskal’s Algorithm** — were implemented and compared in terms of **accuracy**, **efficiency**, and **performance**.

---

## 2. Background and Motivation

In urban planning, constructing road networks efficiently is a critical task.  
The MST model allows determining the minimal set of roads that connects all city districts while minimizing overall cost.

By using MST algorithms, planners can reduce redundant paths, minimize infrastructure expenses, and still guarantee full connectivity between all districts.

Both **Prim’s** and **Kruskal’s** algorithms are greedy algorithms that progressively select the smallest edges until all districts are connected without forming cycles.

---

## 3. Input Data Description

The dataset was created manually in **JSON** format to simulate a city’s road network.  
It contains three separate graphs with different complexities to test the scalability of both algorithms.

| Graph ID | Description | Vertices | Edges |
|-----------|--------------|----------|--------|
| 1 | Small city network (5 districts) | 5 | 6 |
| 2 | Medium transportation network (12 districts) | 12 | 14 |
| 3 | Large inter-district network (25 districts) | 25 | 28 |

All graphs are stored in:  
`src/main/resources/input/assign_3_input.json`

The program outputs results into:  
`src/main/resources/output/assign_3_output.json`

4.2 Project Structure
Assignment3MST/
 ├── pom.xml
 ├── src/main/java/org/example/
 │     ├── Main.java
 │     ├── Graph.java
 │     ├── Edge.java
 │     ├── PrimAlgorithm.java
 │     ├── KruskalAlgorithm.java
 │     └── JsonHandler.java
 └── src/main/resources/
       ├── input/assign_3_input.json
       └── output/assign_3_output.json

4.3 Class Overview
Class	Description
Graph.java	Defines graph structure, adjacency list, and edge management.
Edge.java	Represents an edge with source, destination, and weight.
PrimAlgorithm.java	Implements Prim’s algorithm using a priority queue.
KruskalAlgorithm.java	Implements Kruskal’s algorithm using Union-Find.
JsonHandler.java	Handles reading and writing JSON files using Gson.
Main.java	Integrates all modules, executes both algorithms, measures execution time, and writes results to JSON.
5. Algorithms
5.1 Prim’s Algorithm

Prim’s algorithm starts with a single vertex and repeatedly adds the smallest edge that connects a visited vertex to an unvisited one.
It is efficient for dense graphs (many edges).

Time Complexity: O(E log V)

Pseudocode Summary:

Start from an arbitrary vertex.

Use a priority queue to select the smallest edge.

Add the connected vertex to the MST set.

Repeat until all vertices are included.

5.2 Kruskal’s Algorithm

Kruskal’s algorithm sorts all edges by weight and adds them one by one to the MST, skipping those that would form a cycle.
It is efficient for sparse graphs (few edges).

Time Complexity: O(E log E)

Pseudocode Summary:

Sort all edges by weight.

Initialize disjoint sets for each vertex.

For each edge, use Union-Find to check if it forms a cycle.

Add the edge if it connects different sets.

Repeat until all vertices are connected.

6. Experimental Results
Graph ID	Vertices	Edges	MST Cost	Prim Ops	Kruskal Ops	Prim Time (ms)	Kruskal Time (ms)
1	5	6	10	10	4	1.94	8.63
2	12	14	43	28	14	0.06	0.04
3	25	28	82	53	25	0.08	0.07
7. Discussion and Analysis
7.1 Algorithm Efficiency

Both algorithms produced identical MST total costs, confirming correctness.
Kruskal’s Algorithm performed fewer operations and was slightly faster for smaller and sparse graphs.
Prim’s Algorithm showed more stable performance on larger or denser graphs due to the use of a priority queue.

7.2 Observations

The difference in execution time is minimal for graphs under 30 vertices.
As the graph becomes denser, Prim’s Algorithm scales better since it doesn’t require sorting all edges.
Kruskal’s Algorithm is easier to implement and debug but involves extra Union-Find operations.
Both algorithms demonstrated linear scalability for small to medium graphs.

8. Analytical Summary
Criterion	Prim’s Algorithm	Kruskal’s Algorithm
Graph Type Efficiency	Performs better on dense graphs	Performs better on sparse graphs
Sorting Requirement	No edge sorting required	Requires sorting of all edges
Data Structure Used	Priority Queue (Min-Heap)	Disjoint Set (Union-Find)
Implementation Complexity	Moderate	Simple and intuitive
Cycle Detection	Implicit (via visited nodes)	Explicit (via Union-Find)
Performance on Large Graphs	More consistent and scalable	May slow down due to sorting overhead
Best Use Case	Dense or weighted city networks	Sparse or simpler graph models
9. Conclusions

The experiment successfully demonstrated how both Prim’s and Kruskal’s algorithms can optimize a transportation network by producing the same MST with the minimum total cost.

Key Conclusions:

Both algorithms are correct and efficient for MST construction.

Kruskal’s Algorithm performs better on small and sparse graphs.

Prim’s Algorithm is more efficient on dense and large graphs.

The total MST cost remains identical across algorithms, confirming accuracy.

For real-world applications like city planning, both algorithms can ensure minimal construction cost while maintaining full network connectivity.

The choice of algorithm should depend on graph density, data structure availability, and implementation constraints.
