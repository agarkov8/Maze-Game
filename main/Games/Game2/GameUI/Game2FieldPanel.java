package team06.main.Games.Game2.GameUI;

import team06.main.Games.Game2.Elements.*;
import team06.main.Games.Game2.Elements.Button;
import team06.main.Games.Game2.FishFrame;
import team06.main.Games.Game2.GraphColoring.GraphColoringProblem;
import team06.main.Games.Shared.GameState;
import team06.main.Games.Shared.Highscore.HighScoreFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.util.List;


//TODO: Fishes should stay in fishTank
//TODO: Minus Button -> ein Aquarium muss gelöscht werden, wenn er leer ist

public class Game2FieldPanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener {

    //create a graph object
    private GraphColoringProblem graph;

    //Create a fishTank objects
    FishTank fishTank1;
    ArrayList<Fish> listFishTank1 = new ArrayList<>();
    ArrayList<String> listFishTank1Names = new ArrayList<>();
    ArrayList<String> listFishTank2Names = new ArrayList<>();
    ArrayList<String> listFishTank3Names = new ArrayList<>();
    ArrayList<String> listFishTank4Names = new ArrayList<>();
    ArrayList<String> listFishTank5Names = new ArrayList<>();
    ArrayList<String> listFishTank6Names = new ArrayList<>();

    StatusDisplay gameSummaryDisplay;
    int currentNumberOfTanks = 0;

    Fish currentFish;
    FishTank fishTank2;
    ArrayList<Fish> listFishTank2 = new ArrayList<>();
    FishTank fishTank3;
    ArrayList<Fish> listFishTank3 = new ArrayList<>();

    FishTank fishTank4;
    ArrayList<Fish> listFishTank4 = new ArrayList<>();

    FishTank fishTank5;
    ArrayList<Fish> listFishTank5 = new ArrayList<>();

    FishTank fishTank6;
    ArrayList<Fish> listFishTank6 = new ArrayList<>();

    FishTank fishTank7;
    ArrayList<Fish> listFishTank7;

    FishTank fishTank8;
    ArrayList<Fish> listFishTank8;

    ArrayList<FishTank> fishTanksList;

    //Create a car object
    Car car;

    boolean btnIsClicked = false;

    //Create fish objects
    Fish fish1t1;
    Fish fish2t1;
    Fish fish3t1;
    Fish fish4t1;
    Fish fish5t1;
    Fish fish6t1;
    Fish fish7t1;
    Fish fish8t1;
    Fish fish9t1;
    Fish fish10t1;
    Fish fish11t1;
    Fish fish1;
    Description fish1d;
    Description fish1c;
    Description fish2c;
    Description fish3c;
    Description fish4c;
    Description fish5c;
    Description fish6c;
    Description fish7c;
    Description fish8c;
    Description fish9c;
    Description fish10c;
    Description fish11c;
    Description fish2d;
    Description fish3d;
    Description fish4d;
    Description fish5d;
    Description fish6d;
    Description fish7d;
    Description fish8d;
    Description fish9d;
    Description fish10d;
    Description fish11d;
    Fish fish2;
    Fish fish3;
    Fish fish4;
    Fish fish5;
    Fish fish6;
    Fish fish7;
    Fish fish8;
    Fish fish9;
    Fish fish10;
    Fish fish11;

    ArrayList<Fish> fishes;

    Fish draggedFish;

    int TargetNumber;

    //Create Exit
    static Exit exit;

    Button btnplus;
    Button btnminus;

    int plusNumOfClicks = 0;
    int minusNumOfClicks = 0;
    StatusDisplay statusDisplay;
    StatusDisplay targetDisplay;
    int currentValue;
    int fishValue;

    boolean[] removeFishTank = {false,false,false,false,false,false};


    FishFrame frame;

    TwoFixedPositions.PredefinedPosition[] fixedPositions = Arrays.copyOfRange(TwoFixedPositions.PredefinedPosition.values(), 0 , 12);


    public int fishAmount;
    public static int Score = 0;

    public static Random random = new Random();

    //Declare Buttons


    public Game2FieldPanel(FishFrame parentFrame) {
        super(null);
        frame = parentFrame;
        gameSummaryDisplay = new StatusDisplay();
        statusDisplay = new StatusDisplay();
        targetDisplay = new StatusDisplay();
        add(targetDisplay);

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setBackground(Color.LIGHT_GRAY);
    }




