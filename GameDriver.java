import javax.smartcardio.Card;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDriver
{
    //TODO change to command line argument
    private static int playerCount = 4;

    private static MainPanel mainPanel;
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
        createRuleParser();
        createInitialGameSetup();
        createUi();

        List<Rule> rules = ruleParser.generateRules("files/rules.txt");
        ruleParser.parse(rules);

        while (true) //true is temporary
        {
            //TODO game loop, remove break
            break;
        }
    }

    private static void createRuleParser()
    {
        ruleParser = new RuleParser();
    }

    private static void createInitialGameSetup()
    {
        createCards();
        createPlayers();
    }

    private static void createCards()
    {
        people = new ArrayList<>();
        people.add("Mr. Green");
        people.add("Mrs. White");
        people.add("Miss Scarlet");

        weapons = new ArrayList<>();
        weapons.add("Knife");
        weapons.add("Revolver");
        weapons.add("Rope");

        rooms = new ArrayList<>();
        rooms.add("Conservatory");
        rooms.add("Living Room");
        rooms.add("Kitchen");

        murderPerson = "Mr. Green";
        murderWeapon = "Knife";
        murderRoom = "Conservatory";
    }

    private static void createPlayers()
    {
        Random randomGenerator = new Random();

        players.add(new Player("User"));
        for (int player = 1; player < playerCount; player++)
        {
            players.add(new Player("Player " + player + 1));
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
        mainPanel = new MainPanel(people, weapons, rooms);

        List<String> startingHand = players.get(0).getCards();
        mainPanel.setHand(startingHand);
        for (String card : startingHand)
        {
            mainPanel.markRow(card, ResolutionFactory.Info.NO);
        }

        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
