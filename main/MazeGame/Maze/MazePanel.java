package team06.main.MazeGame.Maze;

import team06.main.MazeGame.Player.Player;
import team06.main.MazeGeneration.MazeCell;
import team06.main.MazeGeneration.MazeGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class MazePanel extends JPanel implements KeyListener{
	
    private static final int MazeSize = 10;
    private static Player player;
    private static boolean enterIsPressed = false;
    private Maze MazeDisplay;

    public MazePanel() {
        super();
        setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        var mergedMaze = CreateMaze();
        MazeDisplay = new Maze(mergedMaze, this,MazeSize * 2);

        for (MazeCell c: mergedMaze) {
            if(c.isStart) {
                this.player = new Player(c, mergedMaze);
            }
        }

        var constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        add(MazeDisplay, constraints);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
    }

    /**
     * adding labels to four game fields
     * - paint four labels in plain text and paint the bold text over to create the effect
     *   when the play character has reached the goal.
     * - use Map to set the Text Attribute
     * - the integer behind the x and y was just try and error to find the perfect position and the space between Maze and Labels.
     *
     * @param g the <code>Graphics</code> object to protect
     */

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        if(player.getCell().isTarget() && player.getPlayerOnShortestPath()){
            if (player.getCell().getX() <= MazePanel.getMazeSize() && player.getCell().getY() <= MazePanel.getMazeSize() -1) {
                g.setFont(new Font("Helvetica", Font.BOLD, 12).deriveFont(fontAttributes));
                g.drawString(" Game 1", MazeDisplay.getX(), MazeDisplay.getY() - 10);

            } else if (player.getCell().getX() >= MazePanel.getMazeSize() + 1 && player.getCell().getY() <= MazePanel.getMazeSize() -1){
                g.setFont(new Font("Helvetica", Font.BOLD, 12).deriveFont(fontAttributes));
                g.drawString(" Game 2", MazeDisplay.getX()+ MazeDisplay.getWidth() - 45, MazeDisplay.getY() -10);


            } else if (player.getCell().getX() <= MazePanel.getMazeSize() && player.getCell().getY() >= MazePanel.getMazeSize()){
                g.setFont(new Font("Helvetica", Font.BOLD, 12).deriveFont(fontAttributes));
                g.drawString(" Game 3", MazeDisplay.getX(), MazeDisplay.getY() + MazeDisplay.getHeight() +20);


            } else if (player.getCell().getX() >= MazePanel.getMazeSize() + 1 && player.getCell().getY() >= MazePanel.getMazeSize()){
                g.setFont(new Font("Helvetica", Font.BOLD, 12).deriveFont(fontAttributes));
                g.drawString(" Game 4", MazeDisplay.getX() + MazeDisplay.getWidth() - 45,MazeDisplay.getY() + MazeDisplay.getHeight()+ 20);
            }
        }

        g.setFont(new Font("Helvetica", Font.PLAIN, 12));
        g.drawString(" Game 1", MazeDisplay.getX(), MazeDisplay.getY() - 10);
        g.drawString(" Game 2", MazeDisplay.getX()+ MazeDisplay.getWidth() - 45, MazeDisplay.getY() -10);
        g.drawString(" Game 3", MazeDisplay.getX(), MazeDisplay.getY() + MazeDisplay.getHeight() +20);
        g.drawString(" Game 4", MazeDisplay.getX() + MazeDisplay.getWidth() - 45,MazeDisplay.getY() + MazeDisplay.getHeight()+ 20);
    }

    public static Player getPlayer() {
        return player;
    }
    public static int getMazeSize() {
        return MazeSize;
    }
    public static boolean isEnterIsPressed() {
        return enterIsPressed;
    }

    /**
     * Creates Maze through the MazeGenerator and mirroring an initial maze to create 4 mazes merged into one
     * @return the final maze containing 4 sub mazes
     */
    private java.util.List<MazeCell> CreateMaze() {
        var mazeGenerator = new MazeGenerator(MazeSize);
        var initialMaze = mazeGenerator.GetMaze();

        var topRightMaze = mazeGenerator.CreateTransformation(initialMaze, true);
        var bottomLeftMaze = mazeGenerator.CreateTransformation(initialMaze, false);
        var bottomRightMaze = mazeGenerator.CreateTransformation(topRightMaze, false);

        var mergedMaze = mazeGenerator.MergeFourMazesAndCreateTargets(initialMaze, topRightMaze, bottomLeftMaze, bottomRightMaze,MazeSize);
        return mergedMaze;
    }

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		switch(keyCode) {
			case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
				player.moveLeft();
			break;

			case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					player.moveRight();
			break;

			case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
					player.moveUp();
			break;

			case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					player.moveDown();
			break;

            case KeyEvent.VK_ENTER:
                enterIsPressed = true;
                player.startNewGame();
            break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

