package team06.main.Games.Shared.Highscore;

import team06.main.Games.Shared.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class HighScoreFrame extends JFrame {
    public JTextField input;
    public HighScoreEntryObject[] highScores;

    public HighScoreFrame(){
        super();
        setSize(550, 400);

        highScores = null;
        ReadHighScores();
        if(highScores != null && !CheckIfScoreIsHighScore()){
            dispose();
            return;
        }

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        var constraints = new GridBagConstraints();

        constraints.gridwidth = GridBagConstraints.REMAINDER;

        NameEnter(constraints);
        SubmitButton(constraints);

        setVisible(true);
    }

    private boolean CheckIfScoreIsHighScore(){
        for (var highScore: highScores) {
            if(highScore != null && GameState.Score > highScore.Score || highScores.length < 3)
                return true;
        }
        return false;
    }

    private void ReadHighScores(){
        //Korrekturhilfe Einlesen
        try (FileInputStream fis = new FileInputStream("src/resources/"+ GameState.CurrentGame +".ser");
             ObjectInputStream oi = new ObjectInputStream(fis))   {

            // write object to file
            highScores = (HighScoreEntryObject[]) oi.readObject();

            if(highScores == null)
                return;

            Arrays.sort(highScores, new SortByScore());
            if(highScores.length >= 3)
                highScores = Arrays.copyOfRange(highScores, 0, 3);
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    private void SubmitButton(GridBagConstraints c) {
        c.insets = new Insets(30,0,0,0);
        JButton btnSubmit = new JButton("Submit");

        add(btnSubmit, c);
        btnSubmit.addMouseListener(new MouseAdapter()
        {
            Font originalFont = null;
            public void mouseEntered(MouseEvent evt)
            {
                originalFont = btnSubmit.getFont();
                Map attributes = originalFont.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                btnSubmit.setFont(originalFont.deriveFont(attributes));

            }
            public void mouseExited(MouseEvent evt)
            {
                btnSubmit.setFont(originalFont);
            }
            public void mouseClicked(MouseEvent evt)
            {
                SaveToFile();
                HighScoreFrame.super.dispose();
            }
        });
    }

    private void NameEnter(GridBagConstraints constraints) {
        JTextArea playguide = new JTextArea(2, 38);
        playguide.setLineWrap(true); //Text länger als der Bereich dann neue Zeile
        playguide.setWrapStyleWord(true); //vor dem Rand neue Zeile
        playguide.setEditable(false);
        playguide.setText("Please enter your name for the highscore");
        add(playguide, constraints);

        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        input = new JTextField(20);
        inputpanel.add(input);
        add(inputpanel, constraints);
    }

    private void SaveToFile(){
        ReadHighScores();
        //Korrekturhilfe Einlesen
        try (FileOutputStream fos = new FileOutputStream("src/resources/"+ GameState.CurrentGame +".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            HighScoreEntryObject entry = new HighScoreEntryObject(GameState.Score, input.getText());

            if(highScores != null){
                highScores = concatenate(highScores, new HighScoreEntryObject[]{entry});

                Arrays.sort(highScores, new SortByScore());
                if(highScores.length >= 3)
                    highScores = Arrays.copyOfRange(highScores, 0, 3);
            }
            else
                highScores = new HighScoreEntryObject[]{ entry };

            // write object to file
            oos.writeObject(highScores);
            oos.close();
            HighScoreModel.entries = null;

        } catch (IOException e) {        }
    }
    public <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }
}
