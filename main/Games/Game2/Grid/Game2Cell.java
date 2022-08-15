package team06.main.Games.Game2.Grid;

import java.awt.*;

public class Game2Cell {

    private int x;
    private int y;
    private int distance;
    private boolean visited = false;
    private boolean shouldBeDisplayed = true;
//    private boolean path = false;
    private boolean deadEnd = false;
//    public boolean isStart = false;
//    public boolean isCopiedStart = false;
    private boolean isTarget = false;
    public boolean containsPlayer=false; //containsAFigure



    /**
     * Declare a boolean array, which specifies the walls (top, right, bottom, left)
     */
    //With walls
//     boolean[] walls = {true, true, true, true};

    //No walls
     boolean[] walls = {false, false, false, false};

    public static Game2Cell CreateEdgeCell(int x, int y, boolean isTarget){
        var cell = new Game2Cell(x,y);
        cell.isTarget = isTarget;
        cell.shouldBeDisplayed = isTarget;
        if(isTarget)
            cell.walls = new boolean[]{true, x != 0, true, x == 0}; //TODO: Must change the values of the array

        return cell;
    }

    /**
     * Constructors
     */
    public Game2Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = -1;
    }

    public Game2Cell(int x, int y, Game2Cell copyMazeCell) {
        this.x = x;
        this.y = y;
        this.distance = copyMazeCell.distance;
        this.visited = copyMazeCell.visited;
        this.deadEnd = copyMazeCell.deadEnd;
        this.isTarget = copyMazeCell.isTarget;
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

//    public boolean isStart() {
//        return isStart;
//    }

//    public void setStart(boolean isStart) {
//        this.isStart = isStart;
//        containsPlayer = true;
//        pathList.add(this);
//    }
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

    public void draw(Graphics g, int cellWidth) {

        int x2 = x * cellWidth;
        int y2 = y * cellWidth;


        if(isTarget){
            displayAsColor(g, Color.CYAN, cellWidth);
        }

        if(containsPlayer){
            displayAFigure(g, Color.RED, cellWidth);
        }

        //TODO: Should they be adjusted?
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

    public void displayAFigure(Graphics g, Color color, int cellWidth) {
        int playerX = x * cellWidth + (cellWidth / 4);
        int playerY = y * cellWidth + (cellWidth / 4);
        g.setColor(color);
        //TODO: Using getRessource add a figure
        g.fillOval(playerX, playerY, cellWidth/2, cellWidth/2);
    }

    /**
     * Removes walls of maze cells
     */
    public void removeWalls() {
        // top 0, right 1, bottom 2, left 3

        if(x == 1) {
            walls[3] = false;
        } else if (x == -1) {
            walls[1] = false;
        }

        if(y == 1) {
            walls[0] = false;
        } else if (y == -1) {
            walls[2] = false;
        }
    }

    public void addWall(){
        if (x == 2 && y == 4){
            walls[3] = true;
        }
    }

}
