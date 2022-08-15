package team06.main.Games.Game3;

import team06.main.Games.Game3.Game.Pipe;
import team06.main.Games.Game3.Game.PipeNetwork;
import team06.main.Games.Game3.Game.Station;
import team06.main.MazeGame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

public class Game3GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{

		JLabel extraPipeImage;
		BufferedImage ventilationSystemImage;
		public static int panelHeight; 
		public static int panelWidth;
	    public ArrayList<Station> potentialStations;
	    public Set<Station> actualStations;
	    public ArrayList<Pipe> pipesTraining;
	    public ArrayList<Pipe> pipesMittel;
	    public ArrayList<Pipe> pipesSchwer;
	    public ArrayList<ArrayList<Station>> realStations;
		int number;
		public MSTFrame f;
		public static boolean training;
		public static boolean mittel;
		public static boolean schwer;
		public static boolean started;
		public static String difficulty;
		public static int xVenti;
		public static int yVenti;
		public static int xExtraPipe;
		public static int yExtraPipe;
		public int randomPosition;
		int mousex;
		int mousey;
		Point startCooPipe;
		Point p;
		
		public Game3GamePanel(MSTFrame f) {
	        super();
	        this.f = f;
	       
	        setLayout(null);
	        // sets the coordinates of the ventilation system
	        //setxVenti((f.getWidth()/3*2)-700);
	        //setyVenti((f.getHeight()+50));
	        
	        startCooPipe = new Point(f.getWidth()/3*2-140, f.getHeight()-260 );
	        setxVenti((f.getWidth()/3*2)-380);
	        setyVenti((f.getHeight()-600));
	        
	        // sets the coordinates of the extra pipe
	        setxExtraPipe((f.getWidth()/3*2)-140);
	        setyExtraPipe((f.getHeight())-260);
	        
	        //System.out.println("frame width" + f.getWidth());
	        //System.out.println("frame height" + f.getHeight());
	        
	        // creates the extra pipe as a jlabel and adds mouse motion listener to it for drag and drop
	        extraPipeImage = new JLabel();
	        extraPipeImage.setIcon(new ImageIcon(getClass().getResource("/resources/rohr.png")));
	        extraPipeImage.setBounds(getxExtraPipe(), getyExtraPipe(), 25, 110);
	       //add(extraPipeImage);
	        extraPipeImage.addMouseMotionListener(dragHandler);
	        
	        // sets training difficulty as default
	        training = true;
	        mittel = false;
	        schwer = false;
	        started = false;
	        setDifficulty("Training"); 
	        
	        setPanelHeight(f.getHeight()-100);
	        setPanelWidth(f.getWidth()-400);
	        repaint();
	        this.setBackground(Color.white);
	       
			// loads the image of the ventilation system
			var filePathVenti = getClass().getResource("/resources/ventilationSystem.png");
			try {
				ventilationSystemImage = ImageIO.read(new File(filePathVenti.toURI()));
	        } catch (IOException | URISyntaxException e) {
	            System.out.println("Error during reading/writing: "+ e.getMessage());
	        }
			
	        pipesTraining = PipeNetwork.getPipesTraining();
	        pipesMittel = PipeNetwork.getPipesMittel(); 
	        pipesSchwer = PipeNetwork.getPipesSchwer();
	        repaint();
	        //MST mst = new MST(this);
	        
	        addMouseListener(this);
	        repaint();
	        this.addKeyListener(this);
	        this.setFocusable(true);
	        this.requestFocusInWindow();
	        setFocusTraversalKeysEnabled(false);
	        this.setVisible(true);
	    }
	
