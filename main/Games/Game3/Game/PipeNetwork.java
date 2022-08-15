package team06.main.Games.Game3.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

import team06.main.Games.Game3.Game3GamePanel;

public class PipeNetwork {

	private int numberOfPotentialStations;
	private int numberOfActualStations;
	private int maxCosts;
	private int distanceBetweenStations;
	private static int totalWidth;
	private static int totalHeight;
	public String difficulty;
	public static int horizontalStep;
	public static int verticalStep;

	public static Set<Station> actualStations;
	public static ArrayList<Station> potentialStations;
	public static ArrayList<Pipe> pipes;
	public static ArrayList<Pipe> pipesTraining;
	public static ArrayList<Pipe> pipesMittel;
	public static ArrayList<Pipe> pipesSchwer;

	public static ArrayList<ArrayList<Station>> possibilities4stations;
	public static ArrayList<ArrayList<Station>> possibilities8stations;
	public static ArrayList<ArrayList<Station>> possibilities12stations;
	public static int num;
	static int valueTraining;
	static int valueMittel;
	static int valueSchwer;
	int randomStart = generateRandomNumber(3);
	public static int row;
	public static int col;
	public static int currentX;
	public static int currentY;

	public PipeNetwork(int numberOfPotentialStations, int numberOfActualStations) {
		super();
		setTotalWidth(Game3GamePanel.getPanelWidth() - 200);
		setTotalHeight(Game3GamePanel.getPanelHeight());
		difficulty = Game3GamePanel.getDifficulty();
		this.numberOfPotentialStations = numberOfPotentialStations;
		this.numberOfActualStations = numberOfActualStations;
		this.setNumberOfPotentialStations(15);
		potentialStations = CreatePotentialStations();

		possibilities4stations = new ArrayList<ArrayList<Station>>();
		possibilities8stations = new ArrayList<ArrayList<Station>>();
		possibilities12stations = new ArrayList<ArrayList<Station>>();

		makePossibleGraphs();
		// value = generateRandomNumber(possibilities8stations.size());
		// value = generateRandomNumber(possibilities4stations.size());

		valueTraining = generateRandomNumber(possibilities4stations.size());
		valueMittel = generateRandomNumber(possibilities8stations.size());
		valueSchwer = generateRandomNumber(possibilities12stations.size());
		pipesTraining = createPipes(potentialStations, Game3GamePanel.getDifficulty());
		pipesMittel = createPipes(potentialStations, Game3GamePanel.getDifficulty());
		pipesSchwer = createPipes(potentialStations, Game3GamePanel.getDifficulty());
		
	}

	// creates a list of 15 potential stations
	public ArrayList<Station> CreatePotentialStations() {
		potentialStations = new ArrayList<>(15);
		Station s0 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s0.setxCoo(this.getTotalWidth()/3);
		s0.setyCoo(this.getTotalHeight()/5);
		potentialStations.add(s0);
		
		Station s1 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s1.setxCoo((this.getTotalWidth()/3)*2);
		s1.setyCoo(this.getTotalHeight()/5);
		potentialStations.add(s1);
		
		Station s2 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s2.setxCoo((this.getTotalWidth()/3)*3);
		s2.setyCoo(this.getTotalHeight()/5);
		potentialStations.add(s2);
		
		Station s3 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s3.setxCoo(this.getTotalWidth()/3);
		s3.setyCoo((this.getTotalHeight()/5)*2);
		potentialStations.add(s3);
		
		Station s4 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s4.setxCoo((this.getTotalWidth()/3)*2);
		s4.setyCoo((this.getTotalHeight()/5)*2);
		potentialStations.add(s0);
		
		Station s5 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s5.setxCoo((this.getTotalWidth()/3)*3);
		s5.setyCoo((this.getTotalHeight()/5)*2);
		potentialStations.add(s5);
		
		Station s6 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s6.setxCoo(this.getTotalWidth()/3);
		s6.setyCoo((this.getTotalHeight()/5)*3);
		potentialStations.add(s6);
		
		Station s7 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s7.setxCoo((this.getTotalWidth()/3)*2);
		s7.setyCoo((this.getTotalHeight()/5)*3);
		potentialStations.add(s7);
		
		Station s8 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s8.setxCoo((this.getTotalWidth()/3)*3);
		s8.setyCoo((this.getTotalHeight()/5)*3);
		potentialStations.add(s8);
		
		Station s9 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s9.setxCoo(this.getTotalWidth()/3);
		s9.setyCoo((this.getTotalHeight()/5)*4);
		potentialStations.add(s9);
		
		Station s10 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s10.setxCoo((this.getTotalWidth()/3)*2);
		s10.setyCoo((this.getTotalHeight()/5)*4);
		potentialStations.add(s10);
		
		Station s11 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s11.setxCoo((this.getTotalWidth()/3)*3);
		s11.setyCoo((this.getTotalHeight()/5)*4);
		potentialStations.add(s11);
		
		Station s12 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s12.setxCoo(this.getTotalWidth()/3);
		s12.setyCoo((this.getTotalHeight()/5)*5);
		potentialStations.add(s12);
		
		Station s13 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s13.setxCoo((this.getTotalWidth()/3)*2);
		s13.setyCoo((this.getTotalHeight()/5)*5);
		potentialStations.add(s13);
		
		Station s14 = new Station(this.getTotalWidth(), this.getTotalHeight());
		s14.setxCoo((this.getTotalWidth()/3)*3);
		s14.setyCoo((this.getTotalHeight()/5)*5);
		potentialStations.add(s14);
		
		return potentialStations;
	}
	

