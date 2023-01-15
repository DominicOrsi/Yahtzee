/** 
 * This class creates Player used in the Yahtzee program
 * CPSC 224-01, Fall 2021
 * HW #3
 * 
 * @author Dominic Orsi
 * @version v1.3 10/22/21 9:00AM
 * (C)2021 
 */
package edu.gonzaga;

import java.util.*;

/*
 *  A Class for Hand used in Yahtzee
 */

public class Player
{
    private int numRolls = 0;

    Game newGame = new Game(0); // Init with 0 as this is the first class that calls the game class. Setup is then only run once
    Hand newHand = new Hand();
    ScoreCard card = new ScoreCard();

    /**
     * Finds the number of roles the player has had by checking how many roles there are after each if loop
     */
    public boolean playerRolls()
    {
        int sayOverAll = 0; // This int is to used to keep from saying overall score more than once
        if(numRolls == 0)
        {
            // Starts playing the game
            newHand.handPlacement();
            newHand.printHand();
            numRolls += 1;
        }
        if(numRolls > newGame.rollsPerHand - 1) // If statement to check if numRolls is greater than nameGame.rollsPerHand and then either allow the player to reroll or output scorecard
        {
            Collections.sort(newHand.hand); // Sorts the Array List from smallest to biggest
            card.upperSection(newHand.hand);
            card.lowerSection(newHand.hand); // Max Straight does not work, only returns small straight even when there are 5 in a row
            card.scoreChoice();
            System.out.println(); // Adds another space for formatting
            card.scoreKeeper();
            System.out.println("Type 'S' to view your scorecard");
            card.printCurrentScoreCard();

        }
        else
        {
            numRolls += newHand.reNum();
            playerRolls();
        }
        numRolls = 0;

        if(card.checkScoreCardDone() && sayOverAll == 0)
        {
            System.out.println("Your overall score is: " + card.findOverAllScore());
            sayOverAll += 1;
        }
        return card.checkScoreCardDone();
    }

    /**
     * setUp calls the function intScoreCard from the ScoreCard class to set up the scoreCard
     * The reason that I have it here is to not make a new scoreCard in Yahtzee.java
     */
    public void setUp()
    {
        card.intScoreCard();
    }
}
