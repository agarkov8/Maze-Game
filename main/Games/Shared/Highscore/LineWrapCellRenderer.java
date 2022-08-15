package team06.main.Games.Shared.Highscore;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class LineWrapCellRenderer  extends JTextArea implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        this.setText((String)value);
        this.setWrapStyleWord(true);
        this.setLineWrap(true);
        int fontHeight = this.getFontMetrics(this.getFont()).getHeight();
        int textLength = this.getText().length();
        int lines = (int) Math.ceil(textLength / 9.0);//+1, cause we need at least 1 row.
        int height = fontHeight * lines;
        if(height <= 0)
            height = 1;
        table.setRowHeight(row, height);
        return this;
    }

}