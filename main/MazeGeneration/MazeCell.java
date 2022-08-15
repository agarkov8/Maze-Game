package team06.main.MazeGeneration;


import team06.main.MazeGame.Maze.MazePanel;
import team06.main.MazeGame.Player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazeCell {
	
    private int x;
    private int y;
    private int distance;
    private boolean visited = false;
    private boolean shouldBeDisplayed = true;
    private boolean path = false;
    private boolean deadEnd = false;
    public boolean isStart = false;
    public boolean isCopiedStart = false;
	private boolean isTarget = false;
	public boolean containsPlayer=false;
	private MazeCell parent;
    public List<MazeCell> possibleParents = new ArrayList<>();

    private static List<MazeCell> pathList = new ArrayList<>();
    private static int startStepsCounter = 0;
    
    /**
     * Declare a boolean array, which specifies the walls (top, right, bottom, left)
     */
    private boolean[] walls = {true, true, true, true};

    public static MazeCell CreateEdgeCell(int x, int y, boolean isTarget){
        var cell = new MazeCell(x,y);
        cell.isTarget = isTarget;
        cell.shouldBeDisplayed = isTarget;
        if(isTarget)
            cell.walls = new boolean[]{true, x != 0, true, x == 0};

        return cell;
    }

    //Resets the maze
    public static void Reset(MazeCell startCell){
        Player.setPlayerOnShortestPath(true);
        startStepsCounter = 0;
        pathList = new ArrayList<>();
        pathList.add(startCell);
    }

    public static void ResetOneStep(MazeCell previousCell){
        if(previousCell.isCopiedStart || previousCell.isStart)
            startStepsCounter = startStepsCounter-2;
        pathList.remove(previousCell);
    }

    /**
     * Constructors
     */
    public MazeCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = -1;
    }

    public MazeCell(int x, int y, MazeCell copyMazeCell) {
        this.x = x;
        this.y = y;
        this.distance = copyMazeCell.distance;
        this.visited = copyMazeCell.visited;
        this.deadEnd = copyMazeCell.deadEnd;
        this.isTarget = copyMazeCell.isTarget;
        this.path = copyMazeCell.path;
        this.isCopiedStart = copyMazeCell.isStart || copyMazeCell.isCopiedStart;
        if(copyMazeCell.isStart()) {
        	this.path = true;
        }
    }

    /**
     * Getters and Setters
     */
    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }
    
    public void removePlayer() {
    	containsPlayer = false;
    }

    public void setPlayer() {
    	containsPlayer = true;
        if(isStart ||isCopiedStart)
            startStepsCounter++;
        if(pathList.contains(this) || startStepsCounter >= 3)
            Player.setPlayerOnShortestPath(false);
        pathList.add(this);
    }
    public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
        containsPlayer = true;
        pathList.add(this);
	}
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isTarget() {
        return isTarget;
    }

    public boolean getContainsPlayer(){
        return containsPlayer;
    }

    public void setTarget(boolean isTarget) {
        this.isTarget = isTarget;
    }

    public boolean isDeadEnd() {
        return deadEnd;
    }

    public void setDeadEnd(boolean deadEnd) {
        this.deadEnd = deadEnd;
    }

    public boolean isPath() {
        return path;
    }

    public void setPath(boolean path) {
        this.path = path;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public MazeCell getParent() {
        return parent;
    }

    public void setParent(MazeCell parent) {
        this.parent = parent;
    }
    
    public static List<MazeCell> getPathList() {
        return pathList;
    }

    public void draw(Graphics g, int cellWidth) {
        if(!shouldBeDisplayed) {
            displayAsColor(g, Color.BLACK, cellWidth);
            return;
        }
        int x2 = x * cellWidth;
        int y2 = y * cellWidth;

        if(isStart) {
        	displayAsColor(g, Color.lightGray, cellWidth);
        }
        else if(isTarget){
            displayAsColor(g, Color.blue, cellWidth);
        }

        if(containsPlayer && (path || isTarget || isStart) && Player.getPlayerOnShortestPath()) {
        	displayPlayer(g, Color.GREEN, cellWidth);
        }
        else if(containsPlayer){
            displayPlayer(g, Color.RED, cellWidth);
            Player.setPlayerOnShortestPath(false);
        }



        if(pathList.contains(this)){
            displayPath(g, Color.BLACK, cellWidth);
        }


        g.setColor(Color.BLACK);
        if (walls[0]) {
            g.drawLine(x2, y2, x2+cellWidth, y2);
        }
        if (walls[1]) {
            g.drawLine(x2+cellWidth, y2, x2+cellWidth, y2+cellWidth);
        }
        if (walls[2]) {
            g.drawLine(x2+cellWidth, y2+cellWidth, x2, y2+cellWidth);
        }
        if (walls[3]) {
            g.drawLine(x2, y2+cellWidth, x2, y2);
        }
    }

    public void displayAsColor(Graphics g, Color color, int cellWidth) {
        int x2 = x * cellWidth;
        int y2 = y * cellWidth;
        g.setColor(color);
        g.fillRect(x2, y2, cellWidth, cellWidth);
    }

    public void displayPlayer(Graphics g, Color color, int cellWidth) {
        int playerX = x * cellWidth + (cellWidth / 4);
        int playerY = y * cellWidth + (cellWidth / 4);
        g.setColor(color);
        g.fillOval(playerX, playerY, cellWidth/2, cellWidth/2);
    }
    
    public void displayPath(Graphics g, Color color, int cellWidth) {
        int x2 = x * cellWidth + (cellWidth / 4);
        int y2 = y * cellWidth + (cellWidth / 4);
        g.setColor(color);
        g.drawOval(x2, y2, cellWidth/2, cellWidth/2);
    }

    /**
     * Removes walls of maze cells
     * @param next
     */
    public void removeWalls(MazeCell next) {
        int x = this.x - next.x;
        // top 0, right 1, bottom 2, left 3

        if(x == 1) {
            walls[3] = false;
            next.walls[1] = false;
        } else if (x == -1) {
            walls[1] = false;
            next.walls[3] = false;
        }

        int y = this.y - next.y;

        if(y == 1) {
            walls[0] = false;
            next.walls[2] = false;
        } else if (y == -1) {
            walls[2] = false;
            next.walls[0] = false;
        }
    }

    private MazeCell checkNeighbourInGridBounds(List<MazeCell> mazeGrid, MazeCell neighbour) {
        if (mazeGrid.contains(neighbour)) {
            return mazeGrid.get(mazeGrid.indexOf(neighbour));
        } else {
            return null;
        }
    }

    /**
     * No walls between two neighbour cells
     * @param mazeGrid
     */
    public List<MazeCell> getValidMoveNeighbours(List<MazeCell> mazeGrid) {
        List<MazeCell> neighbours = new ArrayList<MazeCell>(4);

        MazeCell top = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x, y - 1));
        MazeCell right = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x + 1, y));
        MazeCell bottom = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x, y + 1));
        MazeCell left = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x - 1, y));

        if (top != null) {
            if (!walls[0]) neighbours.add(top);
        }

        if (right != null) {
            if (!walls[1]) neighbours.add(right);
        }

        if (bottom != null) {
            if (!walls[2]) neighbours.add(bottom);
        }

        if (left != null) {
            if (!walls[3]) neighbours.add(left);
        }

        return neighbours;
    }


    public List<MazeCell> getAllNeighbours(List<MazeCell> mazeGrid) {
        List<MazeCell> neighbours = new ArrayList<MazeCell>();

        MazeCell top = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x, y - 1));
        MazeCell right = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x + 1, y));
        MazeCell bottom = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x, y + 1));
        MazeCell left = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x - 1, y));

        if (top != null) neighbours.add(top);
        if (right != null) neighbours.add(right);
        if (bottom != null) neighbours.add(bottom);
        if (left != null) neighbours.add(left);

        return neighbours;
    }

    public MazeCell getNeighbourByWallNumber(List<MazeCell> mazeGrid, int wallNumber){
        switch (wallNumber){
            case 0: return getTopNeighbour(mazeGrid);
            case 1: return getRightNeighbour(mazeGrid);
            case 2: return getBottomNeighbour(mazeGrid);
            case 3: return getLeftNeighbour(mazeGrid);
            default: return null;
        }

    }

    public MazeCell getTopNeighbour(List<MazeCell> mazeGrid) {
        return checkNeighbourInGridBounds(mazeGrid, new MazeCell(x, y - 1));
    }

    public MazeCell getRightNeighbour(List<MazeCell> mazeGrid) {
        return checkNeighbourInGridBounds(mazeGrid, new MazeCell(x + 1, y));
    }

    public MazeCell getBottomNeighbour(List<MazeCell> mazeGrid) {
        return checkNeighbourInGridBounds(mazeGrid, new MazeCell(x, y + 1));
    }

    public MazeCell getLeftNeighbour(List<MazeCell> mazeGrid) {
        return checkNeighbourInGridBounds(mazeGrid, new MazeCell(x - 1, y));
    }

    // checks whether the player can move in this direction
    public MazeCell getTopNeighbourIfValid(List<MazeCell> mazeGrid) {
    	var neighbour = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x, y - 1));
    	if (neighbour != null) {
            if (!walls[0]) return neighbour;
        }
    	return null;
    }

    public MazeCell getRightNeighbourIfValid(List<MazeCell> mazeGrid) {
    	var neighbour = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x + 1, y));
    	if (neighbour != null) {
            if (!walls[1]) return neighbour;
        }
    	return null;
    }

    public MazeCell getBottomNeighbourIfValid(List<MazeCell> mazeGrid) {
    	var neighbour = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x, y + 1));
    	if (neighbour != null) {
            if (!walls[2]) return neighbour;
        }
    	return null;
    }

    public MazeCell getLeftNeighbourIfValid(List<MazeCell> mazeGrid) {
    	var neighbour = checkNeighbourInGridBounds(mazeGrid, new MazeCell(x - 1, y));
    	if (neighbour != null) {
            if (!walls[3]) return neighbour;
        }
    	return null;
    	}

    public MazeCell getStartNeighbourIfValid(List<MazeCell> mazeGrid) {
        var neighbour =
                checkNeighbourInGridBounds(mazeGrid, new MazeCell(MazePanel.getMazeSize(), MazePanel.getMazeSize() -1));
        return neighbour;
    }

    public MazeCell getPreviousNeighbourIfValid(List<MazeCell> mazeGrid) {
        var pathSize = pathList.size();
        if (pathList.size() < 2) {
            pathSize = 0;
        }
        else{
            pathSize = pathSize -2;
        }
        var neighbour = checkNeighbourInGridBounds(mazeGrid, pathList.get(pathSize));
        return neighbour;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MazeCell other = (MazeCell) obj;
        if (x != other.x)
            return false;
        return y == other.y;
    }
}