	public ArrayList<Pipe> createPipes(ArrayList<Station> potentialStations, String difficulty) {

		// chooses a random index from the list of possible stations
		// gets the list at this index
		// creates pipes between the stations stored in the list (currentlist)
		
		if (difficulty == "Training") {
			pipesTraining = new ArrayList<Pipe>();
			var currentList = possibilities4stations.get(valueTraining);
		
				for (int i = 0; i < currentList.size(); i++) {
					int j = i + 1;
					if (j < currentList.size()) {
						
						var pipe = new Pipe(currentList.get(i), currentList.get(j));
					
						currentList.get(i).setHasPipe(true);
						currentList.get(j).setHasPipe(true);
						pipe.setStartX(currentList.get(i).getxCoo());
						pipe.setStartY(currentList.get(i).getyCoo());
						pipe.setEndX(currentList.get(i).getxCoo());
						pipe.setEndY(currentList.get(i).getyCoo());
						pipesTraining.add(pipe);
						
						if(i == randomStart) {
							currentList.get(i).setStartStation(true);
						}
					}
			}
			//pipes = pipesTraining;
			//System.out.println((pipesTraining.size()));
			return pipesTraining;
		} else if (difficulty == "Mittel") {
			pipesMittel = new ArrayList<Pipe>();
			var currentList = possibilities8stations.get(valueMittel);
				for (int i = 0; i < currentList.size(); i++) {
					int j = i + 1;
					if (j < currentList.size()) {

						var pipe = new Pipe(currentList.get(i), currentList.get(j));
					
						currentList.get(i).setHasPipe(true);
						currentList.get(j).setHasPipe(true);
						pipe.setStartX(currentList.get(i).getxCoo());
						pipe.setStartY(currentList.get(i).getyCoo());
						pipe.setEndX(currentList.get(i).getxCoo());
						pipe.setEndY(currentList.get(i).getyCoo());
						pipesMittel.add(pipe);
						
						if(i == randomStart) {
							currentList.get(i).setStartStation(true);
						}
					}
			}
			//pipes = pipesMittel;
			return pipesMittel;
		} else if (difficulty == "Schwer") {
			pipesSchwer = new ArrayList<Pipe>();
			var currentList = possibilities12stations.get(valueSchwer);
				for (int i = 0; i < currentList.size(); i++) {
					int j = i + 1;
					if (j < currentList.size()) {

						var pipe = new Pipe(currentList.get(i), currentList.get(j));
						
						currentList.get(i).setHasPipe(true);
						currentList.get(j).setHasPipe(true);
						pipe.setStartX(currentList.get(i).getxCoo());
						pipe.setStartY(currentList.get(i).getyCoo());
						pipe.setEndX(currentList.get(i).getxCoo());
						pipe.setEndY(currentList.get(i).getyCoo());
						pipesSchwer.add(pipe);
						
						if(i == randomStart) {
							currentList.get(i).setStartStation(true);
						}
					}
				}
			
			//pipes = pipesSchwer;
			return pipesSchwer;
		}
		return pipes;
	}

