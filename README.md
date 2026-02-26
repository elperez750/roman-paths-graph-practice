# 🏛️ Roman Road Network — Graph Algorithm Simulator

A Java implementation of graph data structures and traversal algorithms, modeled after the real road network of the Roman Empire at its peak. Built as a hands-on learning project to understand BFS, DFS, adjacency lists, and path reconstruction.

---

## 🗺️ Overview

This project models **58 cities** across the Roman Empire — from Hadrian's Wall in northern Britain to Ctesiphon in Mesopotamia, and from Gades (modern Cadiz) on the Atlantic coast to Alexandria in Egypt. Cities are vertices and roads are edges in an undirected graph.

The goal: implement graph traversal from scratch and use it to answer real questions about the empire's connectivity.

---

## 🏙️ Cities Covered

| Region | Cities |
|---|---|
| **Italy** | Rome, Florence, Venice, Naples, Bologna, Milan, Genoa, Turin, Brindisi, Palermo |
| **Hispania** | Carthago Nova, Hispalis, Emerita Augusta, Caesaraugusta, Tarraco, Gades, Bracara |
| **Gaul** | Massilia, Lugdunum, Burdigala, Lutetia, Narbo, Arelate |
| **Britannia** | Londinium, Eboracum, Camulodunum, Aquae Sulis, Hadrian's Wall |
| **Germania** | Colonia, Mogontiacum, Augusta Treverorum |
| **Balkans** | Vindobona, Aquincum, Sirmium, Naissus, Thessalonica |
| **Greece** | Athens, Corinth, Sparta |
| **Asia Minor** | Constantinople, Nicomedia, Ephesus, Ancyra, Antioch |
| **Levant** | Damascus, Jerusalem, Caesarea Maritima, Petra, Masada |
| **Egypt & North Africa** | Alexandria, Memphis, Cyrene, Carthage, Leptis Magna, Caesarea, Tingis |
| **Mesopotamia** | Ctesiphon, Seleucia |

---

## 🔧 Data Structure

The graph uses an **adjacency list** representation:

```java
ArrayList<ArrayList<Integer>> adjList;
```

Each index corresponds to a city ID. `adjList.get(i)` returns a list of all cities directly connected to city `i` by road. This is more memory efficient than an adjacency matrix for sparse graphs like a road network.

---

## 🔍 Algorithms Implemented

### BFS — Breadth First Search

Explores the graph **layer by layer**, visiting all neighbors at distance 1 before distance 2, and so on. Uses a **Queue (FIFO)**.

**Guarantees the shortest path** (fewest roads) between two cities.

Uses a `parents[]` array to reconstruct the path:
- Every time a city is discovered, record which city found it
- After BFS completes, walk backwards from destination to source using `parents[]`
- Reverse the result for the final path

```
Rome → Carthage → Leptis Magna → Cyrene → Alexandria → Caesarea Maritima → Jerusalem
```

### DFS — Depth First Search

Explores the graph by **diving as deep as possible** before backtracking. Uses a **Stack (LIFO)**.

**Does not guarantee shortest path** — finds *a* path, not *the* path. The path found depends entirely on the order neighbors appear in the adjacency list.

The stack naturally represents the current exploration path — dead ends get popped off, and whatever remains in the stack when the destination is found IS the path.

---

## 🏆 Example Queries

```java
network.bfs(0, 44);   // Rome → Jerusalem
network.bfs(23, 54);  // Londinium → Ctesiphon
network.bfs(56, 57);  // Hadrian's Wall → Masada
network.bfs(15, 47);  // Gades → Alexandria
network.dfs(3, 2);    // Naples → Venice
```

### Sample BFS Output — Londinium → Ctesiphon
```
Londinium → Lutetia → Lugdunum → Augusta Treverorum → Mogontiacum 
→ Vindobona → Aquincum → Sirmium → Naissus → Constantinople 
→ Nicomedia → Ephesus → Antioch → Ctesiphon
```
*4000 miles of Roman roads in one traversal.*

### Sample BFS Output — Hadrian's Wall → Masada
```
Hadrian's Wall → Eboracum → Londinium → Lutetia → Lugdunum 
→ Massilia → Genoa → Florence → Rome → Carthage → Leptis Magna 
→ Cyrene → Alexandria → Caesarea Maritima → Masada
```
*Two of the most remote frontier fortresses in the empire, connected.*

---

## 📁 Project Structure

```
RomanRoads.java       — Main class, graph construction, BFS, DFS
README.md             — This file
```

---

## 🚀 How to Run

```bash
javac RomanRoads.java
java RomanRoads
```

---

## 💡 Key Concepts Learned

- **Adjacency list** construction and traversal
- **BFS** with queue, visited array, and parents array for path reconstruction
- **DFS** with explicit stack and early exit condition
- Difference between shortest path (BFS) and any path (DFS)
- Why `parents[]` size equals `noOfVertices` — one slot per city regardless of target
- How the DFS stack naturally represents the current live path

---

## 🔮 Stretch Goals

- [ ] `isFullyConnected()` — can every city reach every other? (Hint: Palermo and the Mesopotamian cities are tricky)
- [ ] `findAllPaths(start, end)` — use DFS to enumerate every possible route
- [ ] Weighted edges (road distances in miles) and Dijkstra's algorithm for true shortest distance
- [ ] Visualize the graph in the console as an adjacency matrix

---

## 📜 Historical Note

The road network is based on real Roman infrastructure at the empire's peak (~117 AD under Emperor Trajan). The Via Appia, Via Egnatia, and coastal Mediterranean sea lanes are all represented. Ctesiphon and Seleucia represent Trajan's brief Mesopotamian conquests — connected to the empire by only a thin thread, just like in real history.

*"All roads lead to Rome" — and BFS will prove it.*
