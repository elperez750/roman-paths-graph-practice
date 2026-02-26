import java.util.*;

public class RomanRoads {


    private int numCities;
    private String[] cityNames;
    private ArrayList<ArrayList<Integer>> adjList;

    public RomanRoads(int n, String[] names) {
        // YOUR CODE
        numCities = n;
        cityNames = names;
        adjList = new ArrayList<>(numCities);
        for (int i = 0; i < numCities; i++) {
            adjList.add(new ArrayList<>());
        }

    }

    public void addRoad(int cityA, int cityB) {
        // YOUR CODE — undirected edge
        adjList.get(cityA).add(cityB);
        adjList.get(cityB).add(cityA);


    }

    public void bfs(int start, int end) {
        // Find shortest path, print it

        // Rome = 0
        // Milan = 5

        // Parents array
        int[] parents =  new int[numCities];

        // Visited array
        boolean[] visited = new boolean[numCities];

        // Make everything in the parents array be equal to -1
        for (int i = 0; i < numCities; i++) {
            parents[i] = -1;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // Initialize our queue with the start node
        queue.add(start);

        // Set the start node to visited.
        visited[start] = true;


        // We loop until the queue is empty, but we will exit early if we find end
        while (!queue.isEmpty()) {

            // Remove first inserted node from queue.
            int current = queue.poll();

            // If the node is equal to the end. We exit the while loop
            if (current == end) {
                break;
            }


            // We loop through each of the neighbors for the current node
            for (int neighbor : adjList.get(current)) {

                // Check if the neighbor has been visited
                if (!visited[neighbor]) {

                    /*
                    -If neighbor has not been visited, then set visited for the neighbor to true
                    -Add the neighbor to the queue
                    -Make the parent of the neighbor the current node.
                     */

                    visited[neighbor] = true;
                    queue.add(neighbor);
                    parents[neighbor] = current;
                }

            }

        }


        // We set the current equal to the end
        int current = end;

        // We will construct a path that starts at the "end" and goes to "start"
        List<Integer> path = new ArrayList<>();


        // While the parent is not -1. Remember the only value that will have -1 as its parent is start. The others have been updated
        while (parents[current] != -1) {

            // Add that node to the path
            path.add(current);

            // Make current it's parent now
            current = parents[current];

        }

        // Add start, since we will exit before it is added to the path
        path.add(start);

        // Reverse the path to get the right order
        Collections.reverse(path);

        // Call this function to actually see city names instead of integers.
        visualizePath(path);

    }

    public void visualizePath(List<Integer> path) {
        for (int city: path) {
            System.out.print(cityNames[city] + "-> ");
        }
    }


    public void dfs(int start, int end) {
        // Find if connected, print exploration path
        int[] stack = new int[numCities];
        int stackSize = 0;
        boolean[] visited = new boolean[numCities];
        stack[0] = start;
        visited[start] = true;
        stackSize++;

        boolean found = false;

        while (stackSize > 0 && !found) {
            int top = stack[stackSize - 1];
            stackSize--;
            for (int neighbor : adjList.get(top)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack[stackSize] = neighbor;
                    stackSize++;

                    if (neighbor == end) {
                        found = true;

                    }


                }
            }



        }

        List<Integer> path = new ArrayList<>(stackSize);
        for (int i = 0; i < stackSize; i++) {
            path.add(stack[i]);
        }

        System.out.println(path);

    }

