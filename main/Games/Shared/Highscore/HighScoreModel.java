package team06.main.Games.Shared.Highscore;

import team06.main.Games.Shared.GameState;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class HighScoreModel implements TableModel {
    public static HighScoreEntryObject[] entries;

    private void ReadHighScores(){
        //Korrekturhilfe Einlesen
        try (FileInputStream fis = new FileInputStream("src/resources/"+ GameState.CurrentGame +".ser");
             ObjectInputStream oi = new ObjectInputStream(fis))   {

            // write object to file
            entries = (HighScoreEntryObject[]) oi.readObject();

            if(entries == null)
                return;

            Arrays.sort(entries, new SortByScore());
            if(entries.length >= 3)
                entries = Arrays.copyOfRange(entries, 0, 3);
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    @Override
    public int getRowCount() {
        return 3;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch( columnIndex ){
            case 0: return "Rank";
            case 1: return "Name";
            case 2: return "Score";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch( columnIndex ) {
            case 0:
            case 2:
                return Integer.class;
            case 1:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(entries == null)
            ReadHighScores();
        if(entries == null || entries.length -1 < rowIndex)
            return "/";

        var currentValue = entries[rowIndex];
        if(currentValue == null)
            return "/";

        switch( columnIndex ) {
            case 0:
                return rowIndex+1;
            case 2:
                return currentValue.Score;
            case 1:
                return currentValue.Name;
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}

