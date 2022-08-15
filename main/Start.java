package team06.main;

import team06.main.MazeGame.MainFrame;

import java.util.Scanner;

/**
 * Diese Datei nicht verschieben und diese main-Methode nutzen, um das Programm
 * zu starten.
 * 
 * @author Progprak-Team
 *
 */
public class Start {

	public static void main(String[] args) {
		new MainFrame();

		Scanner sc = new Scanner(System.in);
		System.out.println("1: Rucksack-Spiel\n" +
				"2: Fische-Spiel\n" +
				"3: MST-Spiel\n" +
				"4: Burgen-Spiel\n" +
				"Enter the Number of Game: ");

//		Boolean gameRunning = true;
		String chooseGame;

//		while(gameRunning) {
			chooseGame = sc.nextLine();

			switch(chooseGame) {
				case "1":
					MainFrame.switchCard("GameOne");
					break;

				case "2":
					MainFrame.switchCard("GameTwo");
					break;

				case "3":
					MainFrame.switchCard("GameThree");
					break;

				case "4":
					MainFrame.switchCard("GameFour");
					break;
			}
//		}


	}
}
