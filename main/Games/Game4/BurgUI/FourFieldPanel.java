package team06.main.Games.Game4.BurgUI;

import team06.main.Games.Game4.BurgFrame;
import team06.main.Games.Game4.Elements.Burg;
import team06.main.Games.Game4.Elements.DorfEntity;
import team06.main.Games.Game4.Elements.DorfPosition;
import team06.main.Games.Game4.Elements.Thron;
import team06.main.Games.Shared.GameState;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;


public class FourFieldPanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener {
    BurgFrame frame4;
    Thron thron;
    Burg dragBurg;
    DorfPosition burgStandardPosition = new DorfPosition(0.5, 0.05);
    ArrayList<Burg> burgList = new ArrayList<>();
    ArrayList<DorfEntity> dorfList;
    static DorfEntity[][] combinations;
    StatusDisplay statusDisplay;
    StatusDisplay targetDisplay;
    StatusDisplay gameSummaryDisplay;


    private int dorfAmount;
    private int burgAmount;
    private double nextBurg;
    public int OptimalValue;
    public int TargetValue;
    public int ThronSize;
    public static int Score = 0;
    public static int currentindex = 0;
    public static Random random = new Random();


    public FourFieldPanel(BurgFrame burgFrame) {
        super(null);
        this.setBackground(Color.pink);
        statusDisplay = new StatusDisplay();
        targetDisplay = new StatusDisplay();
        gameSummaryDisplay = new StatusDisplay();
        add(targetDisplay);

        this.setFocusable(true);
        this.requestFocusInWindow();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void CreateGame(int dorfAmount, int burgAmount, double nextBurg) {
        ;
        this.dorfAmount = dorfAmount;
        this.burgAmount = burgAmount;
        this.nextBurg = nextBurg;
        burgList = new ArrayList<>();
        dorfList = new ArrayList<>();
        ThronSize = random.nextInt(4, 51);
        //fixedPositions = Arrays.copyOfRange(FourFixPositions.PredefinedPosition.values(), 0, 13);
        ArrayList<DorfPosition> dorfPositions = buildDoerfer(dorfAmount);
        combinations = new DorfEntity[getPossibleCombinations()][burgAmount];

        for (int i = 0; i < burgAmount; i++) {
            burgList.add(new Burg("/resources/burg.png", new DorfPosition(nextBurg, 0.05)/*burgStandardPosition*/));
            nextBurg += 0.1;
        }

        for (int j = 0; j < dorfAmount; j++) {
            DorfEntity dorf = new DorfEntity(dorfPositions.get(j));
            dorfList.add(dorf);
        }

//        combinations = new DorfEntity[getPossibleCombinations()][burgAmount];
////        combinationen(new DorfEntity([burgAmount]));
//       combinationen(dorfList, burgAmount, 0, new DorfEntity[burgAmount]);
//        System.out.println(Arrays.toString(combinations[0]));


        selectDoerferMitBurg(dorfAmount, calculateDistances(dorfAmount), burgAmount);

        thron = new Thron("/resources/Thron_2.png", new DorfPosition(0.1, 0.11));
        repaint();
    }

    public ArrayList<DorfPosition> buildDoerfer(int amount) {
        ArrayList<DorfPosition> returnList = new ArrayList<>();

        while (amount > 0) {
            double x = 0.15 + (0.85 - 0.15) * random.nextDouble();
            double y = 0.15 + (0.85 - 0.15) * random.nextDouble();

            if (isTooClose(returnList, x, y)) {
                continue;
            }

            returnList.add(new DorfPosition(x, y));
            amount--;
        }

        return returnList;
    }

    public boolean isTooClose(ArrayList<DorfPosition> returnList, double x, double y) {
        for (var item :
                returnList) {

            // calculate distance
            if (Math.hypot(x - item.getxCoord(), y - item.getyCoord()) < 0.12) {
                return true;
            }
        }
        return false;
    }


    public void EvaluateAndFinishLevel() {
        var successful = checkFinish();
        frame4.StopTimerFromGame();

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
            frame4.FinishByGamePanel(true);
            CreateGame(dorfAmount, burgAmount, nextBurg);
        });
        thread.start();
    }

    private void AddToScore() {
        GameState.Score  += dorfAmount;
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

    private void ResetGame(){
        CreateGame(this.dorfAmount, this.burgAmount, this.nextBurg);
    }

    public void FinishGame(boolean successful) {
        if (!successful)
            return; //TODO: Create lose process

//        Score += Math.round(currentValue * currentQuantifier);
//        CreateGame(, 7);
    }


    private void RemoveStatusDisplay() {
        if (statusDisplay.IsActive) {
            remove(statusDisplay);
            statusDisplay.setText("");
            statusDisplay.IsActive = false;
            repaint();
        }
    }

    private boolean CheckIfInItem(int posX, int posY, DorfPosition position) {
        return position.getxCoord() < posX && posX < (position.getxCoord() + getWidth() * 0.05)
                && position.getyCoord() < posY && posY < (position.getyCoord() + getHeight() * 0.05);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        var size = getSize();

        for (var dorf :
                dorfList) {
            g2d.setColor(dorf.getDorfColor());
//
//            if (dorf.getHasBurg() && !dorf.getHasThron()) {
//                g2d.setColor(Color.LIGHT_GRAY);
//            } else if (dorf.getHasThron()) {
//                g2d.setColor(Color.YELLOW);
//            } else {
//                g2d.setColor(Color.BLACK);
//            }
            g2d.fillOval((int) (dorf.getX() * getWidth()), (int) (dorf.getY() * getHeight()), dorf.getSizeX(), dorf.getSizeY());
        }

        g2d.drawImage(thron.getImage(), (int) (thron.getX() * getWidth()) - 13, (int) (thron.getY() * getHeight()) - 55, thron.getSizeX(), thron.getSizeY(), null);


        for (var item :
                burgList) {
            g2d.drawImage(item.getImage(), (int) (item.getDorfPosition().getxCoord() * getWidth()), (int) (item.getDorfPosition().getyCoord() * getHeight()), item.getSizeX(), item.getSizeY(), null);
        }
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

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                thron.moveLeft(getWidth());
                break;

            case KeyEvent.VK_RIGHT:
                thron.moveRight(getWidth());
                break;

            case KeyEvent.VK_UP:
                thron.moveUp(getHeight());
                break;

            case KeyEvent.VK_DOWN:
                thron.moveDown(getHeight());
                break;
            case KeyEvent.VK_R:
                CreateGame(this.dorfAmount, this.burgAmount, this.nextBurg);
                break;
            case KeyEvent.VK_P:
                if (!GameState.IsGuideOpen)
                    frame4.PauseByGamePanel();
                break;
        }
        if(!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        RemoveStatusDisplay();

        repaint();
        if(checkFinish()){
            checkNoBurg(selectDoerferMitBurg(dorfAmount, calculateDistances(dorfAmount), burgAmount));
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        checkIfValidThronPosition(thron.getX(), thron.getY());
        repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        double mouseX = e.getX();
        double mouseY = e.getY();

        RemoveStatusDisplay();
        if (dragBurg != null) {
            dragBurg.setDorfPosition(new DorfPosition(mouseX / getWidth(), mouseY / getHeight()));
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        double mouseX = e.getX();
        double mouseY = e.getY();
        for (var dorf :
                dorfList) {
            if (Math.hypot(mouseX / getWidth() - dorf.getPosition().getxCoord(), mouseY / getHeight() - dorf.getPosition().getyCoord()) < 0.1) {
                DorfEntity closestDorfWithBurg = getClosestDorfWithBurg(dorf);

                if (closestDorfWithBurg == null) {
                    new FourWarnFrame();
                    break;
                }

                closestDorfWithBurg.setDorfColor(Color.GREEN);
                repaint();

                break;
            }
        }
    }

    private int getPossibleCombinations() {
        return (((getFaculty(dorfAmount) / (getFaculty(burgAmount)) * getFaculty(dorfAmount - burgAmount))));
    }

    private int getFaculty(int number) {
        int fact = 1;
        for (int i = 1; i <= number; i++) {
            fact = fact * i;
        }
        return fact;
    }

    private double[] calculateMaxDistanceOfEachPossibleSolution(DorfEntity[] combinations) {

        double[] returnArray = new double[combinations.length];

        for (int i = 0; i < combinations.length; i++) {

        }

        return returnArray;
    }

    private void combinationen(ArrayList<DorfEntity> dorfList, int len, int startPosition, DorfEntity[] results) {
       if (len == 0) {
            this.combinations[this.currentindex] = results;
            this.currentindex++;
        }

        for (int i = startPosition; i <= dorfList.size()-len; i++) {
            results[results.length - len] = dorfList.get(i);
            combinationen(dorfList, len - 1, i + 1, results);

        }
    }

//    static DorfEntity[][] combinations(ArrayList<DorfEntity> dorfList, int len, int startPosition, DorfEntity[] results) {
//
//        if (len == 0) {
//            combinations[currentindex++] = results;
//            currentindex++;
//        }if (len == 0) {
//            combinations[currentindex++] = results;
//            currentindex++;
//        }
//        for (int i = startPosition; i <= dorfList.size() - len; i++) {
//            results[results.length - len] = dorfList.get(i);
//            combinations(dorfList, len - 1, i + 1, results);
//        }
//        return new DorfEntity[][]{results};
//    }


    private double[][] calculateDistances(int dorfAmount) {

        double[][] weights = new double[dorfAmount][dorfAmount];
        double tempDistance = 0.0;

        for (int i = 0; i < dorfAmount; i++) {
            for (int j = 0; j < dorfAmount; j++) {
                tempDistance = calculateDistanceBetweenTwoDoerfer(dorfList.get(i), dorfList.get(j));
                if (tempDistance != 0) {
                    weights[i][j] = tempDistance;
//                    System.out.println(weights[i][j]);
                }

            }
        }
        return weights;
    }

    private ArrayList<Integer> selectDoerferMitBurg(int anzahlDoerfer, double[][] weights, int anzahlBurgen) {
        double[] dist = new double[anzahlDoerfer];
        ArrayList<Integer> centers = new ArrayList<>();
        for(int i = 0; i < anzahlDoerfer; i++)
        {
            dist[i] = Integer.MAX_VALUE;
        }

        int max = 0;
        for(int i = 0; i < anzahlBurgen; i++)
        {
            centers.add(max);
            for(int j = 0; j < anzahlDoerfer; j++)
            {

                dist[j] = Math.min(dist[j], weights[max][j]);
            }

            max = maxindex(dist, anzahlDoerfer);
        }

//        System.out.println(dist[max]);

        for(int i = 0; i < centers.size(); i++)
        {
            System.out.print(centers.get(i) + " ");
        }
//        System.out.print("\n");

        return centers;
    }

    static int maxindex(double[] dist, int n)
    {
        int mi = 0;
        for(int i = 0; i < n; i++)
        {
            if (dist[i] > dist[mi])
                mi = i;
        }
        return mi;
    }

    public DorfEntity getClosestDorfWithBurg(DorfEntity inputDorf) {
        if (inputDorf.getHasBurg()) {
            return inputDorf;
        }

        DorfEntity outputDorf = null;
        double currentDistance = Double.POSITIVE_INFINITY;

        for (var dorf :
                dorfList) {
            if (dorf.getHasBurg() && !dorf.equals(inputDorf)) {
                double calculatedDistance = calculateDistanceBetweenTwoDoerfer(inputDorf, dorf);

                if (calculatedDistance < currentDistance) {
                    outputDorf = dorf;
                    currentDistance = calculatedDistance;
                }
            }
        }
        return outputDorf;
    }

    private void storeNewCombiToCombiArray(DorfEntity[] newCombi) {
        int currentIndex = this.combinations.length; //das könnte auch length-1 sein musst du mal gucken
// dann setzen wir die neue kombi in das array mit allen kombis ein
        this.combinations[currentIndex] = newCombi;
    }

    public double disClosestDorfWithBurg(DorfEntity inputDorf) {
        if (inputDorf.getHasBurg()) {
            return 0.0;
        }

        DorfEntity outputDorf = null;
        double currentDistance = Double.POSITIVE_INFINITY;

        for (var dorf :
                dorfList) {
            if (dorf.getHasBurg() && !dorf.equals(inputDorf)) {
                double calculatedDistance = calculateDistanceBetweenTwoDoerfer(inputDorf, dorf);

                if (calculatedDistance < currentDistance) {
                    outputDorf = dorf;
                    currentDistance = calculatedDistance;
                }
            }
        }
        return currentDistance;
    }



    public double calculateDistanceBetweenTwoDoerfer(DorfEntity dorf1, DorfEntity dorf2) {
        double distance = Point2D.distance(dorf1.getX(), dorf1.getY(), dorf2.getX(), dorf2.getY());
        return distance;
//        return Math.hypot(ac, cb);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        checkAllDoerferForBurg();
        repaint();

        if (dragBurg == null) {
            double mouseX = e.getX();
            double mouseY = e.getY();
            for (var item : burgList) {
                if (Math.hypot(mouseX / getWidth() - item.getDorfPosition().getxCoord(), mouseY / getHeight() - item.getDorfPosition().getyCoord()) < 0.1) {
                    dragBurg = item;
                    dragBurg.setLastValidPosition(dragBurg.getDorfPosition());
                    break;
                }
            }
        }
    }
    public boolean checkFinish() {
        int burgCount = 0;
        for (var item : dorfList) {
            if (item.getHasBurg() == true) {
                burgCount++;
            }
        }
        if (burgCount == burgAmount) {
            return true;
        }

        return false;
    }

    public void checkNoBurg(ArrayList<Integer> centers) {
        Integer[] array = centers.toArray(new Integer[0]);
        int burgCount = 0;
        for (var item : dorfList) {
            if (item.getHasBurg() == true) {
                burgCount++;
            }
        }
        if (burgCount == burgAmount) {
            for (int i = 0; i < array.length; i++) {
                dorfList.get(i).setDorfColor(Color.CYAN);
            }
        }
    }

    public void showAnswer(){
        checkNoBurg(selectDoerferMitBurg(dorfAmount, calculateDistances(dorfAmount), burgAmount));
    }

    private void checkIfValidBurgPosition(double x, double y, Burg moveBurg) {

        checkAllDoerferForBurg();

        for (var item : dorfList) {
            if (item.getHasBurg() == true || item.getHasThron() == true) {
                continue;
            }

            if ((Math.hypot(x / getWidth() - item.getPosition().getxCoord(), y / getHeight() - item.getPosition().getyCoord())) < 0.1) {
                item.setHasBurg(true);
                moveBurg.setDorfPosition(item.getPosition());
                moveBurg.setIsBuilt(true);

                return;
            }
        }

        moveBurg.setDorfPosition(moveBurg.getLastValidPosition());
        if (moveBurg.getDorfPosition().equals(burgStandardPosition)) {
            moveBurg.setIsBuilt(false);
        }
        checkAllDoerferForBurg();

        return;
    }

    private void checkIfValidThronPosition(double x, double y) {

        for (var dorf :
                dorfList) {
            dorf.setHasThron(false);
        }
        checkAllDoerferForBurg();

        for (var item : dorfList) {
            if (item.getHasBurg() == false) {
                continue;
            }

            if ((Math.hypot(x - item.getPosition().getxCoord(), y - item.getPosition().getyCoord())) < 0.06) {
                item.setHasThron(true);
                thron.setDorfPosition(item.getPosition());

                return;
            }
        }
        return;
    }


    private void checkAllDoerferForBurg() {
        // set all doerfer to no burg
        for (var dorf :
                dorfList) {
            dorf.setHasBurg(false);
        }

        for (var burg :
                burgList) {
            for (var dorf :
                    dorfList) {
                if (dorf.getPosition().equals(burg.getDorfPosition())) {
                    dorf.setHasBurg(true);
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());

        if (!GameState.IsGameRunning || GameState.IsGamePaused)
            return;

        RemoveStatusDisplay();

        if (dragBurg == null)
            return;

        double mouseX = e.getX();
        double mouseY = e.getY();

        checkIfValidBurgPosition(mouseX, mouseY, dragBurg);

        dragBurg = null;

        checkNoBurg(selectDoerferMitBurg(dorfAmount, calculateDistances(dorfAmount), burgAmount));


        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void EvaluateAndFinishGame() {
    }
}