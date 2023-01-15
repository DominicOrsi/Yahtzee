/**
 * This class creates Game used in the Yahtzee program
 * CPSC 224-01, Fall 2021
 * HW #3
 *
 * @author Dominic Orsi
 * @version v1.3 10/22/21 9:00AM
 * (C)2021
 */
package edu.gonzaga;

import java.io.*;
import java.util.Scanner;

public class Game
{
    public int diceSides;
    public int diceInHand;
    public int rollsPerHand;
    private int[] array = new int[3];
    public boolean configEx = false;

    public Game(int i)
    {
        readInConfig();
        this.diceSides = array[0]; // Sets diceSides to array at index 0
        this.diceInHand = array[1]; // Sets diceInHand to array at index 1
        this.rollsPerHand = array[2]; // Sets rollsPerHand to array at index 2
        // Sets up a new game config if needed
        if(i == 0)
        {
            // Opens a scanner and then sets up a new game config
            Scanner input = new Scanner(System.in);
            System.out.println("You are playing with " + diceInHand + " " + diceSides + "-sided die");
            System.out.println("You get " + rollsPerHand + " rolls per hand");
            System.out.println("Press 'y' to change the game configuration");
            String config = input.next();

            if (config.equals("y")) // If statement to see if config is equal to "y"
            {
                changeConfig();

            }
        }

    }

    /**
     * Reads in the config file then sets each string read in into an int that is put into an array. Array is later used to set the values
     */
    private void readInConfig()
    {
        try
        {
            // Creates a BufferedReader to read in the file given
            BufferedReader br = new BufferedReader(
                    new FileReader(System.getProperty("user.dir") + "/yahtzeeConfig.txt"));
            String s;
            Integer i = 0;
            // Goes through the BufferedReader (br) till it equals null
            while ((s = br.readLine()) != null)
            {
                i++;
                if(i == 1)
                {
                    array[0] = Integer.parseInt(s);
                }
                else if(i == 2)
                {
                    array[1] = Integer.parseInt(s);
                }
                else if(i == 3)
                {
                    array[2] = Integer.parseInt(s);
                }
            }

            //System.out.println("Dice sides: " + array[0] + "\nDice in hand: " + array[1] + "\nRolls per hand: " + array[2]);
            br.close();
        }
        catch(Exception ex) {
            System.out.println("Problem reading in yahtzeeConfig.txt");
        }
    }

    /**
     * Writes over the Config file with the new values entered in.
     */
    private void changeConfig()
    {
        try
        {
            Scanner input = new Scanner(System.in);
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(System.getProperty("user.dir") + "/yahtzeeConfig.txt"));
            System.out.println("Enter the number of sides on each die: ");
            int dieSides = input.nextInt();
            this.diceSides = dieSides;
            System.out.println("Enter the number of die in play: ");
            int dieInPlay = input.nextInt();
            this.diceInHand = dieInPlay;
            System.out.println("Enter the number of rolls per hand: ");
            int rollsInHand = input.nextInt();
            this.rollsPerHand = rollsInHand;
            bw.write(String.valueOf(dieSides) + "\n");
            bw.write(String.valueOf(dieInPlay) + "\n");
            bw.write(String.valueOf(rollsInHand));


            configEx = true;
            bw.close();
        }
        catch(Exception ex)
        {
            System.out.println("Problem changing yahtzeeConfig.txt");
        }
    }
}
