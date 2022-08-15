package team06.main.MazeGame.Maze;

import team06.main.MazeGeneration.MazeCell;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Maze extends JPanel {

    private final List<MazeCell> gridMaze;
    private final int mazeSize;
    private MazePanel mazePanel;

    //Constructor
    public Maze(List<MazeCell> gridMaze, MazePanel mazePanel, int mazeSize){
        super();
        this.mazePanel = mazePanel;
        this.gridMaze = gridMaze;
        this.mazeSize = mazeSize + 2;
    }

    @Override
    public Dimension getPreferredSize() {
        // +1 pixel on width and height so bottom and right borders can be drawn.
        var cellWidth = Math.min(mazePanel.getHeight(), mazePanel.getWidth()) / mazeSize;
        var size = (mazeSize * cellWidth) - cellWidth * 2;
        var sizeForX = (mazeSize * cellWidth);
        return new Dimension(sizeForX, size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var cellWidth = Math.min(mazePanel.getHeight(), mazePanel.getWidth()) / mazeSize;

        for (MazeCell c : gridMaze) {
            c.draw(g, cellWidth);
        }
    }
}