		// todo: if p == coordinates of
		// makes extra pipe drag and droppable
		 private MouseMotionListener dragHandler = new MouseMotionListener() {
		        @Override
		        public void mouseDragged(MouseEvent e) {
		        	
		            Dimension size = extraPipeImage.getSize();
		            p = e.getPoint();
		            p = SwingUtilities.convertPoint(e.getComponent(), p, f.getRootPane());  
		            //for (Station s : realStations.get(number)) {
		            	//if(extraPipeImage.getParent().getBounds().contains(p) && p.x == s.getxCoo() && p.y == s.getyCoo() && s.isEingebunden()) {
		            		// p = SwingUtilities.convertPoint(e.getComponent(), p, f.getContentPane());  
		            		//extraPipeImage.setBounds(p.x - size.width/2, p.y - size.height/2, size.width, size.height);
		            	
		            if (extraPipeImage.getParent().getBounds().contains(p) ) {
		        	extraPipeImage.setBounds(p.x - size.width/2, p.y - size.height/2, size.width, size.height);
		            }
		        }
		        @Override
		        public void mouseMoved(MouseEvent e) {
		        }
		        
		        
		    };
	
		
		// depending on the chosen difficulty, a different network is created
		public void startGame(String difficulty) {
			
			if(difficulty == "Training") {
				//setTraining(true);
				setDifficulty("Training"); 
				PipeNetwork networkTraining = new PipeNetwork(15, 8);
				potentialStations = PipeNetwork.getPotentialStations();
				realStations = PipeNetwork.getPossibilities4stations();
				number = PipeNetwork.getValueTraining();
				pipesTraining = PipeNetwork.getPipesTraining();
				
				repaint();
	        } else if(difficulty == "Medium") {
	        	setDifficulty("Mittel"); 
	        	PipeNetwork networkMittel = new PipeNetwork(15, 8);
		        potentialStations = PipeNetwork.getPotentialStations();
		        realStations = PipeNetwork.getPossibilities8stations();
		        number = PipeNetwork.getValueMittel();
		        pipesMittel = PipeNetwork.getPipesMittel();
		        
		        repaint();
	        } else if(difficulty == "Hard") {
	        	setDifficulty("Schwer"); 
	        	PipeNetwork networkSchwer = new PipeNetwork(15, 8);
		        potentialStations = PipeNetwork.getPotentialStations();
		        realStations = PipeNetwork.getPossibilities12stations();
		        number = PipeNetwork.getValueSchwer();
		        pipesSchwer = PipeNetwork.getPipesSchwer();
		        
		        repaint();
	        }
		}
		
		
	    // draws stations and pipes between them
		@Override
		protected void paintComponent(Graphics g) {
			
			 super.paintComponent(g);
			 
		        g.setColor(Color.black);
		        
		        setPanelHeight(f.getHeight()-100);
		        setPanelWidth(f.getWidth()-450);
		      
		       
		        //setxVenti((f.getWidth()/3*2)-380);
		        //setyVenti((f.getHeight()-600));
		        
		        //setxVenti((getPanelWidth()-120));
		        //setyVenti((getPanelHeight())-500);
			 
		        //setxExtraPipe((getPanelWidth()/3*2)-140);
		        //setyExtraPipe((getPanelHeight())-260);
		        
		        
		        if(isStarted()) {
		        	
		        	  if(difficulty == "Training") {
		  		        for(Pipe p : pipesTraining) {
		  		        	p.displayPipe(g, Color.gray);
		  		        	if(p.isClicked()) {
		  		        		p.displayPipe(g, Color.pink);
		  		        		g.setColor(Color.pink);

		  		        	}
		  		        	
		  		        }
		  		        } else if(difficulty == "Mittel") {
		  		        	for(Pipe p : pipesMittel) {
		  		        		p.displayPipe(g, Color.gray);
		  		        		if(p.isClicked()) {
			  		        		p.displayPipe(g, Color.pink);
			  		        		g.setColor(Color.pink);
			  		        	}
		  			        }
		  		        } else if(difficulty == "Schwer") {
		  		        	for(Pipe p : pipesSchwer) {
		  		        		p.displayPipe(g, Color.gray);
		  		        		if(p.isClicked()) {
			  		        		p.displayPipe(g, Color.pink);
			  		        		g.setColor(Color.pink);

			  		        	}
		  			        }
		  		        }
		        	
		        for (Station s : realStations.get(number)) {
		        	s.setStationWidth(getPanelWidth()/12);
		        	
		        	if(s.getId() == 0) {
		        		s.setxCoo(getPanelWidth()/3);
		        		s.setyCoo(getPanelHeight()/5);
		        } else if(s.getId() == 1) {
		        		s.setxCoo((getPanelWidth()/3)*2);
		        		s.setyCoo(getPanelHeight()/5);
		        } else if(s.getId()==2) {
		        		s.setxCoo((getPanelWidth()/3)*3);
		        		s.setyCoo(getPanelHeight()/5);
		        } else if(s.getId() == 3) {
		        		s.setxCoo(getPanelWidth()/3);
		        		s.setyCoo((getPanelHeight()/5)*2);
		        } else if(s.getId()==4) {
		        		s.setxCoo((getPanelWidth()/3)*2);
		        		s.setyCoo((getPanelHeight()/5)*2);
		        } else if(s.getId() ==5) {
		        		s.setxCoo((getPanelWidth()/3)*3);
		        		s.setyCoo((getPanelHeight()/5)*2);
		        } else if(s.getId() ==6) {
		        		s.setxCoo(getPanelWidth()/3);
		        		s.setyCoo((getPanelHeight()/5)*3);
		        } else if(s.getId() == 7) {
		        		s.setxCoo((getPanelWidth()/3)*2);
		        		s.setyCoo((getPanelHeight()/5)*3);
		        } else if(s.getId()==8) {
		        		s.setxCoo((getPanelWidth()/3)*3);
		        		s.setyCoo((getPanelHeight()/5)*3);
		        } else if(s.getId()==9) {
		        		s.setxCoo(getPanelWidth()/3);
		        		s.setyCoo((getPanelHeight()/5)*4);
		        } else if(s.getId()==10) {
		        		s.setxCoo((getPanelWidth()/3)*2);
		        		s.setyCoo((getPanelHeight()/5)*4);
		        } else if(s.getId()==11) {
		        		s.setxCoo((getPanelWidth()/3)*3);
		        		s.setyCoo((getPanelHeight()/5)*4);
		        } else if(s.getId()==12) {
		        		s.setxCoo(getPanelWidth()/3);
		        		s.setyCoo((getPanelHeight()/5)*5);
		        } else if(s.getId()==13) {
		        		s.setxCoo((getPanelWidth()/3)*2);
		        		s.setyCoo((getPanelHeight()/5)*5);
		        } else if(s.getId()==14) {
		        		s.setxCoo((getPanelWidth()/3)*3);
		        		s.setyCoo((getPanelHeight()/5)*5);
		        	}
		        	
					s.draw(g);
					
		      
		        }
		       setxExtraPipe(panelWidth+55);
		        setyExtraPipe(panelHeight-260);
		        //extraPipeImage.setBounds(getxExtraPipe(), getyExtraPipe(), 25, 110);
		        add(extraPipeImage);
		        
		        if (extraPipeImage == null)
		            return;

		        //setxExtraPipe((getPanelWidth())-140);
		        //setyExtraPipe((getPanelHeight())-260);
		        //extraPipeImage.setBounds(getxExtraPipe(), getyExtraPipe(), 25, 110);
		        //add(extraPipeImage);
		        
		        
		        repaint();
		        if (ventilationSystemImage == null)
		            return;

		        g.drawImage(ventilationSystemImage, getxVenti(), getyVenti(), getPanelWidth()/2, getPanelHeight()/7,null); 
		        repaint();
		        }
}
		
