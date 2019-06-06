package driver;

import resolution.ResolutionFactory;
import resolution.Rule;
import resolution.RuleParser;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDriver
{
    //TODO change to command line argument
    private static int playerCount = 4;

    private static GameDriverPanel gameDriverPanel;
    private static RuleParser ruleParser;

    private static List<String> people;
    private static List<String> weapons;
    private static List<String> rooms;

    private static String murderPerson;
    private static String murderWeapon;
    private static String murderRoom;

    private static List<Player> players;

    public static void main(String[] args)
    {
        ruleParser = new RuleParser();

        List<Rule> facts = ruleParser.generateRules("files/facts.txt");
        List<Rule> rules = ruleParser.generateRules("files/rules.txt");

        createInitialGameSetup(facts);
        createUi();
    }

    private static void createInitialGameSetup(List<Rule> facts)
    {
        Random randomGenerator = new Random();

        people = new ArrayList<>();
        weapons = new ArrayList<>();
        rooms = new ArrayList<>();

        for (Rule fact : facts)
        {
            String type = fact.predicates.get(0).name;
            String value = ruleParser.factToUserFriendlyString(fact.predicates.get(0).symbols.get(0).name);

            if (type.equals("Person")) people.add(value);
            else if (type.equals("Weapon")) weapons.add(value);
            else if (type.equals("Room")) rooms.add(value);
        }

        murderPerson = "Mr. Green";
        murderWeapon = "Knife";
        murderRoom = "Conservatory";

        players = new ArrayList<>();
        players.add(new Player("User"));
        for (int player = 1; player < playerCount; player++)
        {
            players.add(new Player("Player " + (player + 1)));
        }

        List<String> deck = new ArrayList<>();
        List<String> peopleSubdeck = new ArrayList<>();
        List<String> weaponsSubdeck = new ArrayList<>();
        List<String> roomsSubdeck = new ArrayList<>();

        peopleSubdeck.addAll(people);
        murderPerson = peopleSubdeck.get(randomGenerator.nextInt(peopleSubdeck.size()));
        peopleSubdeck.remove(murderPerson);

        weaponsSubdeck.addAll(weapons);
        murderWeapon = weaponsSubdeck.get(randomGenerator.nextInt(weaponsSubdeck.size()));
        weaponsSubdeck.remove(murderWeapon);

        roomsSubdeck.addAll(rooms);
        murderRoom = roomsSubdeck.get(randomGenerator.nextInt(roomsSubdeck.size()));
        roomsSubdeck.remove(murderRoom);

        deck.addAll(peopleSubdeck);
        deck.addAll(weaponsSubdeck);
        deck.addAll(roomsSubdeck);

        int player = 0;
        while (!deck.isEmpty())
        {
            String card = deck.remove(randomGenerator.nextInt(deck.size()));
            players.get(player).addCard(card);
            player = (player + 1) % playerCount;
        }
    }

    private static void createUi()
    {
        gameDriverPanel = new GameDriverPanel(players, people, weapons, rooms);

        List<String> startingHand = players.get(0).getCards();
        gameDriverPanel.setHand(startingHand);
        for (String card : startingHand)
        {
            gameDriverPanel.markRow(card, ResolutionFactory.Info.NO);
        }

        JFrame frame = new JFrame("Clue Solver");
        frame.add(gameDriverPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 775);
        frame.setVisible(true);
    }
}
