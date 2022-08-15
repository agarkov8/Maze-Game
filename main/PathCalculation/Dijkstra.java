package team06.main.PathCalculation;

import team06.main.MazeGeneration.MazeCell;
import java.util.*;

public class Dijkstra {

    /**
     * Variables of this class
     */
    private final Queue<MazeCell> queue;
    private MazeCell evaluationNode;
    private final CellDistanceFromGoalComparator cellComparer;
    private final List<MazeCell> gridMaze;
    //Holds the cell history of the Dijkstra Path
    public static List<MazeCell> dijkstraPathHistory = new ArrayList<>();

    /**
     * Constructor
     * @param gridMaze
     */
    public Dijkstra(List<MazeCell> gridMaze) {
        this.gridMaze = gridMaze;
        cellComparer = new CellDistanceFromGoalComparator();
        queue = new PriorityQueue<>(cellComparer);
        RunDijkstra(gridMaze);
    }

    /**
     * Run Dijkstra algorithm with the option to reset the existing maze and a previously calculated path
     * @param gridMaze the maze to be calculated
     * @param secondRun indicator if Dijkstra is being run the second time, therefore if true the previous run is reset
     */
    public Dijkstra(List<MazeCell> gridMaze, boolean secondRun){
        if(secondRun)
        {
            for(var cell: gridMaze){
                cell.setPath(false);
                cell.setDeadEnd(false);
                cell.setDistance(-1);
                cell.setParent(null);
                cell.possibleParents = new ArrayList<>();
            }
        }

        this.gridMaze = gridMaze;
        cellComparer = new CellDistanceFromGoalComparator();
        queue = new PriorityQueue<>(cellComparer);
        RunDijkstra(gridMaze);
    }


    //Starts the Dijkstra Algo
    private void RunDijkstra(List<MazeCell> gridMaze) {
        evaluationNode = gridMaze.get(gridMaze.size() -1);
        evaluationNode.setDistance(0);
        queue.offer(evaluationNode);

        while(true){
            if (!evaluationNode.equals(gridMaze.get(0))){
                flood();
            } else {
                drawPath();
                break;
            }
        }
    }

    /**
     * This method "floods" the cells in the maze trying
     * to calculate the shortest path
     */
    private void flood(){
        evaluationNode.setDeadEnd(true);
        evaluationNode = queue.poll();
        List<MazeCell> adjacentCells = evaluationNode.getValidMoveNeighbours(gridMaze);
        for (MazeCell c: adjacentCells){
            c.possibleParents.add(evaluationNode);
            if (c.getDistance() == -1){
                c.setDistance(evaluationNode.getDistance() + 1);
                queue.offer(c);
            }
        }

    }

    //Draws the path of Dijkstra
    private void drawPath(){
        var additionalNode = drawPathLoop(evaluationNode);
        if(additionalNode != null)
            drawPathLoop(additionalNode);

        //The goal node and the start node must also be added
        dijkstraPathHistory.add(gridMaze.get(0));
        dijkstraPathHistory.add(gridMaze.get(gridMaze.size() -1));
    }

    /**
     * loop over the parents of the nodeToFollow to find the shortest path
     * @param nodeToFollow the node which parents should be followed
     * @return a new node to follow in case of two paths with the same length
     */
    private MazeCell drawPathLoop(MazeCell nodeToFollow) {
        MazeCell additionalNode = null;
        while(nodeToFollow != gridMaze.get(gridMaze.size()-1)){
            nodeToFollow.setPath(true);
            dijkstraPathHistory.add(nodeToFollow);
            nodeToFollow.possibleParents.sort(cellComparer);
            if(nodeToFollow.possibleParents.size() > 1 &&
                cellComparer.compare(nodeToFollow.possibleParents.get(0), nodeToFollow.possibleParents.get(1)) == 0)
                    additionalNode = nodeToFollow.possibleParents.get(1);
            nodeToFollow.setParent(nodeToFollow.possibleParents.get(0));
            nodeToFollow = nodeToFollow.getParent();
        }
        return additionalNode;
    }

    /**
     * This inner class of the main class.
     * Compares cells to find the minimal
     * distance from the goal cell.
     */
    private static class CellDistanceFromGoalComparator implements Comparator<MazeCell>{

        //This method compares 2 nodes
        @Override
        public int compare(MazeCell node1, MazeCell node2) {
            if (getDistanceFromGoal(node1) > getDistanceFromGoal(node2)){
                return 1;
            } else {
                return getDistanceFromGoal(node1) < getDistanceFromGoal(node2) ? -1 : 0;
            }
        }

        private double getDistanceFromGoal(MazeCell cell){
            return cell.getDistance();
        }
    }
}