		public void resetVentiPosition() {
			 setxVenti((f.getWidth()/3*2)-380);
		     setyVenti((f.getHeight()-600));
		}
		
		public void resetPipePosition() {
			setxExtraPipe((f.getWidth()/3*2)-140);
	        setyExtraPipe((f.getHeight())-260);
	        extraPipeImage.setBounds(getxExtraPipe(), getyExtraPipe(), 25, 110);
		}
		
		// getters and setters	    

		@Override
	    public void keyTyped(KeyEvent e) {

	    }

	    public static int getxExtraPipe() {
			return xExtraPipe;
		}

		public static void setxExtraPipe(int xExtraPipe) {
			Game3GamePanel.xExtraPipe = xExtraPipe;
		}

		public static int getyExtraPipe() {
			return yExtraPipe;
		}

		public static void setyExtraPipe(int yExtraPipe) {
			Game3GamePanel.yExtraPipe = yExtraPipe;
		}

		public static boolean isTraining() {
			return training;
		}

		public static void setTraining(boolean training) {
			Game3GamePanel.training = training;
		}

		public static boolean isMittel() {
			return mittel;
		}

		public static void setMittel(boolean mittel) {
			Game3GamePanel.mittel = mittel;
		}

		public static boolean isSchwer() {
			return schwer;
		}

		public static void setSchwer(boolean schwer) {
			Game3GamePanel.schwer = schwer;
		}
		

		public static boolean isStarted() {
			return started;
		}

		public static void setStarted(boolean started) {
			Game3GamePanel.started = started;
		}
		

		public static String getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(String difficulty) {
			this.difficulty = difficulty;
		}
		

		public void setxVenti(int xVenti) {
			this.xVenti = xVenti;
		}

		public int getyVenti() {
			return yVenti;
		}

		public void setyVenti(int yVenti) {
			this.yVenti = yVenti;
		}

		public int getxVenti() {
			return xVenti;
		}
	
	    public ArrayList<Pipe> getPipesTraining() {
			return pipesTraining;
		}

		public void setPipesTraining(ArrayList<Pipe> pipesTraining) {
			this.pipesTraining = pipesTraining;
		}

		public ArrayList<Pipe> getPipesMittel() {
			return pipesMittel;
		}


