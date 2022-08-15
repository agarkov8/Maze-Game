package team06.main.Games.Shared;

import team06.main.Games.Shared.Highscore.HighScoreModel;
import team06.main.Games.Shared.Highscore.LineWrapCellRenderer;
import team06.main.MazeGame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Map;

import static javax.management.timer.Timer.ONE_SECOND;

public abstract class GameSideMenuBase extends JPanel {
    public JButton[] additionalGameButtons = new JButton[7];



    JComboBox difficultyLevel = new JComboBox<>(GameState.Difficulty.values());

    JLabel points = new JLabel("Points: ");
    JLabel time = new JLabel("Time: ");
    JLabel level = new JLabel("Level: ");
    JLabel targetValue = new JLabel("Target value: ");
    protected Timer timer;
    long stopWatchStart;
    long pausedTime;

    public GameSideMenuBase() {
        super(new GridBagLayout());
        setFocusable(false);
        setBackground(Color.lightGray);

        var constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        CreateMenuButtons(constraints);

        StartTimeHandling(false);
    }

    public void StartTimeHandling(boolean start) {
        var timeOfStart = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        if(timer != null)
            timer.stop();
        timer = new Timer((int)ONE_SECOND, evt -> {
            level.setText("Level: " + GameState.Level);
            points.setText("Points: " + GameState.Score);
            targetValue.setText("Target value: " + GameState.TargetValue);
            var timeLabel = "Time: ";
            long timeDisplay = 0;
            var timeSinceStart = System.currentTimeMillis() - timeOfStart - pausedTime;
            switch(GameState.Difficulty.valueOf(difficultyLevel.getSelectedItem().toString())){
                case Training -> timeDisplay = timeSinceStart;
                case Medium -> timeDisplay = GetMediumTimeLimit() - timeSinceStart;
                case Hard -> timeDisplay = GetHardTimeLimit() - timeSinceStart;
            }
            if(timeDisplay <= 0 && GameState.GameDifficulty != GameState.Difficulty.Training)
                EndGame();
            timeLabel += dateFormat.format(timeDisplay);

            time.setText(timeLabel);
            repaint();
        });
        timer.setInitialDelay(0);
        if(start)
            timer.start();
    }

    private void CreateMenuButtons(GridBagConstraints constraints) {
        constraints.insets = new Insets(15, 0, 0, 0);

        String btnNames[] =
                {"Play Guide", "Start Game", "Pause Game", "End Game", "Back to Maze", "Reset High Scores", "Send Result"};

        for (int i = 0; i < additionalGameButtons.length; i++) {
            JButton btn = new JButton("" + btnNames[i]);
            additionalGameButtons[i] = btn;
            btn.setFocusable(false);

            btn.setOpaque(false);
            add(btn, constraints);
            btn.addMouseListener(new MouseAdapter() {
                Font originalFont = null;

                public void mouseEntered(MouseEvent evt) {
                    originalFont = btn.getFont();
                    Map attributes = originalFont.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    btn.setFont(originalFont.deriveFont(attributes));
                }

                public void mouseExited(MouseEvent evt) {
                    btn.setFont(originalFont);
                }
            });
        }
        SetButtonAvailability();


//      Play Guide
        additionalGameButtons[0].addActionListener(event -> {
            if(!GameState.IsGamePaused)
                PauseGameStart();

            GameState.IsGuideOpen = true;
            SetButtonAvailability();
            ShowGuide();
        });

//      Start Game
        additionalGameButtons[1].addActionListener(event -> {
            GameState.IsGameRunning = true;
            GameState.IsGamePaused = false;
            pausedTime = 0;

            GameState.GameDifficulty = GameState.Difficulty.valueOf(difficultyLevel.getSelectedItem().toString());
            SetButtonAvailability();
            StartGame(GameState.GameDifficulty);
            StartTimeHandling(true);
        });

//      Pause Game
        additionalGameButtons[2].addActionListener(event -> {
            PauseGameStart();
        });

//      End Game
        additionalGameButtons[3].addActionListener(event -> {
            EndGame();
            EndGameStart();
        });

//      Back to Maze
        additionalGameButtons[4].addActionListener(event -> {
            EndGame();
            GameState.Reset();
            SetButtonAvailability();
            MainFrame.switchCard("GameStart");
        });

//      Reset HighScore
        additionalGameButtons[5].addActionListener(event -> {
            //Korrekturhilfe Einlesen
            File file = new File("src/resources/"+ GameState.CurrentGame +".ser");
            if(file != null){
                file.delete();
                HighScoreModel.entries = null;
            }
        });

//      Send Result
        additionalGameButtons[6].addActionListener(event -> {
            SubmitResult();
        });

        difficultyLevel.setFocusable(false);
        add(difficultyLevel,constraints);
        add(points,constraints);
        add(time,constraints);
        add(level,constraints);
        add(targetValue, constraints);

        JTable table = new JTable( new HighScoreModel() );
        table.setFocusable(false);
        table.setPreferredScrollableViewportSize(new Dimension(180, 90));
        table.setDefaultRenderer(String.class, new LineWrapCellRenderer());
        add(new JScrollPane(table), constraints);
    }

    public void EndGameStart() {
        GameState.IsGameRunning = false;
        GameState.IsGamePaused = false;
        SetButtonAvailability();
        timer.stop();
    }

    protected void PauseGameStart() {
        GameState.IsGamePaused = !GameState.IsGamePaused;
        if(GameState.IsGameRunning){
            if(GameState.IsGamePaused){
                stopWatchStart = System.currentTimeMillis();
                timer.stop();
            }
            else{
                pausedTime = System.currentTimeMillis() - stopWatchStart;
                stopWatchStart = 0;
                timer.start();
            }
        }

        SetButtonAvailability();
        PauseGame();
    }

    private void SetButtonAvailability(){
        var startEnabled = !GameState.IsGameRunning || GameState.IsGamePaused;
        additionalGameButtons[1].setEnabled(startEnabled);

        var pauseEnabled = GameState.IsGameRunning && !GameState.IsGuideOpen;
        additionalGameButtons[2].setEnabled(pauseEnabled);

        var endEnabled = GameState.IsGameRunning;
        additionalGameButtons[3].setEnabled(endEnabled);

        var submitEnabled = GameState.IsGameRunning;
        additionalGameButtons[6].setEnabled(submitEnabled);


        var returnToMazeEnabled = !GameState.IsGameRunning || GameState.IsGameLost;
        additionalGameButtons[4].setEnabled(returnToMazeEnabled);

        var difficultyEnabled = !GameState.IsGameRunning || GameState.IsGamePaused;
        difficultyLevel.setEnabled(difficultyEnabled);

        if(!difficultyEnabled)
            difficultyLevel.setSelectedItem(GameState.GameDifficulty);
    }

    protected abstract void ShowGuide();
    protected abstract void StartGame(GameState.Difficulty difficulty);
    protected abstract void PauseGame();
    protected abstract void EndGame();
    protected abstract void SubmitResult();
    protected abstract int GetMediumTimeLimit();
    protected abstract int GetHardTimeLimit();
}