    public void FinishGame(boolean successfull){
        if (successfull){
            AddToScore();
        }

        new HighScoreFrame();
    }

    private void AddToScore() {
        GameState.Score  += Math.round(currentValue);
    }

    public void CreateGame(int fishAmount, int fishValue){
        this.fishValue = fishValue;
        this.fishAmount = fishAmount;
        graph = new GraphColoringProblem(fishAmount);
        fishes = new ArrayList<>();
        fishTanksList = new ArrayList<>();
        listFishTank1 = new ArrayList<>();
        fixedPositions = Arrays.copyOfRange(TwoFixedPositions.PredefinedPosition.values(), 0 , 12);

//        this.gameIsStarted = gameIsStarted;
//        graph.greedyColoring();

        fish1 = new Fish("/resources/fish1.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish1);
        fish1d = new Description("/resources/fish1d.png",0.5, TwoFixedPositions.PredefinedPosition.Fish1d);
        fish1c = new Description("/resources/fish1c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish1c);
       fish1t1 = new Fish("/resources/fish1.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish2 = new Fish("/resources/fish2.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish2);
        fish2d = new Description("/resources/fish2d.png",1,TwoFixedPositions.PredefinedPosition.Fish2d);
        fish2c = new Description("/resources/fish2c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish2c);
        fish2t1 = new Fish("/resources/fish2.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish3 = new Fish("/resources/fish3.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish3);
        fish3d = new Description("/resources/fish3d.png",1,TwoFixedPositions.PredefinedPosition.Fish3d);
        fish3c = new Description("/resources/fish3c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish3c);
        fish3t1 = new Fish("/resources/fish3.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish4 = new Fish("/resources/fish4.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish4);
        fish4d = new Description("/resources/fish4d.png",1,TwoFixedPositions.PredefinedPosition.Fish4d);
        fish4c = new Description("/resources/fish4c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish4c);
        fish4t1 = new Fish("/resources/fish4.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish5 = new Fish("/resources/fish5.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish5);
        fish5d = new Description("/resources/fish5d.png",1,TwoFixedPositions.PredefinedPosition.Fish5d);
        fish5c = new Description("/resources/fish5c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish5c);
        fish5t1 = new Fish("/resources/fish5.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish6 = new Fish("/resources/fish6.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish6);
        fish6d = new Description("/resources/fish6d.png",1,TwoFixedPositions.PredefinedPosition.Fish6d);
        fish6c = new Description("/resources/fish6c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish6c);
        fish6t1 = new Fish("/resources/fish6.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish7 = new Fish("/resources/fish7.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish7);
        fish7d = new Description("/resources/fish7d.png",1,TwoFixedPositions.PredefinedPosition.Fish7d);
        fish7c = new Description("/resources/fish7c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish7c);
        fish7t1 = new Fish("/resources/fish7.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish8 = new Fish("/resources/fish8.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish8);
        fish8d = new Description("/resources/fish8d.png",1,TwoFixedPositions.PredefinedPosition.Fish8d);
        fish8c = new Description("/resources/fish8c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish8c);
        fish8t1 = new Fish("/resources/fish8.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish9 = new Fish("/resources/fish9.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish9);
        fish9d = new Description("/resources/fish9d.png",1,TwoFixedPositions.PredefinedPosition.Fish9d);
        fish9c = new Description("/resources/fish9c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish9c);
        fish9t1 = new Fish("/resources/fish9.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish10 = new Fish("/resources/fish10.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish10);
        fish10d = new Description("/resources/fish10d.png",1,TwoFixedPositions.PredefinedPosition.Fish10d);
        fish10c = new Description("/resources/fish10c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish10c);
        fish10t1 = new Fish("/resources/fish10.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fish11 = new Fish("/resources/fish11.png",fishValue,TwoFixedPositions.PredefinedPosition.Fish11);
        fish11d = new Description("/resources/fish11d.png",1,TwoFixedPositions.PredefinedPosition.Fish11d);
        fish11c = new Description("/resources/fish11c.png",0.5, TwoFixedPositions.PredefinedPosition.Fish11c);
        fish11t1 = new Fish("/resources/fish11.png",fishValue,TwoFixedPositions.PredefinedPosition.Tank1);

        fishTank1 = new FishTank("/resources/fishtank.png",15, TwoFixedPositions.PredefinedPosition.Tank1);
        //TODO: Elements should go inside Tank2 and Tank3
        fishTank2 = new FishTank("/resources/fishtank.png",15, TwoFixedPositions.PredefinedPosition.Tank2);
        fishTank3 = new FishTank("/resources/fishtank.png",15, TwoFixedPositions.PredefinedPosition.Tank3);
        fishTank4 = new FishTank("/resources/fishtank.png",15, TwoFixedPositions.PredefinedPosition.Tank4);
        fishTank5 = new FishTank("/resources/fishtank.png",15, TwoFixedPositions.PredefinedPosition.Tank5);
        fishTank6 = new FishTank("/resources/fishtank.png",15, TwoFixedPositions.PredefinedPosition.Tank6);
        car = new Car("/resources/car.png", 15, TwoFixedPositions.PredefinedPosition.BottomMiddleLeftC);
        btnplus = new Button("/resources/button+.png", 15, TwoFixedPositions.PredefinedPosition.BottomLeftB);
        btnminus = new Button("/resources/button-.png", 15, TwoFixedPositions.PredefinedPosition.BottomRightB);


        if (graph.getFishExist()[0]) {
            fishes.add(fish1);
        }
        if (graph.getFishExist()[1]) {
            fishes.add(fish2);
        }
        if (graph.getFishExist()[2]) {
            fishes.add(fish3);
        }
        if (graph.getFishExist()[3]) {
            fishes.add(fish4);
        }
        if (graph.getFishExist()[4]) {
            fishes.add(fish5);
        }
        if (graph.getFishExist()[5]) {
            fishes.add(fish6);
        }
        if (graph.getFishExist()[6]) {
            fishes.add(fish7);
        }
        if (graph.getFishExist()[7]) {
            fishes.add(fish8);
        }
        if (graph.getFishExist()[8]) {
            fishes.add(fish9);
        }
        if (graph.getFishExist()[9]) {
            fishes.add(fish10);
        }
        if (graph.getFishExist()[10]) {
            fishes.add(fish11);
        }

        fishTanksList.add(fishTank1);
//        fishTanksList.add(fishTank2);
//        fishTanksList.add(fishTank3);
//        fishTanksList.add(fishTank4);
//        fishTanksList.add(fishTank5);
//        fishTanksList.add(fishTank6);
        TargetNumber = graph.getMaxColorNumber();
        GameState.TargetValue = graph.getMaxColorNumber();
        int random = (int) Math.floor(Math.random() * (1 + 1) + 0);
        exit = new team06.main.Games.Game2.Elements.Exit(random);
        repaint();

    }


    private void ShowTextPaneF0(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);
            try {
                doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(0), style);
                doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[0], style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

         statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }

    private void ShowTextPaneF1(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

            try {
                doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(1), style);
                doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[1], style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }


        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }
    private void ShowTextPaneF2(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

            try {
                doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(2), style);
                doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[2], style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }


        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }
    private void ShowTextPaneF3(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

            try {
                doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(3), style);
                doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[3], style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }

    private void ShowTextPaneF4(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

        try {
            doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(4), style);
            doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[4], style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }
    private void ShowTextPaneF5(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

            try {
                doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(5), style);
                doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[5], style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }
    private void ShowTextPaneF6(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

            try {
                doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(6), style);
                doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[6], style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }

    private void ShowTextPaneF7(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

        try {
            doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(7), style);
            doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[7], style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }
    private void ShowTextPaneF8(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

        try {
            doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(8), style);
            doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[8], style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }
    private void ShowTextPaneF9(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

        try {
            doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(9), style);
            doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[9], style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }
    private void ShowTextPaneF10(int x, int y){
        if (statusDisplay.IsActive)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);

        try {
            doc.insertString(doc.getLength(), " Current Fish: " + graph.getFishSpecies().get(10), style);
            doc.insertString(doc.getLength(), " \nIn Conflict with: " + graph.getFishConflicts()[10], style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x,y,150,50);
        add(statusDisplay);
    }

    private void ShowTargetText(){
        StyledDocument doc = targetDisplay.getStyledDocument();
        targetDisplay.setText("Max tanks : " + graph.getMaxColorNumber());
        targetDisplay.setBounds((getWidth() / 2) - 50, 0, 100, 20);
    }

    private void RemoveStatusDisplay(){
        if(statusDisplay.IsActive){
            remove(statusDisplay);
            statusDisplay.setText("");
            statusDisplay.IsActive = false;
            repaint();
        }
    }

    public static boolean CheckIfFullyBetweenExit(ElementBase source){
        return exit.getPositionX() < (source.getPositionX())
                && (source.getPositionX() + source.getWidth()) < (exit.getPositionX() + exit.getWidth());
    }

    private boolean CheckIfInItem(int posX, int posY, ElementBase item) {
        return item.getPositionX() < posX && posX < (item.getPositionX() + item.getWidth())
                && item.getPositionY() < posY && posY < (item.getPositionY() + item.getHeight());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        RemoveStatusDisplay();

        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_LEFT:
                car.moveLeft();
                break;

            case KeyEvent.VK_RIGHT:
                car.moveRight(getWidth());
                break;

                //Pause the game
            case KeyEvent.VK_P:
                if (!GameState.IsGuideOpen)
                    frame.PauseByGamePanel();
                break;
        }
        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        RemoveStatusDisplay();

        repaint();
        if(car.getTruePositionY(getWidth(), getHeight()) >= getHeight() -25)
            EvaluateAndFinishLevel();
    }

    private void ResetGame(){
        fishTanksList = null;
        exit = null;
        fishes = new ArrayList<>();
    }
    private void ShowSummaryText(boolean levelFailed){
        StyledDocument doc = gameSummaryDisplay.getStyledDocument();

        var gameSuccessText = "";
        if(levelFailed)
            gameSuccessText = "Level failed.";
        else
            gameSuccessText = "Success!";
        gameSummaryDisplay.setText(new StringBuilder()
                .append(gameSuccessText + "\n Optimal solution for value with the given items: ")
                .append(TargetNumber).toString());
        gameSummaryDisplay.setBounds((getWidth() / 2) - 150, (getHeight() / 2) - 25, 300, 50);
        add(gameSummaryDisplay);
    }

    private void EvaluateAndFinishLevel() {

        boolean successful = false;
        frame.StopTimerFromGame();

        ResetGame();
        ShowSummaryText(!successful);
        repaint();

        ArrayList<String> fishHistory = graph.getFishHistory();
        String[] colors = graph.getColors();
        ArrayList<String> colorsFishTank;
        ArrayList<String> red = new ArrayList<>();
        red.add("RED");
        ArrayList<String> blue = new ArrayList<>();
        blue.add("BLUE");
        ArrayList<String> purple = new ArrayList<>();
        purple.add("PURPLE");
        ArrayList<String> orange = new ArrayList<>();
        orange.add("ORANGE");
        ArrayList<String> white = new ArrayList<>();
        white.add("WHITE");
        ArrayList<String> green = new ArrayList<>();
        green.add("GREEN");
        ArrayList<String> yellow = new ArrayList<>();
        yellow.add("YELLOW");
        ArrayList<String> brown = new ArrayList<>();
        brown.add("BROWN");


        if (!listFishTank1Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank1Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank2Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank2Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank3Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank3Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank4Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank4Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank5Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank5Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank6Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank6Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }


        if (successful && currentNumberOfTanks == graph.getMaxColorNumber()){
            FinishGame(successful);
        } else {
            FinishGame(false);
            return;
        }

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}

            GameState.Level++;
            AddToScore();
            frame.FinishByGamePanel(true);
            CreateGame(fishAmount,fishValue);
        });
        thread.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        var size = getSize();

        for (var fish : fishes) {
            fish.DrawElement(g, size.width, size.height, draggedFish == fish);
        }

        for (var fish : listFishTank1) {
            fish.DrawElement(g, size.width, size.height, true);
        }

        for (var fish : listFishTank2) {
            fish.DrawElement(g, size.width, size.height, true);
        }


        //Draws all fishTanks
        for (var fishTank : fishTanksList) {
            fishTank.DrawElement(g, size.width, size.height, false);
        }

        exit.DrawExit(g, size.width, size.height);
        if (graph.getFishExist()[0]) {
        fish1d.DrawElement(g, size.width, size.height, false);
        fish1c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[1]) {
            fish2d.DrawElement(g, size.width, size.height, false);
            fish2c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[2]) {
            fish3d.DrawElement(g, size.width, size.height, false);
            fish3c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[3]) {
            fish4d.DrawElement(g, size.width, size.height, false);
            fish4c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[4]) {
            fish5d.DrawElement(g, size.width, size.height, false);
            fish5c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[5]) {
            fish6d.DrawElement(g, size.width, size.height, false);
            fish6c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[6]) {
            fish7d.DrawElement(g, size.width, size.height, false);
            fish7c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[7]) {
            fish8d.DrawElement(g, size.width, size.height, false);
            fish8c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[8]) {
            fish9d.DrawElement(g, size.width, size.height, false);
            fish9c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[9]) {
            fish10d.DrawElement(g, size.width, size.height, false);
            fish10c.DrawElement(g, size.width, size.height, false);
        }
        if (graph.getFishExist()[10]) {
            fish11d.DrawElement(g, size.width, size.height, false);
            fish11c.DrawElement(g, size.width, size.height, false);
        }

        //TODO: Fish tank disappears. Research why it happends and use it when minus is clicked

        //Draw tanks
//        fishTank1.DrawElement(g, size.width, size.height, false);

            if (plusNumOfClicks == 0) {
                fishTanksList.add(fishTank2);
                currentNumberOfTanks++;
            }
            if (plusNumOfClicks == 1) {
                fishTanksList.add(fishTank3);
                currentNumberOfTanks++;
            }
            if (plusNumOfClicks == 2) {
                fishTanksList.add(fishTank4);
                currentNumberOfTanks++;
            }
            if (plusNumOfClicks == 3) {
                fishTanksList.add(fishTank5);
                currentNumberOfTanks++;
            }
            if (plusNumOfClicks == 4) {
                fishTanksList.add(fishTank6);
                currentNumberOfTanks++;
            }

            if (removeFishTank[0]) {
                fishTanksList.remove(0);
            }

            if (btnIsClicked) {
                for (int i = 0; i < fishTanksList.size(); i++) {
                    if (removeFishTank[i]) {
                        fishTanksList.remove(i);
                    }
                }
            }

//        if (removeFishTank[0]){
//            fishTanksList.remove(0);
//        }
//        if (removeFishTank[1]){
//            fishTanksList.remove(1);
//        }
//        if (removeFishTank[2]){
//            fishTanksList.remove(2);
//        }
//        if (removeFishTank[3]){
//            fishTanksList.remove(3);
//        }
//        if (removeFishTank[4]){
//            fishTanksList.remove(4);
//        }
//        if (removeFishTank[5]){
//            fishTanksList.remove(5);
//        }



        car.DrawElement(g, size.width, size.height, false);


        btnminus.DrawElement(g, size.width, size.height, false);
        btnplus.DrawElement(g, size.width, size.height, false);

        ShowTargetText();
    }

    public void EvaluateAndFinishGame() {

        boolean successful = false;
        ArrayList<String> fishHistory = graph.getFishHistory();
        String[] colors = graph.getColors();
        ArrayList<String> colorsFishTank;
        ArrayList<String> red = new ArrayList<>();
        red.add("RED");
        ArrayList<String> blue = new ArrayList<>();
        blue.add("BLUE");
        ArrayList<String> purple = new ArrayList<>();
        purple.add("PURPLE");
        ArrayList<String> orange = new ArrayList<>();
        orange.add("ORANGE");
        ArrayList<String> white = new ArrayList<>();
        white.add("WHITE");
        ArrayList<String> green = new ArrayList<>();
        green.add("GREEN");
        ArrayList<String> yellow = new ArrayList<>();
        yellow.add("YELLOW");
        ArrayList<String> brown = new ArrayList<>();
        brown.add("BROWN");


        if (!listFishTank1Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank1Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank1Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank2Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank2Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank2Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank3Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank3Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank3Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank4Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank4Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank4Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank5Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank5Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank5Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }
        if (!listFishTank6Names.isEmpty()) {

            colorsFishTank = new ArrayList<>();
            Iterator<String> it = listFishTank6Names.iterator();
            while (it.hasNext()) {
                String currentFish = it.next();
                for (int i = 0; i < fishHistory.size(); i++) {
                    if (fishHistory.get(i) == currentFish) {
                        colorsFishTank.add(colors[i]);
                        break;
                    }
                }
            }


            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (red.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (blue.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (purple.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (orange.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (white.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (green.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (yellow.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }

            for (int i = 0; i < listFishTank6Names.size(); i++) {
                if (brown.containsAll(colorsFishTank)) {
                    successful = true;
                    break;
                }
            }
        }


        if (successful && currentNumberOfTanks == graph.getMaxColorNumber()){
            FinishGame(successful);
        } else {
            FinishGame(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

       if (CheckIfInItem(e.getX(), e.getY(), fish1)){
            ShowTextPaneF0(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish2)){
            ShowTextPaneF1(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish3)){
            ShowTextPaneF2(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish4)){
            ShowTextPaneF3(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish5)){
            ShowTextPaneF4(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish6)){
            ShowTextPaneF5(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish7)){
            ShowTextPaneF6(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish8)){
            ShowTextPaneF7(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish9)){
            ShowTextPaneF8(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish10)){
            ShowTextPaneF9(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), fish11)){
            ShowTextPaneF10(e.getX(), e.getY());
        } else if (CheckIfInItem(e.getX(), e.getY(), btnplus)){
           plusNumOfClicks++;
           repaint();
       } else if (CheckIfInItem(e.getX(), e.getY(), btnminus)){
           btnIsClicked = true;
           if (listFishTank1.isEmpty()){
               removeFishTank[0] = true;
               repaint();
           }
           if (listFishTank2.isEmpty()){
              removeFishTank[1] = true;
               repaint();
           }
           if (listFishTank3.isEmpty()){
               removeFishTank[2] = true;
               repaint();
           }
           if (listFishTank4.isEmpty()){
               removeFishTank[3] = true;
               repaint();
           }
           if (listFishTank5.isEmpty()){
               removeFishTank[4] = true;
               repaint();
           }
           if (listFishTank6.isEmpty()){
               removeFishTank[5] = true;
               repaint();
           }

           repaint();
       }else {
            RemoveStatusDisplay();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        RemoveStatusDisplay();

        if(draggedFish == null)
            return;

        //Fish exists
        currentFish = draggedFish;
        ArrayList<String> fishSpecies = graph.getFishSpecies();

        var mouseX = e.getX();
        var mouseY = e.getY();

        if(fishTank1.getPositionX() < mouseX && mouseX < (fishTank1.getPositionX() + fishTank1.getSizeX() * fishTank1.quantifier)
                && fishTank1.getPositionY() < mouseY && mouseY < (fishTank1.getPositionY() + fishTank1.getSizeY() * fishTank1.quantifier)){
            currentValue += draggedFish.Value;
            //TODO: Fish has to be painted here
//            fishes.remove(currentFish);
            if (draggedFish == fish1){
                listFishTank1.add(fish1t1);
                listFishTank1Names.add("AF");
            }
            if (draggedFish == fish2){
                listFishTank1.add(fish2t1);
                listFishTank1Names.add("GF");
            }
            if (draggedFish == fish3){
                listFishTank1.add(fish3t1);
                listFishTank1Names.add("G");
            }
            if (draggedFish == fish4){
                listFishTank1.add(fish4t1);
                listFishTank1Names.add("S");
            }
            if (draggedFish == fish5){
                listFishTank1.add(fish5t1);
                listFishTank1Names.add("KF");
            }
            if (draggedFish == fish6){
                listFishTank1.add(fish6t1);
                listFishTank1Names.add("P");
            }
            if (draggedFish == fish7){
                listFishTank1.add(fish7t1);
                listFishTank1Names.add("R");
            }
            if (draggedFish == fish8){
                listFishTank1.add(fish8t1);
                listFishTank1Names.add("E");
                repaint();
            }
            if (draggedFish == fish9){
                listFishTank1.add(fish9t1);
                listFishTank1Names.add("T");
            }
            if (draggedFish == fish10){
                listFishTank1.add(fish10t1);
                listFishTank1Names.add("RF");
            }
            if (draggedFish == fish11){
                listFishTank1.add(fish11t1);
                listFishTank1Names.add("B");
            }
//            listFishTank1.add(currentFish);
        }

        if(fishTank2.getPositionX() < mouseX && mouseX < (fishTank2.getPositionX() + fishTank2.getSizeX() * fishTank2.quantifier)
                && fishTank1.getPositionY() < mouseY && mouseY < (fishTank2.getPositionY() + fishTank2.getSizeY() * fishTank2.quantifier)){
            currentValue += draggedFish.Value;

            if (draggedFish == fish1){
                listFishTank2.add(fish1t1);
                listFishTank1Names.add("AF");
            }
            if (draggedFish == fish2){
                listFishTank2.add(fish2t1);
                listFishTank1Names.add("GF");
            }
            if (draggedFish == fish3){
                listFishTank2.add(fish3t1);
                listFishTank1Names.add("G");
            }
            if (draggedFish == fish4){
                listFishTank2.add(fish4t1);
                listFishTank1Names.add("S");
            }
            if (draggedFish == fish5){
                listFishTank2.add(fish5t1);
                listFishTank1Names.add("KF");
            }
            if (draggedFish == fish6){
                listFishTank2.add(fish6t1);
                listFishTank1Names.add("P");
            }
            if (draggedFish == fish7){
                listFishTank2.add(fish7t1);
                listFishTank1Names.add("R");
            }
            if (draggedFish == fish8){
                listFishTank2.add(fish8t1);
                listFishTank1Names.add("E");
                repaint();
            }
            if (draggedFish == fish9){
                listFishTank2.add(fish9t1);
                listFishTank1Names.add("T");
            }
            if (draggedFish == fish10){
                listFishTank2.add(fish10t1);
                listFishTank1Names.add("RF");
            }
            if (draggedFish == fish11){
                listFishTank2.add(fish11t1);
                listFishTank1Names.add("B");
            }
//            fishes.remove(draggedFish);
        }

        if(fishTank3.getPositionX() < mouseX && mouseX < (fishTank3.getPositionX() + fishTank3.getSizeX() * fishTank3.quantifier)
                && fishTank3.getPositionY() < mouseY && mouseY < (fishTank3.getPositionY() + fishTank3.getSizeY() * fishTank3.quantifier)){
            currentValue += draggedFish.Value;

//            fishes.remove(draggedFish);
        }

        if(fishTank4.getPositionX() < mouseX && mouseX < (fishTank4.getPositionX() + fishTank4.getSizeX() * fishTank4.quantifier)
                && fishTank4.getPositionY() < mouseY && mouseY < (fishTank4.getPositionY() + fishTank4.getSizeY() * fishTank4.quantifier)){
            currentValue += draggedFish.Value;

//            fishes.remove(draggedFish);
        }

        if(fishTank5.getPositionX() < mouseX && mouseX < (fishTank5.getPositionX() + fishTank5.getSizeX() * fishTank5.quantifier)
                && fishTank5.getPositionY() < mouseY && mouseY < (fishTank5.getPositionY() + fishTank5.getSizeY() * fishTank5.quantifier)){
            currentValue += draggedFish.Value;

//            fishes.remove(draggedFish);
        }
        if(fishTank6.getPositionX() < mouseX && mouseX < (fishTank6.getPositionX() + fishTank6.getSizeX() * fishTank6.quantifier)
                && fishTank6.getPositionY() < mouseY && mouseY < (fishTank6.getPositionY() + fishTank6.getSizeY() * fishTank6.quantifier)){
            currentValue += draggedFish.Value;

//            fishes.remove(draggedFish);
        }
        //TODO: Do it also for the other fishTanks
        draggedFish = null;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        RemoveStatusDisplay();
        if(draggedFish == null){
            var mouseX = e.getX();
            var mouseY = e.getY();
            for (var item : fishes) {
                if(CheckIfInItem(mouseX, mouseY, item)) {
                    draggedFish = item;
                    break;
                }
            }
            for (var item : listFishTank1) {
                if(CheckIfInItem(mouseX, mouseY, item)) {
                    draggedFish = item;
                    break;
                }
            }
            if(draggedFish == null)
                return;
        }

        draggedFish.Reposition(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}


