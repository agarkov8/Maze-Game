package team06.main.Games.Game1.GameUI;

import team06.main.Games.Game1.BackPackFrame;
import team06.main.Games.Game1.BackpackCalculation.BackPackProblem;
import team06.main.Games.Game1.GameElements.*;
import team06.main.Games.Shared.GameState;
import team06.main.Games.Shared.Highscore.HighScoreFrame;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static team06.main.Games.Game1.GameUI.FixedPositions.PredefinedPosition.Center;

public class GamePanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener {
    Backpack backpack;
    static Exit exit;
    ArrayList<Item> items = new ArrayList<>();
    Item draggedItem;
    StatusDisplay statusDisplay;
    StatusDisplay targetDisplay;
    StatusDisplay gameSummaryDisplay;
    FixedPositions.PredefinedPosition[] fixedPositions = Arrays.copyOfRange(FixedPositions.PredefinedPosition.values(), 0 , 12);

    int currentWeight;
    int currentValue;
    double currentQuantifier;

    public int ItemAmount;
    public int OptimalValue;
    public int BackpackSize;
    public static Random random = new Random();
    BackPackFrame frame;

    public GamePanel(BackPackFrame parentFrame){
        super(null);
        frame = parentFrame;
        statusDisplay = new StatusDisplay();
        targetDisplay = new StatusDisplay();
        gameSummaryDisplay = new StatusDisplay();
        add(targetDisplay);

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void CreateGame(double targetQuantifier, int itemAmount) {
        ItemAmount = itemAmount;
        currentQuantifier = targetQuantifier;
        currentWeight = 0;
        currentValue = 0;
        items = new ArrayList<>();
        BackpackSize = random.nextInt(4, 51);
        fixedPositions = Arrays.copyOfRange(FixedPositions.PredefinedPosition.values(), 0 , 12);

        for (int i = 0; i < ItemAmount; i++) {
            var fixedPosition = GetRandomFixedPosition();
            items.add(new Item("/resources/can.png",  random.nextInt(1,7),
                    random.nextInt(1, 71),fixedPosition));
        }
        OptimalValue = BackPackProblem.CalculateBackpackSolution(BackpackSize, items);
        GameState.TargetValue = (int) Math.ceil(OptimalValue * targetQuantifier);

        backpack = new Backpack("/resources/backpack.png",  BackpackSize, Center);
        exit = new Exit(random.nextInt(1, 200));
        repaint();
    }

    private void ResetGame(){
        backpack = null;
        exit = null;
        items = new ArrayList<>();
        BackpackSize = 0;
    }

    public void EvaluateAndFinishLevel() {
        var successful = currentWeight <= BackpackSize && currentValue >= GameState.TargetValue;
        frame.StopTimerFromGame();

        ResetGame();
        ShowSummaryText(!successful);
        repaint();

        if(!successful){
            FinishGame(false);
            return;
        }

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}

            remove(gameSummaryDisplay);
            GameState.Level++;
            AddToScore();
            frame.FinishByGamePanel(true);
            CreateGame(currentQuantifier, ItemAmount);
        });
        thread.start();
    }

    public void EvaluateAndFinishGame(){
        var successful = currentWeight <= BackpackSize && currentValue >= GameState.TargetValue;
        FinishGame(successful);
    }

    public void FinishGame(boolean successful) {
        if(successful)
            AddToScore();

        ResetGame();
        ShowSummaryText(!successful);
        repaint();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}

            remove(gameSummaryDisplay);
            new HighScoreFrame();
            frame.FinishByGamePanel(false);
        });
        thread.start();
        repaint();
    }

    private void AddToScore() {
        GameState.Score  += currentValue;
    }

    private FixedPositions.PredefinedPosition GetRandomFixedPosition(){
        while(true){
            var index = random.nextInt(0, 12);
            var position = fixedPositions[index];
            fixedPositions[index] = null;
            if(position != null)
                return position;
        }
    }

    private void ShowTextPane(int x, int y){
        if(statusDisplay.IsActive || BackpackSize <= 0)
            return;
        StyledDocument doc = statusDisplay.getStyledDocument();

        Style style = statusDisplay.addStyle("Color Style", null);
        try {
            doc.insertString(doc.getLength(), "Aktueller Wert: " + currentValue, style);
            doc.insertString(doc.getLength(), " \nRestliches Gewicht: " +(BackpackSize - currentWeight), style);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
        statusDisplay.IsActive = true;
        statusDisplay.setBounds(x, y, 150, 50);
        add(statusDisplay);
    }

    private void ShowTargetText(){
        StyledDocument doc = targetDisplay.getStyledDocument();

        targetDisplay.setText(new StringBuilder().append("Gewichtsschranke: ").append(BackpackSize).toString());
        targetDisplay.setBounds((getWidth() / 2) - 75, 0, 150, 20);
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
                .append(OptimalValue).toString());
        gameSummaryDisplay.setBounds((getWidth() / 2) - 150, (getHeight() / 2) - 25, 300, 50);
        add(gameSummaryDisplay);
    }

    private void RemoveStatusDisplay() {
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

    public static boolean CheckIfAbsoluteFullyBetweenExit(int x, int width){
        return exit.getPositionX() < x && (x + width) < (exit.getPositionX() + exit.getWidth());
    }

    private boolean CheckIfInItem(int posX, int posY, ElementBase item) {
        if(item == null)
            return false;
        return item.getPositionX() < posX && posX < (item.getPositionX() + item.getWidth())
                && item.getPositionY() < posY && posY < (item.getPositionY() + item.getHeight());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                backpack.moveLeft(getHeight());
                break;

            case KeyEvent.VK_RIGHT:
                backpack.moveRight(getWidth(), getHeight());
                break;

            case KeyEvent.VK_UP:
                backpack.moveUp();
                break;

            case KeyEvent.VK_DOWN:
                backpack.moveDown(getHeight());
                break;

            case KeyEvent.VK_P:
                if(!GameState.IsGuideOpen)
                    frame.PauseByGamePanel();
                break;
        }

        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        RemoveStatusDisplay();

        repaint();
        if(backpack.getTruePositionY(getWidth(), getHeight()) >= getHeight() -25)
            EvaluateAndFinishLevel();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(exit == null || backpack == null)
            return;
        var size = getSize();

        for (var item :
                items) {
            item.DrawElement(g, size.width, size.height, draggedItem == item);
        }
        exit.DrawExit(g, size.width, size.height);
        backpack.DrawElement(g, size.width, size.height, false);
        if(draggedItem != null)
            draggedItem.DrawElement(g, size.width, size.height,true);
        ShowTargetText();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;
        var size = getSize();

        RemoveStatusDisplay();
        if(draggedItem != null){

            var newX = e.getX();
            var newY = e.getY();
            if(e.getX() < getX()){
                newX = getX();
            }
            else if(e.getX() > getX() + size.width - draggedItem.getWidth()){
                newX = getX() + size.width - draggedItem.getWidth();
            }
            if(e.getY() < getY()){
                newY = getY();
            }
            else if(e.getY() > getY() + size.height - draggedItem.getHeight()){
                newY = getY() + size.height  - draggedItem.getHeight();
            }


            draggedItem.Reposition(newX, newY);
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        if(CheckIfInItem(e.getX(), e.getY(), backpack))
            ShowTextPane(e.getX(), e.getY());
        else
            RemoveStatusDisplay();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(draggedItem == null){
            var mouseX = e.getX();
            var mouseY = e.getY();
            for (var item : items) {
                if(CheckIfInItem(mouseX, mouseY, item)) {
                    draggedItem = item;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        RemoveStatusDisplay();

        if(draggedItem == null)
            return;

        var mouseX = e.getX();
        var mouseY = e.getY();

        if(backpack.getPositionX() < mouseX && mouseX < (backpack.getPositionX() + backpack.getSizeX() * backpack.quantifier)
                && backpack.getPositionY() < mouseY && mouseY < (backpack.getPositionY() + backpack.getSizeY() * backpack.quantifier)){
            currentWeight += draggedItem.Weight;
            currentValue += draggedItem.Value;

            items.remove(draggedItem);
        }

        draggedItem = null;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
