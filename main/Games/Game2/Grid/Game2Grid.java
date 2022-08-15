package team06.main.Games.Game2.Grid;


import team06.main.Games.Game2.GameUI.Game2FieldPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Game2Grid extends JPanel {

    private final List<Game2Cell> grid;
    private final int mazeSize;
    private Game2FieldPanel fieldPanel;

    //Constructor
    public Game2Grid(List<Game2Cell> grid, Game2FieldPanel fieldPanel, int mazeSize){
        super();
        this.grid = grid;
        this.mazeSize = mazeSize + 2;
        this.fieldPanel = fieldPanel;
        this.setPreferredSize(new Dimension(150,300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        var cellWidth = Math.min(fieldPanel.getHeight(), fieldPanel.getWidth()) / mazeSize;
        var cellWidth = 20;
        for (Game2Cell c : grid) {
            c.draw(g, cellWidth);
        }
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (Game2FieldPanel.plusIsClicked){
//            new Game2GridGenerator(mazeSize, Game2FieldPanel.plusIsClicked);
//        }
//    }
}
