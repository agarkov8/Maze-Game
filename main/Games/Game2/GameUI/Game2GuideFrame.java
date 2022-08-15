package team06.main.Games.Game2.GameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class Game2GuideFrame extends JFrame {
    public Game2GuideFrame(){
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

    private void GuideText (GridBagConstraints c) {
        JTextArea playguide = new JTextArea(15, 38);
        playguide.setLineWrap(true); //Text länger als der Bereich dann neue Zeile
        playguide.setWrapStyleWord(true); //vor dem Rand neue Zeile
        playguide.setEditable(false);
        playguide.setText("                              "
                + "Welcome to Color Fish Game from Team06\n\n"
                + " - Using the drag-and-drop function you can move the fishes\n"
                + "   from the table to the tanks\n\n"
                + "   Using the drop-down menu please choose the desired difficulty level:\n"
                + "   - Training/Medium/Hard\n\n"
                + " - While you play there is a timer that count the seconds\n"
                + "   - For Training Level -> Timer starts from 0 and counts\n"
                + "   - For Medium -> Timer starts to count down from 90 seconds\n"
                + "   - For Hard -> Timer starts to count down from 120 seconds\n\n"
                + " - For every successful level, the player gets points\n"
                + "   - For Training Level -> 4 Points\n"
                + "   - For Medium -> 12 Points\n"
                + "   - For Hard -> 24 Points\n"
                + "                                                Good Luck!!");
        add(playguide, c);

    }

    private void CloseGuide (GridBagConstraints c) {
        c.insets = new Insets(30,0,0,0);
        JButton btnClose = new JButton("Close");

        add(btnClose, c);
        btnClose.addMouseListener(new MouseAdapter()
        {
            Font originalFont = null;
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                originalFont = btnClose.getFont();
                Map attributes = originalFont.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                btnClose.setFont(originalFont.deriveFont(attributes));

            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                btnClose.setFont(originalFont);
            }
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                Game2GuideFrame.super.dispose();
            }
        });

    }
}