    public static void main(String[] args) {
        String[] cities = {
                // Italy
                "Rome",           // 0
                "Florence",       // 1
                "Venice",         // 2
                "Naples",         // 3
                "Bologna",        // 4
                "Milan",          // 5
                "Genoa",          // 6
                "Turin",          // 7
                "Brindisi",       // 8
                "Palermo",        // 9

                // Iberian Peninsula (Hispania)
                "Carthago Nova",  // 10  (modern Cartagena)
                "Hispalis",       // 11  (modern Seville)
                "Emerita Augusta",// 12  (modern Merida)
                "Caesaraugusta",  // 13  (modern Zaragoza)
                "Tarraco",        // 14  (modern Tarragona)
                "Gades",          // 15  (modern Cadiz)
                "Bracara",        // 16  (modern Braga, Portugal)

                // Gaul (modern France)
                "Massilia",       // 17  (modern Marseille)
                "Lugdunum",       // 18  (modern Lyon)
                "Burdigala",      // 19  (modern Bordeaux)
                "Lutetia",        // 20  (modern Paris)
                "Narbo",          // 21  (modern Narbonne)
                "Arelate",        // 22  (modern Arles)

                // Britannia
                "Londinium",      // 23  (modern London)
                "Eboracum",       // 24  (modern York)
                "Camulodunum",    // 25  (modern Colchester)
                "Aquae Sulis",    // 26  (modern Bath)

                // Germania / Rhine Frontier
                "Colonia",        // 27  (modern Cologne)
                "Mogontiacum",    // 28  (modern Mainz)
                "Augusta Treverorum", // 29 (modern Trier)

                // Danube / Balkans
                "Vindobona",      // 30  (modern Vienna)
                "Aquincum",       // 31  (modern Budapest)
                "Sirmium",        // 32  (modern Serbia)
                "Naissus",        // 33  (modern Nis, Serbia)
                "Thessalonica",   // 34  (modern Thessaloniki)

                // Greece
                "Athens",         // 35
                "Corinth",        // 36
                "Sparta",         // 37

                // Asia Minor (modern Turkey)
                "Constantinople", // 38  (modern Istanbul)
                "Nicomedia",      // 39  (modern Izmit)
                "Ephesus",        // 40
                "Ancyra",         // 41  (modern Ankara)
                "Antioch",        // 42  (modern Antakya)

                // Levant / Middle East
                "Damascus",       // 43
                "Jerusalem",      // 44
                "Caesarea Maritima", // 45 (coastal Palestine)
                "Petra",          // 46  (modern Jordan)

                // Egypt & North Africa
                "Alexandria",     // 47
                "Memphis",        // 48
                "Cyrene",         // 49  (modern Libya)
                "Carthage",       // 50  (modern Tunisia)
                "Leptis Magna",   // 51  (modern Libya)
                "Caesarea",       // 52  (modern Algeria)
                "Tingis",         // 53  (modern Tangier, Morocco)

                // Mesopotamia (briefly held)
                "Ctesiphon",      // 54  (near modern Baghdad)
                "Seleucia",       // 55

                // Isolated / Hard to reach
                "Hadrians Wall",  // 56  (northern Britannia frontier)
                "Masada"          // 57  (fortress in Judea, remote)
        };


        RomanRoads network = new RomanRoads(cities.length, cities);
        System.out.println(cities.length);

// --- Italy internal ---
        network.addRoad(0, 1);   // Rome - Florence
        network.addRoad(0, 3);   // Rome - Naples
        network.addRoad(1, 4);   // Florence - Bologna
        network.addRoad(1, 6);   // Florence - Genoa
        network.addRoad(4, 5);   // Bologna - Milan
        network.addRoad(5, 7);   // Milan - Turin
        network.addRoad(5, 6);   // Milan - Genoa
        network.addRoad(3, 8);   // Naples - Brindisi
        network.addRoad(0, 9);   // Rome - Palermo (by sea)

// --- Italy to Gaul (via Alpine passes) ---
        network.addRoad(7, 18);  // Turin - Lugdunum
        network.addRoad(6, 17);  // Genoa - Massilia

// --- Gaul internal ---
        network.addRoad(17, 22); // Massilia - Arelate
        network.addRoad(22, 21); // Arelate - Narbo
        network.addRoad(21, 14); // Narbo - Tarraco (into Hispania)
        network.addRoad(17, 18); // Massilia - Lugdunum
        network.addRoad(18, 19); // Lugdunum - Burdigala
        network.addRoad(18, 20); // Lugdunum - Lutetia
        network.addRoad(18, 29); // Lugdunum - Augusta Treverorum
        network.addRoad(19, 16); // Burdigala - Bracara (into Hispania)

// --- Gaul to Britannia (sea crossing) ---
        network.addRoad(20, 23); // Lutetia - Londinium
        network.addRoad(29, 27); // Augusta Treverorum - Colonia

// --- Britannia internal ---
        network.addRoad(23, 25); // Londinium - Camulodunum
        network.addRoad(23, 26); // Londinium - Aquae Sulis
        network.addRoad(23, 24); // Londinium - Eboracum
        network.addRoad(24, 56); // Eboracum - Hadrian's Wall (frontier!)

// --- Germania / Rhine ---
        network.addRoad(27, 28); // Colonia - Mogontiacum
        network.addRoad(28, 29); // Mogontiacum - Augusta Treverorum
        network.addRoad(28, 30); // Mogontiacum - Vindobona (down the Danube)

// --- Danube / Balkans ---
        network.addRoad(30, 31); // Vindobona - Aquincum
        network.addRoad(31, 32); // Aquincum - Sirmium
        network.addRoad(32, 33); // Sirmium - Naissus
        network.addRoad(33, 34); // Naissus - Thessalonica
        network.addRoad(33, 38); // Naissus - Constantinople

// --- Hispania internal ---
        network.addRoad(14, 13); // Tarraco - Caesaraugusta
        network.addRoad(13, 12); // Caesaraugusta - Emerita Augusta
        network.addRoad(12, 11); // Emerita Augusta - Hispalis
        network.addRoad(11, 15); // Hispalis - Gades
        network.addRoad(11, 10); // Hispalis - Carthago Nova
        network.addRoad(10, 14); // Carthago Nova - Tarraco
        network.addRoad(12, 16); // Emerita Augusta - Bracara
        network.addRoad(15, 53); // Gades - Tingis (strait of Gibraltar, sea)

// --- North Africa ---
        network.addRoad(53, 52); // Tingis - Caesarea
        network.addRoad(52, 50); // Caesarea - Carthage
        network.addRoad(50, 51); // Carthage - Leptis Magna
        network.addRoad(51, 49); // Leptis Magna - Cyrene
        network.addRoad(49, 47); // Cyrene - Alexandria
        network.addRoad(47, 48); // Alexandria - Memphis
        network.addRoad(50, 9);  // Carthage - Palermo (sea)
        network.addRoad(0, 50);  // Rome - Carthage (sea, grain route)

// --- Egypt to Levant ---
        network.addRoad(47, 45); // Alexandria - Caesarea Maritima
        network.addRoad(45, 44); // Caesarea Maritima - Jerusalem
        network.addRoad(44, 43); // Jerusalem - Damascus
        network.addRoad(44, 46); // Jerusalem - Petra
        network.addRoad(45, 57); // Caesarea Maritima - Masada (remote fortress)

// --- Levant to Asia Minor ---
        network.addRoad(43, 42); // Damascus - Antioch
        network.addRoad(42, 40); // Antioch - Ephesus
        network.addRoad(42, 41); // Antioch - Ancyra
        network.addRoad(40, 39); // Ephesus - Nicomedia
        network.addRoad(39, 38); // Nicomedia - Constantinople
        network.addRoad(41, 39); // Ancyra - Nicomedia

// --- Greece ---
        network.addRoad(34, 35); // Thessalonica - Athens
        network.addRoad(35, 36); // Athens - Corinth
        network.addRoad(36, 37); // Corinth - Sparta
        network.addRoad(35, 38); // Athens - Constantinople (sea)
        network.addRoad(8, 34);  // Brindisi - Thessalonica (sea, Via Egnatia)

// --- Mesopotamia (Trajan's conquest, briefly held) ---
        network.addRoad(42, 54); // Antioch - Ctesiphon
        network.addRoad(54, 55); // Ctesiphon - Seleucia
// Ctesiphon and Seleucia are a dead-end cluster — barely connected to empire


      network.bfs(56, 57);
      network.dfs(0, 34);
    }
}