	public void makePossibleGraphs() {

		ArrayList<Station> stations1 = new ArrayList<>();
		ArrayList<Station> stations2 = new ArrayList<>();
		ArrayList<Station> stations3 = new ArrayList<>();
		ArrayList<Station> stations4 = new ArrayList<>();
		ArrayList<Station> stations5 = new ArrayList<>();
		ArrayList<Station> stations6 = new ArrayList<>();
		ArrayList<Station> stations7 = new ArrayList<>();
		ArrayList<Station> stations8 = new ArrayList<>();
		ArrayList<Station> stations9 = new ArrayList<>();
		ArrayList<Station> stations10 = new ArrayList<>();
		ArrayList<Station> stations11 = new ArrayList<>();
		ArrayList<Station> stations12 = new ArrayList<>();
		ArrayList<Station> stations13 = new ArrayList<>();
		ArrayList<Station> stations14 = new ArrayList<>();
		ArrayList<Station> stations15 = new ArrayList<>();
		ArrayList<Station> stations16 = new ArrayList<>();
		ArrayList<Station> stations17 = new ArrayList<>();

		ArrayList<Station> stations18 = new ArrayList<>();
		ArrayList<Station> stations19 = new ArrayList<>();
		ArrayList<Station> stations20 = new ArrayList<>();
		ArrayList<Station> stations21 = new ArrayList<>();
		ArrayList<Station> stations22 = new ArrayList<>();
		ArrayList<Station> stations23 = new ArrayList<>();
		ArrayList<Station> stations24 = new ArrayList<>();
		ArrayList<Station> stations25 = new ArrayList<>();
		ArrayList<Station> stations26 = new ArrayList<>();
		ArrayList<Station> stations27 = new ArrayList<>();
		ArrayList<Station> stations28 = new ArrayList<>();
		ArrayList<Station> stations29 = new ArrayList<>();
		ArrayList<Station> stations30 = new ArrayList<>();

		ArrayList<Station> stations31 = new ArrayList<>();
		ArrayList<Station> stations32 = new ArrayList<>();
		ArrayList<Station> stations33 = new ArrayList<>();
		ArrayList<Station> stations34 = new ArrayList<>();
		ArrayList<Station> stations35 = new ArrayList<>();
		ArrayList<Station> stations36 = new ArrayList<>();
		ArrayList<Station> stations37 = new ArrayList<>();
		ArrayList<Station> stations38 = new ArrayList<>();
		ArrayList<Station> stations39 = new ArrayList<>();
		ArrayList<Station> stations40 = new ArrayList<>();

		Station s0 = potentialStations.get(0);
		s0.setId(0);
		Station s1 = potentialStations.get(1);
		s1.setId(1);
		Station s2 = potentialStations.get(2);
		s2.setId(2);
		Station s3 = potentialStations.get(3);
		s3.setId(3);
		Station s4 = potentialStations.get(4);
		s4.setId(4);
		Station s5 = potentialStations.get(5);
		s5.setId(5);
		Station s6 = potentialStations.get(6);
		s6.setId(6);
		Station s7 = potentialStations.get(7);
		s7.setId(7);
		Station s8 = potentialStations.get(8);
		s8.setId(8);
		Station s9 = potentialStations.get(9);
		s9.setId(9);
		Station s10 = potentialStations.get(10);
		s10.setId(10);
		Station s11 = potentialStations.get(11);
		s11.setId(11);
		Station s12 = potentialStations.get(12);
		s12.setId(12);
		Station s13 = potentialStations.get(13);
		s13.setId(13);
		Station s14 = potentialStations.get(14);
		s14.setId(14);

		// possible constellations for 4 stations
		stations1.add(s7);
		stations1.add(s8);
		stations1.add(s10);
		stations1.add(s7);
		stations1.add(s9);
		stations1.add(s10);

		stations2.add(s6);
		stations2.add(s7);
		stations2.add(s10);
		stations2.add(s6);
		stations2.add(s9);
		stations2.add(s10);

		stations3.add(s13);
		stations3.add(s9);
		stations3.add(s10);
		stations3.add(s13);
		stations3.add(s14);
		stations3.add(s10);

		stations4.add(s7);
		stations4.add(s10);
		stations4.add(s6);
		stations4.add(s7);
		stations4.add(s4);
		stations4.add(s6);

		stations5.add(s5);
		stations5.add(s4);
		stations5.add(s1);
		stations5.add(s5);
		stations5.add(s7);
		stations5.add(s4);

		stations6.add(s10);
		stations6.add(s13);
		stations6.add(s9);
		stations6.add(s10);
		stations6.add(s11);
		stations6.add(s13);

		stations7.add(s13);
		stations7.add(s9);
		stations7.add(s10);
		stations7.add(s11);
		stations7.add(s13);
		stations7.add(s10);

		stations8.add(s4);
		stations8.add(s6);
		stations8.add(s7);
		stations8.add(s4);
		stations8.add(s8);
		stations8.add(s7);

		stations9.add(s13);
		stations9.add(s9);
		stations9.add(s7);
		stations9.add(s11);
		stations9.add(s13);
		stations9.add(s10);
		stations9.add(s7);

		stations10.add(s0);
		stations10.add(s6);
		stations10.add(s10);
		stations10.add(s0);
		stations10.add(s8);
		stations10.add(s10);

		stations11.add(s4);
		stations11.add(s8);
		stations11.add(s6);
		stations11.add(s4);
		stations11.add(s3);
		stations11.add(s6);

		stations12.add(s4);
		stations12.add(s10);
		stations12.add(s8);
		stations12.add(s4);
		stations12.add(s2);
		stations12.add(s8);

		stations13.add(s1);
		stations13.add(s6);
		stations13.add(s3);
		stations13.add(s1);
		stations13.add(s10);
		stations13.add(s6);

		stations14.add(s8);
		stations14.add(s5);
		stations14.add(s6);
		stations14.add(s8);
		stations14.add(s11);
		stations14.add(s6);

		stations15.add(s1);
		stations15.add(s6);
		stations15.add(s3);
		stations15.add(s1);
		stations15.add(s10);
		stations15.add(s6);

		stations16.add(s12);
		stations16.add(s9);
		stations16.add(s7);
		stations16.add(s12);
		stations16.add(s13);
		stations16.add(s7);

		stations17.add(s11);
		stations17.add(s4);
		stations17.add(s5);
		stations17.add(s11);
		stations17.add(s7);
		stations17.add(s4);

		possibilities4stations.add(stations1);
		possibilities4stations.add(stations2);
		possibilities4stations.add(stations3);
		possibilities4stations.add(stations4);
		possibilities4stations.add(stations5);
		possibilities4stations.add(stations6);
		possibilities4stations.add(stations7);
		possibilities4stations.add(stations8);
		possibilities4stations.add(stations9);
		possibilities4stations.add(stations10);
		possibilities4stations.add(stations11);
		possibilities4stations.add(stations12);
		possibilities4stations.add(stations13);
		possibilities4stations.add(stations14);
		possibilities4stations.add(stations15);
		possibilities4stations.add(stations16);
		possibilities4stations.add(stations17);

		// possible constellations for 8 stations
		stations18.add(s3);
		stations18.add(s1);
		stations18.add(s5);
		stations18.add(s4);
		stations18.add(s3);
		stations18.add(s6);
		stations18.add(s7);
		stations18.add(s8);
		stations18.add(s5);
		stations18.add(s7);
		stations18.add(s9);
		stations18.add(s6);
		stations18.add(s7);

		stations19.add(s1);
		stations19.add(s3);
		stations19.add(s6);
		stations19.add(s9);
		stations19.add(s13);
		stations19.add(s11);
		stations19.add(s8);
		stations19.add(s5);
		stations19.add(s9);
		stations19.add(s1);
		stations19.add(s5);

		stations20.add(s1);
		stations20.add(s4);
		stations20.add(s3);
		stations20.add(s1);
		stations20.add(s5);
		stations20.add(s4);
		stations20.add(s7);
		stations20.add(s3);
		stations20.add(s6);
		stations20.add(s7);
		stations20.add(s5);
		stations20.add(s8);
		stations20.add(s7);
		stations20.add(s10);
		stations20.add(s6);

		stations21.add(s3);
		stations21.add(s1);
		stations21.add(s0);
		stations21.add(s3);
		stations21.add(s4);
		stations21.add(s7);
		stations21.add(s3);
		stations21.add(s6);
		stations21.add(s7);
		stations21.add(s5);
		stations21.add(s4);
		stations21.add(s1);
		stations21.add(s5);
		stations21.add(s2);
		stations21.add(s1);

		stations22.add(s2);
		stations22.add(s0);
		stations22.add(s6);
		stations22.add(s2);
		stations22.add(s8);
		stations22.add(s6);
		stations22.add(s9);
		stations22.add(s13);
		stations22.add(s10);
		stations22.add(s11);
		stations22.add(s8);
		stations22.add(s10);
		stations22.add(s9);

		stations23.add(s7);
		stations23.add(s3);
		stations23.add(s1);
		stations23.add(s5);
		stations23.add(s7);
		stations23.add(s9);
		stations23.add(s13);
		stations23.add(s11);
		stations23.add(s7);
		stations23.add(s10);
		stations23.add(s13);

		stations24.add(s10);
		stations24.add(s13);
		stations24.add(s12);
		stations24.add(s10);
		stations24.add(s14);
		stations24.add(s11);
		stations24.add(s10);
		stations24.add(s8);
		stations24.add(s7);
		stations24.add(s10);
		stations24.add(s9);
		stations24.add(s6);
		stations24.add(s10);

		stations25.add(s4);
		stations25.add(s7);
		stations25.add(s6);
		stations25.add(s4);
		stations25.add(s8);
		stations25.add(s7);
		stations25.add(s10);
		stations25.add(s9);
		stations25.add(s13);
		stations25.add(s10);
		stations25.add(s11);
		stations25.add(s13);

		stations26.add(s5);
		stations26.add(s2);
		stations26.add(s4);
		stations26.add(s7);
		stations26.add(s9);
		stations26.add(s12);
		stations26.add(s10);
		stations26.add(s7);
		stations26.add(s8);
		stations26.add(s5);
		stations26.add(s7);

		stations27.add(s0);
		stations27.add(s6);
		stations27.add(s9);
		stations27.add(s12);
		stations27.add(s14);
		stations27.add(s6);
		stations27.add(s5);
		stations27.add(s0);
		stations27.add(s4);
		stations27.add(s3);

		stations28.add(s13);
		stations28.add(s12);
		stations28.add(s10);
		stations28.add(s9);
		stations28.add(s7);
		stations28.add(s6);
		stations28.add(s4);
		stations28.add(s3);
		stations28.add(s6);
		stations28.add(s9);
		stations28.add(s12);

		stations29.add(s3);
		stations29.add(s4);
		stations29.add(s1);
		stations29.add(s3);
		stations29.add(s7);
		stations29.add(s5);
		stations29.add(s8);
		stations29.add(s7);
		stations29.add(s10);
		stations29.add(s11);
		stations29.add(s7);

		stations30.add(s5);
		stations30.add(s2);
		stations30.add(s6);
		stations30.add(s14);
		stations30.add(s11);
		stations30.add(s7);
		stations30.add(s8);
		stations30.add(s5);
		stations30.add(s7);

		possibilities8stations.add(stations18);
		possibilities8stations.add(stations19);
		possibilities8stations.add(stations20);
		possibilities8stations.add(stations21);
		possibilities8stations.add(stations22);
		possibilities8stations.add(stations23);
		possibilities8stations.add(stations24);
		possibilities8stations.add(stations25);
		possibilities8stations.add(stations26);
		possibilities8stations.add(stations27);
		possibilities8stations.add(stations28);
		possibilities8stations.add(stations29);
		possibilities8stations.add(stations30);

		// possible constallations for 12 stations
		stations31.add(s3);
		stations31.add(s1);
		stations31.add(s4);
		stations31.add(s3);
		stations31.add(s6);
		stations31.add(s4);
		stations31.add(s7);
		stations31.add(s6);
		stations31.add(s9);
		stations31.add(s7);
		stations31.add(s10);
		stations31.add(s9);
		stations31.add(s13);
		stations31.add(s11);
		stations31.add(s10);
		stations31.add(s8);
		stations31.add(s7);
		stations31.add(s5);
		stations31.add(s4);
		stations31.add(s2);
		stations31.add(s1);

		stations32.add(s1);
		stations32.add(s3);
		stations32.add(s6);
		stations32.add(s9);
		stations32.add(s13);
		stations32.add(s11);
		stations32.add(s8);
		stations32.add(s5);
		stations32.add(s1);
		stations32.add(s4);
		stations32.add(s7);
		stations32.add(s10);
		stations32.add(s13);

		stations33.add(s2);
		stations33.add(s1);
		stations33.add(s0);
		stations33.add(s3);
		stations33.add(s2);
		stations33.add(s5);
		stations33.add(s3);
		stations33.add(s11);
		stations33.add(s9);
		stations33.add(s12);
		stations33.add(s11);
		stations33.add(s8);
		stations33.add(s7);
		stations33.add(s6);
		stations33.add(s3);

		stations34.add(s0);
		stations34.add(s7);
		stations34.add(s14);
		stations34.add(s13);
		stations34.add(s10);
		stations34.add(s7);
		stations34.add(s6);
		stations34.add(s10);
		stations34.add(s9);
		stations34.add(s13);
		stations34.add(s12);
		stations34.add(s9);
		stations34.add(s6);
		stations34.add(s3);
		stations34.add(s0);
		stations34.add(s1);
		stations34.add(s4);
		stations34.add(s7);
		stations34.add(s2);
		stations34.add(s1);

		stations35.add(s2);
		stations35.add(s1);
		stations35.add(s0);
		stations35.add(s3);
		stations35.add(s2);
		stations35.add(s5);
		stations35.add(s6);
		stations35.add(s9);
		stations35.add(s8);
		stations35.add(s11);
		stations35.add(s12);
		stations35.add(s13);
		stations35.add(s14);
		stations35.add(s11);

		stations36.add(s0);
		stations36.add(s7);
		stations36.add(s14);
		stations36.add(s11);
		stations36.add(s7);
		stations36.add(s8);
		stations36.add(s4);
		stations36.add(s5);
		stations36.add(s1);
		stations36.add(s4);
		stations36.add(s0);
		stations36.add(s3);
		stations36.add(s7);
		stations36.add(s6);
		stations36.add(s9);
		stations36.add(s10);
		stations36.add(s6);

		stations37.add(s11);
		stations37.add(s7);
		stations37.add(s3);
		stations37.add(s4);
		stations37.add(s5);
		stations37.add(s7);
		stations37.add(s8);
		stations37.add(s5);
		stations37.add(s2);
		stations37.add(s4);
		stations37.add(s1);
		stations37.add(s0);
		stations37.add(s3);
		stations37.add(s6);
		stations37.add(s10);
		stations37.add(s13);
		stations37.add(s11);
		stations37.add(s10);

		stations38.add(s1);
		stations38.add(s3);
		stations38.add(s6);
		stations38.add(s10);
		stations38.add(s12);
		stations38.add(s13);
		stations38.add(s14);
		stations38.add(s10);
		stations38.add(s8);
		stations38.add(s5);
		stations38.add(s1);
		stations38.add(s4);
		stations38.add(s7);
		stations38.add(s6);
		stations38.add(s9);
		stations38.add(s10);

		stations39.add(s11);
		stations39.add(s14);
		stations39.add(s10);
		stations39.add(s12);
		stations39.add(s9);
		stations39.add(s7);
		stations39.add(s11);
		stations39.add(s8);
		stations39.add(s4);
		stations39.add(s6);
		stations39.add(s3);
		stations39.add(s1);
		stations39.add(s5);
		stations39.add(s8);

		stations40.add(s1);
		stations40.add(s3);
		stations40.add(s7);
		stations40.add(s5);
		stations40.add(s8);
		stations40.add(s10);
		stations40.add(s6);
		stations40.add(s9);
		stations40.add(s10);
		stations40.add(s12);
		stations40.add(s13);
		stations40.add(s10);
		stations40.add(s14);
		stations40.add(s11);
		stations40.add(s10);
		stations40.add(s7);

		possibilities12stations.add(stations31);
		possibilities12stations.add(stations32);
		possibilities12stations.add(stations33);
		possibilities12stations.add(stations34);
		possibilities12stations.add(stations35);
		possibilities12stations.add(stations36);
		possibilities12stations.add(stations37);
		possibilities12stations.add(stations38);
		possibilities12stations.add(stations39);
		possibilities12stations.add(stations40);
	}

