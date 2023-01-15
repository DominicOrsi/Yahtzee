/** 
 * This class creates Hand used in the Yahtzee program
 * CPSC 224-01, Fall 2021
 * HW #3
 * 
 * @author Dominic Orsi
 * @version v1.3 10/22/21 9:00AM
 * (C)2021 
 */
package edu.gonzaga;

/*
 *  A Class for Hand used in Yahtzee
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Hand
{
    // Declaring other classes
    Game newGame = new Game(1); // Init with 1 as config does not need to be run again

    public ArrayList<Character> rollKeep = new ArrayList<>(); // Was made public to use in test
    public static ArrayList<Integer> hand = new ArrayList<>();


    /**
     * reNum checks for input function then if there is no need to reroll it returns a 3 but if there is a reroll it returns a 1
     *
     * @return the roll amount, 1 on if it was rerolled or 3 if there is no need to reroll, and we are done with this had
     */
    public int reNum()
    {
        int roll;
        input();
        if(!checkReRoll()) // if statement to see if checkReRoll is true
        {
            reRoll();
            roll = 1; // Sets roll equal to one to help Player Class keep track
        }
        else
        {
            roll = newGame.rollsPerHand; // Sets roll equal to newGame.rollsPerHand to have the scorecard output in Player Class
        }
        return roll;
    }

    /**
     * Takes in die and then sets the opening hand by rolling the die then calling the print function
     */
    public void handPlacement()
    {
        hand.clear(); // Clearing here to make sure that when we run through the loop again we are starting with no other values than an empty vector array
        Die die1 = new Die(newGame.diceSides);
        popRollKeep();
        for(int i = 0; i < newGame.diceInHand; i++) // for loop to iterate through hand and assign each value with a dice roll
        {
            die1.roll();
            hand.add(i, die1.getSideUp());
        }
    }

    /**
     * Rerolls the hand when rollKeep array has an 'n' char at the same place as hand array
     */
    private void reRoll()
    {
        Die die1 = new Die(newGame.diceSides);
        for (int i = 0; i < newGame.diceInHand; i++) // for loop to iterate through rollKeep
        {
            if (rollKeep.get(i) == 'n') // if statement to reroll dice with an 'n' char in the same position in hand
            {
                hand.remove(i);
                die1.roll();
                hand.add(i, die1.getSideUp());
            }
        }
        printHand();
    }
    /**
     * input creates handIn, a Scanner to get the input for rollKeep. Then calls stringToChar function for rollKeep
     */
    private void input()
    {
        System.out.print("Enter die to keep (y or n): ");
        Scanner handIn = new Scanner(System.in);
        String keepRoll = handIn.next();
        stringToChar(keepRoll);
    }

    /**
     * stringToChar takes a string and turns in into a char for then the chars to be put into rollKeep array
     * @param s Takes in a string to then turn it into a char
     */
    private void stringToChar(String s)
    {
        for(int i = 0; i < newGame.diceInHand; i++) // for loop to iterate through string and put them as char
        {
            char c = s.charAt(i);
            rollKeep.set(i, c);
        }
    }

    /**
     * printHand prints out the hand array
     */
    public void printHand()
    {
        System.out.print("\nHand: ");
        for(int i = 0; i < newGame.diceInHand; i++) // for loop to iterate through hand and print out the dice numbers in it
        {
            System.out.print(hand.get(i) + " ");
        }
        System.out.println();
    }

    /**
     * checkReRoll goes through the array rollKeep looking for the char 'y' and adds them up to see if there is 5 'y' chars
     * @return Returns a boolean saying either there is 5 'y' chars (true) or there is not 5 'y' chars (false)
     */
    public boolean checkReRoll() // Made public for a test
    {
        int yes = 0;
        for(int i = 0; i < newGame.diceInHand; i++) // for loop to iterate through rollHand
        {
            if(rollKeep.get(i) == 'y') // if statement to count up number of 'y' chars in rollHand
            {
                yes += 1;
            }
        }
        // if statement to either return true for no need to reroll or false to reroll
        return yes == newGame.diceInHand;
    }

    /**
     * popRollKeep iterates through the array rollKeep and puts the char 'y' in each index
     */
    private void popRollKeep()
    {
        for(int i = 0; i < newGame.diceInHand; i++) // Iterates through newGame.diceInHand to populate rollKeep with 'y'
        {
            rollKeep.add(i, 'y');
        }
    }
}
