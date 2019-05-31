import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ScorecardTableModel implements TableModel
{
    private List<String> keys;
    private List<ResolutionFactory.Info> values;

    public ScorecardTableModel(List<String> rows)
    {
        keys = new ArrayList<>();
        values = new ArrayList<>();

        for (String row : rows)
        {
            keys.add(row);
            values.add(ResolutionFactory.Info.UNKNOWN);
        }
    }

    public boolean containsKey(String key)
    {
        return keys.contains(key);
    }

    public void put(String key, ResolutionFactory.Info value)
    {
        int index = keys.indexOf(key);
        if (index >= 0)
        {
            values.set(index, value);
        }
    }

    @Override
    public int getRowCount()
    {
        return keys.size();
    }

    @Override
    public int getColumnCount()
    {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if (columnIndex == 0) return String.class;
        else if (columnIndex == 1) return ResolutionFactory.Info.class;

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
        if (columnIndex == 0) return keys.get(rowIndex);
        else if (columnIndex == 1) return values.get(rowIndex);

        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        if (columnIndex == 0) keys.set(rowIndex, (String)value);
        if (columnIndex == 1) values.set(rowIndex, (ResolutionFactory.Info) value);
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
