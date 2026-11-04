# Practice 8: Graph Algorithms in Java

A **Java console application** for creating and managing **weighted, undirected graphs**, displaying their adjacency matrix, and running four classic algorithms: **Dijkstra, Floyd-Warshall, Prim, and Kruskal**.

Developed as an academic project for the **Data Structures and Their Applications** course at the **Faculty of Computer Science, Benemérita Universidad Autónoma de Puebla (BUAP)**.

## Requirements

- **Java Development Kit (JDK) 8 or later**, with `javac` and `java` available.
- A terminal or Java-compatible IDE, such as IntelliJ IDEA, Eclipse, NetBeans, or VS Code.
- **No external dependencies**.

Check your Java installation:

```bash
java -version
javac -version
```

## Project Structure

```text
Practica8Grafos/
├── Main.java                # Interactive menu and application entry point
├── Grafo.java               # Adjacency matrix and graph operations
├── Arista.java              # Weighted edge model
├── Entrada.java             # Input handling and validation
├── Dijkstra.java            # Single-source shortest paths
├── FloydWarshall.java       # All-pairs shortest paths
├── Prim.java                # Minimum spanning tree using Prim's algorithm
├── Kruskal.java             # Minimum spanning tree using Kruskal's algorithm
├── ConjuntosDisjuntos.java  # Disjoint-set (Union-Find) for cycle detection
└── README.md
```

All `.java` files are in the **same directory** and do not declare a Java package.

## How to Compile and Run

Open a terminal in the `Practica8Grafos` directory and compile the Java files:

```bash
javac *.java
```

Then start the application:

```bash
java Main
```

Alternatively, open the folder in your preferred IDE and run the `main` method in `Main.java`.

> **Note:** The application's interactive prompts are currently in Spanish.

## Features

The program offers the following menu:

```text
========== MENU DE GRAFOS ==========
1. Crear un nuevo grafo
2. Agregar vertices
3. Agregar arista con peso
4. Mostrar grafo
5. Ejecutar Dijkstra
6. Ejecutar Floyd-Warshall
7. Ejecutar Prim
8. Ejecutar Kruskal
9. Reiniciar / borrar grafo
10. Salir
```

In English, these options allow you to:

1. Create a new graph.
2. Add vertices.
3. Add weighted edges.
4. Display the adjacency matrix and edge list.
5. Run Dijkstra from a selected source vertex.
6. Run Floyd-Warshall.
7. Run Prim's algorithm.
8. Run Kruskal's algorithm.
9. Reset or delete the graph.
10. Exit the application.

Vertices are labeled sequentially from **1** to **n**.

## Implemented Algorithms

| Algorithm | Purpose | Output |
| --- | --- | --- |
| **Dijkstra** | Find shortest paths from one source vertex. | Minimum distances and reconstructed paths to reachable vertices. |
| **Floyd-Warshall** | Find shortest distances between every pair of vertices. | Final shortest-distance matrix. |
| **Prim** | Build a minimum spanning tree. | Selected edges and total weight. |
| **Kruskal** | Build a minimum spanning tree while avoiding cycles. | Selected edges and total weight using Union-Find. |

For **disconnected graphs**, Dijkstra reports unreachable vertices and Floyd-Warshall uses `INF`. Prim and Kruskal output a **minimum spanning forest** instead of claiming that a spanning tree exists for the entire graph.

## Example

Create a graph with **5 vertices** and **7 edges**:

| Source | Destination | Weight |
| --- | --- | --- |
| 1 | 2 | 4 |
| 1 | 3 | 2 |
| 2 | 3 | 1 |
| 2 | 4 | 5 |
| 3 | 4 | 8 |
| 3 | 5 | 10 |
| 4 | 5 | 2 |

### Expected Dijkstra Results (Source: 1)

| Destination | Shortest Distance | Path |
| --- | --- | --- |
| 1 | 0 | 1 |
| 2 | 3 | 1 → 3 → 2 |
| 3 | 2 | 1 → 3 |
| 4 | 8 | 1 → 3 → 2 → 4 |
| 5 | 10 | 1 → 3 → 2 → 4 → 5 |

### Expected Minimum Spanning Tree

Both **Prim** and **Kruskal** should find a minimum spanning tree with a **total weight of 10**. Its edges are:

- `2–3` (weight `1`)
- `1–3` (weight `2`)
- `4–5` (weight `2`)
- `2–4` (weight `5`)

The order in which the edges are selected may differ between algorithms.

You can also test a second, larger graph (for example, with 7 or 8 vertices) to compare the algorithms.

## Input Validation and Constraints

- Supports **1 to 100 vertices**.
- Supports **undirected edges**: `1–2` is the same edge as `2–1`.
- Accepts integer edge weights from **0 to 1,000,000,000,000**.
- Rejects invalid vertex IDs, duplicate edges, and self-loops.
- Prompts the user again after invalid input rather than terminating.
- Uses `INF` to represent the absence of an edge or an unreachable destination.
- Requires **non-negative edge weights** for shortest-path algorithms.

## Graph Representation

The graph uses an **adjacency matrix** (`long[][]`):

- `matrix[i][j]` stores the edge weight between two vertices.
- Diagonal entries are `0`.
- `INF` indicates that no direct edge exists.
- Each edge is stored in both directions because the graph is undirected.

## Technologies and Data Structures

- **Java** (standard library only).
- `ArrayList`, `PriorityQueue`, `Arrays`, and other `java.util` utilities.
- **Disjoint-set / Union-Find**, using path compression and union by rank.

## Academic Context

This repository contains a Java implementation developed for **Practice 8: Implementation of Graph Algorithms** in the **Data Structures and Their Applications** course at BUAP.

---

**Practice 8 — Data Structures and Their Applications · BUAP**
