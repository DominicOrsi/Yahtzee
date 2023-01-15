package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class TestGUI {
    ScoreCard card = new ScoreCard();
    Hand newHand = new Hand();

    private String diceSides = "6";
    private String handCount = "5";
    private String[] diceAndHand = {diceSides, handCount};

    private JPanel[] upperJPanel = new JPanel[12];
    private JPanel[] lowerJPanel = new JPanel[7];
    private JFrame f;

    public static void main(String[] args) {
        new TestGUI();
    }

    public TestGUI() {
        SwingUtilities.invokeLater(() ->
                {
                    newFrame(0);
                    dropDownMenu(f);
                    cardLayout(f);
                    diceDisplay(f);
                    setBottomPane(f);

                    f.setVisible(true);
                }
        );
    }

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

    private void saveDiceHand(String diceHand, int n)
    {
        if (n == 0) {
            diceSides = diceHand;
        } else if (n == 1) {
            handCount = diceHand;
        }

        diceAndHand = new String[]{diceSides, handCount};

    }

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

    private String diceImageSelect(int dieS)
    {
        String[] dieImage = {"./Dice/D6-01.png", "./Dice/D6-02.png", "./Dice/D6-03.png", "./Dice/D6-04.png", "./Dice/D6-05.png", "./Dice/D6-06.png", "./Dice/D6-07.png", "./Dice/D6-08.png", "./Dice/D6-09.png", "./Dice/D6-10.png", "./Dice/D6-11.png", "./Dice/D6-12.png"};
        return dieImage[dieS - 1];
    }

    private void newFrame(int n)
    {
        if(n == 1)
        {
            f.removeAll();
            f.revalidate();
            f.repaint();
        }
        f = new JFrame();
        f.setLayout(new BorderLayout(10, 5));
        f.setTitle("JPanel Example");
        f.setSize(800, 900);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


}

