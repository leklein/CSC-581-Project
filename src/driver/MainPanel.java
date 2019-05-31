package driver;

import resolution.ResolutionFactory;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class MainPanel extends JPanel
{
    public static String panelId = "MainPanel";

    private Scorecard scorecard;

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel mainPanel;
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
    private JLabel guessLabel;
    private JComboBox<String> guessPersonComboBox;
    private JComboBox<String> guessWeaponComboBox;
    private JComboBox<String> guessRoomComboBox;
    private JButton guessButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public MainPanel(List<String> people, List<String> weapons, List<String> rooms) {
        initComponents();

        scorecard = new Scorecard(people, weapons, rooms);
        peopleTable.setModel(scorecard.people);
        weaponsTable.setModel(scorecard.weapons);
        roomsTable.setModel(scorecard.rooms);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mainPanel = new JPanel();
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
        guessLabel = new JLabel();
        guessPersonComboBox = new JComboBox<>();
        guessWeaponComboBox = new JComboBox<>();
        guessRoomComboBox = new JComboBox<>();
        guessButton = new JButton();

        //======== mainPanel ========
        {
            mainPanel.setBorder(null);
            mainPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)mainPanel.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)mainPanel.getLayout()).rowHeights = new int[] {0, 187, 0, 0};
            ((GridBagLayout)mainPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)mainPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

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
            mainPanel.add(handPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 10, 0), 0, 0));

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
            mainPanel.add(knowledgeCardPanel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 10, 0), 0, 0));

            //======== inputPanel ========
            {
                inputPanel.setBorder(new EtchedBorder());
                inputPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)inputPanel.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)inputPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
                ((GridBagLayout)inputPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)inputPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- guessLabel ----
                guessLabel.setText("Guess");
                inputPanel.add(guessLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- guessPersonComboBox ----
                guessPersonComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "TestPerson"
                }));
                inputPanel.add(guessPersonComboBox, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- guessWeaponComboBox ----
                guessWeaponComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "TestWeapon"
                }));
                inputPanel.add(guessWeaponComboBox, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- guessRoomComboBox ----
                guessRoomComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "TestRoom"
                }));
                inputPanel.add(guessRoomComboBox, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- guessButton ----
                guessButton.setText("Make Guess");
                guessButton.addActionListener(e -> guessButtonActionPerformed(e));
                inputPanel.add(guessButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            mainPanel.add(inputPanel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void guessButtonActionPerformed(ActionEvent e) {
        System.out.println("Guess button pressed with values: ");
        System.out.println(guessPersonComboBox.getSelectedItem());
        System.out.println(guessWeaponComboBox.getSelectedItem());
        System.out.println(guessRoomComboBox.getSelectedItem());

        //TODO call into resolution factory with these three values
    }

    private void refreshUi()
    {
        peopleTable.updateUI();
        weaponsTable.updateUI();
        roomsTable.updateUI();
    }

    public void setHand(List<String> cards)
    {
        //TODO set handTable.model to initial cards
    }

    public void markRow(String rowName, ResolutionFactory.Info rowValue)
    {
        scorecard.updateRow(rowName, rowValue);
        refreshUi();
    }
}
