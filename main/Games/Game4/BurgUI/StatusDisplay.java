package team06.main.Games.Game4.BurgUI;

import javax.swing.*;
import java.awt.*;

public class StatusDisplay extends JTextPane {
    public boolean IsActive;

    public StatusDisplay(){
        super();
        setLayout(null);
        setBackground(Color.pink);
        setPreferredSize(new Dimension(100, 50));
        setEditable(false);
    }
}
