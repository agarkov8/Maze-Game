package team06.main.Games.Game2.GraphColoring;

import java.util.*;

public class GraphColoringProblem {

//    private String[] fishSpecies = {"AF","GF","G","S","KF",
//            "P", "R","E","T","RF","B" };

    ArrayList<Integer> randomNumberOfFishes;

    private ArrayList<String> fishSpecies;

    private List<String>[] fishConflicts;
    private boolean[] fishExist = {false, false, false, false, false, false, false, false, false, false, false};

    public boolean[] getFishExist() {
        return fishExist;
    }


    private ArrayList<String> fishHistory;
    private List<String>[] adj;
    private int numberOfFishes;
    int vertex = 0;
    String colors[];

    public String[] getColors() {
        return colors;
    }

    int maxColorNumber;

    public int getMaxColorNumber() {
        return maxColorNumber;
    }
    //    boolean[] alreadyExist = {false, false, false, false};

    public int getNumberOfFishes() {
        return numberOfFishes;
    }

    public List<String>[] getAdj() {
        return adj;
    }


    public ArrayList<String> getFishSpecies() {
        return fishSpecies;
    }

    public void setFishSpecies(ArrayList<String> fishSpecies) {
        this.fishSpecies = fishSpecies;
    }

    public List<String>[] getFishConflicts() {
        return fishConflicts;
    }

    public void setFishConflicts(List<String>[] fishConflicts) {
        this.fishConflicts = fishConflicts;
    }

    public ArrayList<String> getFishHistory() {
        return fishHistory;
    }

    public void setFishHistory(ArrayList<String> fishHistory) {
        this.fishHistory = fishHistory;
    }

    public void setAdj(List<String>[] adj) {
        this.adj = adj;
    }

    public void setNumberOfFishes(int numberOfFishes) {
        this.numberOfFishes = numberOfFishes;
    }


    //Constructor Original was with GraphColoringProblem(int numberOfFishes, int maxNumberOfConflicts)
    public GraphColoringProblem(int numberOfFishes) {
        this.numberOfFishes = numberOfFishes;
        fishSpecies = new ArrayList<>();
        fillFishList();
        fishConflicts = (List<String>[]) new List[fishSpecies.size()];
        adj = (List<String>[]) new List[numberOfFishes];
        fishHistory = new ArrayList<>();
        for (int i = 0; i < numberOfFishes; i++) {
            adj[i] = new ArrayList<String>();
        }
        for (int i = 0; i < fishSpecies.size(); i++) {
            fishConflicts[i] = new ArrayList<String>();
        }
        fillFishConflictsList();

//Math.floor(Math.random() * (myMax - myMin + 1) + myMin);
        addEdges(numberOfFishes);
        greedyColoring();
    }


    public void fillFishList() {
        fishSpecies.add("AF");
        fishSpecies.add("GF");
        fishSpecies.add("G");
        fishSpecies.add("S");
        fishSpecies.add("KF");
        fishSpecies.add("P");
        fishSpecies.add("R");
        fishSpecies.add("E");
        fishSpecies.add("T");
        fishSpecies.add("RF");
        fishSpecies.add("B");
    }

    //Add all fishes to the ConflictList
    public void fillFishConflictsList() {
        fishConflicts[0].add("GF");
        fishConflicts[0].add("G");
        fishConflicts[0].add("E");
        fishConflicts[0].add("KF");
        fishConflicts[1].add("AF");
        fishConflicts[1].add("G");
        fishConflicts[1].add("S");
        fishConflicts[1].add("KF");
        fishConflicts[1].add("P");
        fishConflicts[1].add("R");
        fishConflicts[2].add("AF");
        fishConflicts[2].add("GF");
        fishConflicts[2].add("T");
        fishConflicts[2].add("B");
        fishConflicts[3].add("GF");
        fishConflicts[3].add("KF");
        fishConflicts[3].add("E");
        fishConflicts[3].add("B");
        fishConflicts[3].add("R");
        fishConflicts[4].add("GF");
        fishConflicts[4].add("S");
        fishConflicts[4].add("AF");
        fishConflicts[5].add("GF");
        fishConflicts[5].add("T");
        fishConflicts[6].add("GF");
        fishConflicts[6].add("S");
        fishConflicts[7].add("AF");
        fishConflicts[7].add("S");
        fishConflicts[7].add("B");
        fishConflicts[8].add("G");
        fishConflicts[8].add("P");
        fishConflicts[8].add("RF");
        fishConflicts[9].add("T");
        fishConflicts[9].add("B");
        fishConflicts[10].add("S");
        fishConflicts[10].add("G");
        fishConflicts[10].add("E");
        fishConflicts[10].add("RF");
    }

    public void getAnotherFish() {
        int r = (int) Math.floor(Math.random() * ((fishSpecies.size() - 1) + 1) + 0);
        if (!fishHistory.contains(fishSpecies.get(r))) {
            fishHistory.add(fishSpecies.get(r));
            System.out.println("The fish is: " + fishSpecies.get(r));
        } else {
            getAnotherFish();
        }
    }


