package team06.main.MazeGame.Player;

import team06.main.MazeGame.MainFrame;
import team06.main.MazeGame.Maze.MazePanel;
import team06.main.MazeGame.SideMenu.SideMenuPanel;
import team06.main.MazeGeneration.MazeCell;

import javax.swing.*;
import java.util.List;

public class Player {

	private MazeCell cell;
	private List<MazeCell> mazeGrid;
	private static boolean playerOnShortestPath = true;
	private static int countNotPath = 1;

	//Constructor
    public Player(MazeCell cell, List<MazeCell> mazeGrid) {
       this.cell = cell;
       this.mazeGrid = mazeGrid;
    }

    /**
     * Allows the player to move left, checking for if the player's next
     * move hits a win cell .
	 *
	 * - count the steps on the wrong path for the conditions in Start- and PreviousPoint
	 * - set the buttons enable after movement detected from StartPoint
     */
    public Action moveLeft(){
    	var newCell = cell.getLeftNeighbourIfValid(mazeGrid);
    	if(newCell != null) {
			if (!getPlayerOnShortestPath() || !newCell.isPath()) countNotPath++;
    		cell.removePlayer();
    		newCell.setPlayer();
    		cell = newCell;
			SideMenuPanel.playButtons[1].setEnabled(true);
			SideMenuPanel.playButtons[2].setEnabled(true);
    	}
		if (getPlayerOnShortestPath()) countNotPath = 1;
    	return null;
	}

    /**
     * Allows the player to move right, checking for if the player's next
     * move hits a win cell .
	 *
	 * - count the steps on the wrong path for the conditions in Start- and PreviousPoint
	 * - set the buttons enable after movement detected from StartPoint
     */
    public Action moveRight(){
    	var newCell = cell.getRightNeighbourIfValid(mazeGrid);
    	if(newCell != null) {
			if (!getPlayerOnShortestPath() || !newCell.isPath()) countNotPath++;
    		cell.removePlayer();
    		newCell.setPlayer();
    		cell = newCell;
			SideMenuPanel.playButtons[1].setEnabled(true);
			SideMenuPanel.playButtons[2].setEnabled(true);
    	}
		if (getPlayerOnShortestPath()) countNotPath = 1;
    	return null;
	}

    /**
     * Allows the player to move up, checking for if the player's next
     * move hits a win cell .
	 *
	 * - count the steps on the wrong path for the conditions in Start- and PreviousPoint
	 * - set the buttons enable after movement detected from StartPoint
     */
    public Action moveUp(){
    	var newCell = cell.getTopNeighbourIfValid(mazeGrid);
    	if(newCell != null) {
			if (!getPlayerOnShortestPath() || !newCell.isPath()) countNotPath++;
    		cell.removePlayer();
    		newCell.setPlayer();
    		cell = newCell;
			SideMenuPanel.playButtons[1].setEnabled(true);
			SideMenuPanel.playButtons[2].setEnabled(true);
    	}
		if (getPlayerOnShortestPath()) countNotPath = 1;
		return null;
	}
    
    /**
     * Allows the player to move down, checking for if the player's next
     * move hits a win cell.
	 *
	 * - count the steps on the wrong path for the conditions in Start- and PreviousPoint
	 * - set the buttons enable after movement detected from StartPoint
     */
    public Action moveDown(){
    	var newCell = cell.getBottomNeighbourIfValid(mazeGrid);
    	if(newCell != null) {
			if (!getPlayerOnShortestPath() || !newCell.isPath()) countNotPath++;
    		cell.removePlayer();
    		newCell.setPlayer();
    		cell = newCell;
			SideMenuPanel.playButtons[1].setEnabled(true);
			SideMenuPanel.playButtons[2].setEnabled(true);
    	}
		if (getPlayerOnShortestPath()) countNotPath = 1;
    	return null;
	}

	/**
	 * set PreviousPoint disable
	 * reset countNotPath
	 */

	public Action startPoint(){
		var newCell = cell.getStartNeighbourIfValid(mazeGrid);
		countNotPath = 0;
		if(newCell != null) {
			cell.removePlayer();
			newCell.setPlayer();
			cell = newCell;
		}
		SideMenuPanel.DefaultButton();
		cell.Reset(newCell);
		return null;
	}

	/**
	 * the play character can still reach the goal in green, if it has been on wrong path for ONCE
	 * so, if countNotPath equals 2 AND the previous step was on the goal AND the character was green,
	 * the PreviousPoint will set the character green.
	 * if the character step on wrong path for more than once, it will be set into red.
	 * that why we put the first condition above all.
	 * if the Player steps wrong for only once, it will set it back to green
	 *
	 * reset countNotPath
	 */

	public Action previousPoint(){
		var newCell = cell.getPreviousNeighbourIfValid(mazeGrid);
		var oldCell = MazeCell.getPathList().remove(MazeCell.getPathList().size()-1);
		var lastState = getPlayerOnShortestPath();
		if(newCell != null) {
			cell.removePlayer();
			newCell.setPlayer();
			cell = newCell;
			SideMenuPanel.playButtons[2].setEnabled(false);
		} if (cell.isStart()){
			SideMenuPanel.DefaultButton();
			cell.Reset(newCell);
		} if (newCell != MazeCell.getPathList().get(MazeCell.getPathList().size() - 1)){
			cell.ResetOneStep(oldCell);
		} if (countNotPath >= 2 && oldCell.isTarget() && getPlayerOnShortestPath()) {
			Player.setPlayerOnShortestPath(true);
		} if (countNotPath >= 2) {
			Player.setPlayerOnShortestPath(false);
		} if (countNotPath <= 1 && !getPlayerOnShortestPath()) {
			Player.setPlayerOnShortestPath(true);
		}

		return null;
	}

	public Action startNewGame() {
		if (playerOnShortestPath && cell.getContainsPlayer() && cell.isTarget() && MazePanel.isEnterIsPressed()) {
			if (cell.getX() == 0 && cell.getY() == 0) {
				MainFrame.switchCard("GameOne");
			} else if (cell.getX() == MazePanel.getMazeSize() * 2 +1 && cell.getY() == 0){
				MainFrame.switchCard("GameTwo");
			} else if (cell.getX() == 0 && cell.getY() == MazePanel.getMazeSize() * 2 -1){
				MainFrame.switchCard("GameThree");
			} else if (cell.getX() == MazePanel.getMazeSize() * 2+1 && cell.getY() == MazePanel.getMazeSize() * 2 -1){
				MainFrame.switchCard("GameFour");
			}
		}
		return null;
	}
	
	// Getters and Setters for the current color of the player and cell
	public static boolean getPlayerOnShortestPath() {
		return playerOnShortestPath;
	}

	public static void setPlayerOnShortestPath(boolean playerOnShortestPath) {
		Player.playerOnShortestPath = playerOnShortestPath;
	}
	
	public MazeCell getCell() {
		return cell;
	}
}
