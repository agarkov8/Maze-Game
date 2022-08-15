package team06.main.Games.Shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public abstract class AdditionalGuide extends JFrame {

    protected abstract String GetGuideText();
    GameSideMenuBase sideMenuBase;

    public AdditionalGuide(GameSideMenuBase sideMenu){
        super();

        setSize(750, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        sideMenuBase = sideMenu;
        var constraints = new GridBagConstraints();

        constraints.gridwidth = GridBagConstraints.REMAINDER;

        GuideText(constraints);
        CloseGuide(constraints);

        setVisible(true);
    }
    private void GuideText (GridBagConstraints c) {
        JTextArea playguide = new JTextArea(25, 38);
        playguide.setLineWrap(true); //Text länger als der Bereich dann neue Zeile
        playguide.setWrapStyleWord(true); //vor dem Rand neue Zeile
        playguide.setEditable(false);
        playguide.setText(GetGuideText());
        add(playguide, c);

    }

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
                dispose();
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        GameState.IsGuideOpen = false;

        sideMenuBase.PauseGameStart();
    }
}