    //Function to add an edge into the graph
    void addEdges(int numberOfFishes) {
        for (int i = 0; i < numberOfFishes; i++) {
            int r = (int) Math.floor(Math.random() * ((fishSpecies.size() - 1) + 1) + 0);
            if (!fishHistory.contains(fishSpecies.get(r))) {
                fishHistory.add(fishSpecies.get(r));
                System.out.println("The fish is: " + fishSpecies.get(r));
            } else {
                getAnotherFish();
            }
        }

        for (int i = 0; i < fishHistory.size(); i++) { //i=0
            int posFishList = fishSpecies.indexOf(fishHistory.get(i)); // index: 0
            for (int j = 0; j < fishConflicts[posFishList].size(); j++) {
                if (fishHistory.contains(fishConflicts[posFishList].get(j)) && !adj[i].contains(fishConflicts[posFishList].get(j))) {
                    int pos = fishHistory.indexOf(fishConflicts[posFishList].get(j));
                    adj[i].add(fishConflicts[posFishList].get(j));
                    adj[pos].add(fishHistory.get(i));
                    System.out.println(fishHistory.get(i) + " is connected to " + fishHistory.get(pos));
                }
            }
        }

            for (int j = 0; j < fishSpecies.size();j++){
                if (fishHistory.contains(fishSpecies.get(j))){
                    fishExist[j] = true;
                }
            }

    }


    private void addLastFishToList() {
        int lastRandom = (int) Math.floor(Math.random() * ((fishSpecies.size() - 1) + 1) + 0);
        if (!fishHistory.contains(fishSpecies.get(lastRandom))) {
            fishHistory.add(fishSpecies.get(lastRandom));
        } else {
            addLastFishToList();
        }
    }

    //Function that checks if a certain vertex has another element in its arrayList
    public boolean hasEdge(int i, int j) {
        return adj[i].contains(j);
    }



    /**
     * Assign colors (starting from 0) to all vertices and prints the assignment of colors
     */
    public void greedyColoring() {

        colors = new String[numberOfFishes];
        //An array that holds the colors
        String colorNames[] = {"Red", "Blue", "Purple", "Orange", "White", "Green", "Yellow", "Brown"};

        Arrays.fill(colors, null);

        //Assign the color to the first vertex
        colors[0] = colorNames[0];

        //A boolean array that holds which colors are available to use
        boolean availableColors[] = new boolean[numberOfFishes];
        Arrays.fill(availableColors, true);

        /**
         * Iterates through the graph.
         * Checks which colors are available to use.
         * If the available color is "true" then assign this color to the vertex.
         * Reset the available color List.
         * Print the final statement
         */
        for (int u = 1; u < numberOfFishes; u++) {
            Iterator<String> it = adj[u].iterator(); // int it = adj[u].iterator();
            while (it.hasNext()) {
                String currentVertex = it.next(); // GF  I 0
                int t = 0;
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentVertex) {
                        t = i;
                        break;
                    }
                }
                if (colors[fishHistory.indexOf(currentVertex)] != null){
                    for (int i = 0; i < colorNames.length; i++){
                        if (colorNames[i] == colors[fishHistory.indexOf(currentVertex)]){
                            availableColors[i] = false;
                        }
                    }
                }

                if (colors[t] != null) { //colors[i] != -1
                    availableColors[t] = false;
                }
            }

            String avColor = "";
            for (int color = 0; color < numberOfFishes; color++) {
                if (availableColors[color]) {
                    avColor = colorNames[color];
                    break;
                }
            }

            colors[u] = avColor;
            Arrays.fill(availableColors, true);
        }

        ArrayList<String> singleMaxColors = new ArrayList<>();

        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colorNames.length; j++) {
                if (colors[i].contains(colorNames[j]) && !singleMaxColors.contains(colorNames[j])) {
                    singleMaxColors.add(colorNames[j]);
                    break;
                }
            }
        }
        maxColorNumber = singleMaxColors.size();

        for (int u = 0; u < numberOfFishes; u++) {
            if (Objects.equals(colors[u], colorNames[0])) {
                System.out.println("Vertex " + fishHistory.get(u) + " --->" + colorNames[0]);
            } else if (Objects.equals(colors[u], colorNames[1])) {
                System.out.println("Vertex " + fishHistory.get(u) + " --->" + colorNames[1]);
            } else if (Objects.equals(colors[u], colorNames[2])) {
                System.out.println("Vertex " + fishHistory.get(u) + " --->" + colorNames[2]);
            } else if (Objects.equals(colors[u], colorNames[3])) {
                System.out.println("Vertex " + fishHistory.get(u) + " --->" + colorNames[3]);
            } else if (Objects.equals(colors[u], colorNames[4])) {
                System.out.println("Vertex " + fishHistory.get(u) + " --->" + colorNames[4]);
            } else if (Objects.equals(colors[u], colorNames[5])) {
                System.out.println("Vertex " + fishHistory.get(u) + " --->" + colorNames[5]);
            } else if (Objects.equals(colors[u], colorNames[6])) {
                System.out.println("Vertex " + fishHistory.get(u) + " --->" + colorNames[6]);
            } else if (Objects.equals(colors[u], colorNames[7])) {
                System.out.println("Vertex " + fishHistory.get(u) + " --->" + colorNames[7]);
            }
        }
    }


//    public static void main(String[] args) {
//        GraphColoringProblem graph1 = new GraphColoringProblem(4);
//        System.out.println(graph1.fishHistory);
//    }
}


