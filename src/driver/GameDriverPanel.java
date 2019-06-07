package driver;

import resolution.ResolutionFactory;
import resolution.Rule;
import resolution.RuleParser;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class GameDriverPanel extends JPanel
{
    public static String panelId = "MainPanel";

    private List<Player> players;
    private Player user;
    private int currentPlayer = 0;
    private ResolutionFactory resolutionFactory;
    private Scorecard scorecard;

    private String personGuess;
    private String weaponGuess;
    private String roomGuess;

    public GameDriverPanel(List<Player> players, List<String> people, List<String> weapons, List<String> rooms)
    {
        initComponents();

        this.players = players;
        this.user = players.get(0);
        this.resolutionFactory = new ResolutionFactory();
        this.scorecard = new Scorecard(people, weapons, rooms);

        for (String card : user.getCards())
        {
            resolutionFactory.add_and_resolve(RuleParser.userFriendlyStringToFact(card), user.getName());
        }

        peopleTable.setModel(scorecard.people);
        weaponsTable.setModel(scorecard.weapons);
        roomsTable.setModel(scorecard.rooms);

        for (String person : people) {
            guessPersonComboBox.addItem(person);
            showComboBox3.addItem(person);
        }
        for (String weapon : weapons) {
            guessWeaponComboBox.addItem(weapon);
            showComboBox3.addItem(weapon);
        }
        for (String room : rooms) {
            guessRoomComboBox.addItem(room);
            showComboBox3.addItem(room);
        }
        for (Player player : players) {
            showComboBox1.addItem("Player" + player.getName());
            showComboBox2.addItem("Player" + player.getName());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        handPanel = new JPanel();
        handLabel = new JLabel();
        handTable = new JTable();
        knowledgeCardPanel = new JPanel();
        scorecardLabel = new JLabel();
        peopleLabel = new JLabel();
        peopleTable = new JTable();
        weaponsLabel = new JLabel();
        weaponsTable = new JTable();
        roomsLabel = new JLabel();
        roomsTable = new JTable();
        separator = new JSeparator();
        inputLabel = new JLabel();
        inputPanel = new JPanel();
        guessPanel = new JPanel();
        guessPersonComboBox = new JComboBox();
        guessLabel1 = new JLabel();
        guessWeaponComboBox = new JComboBox();
        guessLabel2 = new JLabel();
        guessRoomComboBox = new JComboBox();
        guessButton = new JButton();
        showPanel = new JPanel();
        showComboBox1 = new JComboBox();
        showLabel2 = new JLabel();
        showComboBox2 = new JComboBox();
        showLabel3 = new JLabel();
        showComboBox3 = new JComboBox<>();
        showButton = new JButton();
        nextPlayerButton = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0E-4};

        //======== handPanel ========
        {
            handPanel.setBorder(new EtchedBorder());
            handPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)handPanel.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)handPanel.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)handPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)handPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //---- handLabel ----
            handLabel.setText("Your Hand");
            handLabel.setFont(handLabel.getFont().deriveFont(handLabel.getFont().getSize() + 2f));
            handPanel.add(handLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- handTable ----
            handTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", null},
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null},
                },
                new String[] {
                    null, null
                }
            ));
            handPanel.add(handTable, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(handPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== knowledgeCardPanel ========
        {
            knowledgeCardPanel.setBorder(new EtchedBorder());
            knowledgeCardPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)knowledgeCardPanel.getLayout()).columnWidths = new int[] {65, 0, 0};
            ((GridBagLayout)knowledgeCardPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
            ((GridBagLayout)knowledgeCardPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
            ((GridBagLayout)knowledgeCardPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- scorecardLabel ----
            scorecardLabel.setText("Scorecard");
            scorecardLabel.setFont(scorecardLabel.getFont().deriveFont(scorecardLabel.getFont().getSize() + 2f));
            knowledgeCardPanel.add(scorecardLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- peopleLabel ----
            peopleLabel.setText("People");
            knowledgeCardPanel.add(peopleLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 5, 5), 0, 0));
            knowledgeCardPanel.add(peopleTable, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- weaponsLabel ----
            weaponsLabel.setText("Weapons");
            knowledgeCardPanel.add(weaponsLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 5, 5), 0, 0));
            knowledgeCardPanel.add(weaponsTable, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- roomsLabel ----
            roomsLabel.setText("Rooms");
            knowledgeCardPanel.add(roomsLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));
            knowledgeCardPanel.add(roomsTable, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(knowledgeCardPanel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        add(separator, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- inputLabel ----
        inputLabel.setText("Input - Player_1's Guess");
        inputLabel.setFont(inputLabel.getFont().deriveFont(inputLabel.getFont().getSize() + 2f));
        add(inputLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== inputPanel ========
        {
            inputPanel.setBorder(new EtchedBorder());
            inputPanel.setLayout(new CardLayout());

            //======== guessPanel ========
            {
                guessPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)guessPanel.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)guessPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)guessPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)guessPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                guessPanel.add(guessPersonComboBox, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- guessLabel1 ----
                guessLabel1.setText("with the");
                guessPanel.add(guessLabel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));
                guessPanel.add(guessWeaponComboBox, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- guessLabel2 ----
                guessLabel2.setText("in the");
                guessPanel.add(guessLabel2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));
                guessPanel.add(guessRoomComboBox, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- guessButton ----
                guessButton.setText("Go");
                guessButton.addActionListener(e -> guessButtonActionPerformed(e));
                guessPanel.add(guessButton, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            inputPanel.add(guessPanel, "card3");

            //======== showPanel ========
            {
                showPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)showPanel.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)showPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)showPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)showPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                showPanel.add(showComboBox1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- showLabel2 ----
                showLabel2.setText("showed");
                showPanel.add(showLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- showComboBox2 ----
                showComboBox2.setEnabled(false);
                showPanel.add(showComboBox2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- showLabel3 ----
                showLabel3.setText("the card");
                showPanel.add(showLabel3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- showComboBox3 ----
                showComboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
                    "< UNKNOWN >"
                }));
                showPanel.add(showComboBox3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- showButton ----
                showButton.setText("Go");
                showButton.addActionListener(e -> showButtonActionPerformed(e));
                showPanel.add(showButton, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            inputPanel.add(showPanel, "card2");
        }
        add(inputPanel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- nextPlayerButton ----
        nextPlayerButton.setText("Next Player's Turn");
        nextPlayerButton.setEnabled(false);
        nextPlayerButton.addActionListener(e -> nextPlayerButtonActionPerformed(e));
        add(nextPlayerButton, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void refreshUi()
    {
        peopleTable.updateUI();
        weaponsTable.updateUI();
        roomsTable.updateUI();

        guessPersonComboBox.setSelectedIndex(-1);
        guessRoomComboBox.setSelectedIndex(-1);
        guessWeaponComboBox.setSelectedIndex(-1);
        showComboBox1.setSelectedIndex(-1);
        showComboBox2.setSelectedIndex(currentPlayer);
        showComboBox3.setSelectedIndex(-1);
    }

    public void setHand(List<String> cards)
    {
        handTable.setModel(new HandTableModel(cards));
    }

    public void markRow(String rowName, ResolutionFactory.Info rowValue)
    {
        scorecard.updateRow(rowName, rowValue);
        refreshUi();
    }

    private void guessButtonActionPerformed(ActionEvent e) {
        personGuess = guessPersonComboBox.getSelectedItem().toString();
        weaponGuess = guessWeaponComboBox.getSelectedItem().toString();
        roomGuess = guessRoomComboBox.getSelectedItem().toString();

        List<String> guesses = new ArrayList<>();
        guesses.add(players.get(currentPlayer).getName());
        guesses.add(RuleParser.userFriendlyStringToFact(personGuess));
        guesses.add(RuleParser.userFriendlyStringToFact(weaponGuess));
        guesses.add(RuleParser.userFriendlyStringToFact(roomGuess));
        resolutionFactory.add_temp_and_resolve("Ask", guesses);

        ((CardLayout)inputPanel.getLayout()).next(inputPanel);
        nextPlayerButton.setEnabled(true);
        inputLabel.setText("Input - Player" + players.get(currentPlayer).getName() + "'s Shown Cards");
        refreshUi();
    }

    private void showButtonActionPerformed(ActionEvent e) {
        if (showComboBox2.getSelectedItem().equals("Player_1"))
        {
            resolutionFactory.add_and_resolve(
                    RuleParser.userFriendlyStringToFact(showComboBox3.getSelectedItem().toString()),
                    RuleParser.userFriendlyStringToFact(showComboBox1.getSelectedItem().toString()));

            scorecard.updateAllRows(resolutionFactory);
        }
        else
        {
            //showComboBox1.getSelectedItem().toString()  // the player who has the card
            //showComboBox2.getSelectedItem().toString()  // the player who asked for the card
            List<String> shown = new ArrayList<>();
            shown.add(RuleParser.userFriendlyStringToFact(showComboBox1.getSelectedItem().toString()));
            shown.add(RuleParser.userFriendlyStringToFact(showComboBox2.getSelectedItem().toString()));
            resolutionFactory.add_temp_and_resolve("Shown", shown);

            scorecard.updateAllRows(resolutionFactory);
        }

        showComboBox1.setSelectedIndex(-1);
        showComboBox3.setSelectedIndex(-1);
        refreshUi();
    }

    private void nextPlayerButtonActionPerformed(ActionEvent e) {
        personGuess = null;
        weaponGuess = null;
        roomGuess = null;

        currentPlayer = (currentPlayer + 1) % players.size();

        ((CardLayout)inputPanel.getLayout()).next(inputPanel);
        nextPlayerButton.setEnabled(false);
        inputLabel.setText("Input - Player" + players.get(currentPlayer).getName() + "'s Guess");
        resolutionFactory.remove_temporary();
        refreshUi();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel handPanel;
    private JLabel handLabel;
    private JTable handTable;
    private JPanel knowledgeCardPanel;
    private JLabel scorecardLabel;
    private JLabel peopleLabel;
    private JTable peopleTable;
    private JLabel weaponsLabel;
    private JTable weaponsTable;
    private JLabel roomsLabel;
    private JTable roomsTable;
    private JSeparator separator;
    private JLabel inputLabel;
    private JPanel inputPanel;
    private JPanel guessPanel;
    private JComboBox guessPersonComboBox;
    private JLabel guessLabel1;
    private JComboBox guessWeaponComboBox;
    private JLabel guessLabel2;
    private JComboBox guessRoomComboBox;
    private JButton guessButton;
    private JPanel showPanel;
    private JComboBox showComboBox1;
    private JLabel showLabel2;
    private JComboBox showComboBox2;
    private JLabel showLabel3;
    private JComboBox<String> showComboBox3;
    private JButton showButton;
    private JButton nextPlayerButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
