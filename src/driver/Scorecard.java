package driver;

import resolution.ResolutionFactory;
import resolution.RuleParser;

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

    public void updateAllRows(ResolutionFactory resolutionFactory)
    {
        for (int x = 0; x < people.getRowCount(); x++)
        {
            String person = people.getValueAt(x, 0).toString();
            String personFact = RuleParser.userFriendlyStringToFact(person);
            updateRow(person, resolutionFactory.getInfoForSymbol(personFact));
        }
        for (int x = 0; x < weapons.getRowCount(); x++)
        {
            String weapon = weapons.getValueAt(x, 0).toString();
            String weaponFact = RuleParser.userFriendlyStringToFact(weapon);
            updateRow(weapon, resolutionFactory.getInfoForSymbol(weaponFact));
        }
        for (int x = 0; x < rooms.getRowCount(); x++)
        {
            String room = rooms.getValueAt(x, 0).toString();
            String roomFact = RuleParser.userFriendlyStringToFact(room);
            updateRow(room, resolutionFactory.getInfoForSymbol(roomFact));
        }
    }

    public void updateRow(String rowKey, ResolutionFactory.Info newValue)
    {
        if (people.containsKey(rowKey)) people.put(rowKey, newValue);
        else if (weapons.containsKey(rowKey)) weapons.put(rowKey, newValue);
        else if (rooms.containsKey(rowKey)) rooms.put(rowKey, newValue);
    }
}
