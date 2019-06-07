package driver;

import resolution.ResolutionFactory;
import resolution.Rule;
import resolution.RuleParser;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDriver
{
    private static int playerCount = 4;

    private static GameDriverPanel gameDriverPanel;

    private static List<String> people;
    private static List<String> weapons;
    private static List<String> rooms;

    private static String murderPerson;
    private static String murderWeapon;
    private static String murderRoom;

    private static List<Player> players;

    public static void main(String[] args)
    {
        setup();

        List<Rule> facts = RuleParser.generateRules("files/facts.txt");

        createInitialGameSetup(facts);
        createUi();
    }

    private static void setup()
    {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Error setting the Look and Feel.");
        }
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
            String value = RuleParser.factToUserFriendlyString(fact.predicates.get(0).symbols.get(0).name);

            if (type.equals("Person")) people.add(value);
            else if (type.equals("Weapon")) weapons.add(value);
            else if (type.equals("Room")) rooms.add(value);
        }

        players = new ArrayList<>();
        for (int player = 0; player < playerCount; player++)
        {
            players.add(new Player("_" + (player + 1)));
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

        System.out.println("Answer: " + murderPerson + ", " + murderWeapon + ", " + murderRoom);

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

        System.out.println("\nPlayer 2 Cards: ");
        for (String card : players.get(1).getCards()) System.out.println(card);

        System.out.println("\nPlayer 3 Cards: ");
        for (String card : players.get(2).getCards()) System.out.println(card);

        System.out.println("\nPlayer 4 Cards: ");
        for (String card : players.get(3).getCards()) System.out.println(card);

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
