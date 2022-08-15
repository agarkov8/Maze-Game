package team06.main.Games.Game1.GameUI;

import javax.swing.*;
import java.awt.*;

public class StatusDisplay extends JTextPane {
    public boolean IsActive;

    public StatusDisplay(){
        super();
        setLayout(null);
        setPreferredSize(new Dimension(100, 50));
        setEditable(false);
    }
}
