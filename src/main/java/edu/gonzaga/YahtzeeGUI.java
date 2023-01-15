package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YahtzeeGUI
{
    private JFrame f, gameFrame;
    private JPanel menuPane;
    private String diceSides = "6";
    private String handCount = "5";
    private String[] diceAndHand = {diceSides, handCount};

    private JPanel[] upperJPanel = new JPanel[12];
    private JPanel[] lowerJPanel = new JPanel[7];


    public static void main(String[] args)
    {
        new YahtzeeGUI();
    }

    public YahtzeeGUI()
    {
        SwingUtilities.invokeLater(() ->
        {
            f = new JFrame("Yahtzee");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setPreferredSize(new Dimension(800, 500));
            f.add(new MenuPane());
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);

        }
        );
    }

    public class MenuPane extends JPanel
    {
        public MenuPane()
        {
            setLayout(new GridBagLayout());

            // Setting GBC for MenuPane
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            // Adds HTML Title
            add(new JLabel("<html><h1><strong><i>Yahtzee</i></strong></h1><hr></html>"), gbc);

            // Setting GBC for MenuPane
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            menuPane = new JPanel(new GridBagLayout());

            // Sets up the Start Button to play Yahtzee
            JButton startB = new JButton("Start");
            menuPane.add(startB, gbc);
            startB.addActionListener(start ->
                {
                    // Call a new ..... to create Yahtzee game
                    gameFrame = new JFrame("Yahtzee Game");
                    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gameFrame.setPreferredSize(new Dimension(800, 1000));
                    dropDownMenu(gameFrame);
                    cardLayout(gameFrame);
                    diceDisplay(gameFrame);
                    setBottomPane(gameFrame);
                    gameFrame.pack();
                    gameFrame.setLocationRelativeTo(null);
                    gameFrame.setVisible(true);
                }
            );

            // Sets Help Button that displays help text
            JButton helpB = new JButton("Help");
            menuPane.add(helpB, gbc);
            helpB.addActionListener(help ->
                    {
                        JOptionPane.showMessageDialog(null, "<html><h1><strong><i>How to play Yahtzee</i></strong></h1></html>");
                    }
            );

            // Sets Exit Button that closes the window
            JButton exitB = new JButton("Exit");
            menuPane.add(exitB, gbc);
            exitB.addActionListener(exit ->
                {
                    f.dispose();
                    // Uses System.exit(0) as well so that way the GUI ends instantly instead of waiting a few seconds for the process to end
                    System.exit(0);
                }
            );

            gbc.weighty = 1;
            add(menuPane, gbc);
        }
    }

    /**
     * cardLayout sets up the upper and lower scorecard sections labeling and buttons that display score
     * @param name Takes in the JFrame name to place the panels that the cards are placed in
     */
    private void cardLayout(JFrame name)
    {
        final JPanel upperSectionL;
        final JPanel lowerSectionL;

        String[] upperScoreString = {"1's", "2's", "3's", "4's", "5's", "6's", "7's", "8's", "9's", "10's", "11's", "12's"};
        String[] lowerScoreString = {"3K", "4K", "FH", "Sm. Str", "Lg. Str", "Yahtzee", "Chance"};

        int diceSides;
        try {
            diceSides = Integer.parseInt(diceAndHand[0]);
        } catch (NumberFormatException e) {
            diceSides = 6;
        }

        /** Upper Section Label */
        JPanel card1 = new JPanel();
        card1.add(new JLabel("UpperSection"));
        upperSectionL = new JPanel(new CardLayout());
        upperSectionL.add(card1);
        /** End of Upper Section Label */

        // GridBagLayout to make things stack vertically
        JPanel vertPane = new JPanel();
        vertPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding UpperSectionL to vertPane
        vertPane.add(upperSectionL, gbc);


        // Creating Upper Score Section Label
        for (int i = 0; i < diceSides; i++)
        {
            upperJPanel[i] = createCard(upperScoreString[i]);
            vertPane.add(upperJPanel[i], gbc);
        }

        // Lower Score Section Label
        JPanel card2 = new JPanel();
        card2.add(new JLabel("Lower Section"));
        lowerSectionL = new JPanel(new CardLayout());
        lowerSectionL.add(card2);
        vertPane.add(lowerSectionL, gbc);

        // Creating Lower Score Section Label
        for (int i = 0; i < 7; i++)
        {
            lowerJPanel[i] = createCard(lowerScoreString[i]);
            vertPane.add(lowerJPanel[i], gbc);
        }

        name.add(vertPane, BorderLayout.EAST);
    }

    /**
     * dropDownMenu sets up the numbe of dice sides and number of dice in a hand drop down menus
     * @param name Takes in the JFrame name to place the panels that the drop down menus are placed in
     */
    private void dropDownMenu(JFrame name)
    {
        JPanel onion = new JPanel();
        onion.setLayout(new FlowLayout());

        JLabel diceSidesL = new JLabel("Dice Sides");
        final String[] diceString = {""};

        String[] diceSideString = {"6", "8", "12"};
        JComboBox diceSides = new JComboBox(diceSideString);

        // For loop to find what to set selected index at
        for (int i = 0; i < diceSideString.length; i++) {
            if (diceAndHand[0] == diceSideString[i]) {
                diceSides.setSelectedIndex(i);

            } else {
                diceSides.setSelectedIndex(0);
            }
        }

        diceSides.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                {
                    JComboBox cb = (JComboBox) e.getSource();
                    String diceSides = (String) cb.getSelectedItem();
                    System.out.println("Dice sides: " + diceSides);
                    diceString[0] = diceSides;
                    System.out.println("Dice String: " + diceString[0]);
                    saveDiceHand(diceSides, 0);
                }
            }
        });

        JLabel handDiceL = new JLabel("Number of Dice");
        final String[] handString = {""};

        String[] diceHandS = {"5", "6", "7"};
        JComboBox diceHand = new JComboBox(diceHandS);

        // For loop to find what to set selected index at
        for (int i = 0; i < diceSideString.length; i++) {
            if (diceAndHand[1] == diceSideString[i]) {
                diceHand.setSelectedIndex(i);

            } else {
                diceHand.setSelectedIndex(0);
            }
        }

        diceHand.setSelectedIndex(0);
        diceHand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String dice = (String) cb.getSelectedItem();
                System.out.println("Hand: " + dice);
                saveDiceHand(dice, 1);
            }
        });

        JPanel vertPane = new JPanel();
        vertPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel vertPane2 = new JPanel();
        vertPane2.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        // Adding to vertPane to make it vertical
        vertPane.add(diceSidesL, gbc);
        vertPane.add(diceSides);

        // Adding to vertPane2 to make it vertical
        vertPane2.add(handDiceL, gbc2);
        vertPane2.add(diceHand);

        // Adding to flow layout pane with both vertPane
        onion.add(vertPane);
        onion.add(vertPane2);

        name.add(onion);
    }

    /**
     * diceDisplay sets up the dice to be sized correctly and how many dice to display
     * @param name Takes in the JFrame name to place the panels that the dice are placed in
     */
    private void diceDisplay(JFrame name)
    {
        JPanel j1 = new JPanel();
        JPanel j2 = new JPanel();
        JPanel j3 = new JPanel();
        JPanel j4 = new JPanel();
        JPanel j5 = new JPanel();
        JPanel j6 = new JPanel();
        JPanel j7 = new JPanel();

        JPanel panels[] = {j1, j2, j3, j4, j5, j6, j7};

        // GridBagLayout to make things stack vertically
        JPanel vertPane = new JPanel();
        vertPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int numDice;
        try {
            numDice = Integer.parseInt(diceAndHand[1]);
            //System.out.println(numDice);
        } catch (NumberFormatException e) {
            numDice = 5;
        }

        for (int i = 0; i < numDice; i++)
        {
            final String diceNumber = Integer.toString(i);
            JButton btn = createDieImage(numDice);
            panels[i].add(btn);
            vertPane.add(panels[i], gbc);
        }

        name.add(vertPane, BorderLayout.WEST);
    }

    /**
     * saveDicehand saves the drop down menus option choices when redrawing the panel to include more dice
     * @param diceHand Takes in diceHand string that contains either number of dice in hand or the number of sides for a dice
     * @param n is either a 1 or 0 based on whether or not it is a dice sides or dice in hand
     */
    private void saveDiceHand(String diceHand, int n)
    {
        if (n == 0) {
            diceSides = diceHand;
        } else if (n == 1) {
            handCount = diceHand;
        }

        diceAndHand = new String[]{diceSides, handCount};

    }

    /**
     * setBottomPane sets up refresh, roll, preview score card, and exit buttons on the bottom pane
     * @param name Takes in the JFrame name to place the panels that the bottomPane is placed in
     */
    private void setBottomPane(JFrame name)
    {
        JPanel p = new JPanel();

        // Add roll button
        JButton roll = new JButton("Roll");
        p.add(roll);

        // Add preview scorecard button
        JButton preview = new JButton("Preview Score Card");
        preview.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "There is no preview, good luck");
            }
        });
        p.add(preview);


        // Add refresh button
        JButton refresh = new JButton("Reload");
        p.add(refresh);

        // Add exit button
        JButton exit = new JButton("Exit");
        p.add(exit);
        // Action Listener to close the window
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name.dispose();
                System.exit(0);
            }
        });

        name.add(p, BorderLayout.SOUTH);
    }

    /**
     * createCard is a function that is called in cardLayout to set up the cards
     * @param s takes in the string that is used to name the system.out.println, so I can check what button has been hit
     * @return returns the fully made card JPanel to be used in cardLayout
     */
    private JPanel createCard(String s)
    {
        final JPanel card;
        JPanel p = new JPanel();
        p.add(new JLabel(s));

        // Adding button
        JButton btn = new JButton("-"); // A num value will put where "-" is
        p.add(btn);

        btn.addActionListener(new ActionListener() {
                                  public void actionPerformed(ActionEvent e) {
                                      btn.setForeground(Color.RED);
                                      btn.setEnabled(false);
                                      System.out.println(s + " has been selected");
                                      //System.out.println(e);
                                  }
                              }
        );
        // Adding to JPanel p to card JPanel
        card = new JPanel(new CardLayout());
        card.add(p);
        return card;
    }

    /**
     * createDieImage sets the button uo that has the die as its icon
     * @param dieNum takes in the die num to be used as the icon
     * @return returns a JButton that has thr right image icon displayed as ones hand
     */
    private JButton createDieImage(int dieNum)
    {
        ImageIcon li = new ImageIcon(diceImageSelect(dieNum));
        // Changing image size
        Image image = li.getImage(); // transform it
        Image newImg = image.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        li = new ImageIcon(newImg);
        JButton btn = new JButton(li);

        return btn;
    }

    /**
     * Sets up the String array that has all the die images in it to be used a label icons
     * @param dieS takes in the die number that needs to be displayed
     * @return returns a string that has the diceImage to be used
     */
    private String diceImageSelect(int dieS)
    {
        String[] dieImage = {"./Dice/D6-01.png", "./Dice/D6-02.png", "./Dice/D6-03.png", "./Dice/D6-04.png", "./Dice/D6-05.png", "./Dice/D6-06.png", "./Dice/D6-07.png", "./Dice/D6-08.png", "./Dice/D6-09.png", "./Dice/D6-10.png", "./Dice/D6-11.png", "./Dice/D6-12.png"};
        return dieImage[dieS - 1];
    }

}