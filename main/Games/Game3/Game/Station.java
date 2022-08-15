package team06.main.Games.Game3.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

import team06.main.Games.Game3.Game3GamePanel;

public class Station {

	private static int numberOfStations = 0;
	public int id;
	private boolean startStation;
	private boolean partOfMST;
	private int totalWidth;
	private int totalHeight;
	private int xCoo;
	private int yCoo;
	public static int stationWidth = 15; 
	public boolean hasPipe = false;
	public int numberOfPipes;
	public int [] potentialStations;
	public static boolean eingebunden;
	
	public HashSet<Integer> adjList;
	
	public Station(int totalWidth, int totalHeight) {
		super();
		this.totalWidth = totalWidth;
		this.totalHeight = totalHeight;
		setTotalWidth(Game3GamePanel.getPanelWidth());
		setTotalHeight(Game3GamePanel.getPanelHeight());
		this.id = numberOfStations;
		numberOfPipes = 0;
		numberOfStations++;
		this.adjList = new HashSet<>();
		this.potentialStations = new int[] {};
		//System.out.println(getTotalWidth()/18);
	}

	// getters and setters
	public int[] getPotentialStations() {
		return potentialStations;
	}

	public void setPotentialPipes(int[] potentialStations) {
		this.potentialStations = potentialStations;
	}

	public int getNumberOfPipes() {
		return numberOfPipes;
	}

	public void setNumberOfPipes(int numberOfPipes) {
		this.numberOfPipes = numberOfPipes;
	}

	public boolean isHasPipe() {
		return hasPipe;
	}

	public void setHasPipe(boolean hasPipe) {
		this.hasPipe = hasPipe;
	}

	public HashSet<Integer> getAdjList() {
		return adjList;
	}

	public void setAdjList(HashSet<Integer> adjList) {
		this.adjList = adjList;
	}

	public static int getNumberOfStations() {
		return numberOfStations;
	}

	public static void setNumberOfStations(int numberOfStations) {
		Station.numberOfStations = numberOfStations;
	}

	public int getTotalWidth() {
		return totalWidth;
	}

	public void setTotalWidth(int totalWidth) {
		this.totalWidth = totalWidth;
	}

	public int getTotalHeight() {
		return totalHeight;
	}

	public void setTotalHeight(int totalHeight) {
		this.totalHeight = totalHeight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getxCoo() {
		return xCoo;
	}

	public void setxCoo(int xCoo) {
		this.xCoo = xCoo;
	}

	public int getyCoo() { 
		return yCoo;
	}

	public void setyCoo(int yCoo) {
		this.yCoo = yCoo;
	}

	public Station() {
		super();
	}
	
	 public static int getStationWidth() {
		return stationWidth;
	}

	public void setStationWidth(int stationWidth) {
		this.stationWidth = stationWidth;
	}
	
	public boolean isStartStation() {
		return startStation;
	}

	public void setStartStation(boolean startStation) {
		this.startStation = startStation;
	}
	
	

	public static boolean isEingebunden() {
		return eingebunden;
	}

	public static void setEingebunden(boolean eingebunden) {
		Station.eingebunden = eingebunden;
	}

	public void draw(Graphics g) {
		if(startStation) {
			displayStartStation(g, Color.blue, getStationWidth());
		} else {
		 displayStations(g, Color.black, getStationWidth());
	 }
	}
	// set x relative to panelsize
	public void displayStations(Graphics g, Color color, int stationWidth) {
		int x = this.getxCoo();
		int y = this.getyCoo();
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.setStroke(new BasicStroke(3));
	    g2.fillOval(x, y, stationWidth, stationWidth);
	 }
	
	// set x relative to panelsize
		public void displayStartStation(Graphics g, Color color, int stationWidth) {
			int x = this.getxCoo();
			int y = this.getyCoo();
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(color);
			g2.setStroke(new BasicStroke(3));
		    g2.fillOval(x, y, stationWidth, stationWidth);
		 }
}