	// min is 0
	public static int generateRandomNumber(int max) {
		Random rand = new Random();
		int r = rand.nextInt(max);
		return r;
	}

	// chooses a random element from an array
	public static int getRandom(int[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	// getters and setters
	public static int getValueTraining() {
		return valueTraining;
	}

	public static void setValueTraining(int valueTraining) {
		PipeNetwork.valueTraining = valueTraining;
	}

	public static int getValueMittel() {
		return valueMittel;
	}

	public static void setValueMittel(int valueMittel) {
		PipeNetwork.valueMittel = valueMittel;
	}

	public static int getValueSchwer() {
		return valueSchwer;
	}

	public static void setValueSchwer(int valueSchwer) {
		PipeNetwork.valueSchwer = valueSchwer;
	}

	public static int getTotalWidth() {
		return totalWidth;
	}

	public void setTotalWidth(int totalWidth) {
		this.totalWidth = totalWidth;
	}

	public static int getTotalHeight() {
		return totalHeight;
	}

	public void setTotalHeight(int totalHeight) {
		this.totalHeight = totalHeight;
	}

	public static int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public static ArrayList<ArrayList<Station>> getPossibilities4stations() {
		return possibilities4stations;
	}

	public void setPossibilities4stations(ArrayList<ArrayList<Station>> possibilities4stations) {
		this.possibilities4stations = possibilities4stations;
	}

	public static ArrayList<ArrayList<Station>> getPossibilities8stations() {
		return possibilities8stations;
	}

	public void setPossibilities8stations(ArrayList<ArrayList<Station>> possibilities8stations) {
		this.possibilities8stations = possibilities8stations;
	}

	public static ArrayList<ArrayList<Station>> getPossibilities12stations() {
		return possibilities12stations;
	}

	public void setPossibilities12stations(ArrayList<ArrayList<Station>> possibilities12stations) {
		this.possibilities12stations = possibilities12stations;
	}

	public int getNumberOfPotentialStations() {
		return numberOfPotentialStations;
	}

	public void setNumberOfPotentialStations(int numberOfPotentialStations) {
		this.numberOfPotentialStations = numberOfPotentialStations;
	}

	public int getNumberOfActualStations() {
		return numberOfActualStations;
	}

	public void setNumberOfActualStations(int numberOfActualStations) {
		this.numberOfActualStations = numberOfActualStations;
	}

	public static ArrayList<Pipe> getPipes() {
		return pipes;
	}

	public static void setPipes(ArrayList<Pipe> pipes) {
		PipeNetwork.pipes = pipes;
	}

	public static Set<Station> getActualStations() {
		return actualStations;
	}

	public static void setActualStations(Set<Station> actualStations) {
		PipeNetwork.actualStations = actualStations;
	}

	public static ArrayList<Station> getPotentialStations() {
		return potentialStations;
	}

	public static void setPotentialStations(ArrayList<Station> potentialStations) {
		PipeNetwork.potentialStations = potentialStations;
	}

	public static ArrayList<Pipe> getPipesTraining() {
		return pipesTraining;
	}

	public static void setPipesTraining(ArrayList<Pipe> pipesTraining) {
		PipeNetwork.pipesTraining = pipesTraining;
	}

	public static ArrayList<Pipe> getPipesMittel() {
		return pipesMittel;
	}

	public static void setPipesMittel(ArrayList<Pipe> pipesMittel) {
		PipeNetwork.pipesMittel = pipesMittel;
	}

	public static ArrayList<Pipe> getPipesSchwer() {
		return pipesSchwer;
	}

	public static void setPipesSchwer(ArrayList<Pipe> pipesSchwer) {
		PipeNetwork.pipesSchwer = pipesSchwer;
	}

	public static int getHorizontalStep() {
		return horizontalStep;
	}

	public static void setHorizontalStep(int horizontalStep) {
		horizontalStep = horizontalStep;
	}

	public static int getVerticalStep() {
		return verticalStep;
	}

	public static void setVerticalStep(int verticalStep) {
		verticalStep = verticalStep;
	}

	public static int getRow() {
		return row;
	}

	public static void setRow(int row) {
		PipeNetwork.row = row;
	}

	public static int getCol() {
		return col;
	}

	public static void setCol(int col) {
		PipeNetwork.col = col;
	}

	public static int getCurrentX() {
		return currentX;
	}

	public static void setCurrentX(int currentX) {
		PipeNetwork.currentX = currentX;
	}

	public static int getCurrentY() {
		return currentY;
	}

	public static void setCurrentY(int currentY) {
		PipeNetwork.currentY = currentY;
	}

	
	
}
