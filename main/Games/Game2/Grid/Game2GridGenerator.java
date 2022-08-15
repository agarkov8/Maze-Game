package team06.main.Games.Game2.Grid;

import java.util.ArrayList;
import java.util.List;

public class Game2GridGenerator {

    /**
     * Declare variables
     */
    private final List<Game2Cell> grid;

    public List<Game2Cell> getGrid() {
        return grid;
    }

    int gridSize;

    //Return the generated maze
    public List<Game2Cell> GetMaze() { return grid; }

    /**
     * Constructor for MazeGenerator. Directly creates a maze dependent on the given mazeSize
     * @param mazeSize the number of rows and columns the maze should have.
     */
    public Game2GridGenerator(int mazeSize) {
        this.gridSize = mazeSize;
        this.grid = new ArrayList<>();
        for (int x = 0; x < mazeSize; x++) {
            for (int y = 0; y < mazeSize; y++) {
                var newCell = new Game2Cell(x, y);
                drawCar(x,y, newCell);
                drawTank1(x,y,newCell);
                grid.add(newCell);
            }
        }
    }

//    public Game2GridGenerator(int mazeSize, boolean btnIsClicked) {
//        btnIsClicked = Game2FieldPanel.plusIsClicked;
//        this.gridSize = mazeSize;
//        this.grid = new ArrayList<>();
//        if (btnIsClicked) {
//            for (int x = 0; x < mazeSize; x++) {
//                for (int y = 0; y < mazeSize; y++) {
//                    var newCell = new Game2Cell(x, y);
//                    drawCar(x, y, newCell);
//                    drawTank1(x, y, newCell);
//                    drawTank2(x, y, newCell);
//                    grid.add(newCell);
//                }
//            }
//        }
//    }

    public void drawCar(int x, int y, Game2Cell cell){
            if (x == 1 && y == 13){
                cell.walls[2] = true;
            } else if (x == 2 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 3 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 4 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 5 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 6 && y == 13){
                cell.walls[2] = true;
            } else if (x == 7 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 8 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 9 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 10 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 11 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 12 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 13 && y == 13) {
                cell.walls[2] = true;
            } else if (x == 14 && y == 13) {
                cell.walls[2] = true;
            }
    }

    public void drawTank1(int x, int y, Game2Cell cell){
        if (x == 11 && y == 9){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 11 && y == 10){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 11 && y == 11){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 11 && y == 12){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 11 && y == 13){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 14 && y == 9){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 14 && y == 10){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 14 && y == 11){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 14 && y == 12){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 14 && y == 13){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 12 && y == 9){
            cell.setTarget(true);
        } else if (x == 12 && y == 10){
            cell.setTarget(true);
        } else if (x == 12 && y == 11){
            cell.setTarget(true);
        } else if (x == 12 && y == 12){
            cell.setTarget(true);
        } else if (x == 12 && y == 13){
            cell.setTarget(true);
        } else if (x == 13 && y == 9){
            cell.setTarget(true);
        } else if (x == 13 && y == 10){
            cell.setTarget(true);
        } else if (x == 13 && y == 11){
            cell.setTarget(true);
        } else if (x == 13 && y == 12){
            cell.setTarget(true);
        } else if (x == 13 && y == 13){
            cell.setTarget(true);
        }
    }

    public void drawTank2(int x, int y, Game2Cell cell){
        if (x == 5 && y == 9){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 5 && y == 10){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 5 && y == 11){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 5 && y == 12){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 5 && y == 13){
            cell.walls[3] = true;
            cell.setTarget(true);
        } else if (x == 8 && y == 9){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 8 && y == 10){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 8 && y == 11){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 8 && y == 12){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 8 && y == 13){
            cell.walls[1] = true;
            cell.setTarget(true);
        } else if (x == 6 && y == 9){
            cell.setTarget(true);
        } else if (x == 6 && y == 10){
            cell.setTarget(true);
        } else if (x == 6 && y == 11){
            cell.setTarget(true);
        } else if (x == 6 && y == 12){
            cell.setTarget(true);
        } else if (x == 6 && y == 13){
            cell.setTarget(true);
        } else if (x == 7 && y == 9){
            cell.setTarget(true);
        } else if (x == 7 && y == 10){
            cell.setTarget(true);
        } else if (x == 7 && y == 11){
            cell.setTarget(true);
        } else if (x == 7 && y == 12){
            cell.setTarget(true);
        } else if (x == 7 && y == 13){
            cell.setTarget(true);
        }
    }

    public static Game2Cell GetCellAtCoordinate(List<Game2Cell> maze, int x, int y){
        for (var cell: maze) {
            if(cell.getX() == x && cell.getY() == y)
                return cell;
        }
        return null;
    }
}
