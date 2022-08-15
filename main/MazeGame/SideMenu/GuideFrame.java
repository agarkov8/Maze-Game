package team06.main.MazeGame.SideMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class GuideFrame extends JFrame {

    /**
     * Set the basic properties of the Guide Frame.
     * - set fixed size and not resizable
     * - let the window show in the middle
     * - use GridBag to position the elements in the Guide Frame
     */
    public GuideFrame(){
        super();

        setSize(550, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        var constraints = new GridBagConstraints();

        constraints.gridwidth = GridBagConstraints.REMAINDER;

        GuideText(constraints);
        CloseGuide(constraints);

        setVisible(true);
    }

    /**
     * put all the properties for the Guide Text in "GuideText Method"
     * - use JTextArea to display the Text and set the rows and columns
     * - setLineWrap true, to avoid the Text go over the edge
     * - setWrapStyleWord ture, to avoid the Text stays on the edge before a new line
     * - setEditable false, the Text shouldn't be editable
     * */

    private void GuideText (GridBagConstraints c) {
        JTextArea playguide = new JTextArea(15, 38);
        playguide.setLineWrap(true); //Text länger als der Bereich dann neue Zeile
        playguide.setWrapStyleWord(true); //vor dem Rand neue Zeile
        playguide.setEditable(false);
        playguide.setText("                              "
                + "Welcome to Labyrinth from Team06\n\n"
                + " - By default, all the controls are mapped on our keyboard to:\n"
                + "    W (Up), A (Left), S (Down), and D (Right).\n\n"
                + " - To move the Play Character to the Starting Point:\n"
                + "    click at the \"Start Point\" button.\n\n"
                + " - To move the Play Character to the Previous Step:\n"
                + "    click at the \"Previous Point\" button.\n\n"
                + " - To switch between four different Playfields:\n"
                + "    click at these four buttons \"Game 1\", \"Game 2\", \"Game 3\", \"Game 4\".\n\n"
                + "                                                Good Luck!!");
        add(playguide, c);

    }

    /**
     * create a button to let users close the Guide Frame-Window
     * - use Map Attribute to map the font to the Text
     * */

    private void CloseGuide (GridBagConstraints c) {
        c.insets = new Insets(30,0,0,0);
        JButton btnClose = new JButton("Close");

        add(btnClose, c);
        btnClose.addMouseListener(new MouseAdapter()
        {
            Font originalFont = null;
            public void mouseEntered(MouseEvent evt)
            {
                originalFont = btnClose.getFont();
                Map attributes = originalFont.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                btnClose.setFont(originalFont.deriveFont(attributes));

            }
            public void mouseExited(MouseEvent evt)
            {
                btnClose.setFont(originalFont);
            }
            public void mouseClicked(MouseEvent evt)
            {
                GuideFrame.super.dispose();
            }
        });

    }
}