		public void setPipesMittel(ArrayList<Pipe> pipesMittel) {
			this.pipesMittel = pipesMittel;
		}


		public ArrayList<Pipe> getPipesSchwer() {
			return pipesSchwer;
		}


		public void setPipesSchwer(ArrayList<Pipe> pipesSchwer) {
			this.pipesSchwer = pipesSchwer;
		}


		public ArrayList<ArrayList<Station>> getRealStations() {
			return realStations;
		}


		public void setRealStations(ArrayList<ArrayList<Station>> realStations) {
			this.realStations = realStations;
		}


		public int getNumber() {
			return number;
		}


		public void setNumber(int number) {
			this.number = number;
		}


		@Override
	    public void keyReleased(KeyEvent e) {
	    }
	    
	    
	    public static int getPanelHeight() {
			return panelHeight;
		}
 
		public void setPanelHeight(int panelHeight) {
			this.panelHeight = panelHeight;
		}

		public static int getPanelWidth() {
			return panelWidth;
		}

		public void setPanelWidth(int panelWidth) {
			this.panelWidth = panelWidth;
		}

		public int getMousex() {
			return mousex;
		}

		public void setMousex(int mousex) {
			this.mousex = mousex;
		}

		public int getMousey() {
			return mousey;
		}

		public void setMousey(int mousey) {
			this.mousey = mousey;
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
		
		public void Reposition(int x, int y){
			xExtraPipe = x;
			yExtraPipe = y;
	    }
		
		// moves the ventilation system into the desired direction
		public void up() {
			setxVenti(getxVenti());
			setyVenti(getyVenti()-5);
		}
		public void down() {
			setxVenti(getxVenti());
			setyVenti(getyVenti()+5);
		}
		public void left() {
			setxVenti(getxVenti()-5);
			setyVenti(getyVenti());
		}
		public void right() {
			setxVenti(getxVenti()+5);
			setyVenti(getyVenti());
		}
		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
				up();
				repaint();
			}
			if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
				down();
				repaint();
			}
			if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
				left();
				repaint();
			}
			if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
				right();
				repaint();
			}
		}
		
		public void KeyTyped(KeyEvent e) {}
		public void KeyReleased(KeyEvent e) {}


		@Override
		public void mouseClicked(MouseEvent e) {
			// saves the x and y coordinate of the mouse click, to check whether a pipe is clicked or not
			setMousex(e.getX());
			setMousey(e.getY());
			
			//System.out.println("maus x " + getMousey());
			//System.out.println("maus y " +getMousex());
			
			if(difficulty == "Training") {
		        for(Pipe p : pipesTraining) {
		        	matchesPipe(p, e.getX(), e.getY());
		        	}
		        } else if(difficulty == "Mittel") {
		        	for(Pipe p : pipesMittel) {
			        	matchesPipe(p, e.getX(), e.getY());
			        	}
		        } else if(difficulty == "Schwer") {
		        	for(Pipe p : pipesSchwer) {
			        	matchesPipe(p, e.getX(), e.getY());
			        	}
		        }
			
		
			
			repaint();
		}

		public void matchesPipe(Pipe pipe, int mousex, int mousey) {
			
			Point maus = new Point(mousex, mousey);
			Point startStation = new Point(pipe.getStartingPoint().getxCoo(), pipe.getStartingPoint().getyCoo());
			Point endStation = new Point(pipe.getEndingPoint().getxCoo(), pipe.getEndingPoint().getyCoo());
			double pipeDistance = calculateDistance(startStation, endStation);
			if(calculateDistance(maus, startStation) + calculateDistance(maus, endStation) <= pipeDistance +3 && pipe.isClicked()==false) {
				pipe.setClicked(true);
				pipe.getStartingPoint().setEingebunden(true);
				pipe.getEndingPoint().setEingebunden(true);
			} //else if(pipe.isClicked()){
				//pipe.setClicked(false);
			}
		

		public double calculateDistance(Point start, Point end) {
			 return Math.sqrt(Math.pow(start.x-end.x, 2) + Math.pow(start.y-end.y, 2));
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			/*
			Dimension size = extraPipeImage.getSize();
			for (Station s : realStations.get(number)) {
        	if((p.x != s.getxCoo() || p.y != s.getyCoo())) {
        		 p = SwingUtilities.convertPoint(e.getComponent(), startCooPipe, f.getContentPane()); 
        		 extraPipeImage.setBounds(startCooPipe.x - size.width/2, startCooPipe.y - size.height/2, size.width, size.height);	 
        	}
			}*/
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

}
