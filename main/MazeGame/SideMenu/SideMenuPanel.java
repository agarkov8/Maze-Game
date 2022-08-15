package team06.main.MazeGame.SideMenu;

import team06.main.MazeGame.MainFrame;
import team06.main.MazeGame.Maze.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class SideMenuPanel extends JPanel{

    /**
     * declare the Array for the further usage of JButtons
     * gameButtons for the Games i = 1, 2, 3, 4
     * playButtons for the Commands of setting the point of Play Characters and the window of Play Guide
     */
    public static JButton[] gameButtons  = new JButton[4];
    public static JButton[] playButtons  = new JButton[3];

    /**
     * Set the basic properties of the panel.
     */
    public SideMenuPanel() {

        super(new GridBagLayout());
        setFocusable(false);
        setBackground(Color.darkGray);

        var constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        CreateMenuButtons(constraints);
        CreateLabel(constraints);
        CreateGameButtons(constraints);
    }

    /**
     * Create and decorate (hover effect) the MenuButtons
     * - use insets to reserve places between buttons
     * - use Map Attribute to map the font to the Text
     */

    private void CreateMenuButtons(GridBagConstraints constraints) {
        constraints.insets = new Insets(10,0,0,0);

        String btnNames[] = {"Play Guide","Start Point", "Previous Point"};

        for (int i = 0; i < playButtons.length; i++) {
            JButton btn = new JButton("" + btnNames[i]);
            playButtons[i] = btn;
            btn.setFocusable(false);

            btn.setOpaque(false);
            add(btn, constraints);
            btn.addMouseListener(new MouseAdapter()
            {
                Font originalFont = null;
                public void mouseEntered(MouseEvent evt)
                {
                    originalFont = btn.getFont();
                    Map attributes = originalFont.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    btn.setFont(originalFont.deriveFont(attributes));

                }
                public void mouseExited(MouseEvent evt)
                {
                    btn.setFont(originalFont);
                }
            });
        }

        DefaultButton();

        playButtons[0].addActionListener(event -> {
            new GuideFrame();
        });

        /**
         * Issue: if only repaint() the Player will only be repainted on behalf
         *        of resizing of Frame. Use getRootPane to reach the JPanel,
         *        where the Player is painted.
         */

        playButtons[1].addActionListener(event -> {
            MazePanel.getPlayer().startPoint();
            super.getRootPane().repaint();
        });

        playButtons[2].addActionListener(event -> {
            MazePanel.getPlayer().previousPoint();
            super.getRootPane().repaint();
        });

    }

    /**
     * create the "Schriftzug" using JLabel and html to set the font of the text
     * - centering the Text with the method in JLabel
     *
     * @param c: GridBagConstraints to set the buttons in order of the Layout-Manager
     */

    private void CreateLabel (GridBagConstraints c) {
        c.insets = new Insets(30,0,10,0);
        String text = "Entwicklermodus:" + "<br/>" +
                "Spiele direkt starten";
        JLabel label = new JLabel(
                "<html>" +
                "<div style='text-align: center;'><font color='white'>" +
                text + "</font></div></span></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label, c);

    }

    /**
     * Create and decorate (hover effect) the GameButtons
     * use insets to reserve places between buttons
     */
    private void CreateGameButtons(GridBagConstraints c) {
        c.insets = new Insets(0,0,40,0);

        String btnNames[] = {"Game 1","Game 2", "Game 3", "Game 4"};

        for (int i = 0; i < gameButtons.length; i++) {
            JButton btn = new JButton("" + btnNames[i]);
            gameButtons[i] = btn;
            btn.setFocusable(false);

            btn.setOpaque(false);
            add(btn, c);
            btn.addMouseListener(new MouseAdapter()
            {
                Font originalFont = null;
                public void mouseEntered(MouseEvent evt)
                {
                    originalFont = btn.getFont();
                    Map attributes = originalFont.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    btn.setFont(originalFont.deriveFont(attributes));
                }
                public void mouseExited(MouseEvent evt)
                {
                    btn.setFont(originalFont);
                }
            });
        }

        gameButtons[0].addActionListener(event -> {
            MainFrame.switchCard("GameOne");
        });

        gameButtons[1].addActionListener(event -> {
            MainFrame.switchCard("GameTwo");
        });

        gameButtons[2].addActionListener(event -> {
            MainFrame.switchCard("GameThree");
        });

        gameButtons[3].addActionListener(event -> {
            MainFrame.switchCard("GameFour");
        });
    }
    
    public static void DefaultButton(){
        playButtons[2].setEnabled(false);
    }
}