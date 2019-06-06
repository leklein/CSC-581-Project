package driver;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class HandTableModel implements TableModel
{
    List<String> hand;

    public HandTableModel(List<String> hand)
    {
        this.hand = hand;
    }

    @Override
    public int getRowCount()
    {
        return hand.size();
    }

    @Override
    public int getColumnCount()
    {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return hand.get(rowIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        hand.set(rowIndex, (String)aValue);
    }

    @Override
    public void addTableModelListener(TableModelListener l)
    {

    }

    @Override
    public void removeTableModelListener(TableModelListener l)
    {

    }
}
