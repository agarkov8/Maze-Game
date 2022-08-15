package team06.main.Games.Game3.Game;

	import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
	import java.util.ArrayList;
	import java.awt.BasicStroke;
	import java.awt.Graphics2D;

	public class Pipe {

		private Station startingPoint;
		private Station EndingPoint;
		private final int cost;
		private boolean isExtraPipe;
		private boolean isClicked;
		public int id = 0;
		public static int numberOfPipes;
		public ArrayList<Pipe> pipes;
		int startX;
		int startY;
		int endX;
		int endY;
		public boolean hasWeight;
		public static int weightCounter=0;
		
		public Pipe(Station startingPoint, Station endingPoint) {
			super();
			this.startingPoint = startingPoint;
			EndingPoint = endingPoint;
			
			numberOfPipes++;
			this.id = numberOfPipes;
			cost = PipeNetwork.generateRandomNumber(20) + 1;
			
		}
		
		 public void draw(Graphics g) {
			 displayPipe(g, Color.gray);
		 }

		 // draws a pipe = a line between two stations and the corresponding costs
		public void displayPipe(Graphics g, Color color) {
			
			startX = startingPoint.getxCoo()+6;
			startY = startingPoint.getyCoo()+3;
			endX = EndingPoint.getxCoo()+6;
			endY = EndingPoint.getyCoo()+3;
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(color); 
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(startX, startY, endX, endY);
			g.setColor(Color.black);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
			//while(this.getWeightCounter()<=1) {
			g.drawString(String.valueOf(this.getCost()), (startX + endX) /2, ((startY + endY)/ 2)+5);

			//}
		}

		
		public int getCost() {
			return cost;
		}

		//public void setCost(int cost) {
		//	this.cost = cost;
		//}

		public int getStartX() {
			return startX;
		}

		public void setStartX(int startX) {
			this.startX = startX;
		}

		public int getStartY() {
			return startY;
		}

		public void setStartY(int startY) {
			this.startY = startY;
		}

		public int getEndX() {
			return endX;
		}

		public void setEndX(int endX) {
			this.endX = endX;
		}

		public int getEndY() {
			return endY;
		}

		public void setEndY(int endY) {
			this.endY = endY;
		}

		public boolean isClicked() {
			return isClicked;
		}

		public void setClicked(boolean isClicked) {
			this.isClicked = isClicked;
		}

		public Station getStartingPoint() {
			return startingPoint;
		}

		public void setStartingPoint(Station startingPoint) {
			this.startingPoint = startingPoint;
		}

		public Station getEndingPoint() {
			return EndingPoint;
		}

		public void setEndingPoint(Station endingPoint) {
			EndingPoint = endingPoint;
		}

		public boolean isHasWeight() {
			return hasWeight;
		}

		public void setHasWeight(boolean hasWeight) {
			this.hasWeight = hasWeight;
		}
		
		public static int getWeightCounter() {
			return weightCounter;
		}

		public static void setWeightCounter(int weightCounter) {
			Pipe.weightCounter = weightCounter;
		}

		public int getNumberOfPipes() {
			return numberOfPipes;
		}

		public void setNumberOfPipes(int numberOfPipes) {
			this.numberOfPipes = numberOfPipes;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		
		
		
		
	}
