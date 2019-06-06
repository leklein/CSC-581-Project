package driver;

import resolution.ResolutionFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class GameDriverPanel extends JPanel
{
    public static String panelId = "MainPanel";

    private ResolutionFactory resolutionFactory;
    private Scorecard scorecard;

    public GameDriverPanel(List<String> people, List<String> weapons, List<String> rooms)
    {
        initComponents();

        scorecard = new Scorecard(people, weapons, rooms);
        peopleTable.setModel(scorecard.people);
        weaponsTable.setModel(scorecard.weapons);
        roomsTable.setModel(scorecard.rooms);

        for (String person : people) guessPersonComboBox.addItem(person);
        for (String weapon: weapons) guessWeaponComboBox.addItem(weapon);
        for (String room: rooms) guessRoomComboBox.addItem(room);

        resolutionFactory = new ResolutionFactory();
    }

    private void showButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void nextPlayerButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        handPanel = new JPanel();
        handLabel = new JLabel();
        handTable = new JTable();
        knowledgeCardPanel = new JPanel();
        peopleLabel = new JLabel();
        peopleTable = new JTable();
        weaponsLabel = new JLabel();
        weaponsTable = new JTable();
        roomsLabel = new JLabel();
        roomsTable = new JTable();
        inputPanel = new JPanel();
        guessPanel = new JPanel();
        guessPersonComboBox = new JComboBox();
        label4 = new JLabel();
        guessWeaponComboBox = new JComboBox();
        guessRoomComboBox = new JComboBox();
        guessButton = new JButton();
        showPanel = new JPanel();
        label3 = new JLabel();
        guessPersonComboBox2 = new JComboBox();
        label1 = new JLabel();
        guessWeaponComboBox2 = new JComboBox();
        label2 = new JLabel();
        guessRoomComboBox2 = new JComboBox();
        showButton = new JButton();
        separator1 = new JSeparator();
        button1 = new JButton();

        //======== this ========
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

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
            handPanel.add(handLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
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
            ((GridBagLayout)knowledgeCardPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)knowledgeCardPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
            ((GridBagLayout)knowledgeCardPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- peopleLabel ----
            peopleLabel.setText("People");
            knowledgeCardPanel.add(peopleLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 5, 5), 0, 0));
            knowledgeCardPanel.add(peopleTable, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- weaponsLabel ----
            weaponsLabel.setText("Weapons");
            knowledgeCardPanel.add(weaponsLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 5, 5), 0, 0));
            knowledgeCardPanel.add(weaponsTable, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- roomsLabel ----
            roomsLabel.setText("Rooms");
            knowledgeCardPanel.add(roomsLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 5), 0, 0));
            knowledgeCardPanel.add(roomsTable, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(knowledgeCardPanel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== inputPanel ========
        {
            inputPanel.setBorder(new EtchedBorder());
            inputPanel.setLayout(new CardLayout());

            //======== guessPanel ========
            {
                guessPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)guessPanel.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)guessPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
                ((GridBagLayout)guessPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)guessPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                guessPanel.add(guessPersonComboBox, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label4 ----
                label4.setText("text");
                guessPanel.add(label4, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                guessPanel.add(guessWeaponComboBox, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                guessPanel.add(guessRoomComboBox, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- guessButton ----
                guessButton.setText("Go");
                guessButton.addActionListener(e -> guessButtonActionPerformed(e));
                guessPanel.add(guessButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            inputPanel.add(guessPanel, "card3");

            //======== showPanel ========
            {
                showPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)showPanel.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)showPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                ((GridBagLayout)showPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)showPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label3 ----
                label3.setText("Shown");
                showPanel.add(label3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
                showPanel.add(guessPersonComboBox2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label1 ----
                label1.setText("showed");
                showPanel.add(label1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));
                showPanel.add(guessWeaponComboBox2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- label2 ----
                label2.setText("the card");
                showPanel.add(label2, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));
                showPanel.add(guessRoomComboBox2, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- showButton ----
                showButton.setText("Go");
                showButton.addActionListener(e -> {
			guessButtonActionPerformed(e);
			showButtonActionPerformed(e);
		});
                showPanel.add(showButton, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 5, 0), 0, 0));
                showPanel.add(separator1, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- button1 ----
                button1.setText("Next Player's Turn");
                button1.addActionListener(e -> nextPlayerButtonActionPerformed(e));
                showPanel.add(button1, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            inputPanel.add(showPanel, "card2");
        }
        add(inputPanel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void refreshUi()
    {
        peopleTable.updateUI();
        weaponsTable.updateUI();
        roomsTable.updateUI();
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
        System.out.println("Guess button pressed with values: ");
        System.out.println(guessPersonComboBox.getSelectedItem());
        System.out.println(guessWeaponComboBox.getSelectedItem());
        System.out.println(guessRoomComboBox.getSelectedItem());

        //TODO display panel asking user for whether they were shown a card
        //TODO
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel handPanel;
    private JLabel handLabel;
    private JTable handTable;
    private JPanel knowledgeCardPanel;
    private JLabel peopleLabel;
    private JTable peopleTable;
    private JLabel weaponsLabel;
    private JTable weaponsTable;
    private JLabel roomsLabel;
    private JTable roomsTable;
    private JPanel inputPanel;
    private JPanel guessPanel;
    private JComboBox guessPersonComboBox;
    private JLabel label4;
    private JComboBox guessWeaponComboBox;
    private JComboBox guessRoomComboBox;
    private JButton guessButton;
    private JPanel showPanel;
    private JLabel label3;
    private JComboBox guessPersonComboBox2;
    private JLabel label1;
    private JComboBox guessWeaponComboBox2;
    private JLabel label2;
    private JComboBox guessRoomComboBox2;
    private JButton showButton;
    private JSeparator separator1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
