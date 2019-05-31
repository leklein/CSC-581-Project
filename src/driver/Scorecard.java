package driver;

import resolution.ResolutionFactory;

import java.util.List;

public class Scorecard
{
    public ScorecardTableModel people;
    public ScorecardTableModel weapons;
    public ScorecardTableModel rooms;

    public Scorecard(List<String> people, List<String> weapons, List<String> rooms)
    {
        this.people = new ScorecardTableModel(people);
        this.weapons = new ScorecardTableModel(weapons);
        this.rooms = new ScorecardTableModel(rooms);
    }

    public void updateRow(String rowKey, ResolutionFactory.Info newValue)
    {
        if (people.containsKey(rowKey)) people.put(rowKey, newValue);
        else if (weapons.containsKey(rowKey)) weapons.put(rowKey, newValue);
        else if (rooms.containsKey(rowKey)) rooms.put(rowKey, newValue);
    }
}
