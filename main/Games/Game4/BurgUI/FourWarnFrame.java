package team06.main.Games.Game4.BurgUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class FourWarnFrame extends JFrame {

    public FourWarnFrame() {
        super();

        setSize(550, 200);
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
     */

    private void GuideText(GridBagConstraints c) {
        JTextArea playguide = new JTextArea(5, 38);
        playguide.setLineWrap(true); //Text länger als der Bereich dann neue Zeile
        playguide.setWrapStyleWord(true); //vor dem Rand neue Zeile
        playguide.setEditable(false);
        playguide.setText("                  "
                + "set a castle before checking the next nearst castle \n\n               "
                + "drag the castle to the village represented by black dots\n\n"
                + "                                                Good Luck!!");


        add(playguide, c);

    }

    /**
     * create a button to let users close the Guide Frame-Window
     * - use Map Attribute to map the font to the Text
     */

    private void CloseGuide(GridBagConstraints c) {
        c.insets = new Insets(30, 0, 0, 0);
        JButton btnClose = new JButton("Close");

        add(btnClose, c);
        btnClose.addMouseListener(new MouseAdapter() {
            Font originalFont = null;

            public void mouseEntered(MouseEvent evt) {
                originalFont = btnClose.getFont();
                Map attributes = originalFont.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                btnClose.setFont(originalFont.deriveFont(attributes));

            }

            public void mouseExited(MouseEvent evt) {
                btnClose.setFont(originalFont);
            }

            public void mouseClicked(MouseEvent evt) {
                FourWarnFrame.super.dispose();
            }
        });

    }
}