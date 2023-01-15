/** 
 * This program plays the game yahtzee
 * CPSC 224-01, Fall 2021
 * HW #2
 * Sources to Cite:
 * Code example in C++. I used the  fullHouseFound and maxStraightFound functions
 * @author Dominic Orsi
 * @version v1.3 10/22/21 9:00AM
 * (C)2021 
 */
package edu.gonzaga;

import java.util.Scanner;

/*
*  This is the main class for the Yahtzee project.
*  It really should just instantiate another class and run
*   a method of that other class.
*/


/** Main program class for launching Yahtzee program. */
public class Yahtzee {
    public static void main(String[] args)
    {
        // Create game loop

        boolean playAgain = true;

        Scanner play = new Scanner(System.in);

        while(playAgain) // Continues playing the game till playAgain is false
        {
            boolean gameDone = false;
            Player newPlayer = new Player();
            newPlayer.setUp();

            while(!gameDone)
            {
                gameDone = newPlayer.playerRolls();
            }


            System.out.println("If you want to play again type 'y': ");
            String answer = play.next();
            playAgain = answer.equalsIgnoreCase("y"); // Sets playAgain to true if answer is equal to "y"
        }
        play.close();

    }
}
