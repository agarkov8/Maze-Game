package team06.main.MazeGame;

import team06.main.Games.AdditionalGame;
import team06.main.Games.Game1.BackPackFrame;
import team06.main.Games.Game2.FishFrame;
import team06.main.Games.Game3.MSTFrame;
import team06.main.Games.Game4.BurgFrame;
import team06.main.Games.Shared.GameState;
import team06.main.MazeGame.Maze.MazePanel;
import team06.main.MazeGame.SideMenu.SideMenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * A JFrame consisting of the Maze game and side menu
 */
public class MainFrame extends JFrame {
	
    private static CardLayout cl;
    private static JPanel CardPanel;

    /**
     * Create default MainFrame with the maze game running and side menu for administrative functions
     */
    public MainFrame(){
        super();

        SetupFrame();

        var gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        cl = new CardLayout();
        CardPanel = new JPanel(cl);

        var startGamePanel = CreateStartGamePanel(gridBagConstraints);
        var gameOne = new BackPackFrame();
        var gameTwo = new FishFrame();
        var gameThree = new MSTFrame();
        var gameFour = new BurgFrame();

        CardPanel.add(startGamePanel, "GameStart");
//        game2Panel.add(new JLabel("Game 2 aka Fische-Spiel"));
        CardPanel.add(gameOne, "GameOne");
        CardPanel.add(gameTwo, "GameTwo");
        //gameThree.add(new JLabel("Game 3 aka MST-Spiel"));
        CardPanel.add(gameThree, "GameThree");
//      game4Panel.add(new JLabel("Game 4 aka Burgen-Spiel"));
        CardPanel.add(gameFour, "GameFour");

        add(CardPanel, gridBagConstraints);
        pack();
        setVisible(true);
    }

    public static void switchCard(String name)
    {
        cl.show(CardPanel, name);
        var mazeComponent = (Container) CardPanel.getComponent(GetCardComponentByName(name));
        GameState.CurrentGame = name;
        mazeComponent.getComponent(0).requestFocusInWindow();
    }

    private static int GetCardComponentByName(String name){
        switch (name){
            case "GameStart": return 0;
            case "GameOne": return 1;
            case "GameTwo": return 2;
            case "GameThree": return 3;
            case "GameFour": return 4;
            default: return -1;
        }

    }

    private JPanel CreateStartGamePanel(GridBagConstraints gridBagConstraints) {
        var startGamePanel = new JPanel(new GridBagLayout());
        startGamePanel.setFocusable(false);
        CreateMazePanel(gridBagConstraints, startGamePanel);
        CreateSidePanel(gridBagConstraints, startGamePanel);
        return startGamePanel;
    }


    /**
     * Set the basic properties of the frame.
     */
    private void SetupFrame() {
        setSize(600, 600);
        setMinimumSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(1000, 800));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
    }

    /**
     * Set both Panels to fill, when the size of frame has been changed
     * Set 75% of the frame for MazePanel
     * Set 25% of the frame for SidePanel
     */
    private void CreateMazePanel(GridBagConstraints constraints, JPanel panel) {
    	constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.8;
        constraints.weighty = 1;
        panel.add(new MazePanel(), constraints);
        
    }

    private void CreateSidePanel(GridBagConstraints constraints, JPanel panel) {
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.weighty = 1;
        panel.add(new SideMenuPanel(), constraints);
    }
}