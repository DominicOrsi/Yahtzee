/** 
 * This class creates a scorecard for the program Yahtzee
 * CPSC 224-01, Fall 2021
 * HW #3
 * Sources to Cite:
 * Code example in C++. I used fullHouseFound, and maxStraightFound functions in this class
 * @author Dominic Orsi
 * @version v1.3 10/22/21 9:00AM
 * (C)2021 
 */
package edu.gonzaga;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoreCard
{
    // Declaring Classes
    Game newGame = new Game(1); // Init with 1 as config does not need to be run again

    public ArrayList<Integer> totalScoreCard = new ArrayList<>();
    private int[] upperScores = new int[newGame.diceSides];
    private int[] lowerScore = new int[6];
    private int addToScore = 0;


    /**
     * upperSection adds up all the dice of a into there respective spot in the upperScores array which is an array of sum of all dice of the same number
     */
    public void upperSection(ArrayList<Integer> array)
    {
        resetUpper(); // Rests upperScore to have 0s in all spots so scores are not messed up
        for(int i = 0; i < newGame.diceInHand; i++)  // For loop to iterate through array and then add up scores to upperScores
        {
            for(int j = 0; j <= newGame.diceSides; j++)
            {
                if(array.get(i) == j)
                {
                    upperScores[j - 1] += j;
                }
            }

        }
        for(int k = 0; k < newGame.diceSides; k++) // For loop iterates through to print out score for each dye
        {
            if(totalScoreCard.get(k) == -1) // Only prints the options that are not chosen
            {
                System.out.println(k + 1 + ": " + upperScores[k]);
            }
        }
    }

    /**
     * lowerSection calls other functions and then checks them to then execute the correct print statement
     * @param array Takes in array to then allow it to be passed to functions comparing the values of that array
     */
    public void lowerSection(ArrayList<Integer> array)
    {
        resetLower(); // Rests lowerScore to have 0s in all spots so scores are not messed
        if(threeKind() && (totalScoreCard.get(newGame.diceSides) == -1)) // If statement to check if there is three of a kind
        {
            System.out.print(newGame.diceSides + 1 +" 3K: " + total() +"\n");
            lowerScore[0] = total();
        }
        else if((totalScoreCard.get(newGame.diceSides) == -1))
        {
            System.out.println(newGame.diceSides + 1 + " 3K: 0");
            lowerScore[0] = 0;
        }
        if(fourKind() && (totalScoreCard.get(newGame.diceSides + 1) == -1))
        {
            System.out.print(newGame.diceSides + 2 + " 4K: " + total() + "\n");
            lowerScore[1] = total();
        }
        else if((totalScoreCard.get(newGame.diceSides + 1) == -1))
        {
            System.out.println(newGame.diceSides + 2 + " 4K: 0");
            lowerScore[1] = 0;
        }
        if(fullHouseFound(array)&& (totalScoreCard.get(newGame.diceSides + 2) == -1)) // If statement to see if there is a full house
        {
            System.out.println(newGame.diceSides + 3 + " FH: 25");
            lowerScore[2] = 25;
        }
        else if((totalScoreCard.get(newGame.diceSides + 2) == -1))
        {
            System.out.println(newGame.diceSides + 3 + " FH: 0");
            lowerScore[2] = 0;
        }
        if((maxStraightFound(array) >= 4) && (totalScoreCard.get(newGame.diceSides + 3) == -1)) // If statement to check for small straight
        {
            System.out.println(newGame.diceSides + 4 + " Sm. Straight: 30");
            lowerScore[3] = 30;
        }
        else if((totalScoreCard.get(newGame.diceSides + 3) == -1))
        {
            System.out.println(newGame.diceSides + 4 + " Sm. Straight: 0");
            lowerScore[3] = 0;
        }
        if((maxStraightFound(array) >= 5) && (totalScoreCard.get(newGame.diceSides + 4) == -1)) // If statement to check for large straight
        {
            System.out.println(newGame.diceSides + 5 + " Lg. Straight: 40");
            lowerScore[4] = 40;
        }
        else if((totalScoreCard.get(newGame.diceSides + 4) == -1))
        {
            System.out.println(newGame.diceSides + 5 + " Lg. Straight: 0");
            lowerScore[4] = 0;
        }
        if(yahtzeeScore(array) && (totalScoreCard.get(newGame.diceSides + 5) == -1)) // If statement to check for Yahtzee
        {
            System.out.println(newGame.diceSides + 6 + " Yahtzee: 50");
            lowerScore[5] = 50;
        }
        else if((totalScoreCard.get(newGame.diceSides + 5) == -1))
        {
            System.out.println(newGame.diceSides + 6 + " Yahtzee: 0");
            lowerScore[5] = 0;
        }
    }

    /**
     * scoreKeeper adds the score from either the upperScores or lowerScores array to totalScoreCard
     */
    public void scoreKeeper()
    {
        if(addToScore <= newGame.diceSides)
        {
            totalScoreCard.set(addToScore - 1,upperScores[addToScore - 1]);
        }
        else
        {
            totalScoreCard.set(addToScore-1, lowerScore[addToScore - (newGame.diceSides + 1)]);
        }
    }

    /**
     * printCurrentScoreCard prints out the scorecard for the scores that have been recorded on it
     * So if one chose to score a Yahtzee on their scorecard that line would appear when it is scored
     */
    public void printCurrentScoreCard()
    {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        if(s.equalsIgnoreCase("S"))
        {
            System.out.println("\n******************SCORECARD******************");
            for(int i = 0; i < newGame.diceSides; i++)
            {
                if(totalScoreCard.get(i) != -1)
                {
                    System.out.println(i + 1 + ": " + totalScoreCard.get(i));
                }
            }
            for(int j = 0; j <= newGame.diceSides; j++)
            {
                if(totalScoreCard.get(newGame.diceSides + j) != -1)
                {
                    if(j == 0)
                    {
                        System.out.println("3k: " + totalScoreCard.get(newGame.diceSides));
                    }
                    else if(j == 1)
                    {
                        System.out.println("4k: " + totalScoreCard.get(newGame.diceSides + 1));
                    }
                    else if(j == 2)
                    {
                        System.out.println("FH: " + totalScoreCard.get(newGame.diceSides + 2));
                    }
                    else if(j == 3)
                    {
                        System.out.println("Sm. Straight: " + totalScoreCard.get(newGame.diceSides + 3));
                    }
                    else if(j == 4)
                    {
                        System.out.println("Lg. Straight: " + totalScoreCard.get(newGame.diceSides + 4));
                    }
                    else if(j == 5)
                    {
                        System.out.println("Yahtzee: " + totalScoreCard.get(newGame.diceSides + 5));
                    }
                }
            }
            System.out.println("*********************************************");
        }
    }

    /**
     * intScoreCard sets up the ArrayList totalScoreCard and gives every value -1 at start up
     */
    public void intScoreCard()
    {
        for(int i = 0; i < newGame.diceSides + 6; i++)
        {
            totalScoreCard.add(-1);
        }
    }

    /**
     * checkScoreCardDone checks the ArrayList totalScoreCard for -1 which mean that that value has not been chosen yet
     * @return If there are no -1s in totalScoreCard then the function returns true otherwise it returns false
     */
    public boolean checkScoreCardDone()
    {
        int negativeOne = 0;
        for(int i = 0; i < newGame.diceSides + 6; i++)
        {
            if(totalScoreCard.get(i) == -1)
            {
                negativeOne += 1;
            }
        }
        return negativeOne == 0;
    }

    /**
     * findOverAllScore takes in the totalScoreCard and then adds every value up to get the overall score
     */
    public int findOverAllScore()
    {
        int overallScore = 0;
        for(int i = 0; i < newGame.diceSides + 6; i++)
        {
            overallScore += totalScoreCard.get(i);
        }
        overallScore += upperScoreBonus(); //UpperScoreBonus finds out if the there is an upper score bonus
        return overallScore;
    }

    /**
     * scoreChoice takes in an input and then sees to if the number is greater than 6.
     * If the number is less than 6 it then returns upperScores array
     * If the number is greater than 6 it then returns lowerScores array
     */
    public void scoreChoice()
    {
        int numChosen;
        System.out.print("Enter the number to select your score: ");
        numChosen = input();
        addToScore = numChosen; // Assigned to numChosen to then get what scorecard line has been chosen

        if(totalScoreCard.get(numChosen - 1) != -1) // Will check to make sure the entered score is not one already entered
        {
            System.out.println("You have already filled in that score!");
            scoreChoice();
        }
    }

    /**
     * input creates a scanner then takes in the input numChosen
     * @return Returns int numChosen
     */
    private int input()
    {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    /**
     * total adds up the total score of the upperScores array by iterating through a for loop
     * @return Returns the int totalScore which is the sum of all the ints in the array upperScores
     */
    private int total()
    {
        int totalScore = 0;
        for(int i = 0; i < newGame.diceSides; i++)
        {
            totalScore += upperScores[i];
        }
        return totalScore;
    }

    /**
     * threeKind checks each spot in the array upperScores to see if they are greater than 3 times the number
     * @return Returns true or false on whether or not there is a three of a kind
     */
    private boolean threeKind()
    {
        int threeKindCounter = 0;
        for(int i= 0; i < newGame.diceSides; i++ )
        {
            if(upperScores[i] >= (i + 1) * 3)
                threeKindCounter += 1;
        }
        return threeKindCounter >= 1;
    }

    /**
     * fourKInd checks each spot in the array upperScores to see if they are greater than 4 times the number
     * @return Returns true or false on whether or not there is a four of a kind
     */
    private boolean fourKind()
    {
        int fourKindCounter = 0;
        for(int i= 0; i < newGame.diceSides; i++ )
        {
            if(upperScores[i] == (i + 1) * 4)
                fourKindCounter += 1;
        }
        return fourKindCounter >= 1;
    }

    /**
     * fullHouseFound checks for a full house by iterating through 1-6 and seeing if there is 3 of a kind or 4 of kind
     * @param array Takes in array to be used to check for 3 and 2 of a kind
     * @return Returns true if there is a full house and returns false if there is no full house
     */
    private boolean fullHouseFound(ArrayList<Integer> array)
    //this function returns true if the hand is a full house
    //or false if it does not
    {
    boolean foundFH = false;
    boolean found3K = false;
    boolean found2K = false;
    int currentCount ;
    for (int dieValue = 1; dieValue <= newGame.diceSides; dieValue++) // For loop to iterate through die value int
    {
        currentCount = 0;
        for (int diePosition = 0; diePosition < newGame.diceInHand; diePosition++) // For loop to iterate through array
        {
            if (array.get(diePosition) == dieValue) // If statement to check if dieValue is equal to array at diePosition
                currentCount++;
        }
        if (currentCount == 2) // If statement to see if current count is equal to 2
            found2K = true;
        if (currentCount == 3) // If statement to see if current count is equal to 3
            found3K = true;
    }
    if (found2K && found3K) // If statement to see if found2k and found3k are true to get a full house
        foundFH = true;
    return foundFH;
    }

    /**
     * maxStraightFound goes through the array passed in looking for a count of 1-4 to 2-5
     * @param array Takes in array to check for counts of 1-4 to 2-5
     * @return Returns an int see based on how many numbers in numerical it counts
     */
    private int maxStraightFound(ArrayList<Integer> array)
    //this function returns the length of the longest
    //straight found in a hand
    {
        int maxLength = 1;
        int curLength = 1;
        for(int counter = 0; counter < newGame.diceInHand - 1; counter++)
        {
            if (array.get(counter) + 1 == array.get(counter + 1) ) //jump of 1
                curLength++;
            else if (array.get(counter) + 1 < array.get(counter + 1)) //jump of >= 2
                curLength = 1;
            if (curLength > maxLength)
                maxLength = curLength;
        }
        return maxLength;
    }

    /**
     * yahtzeeScore sorts through an array looking for 5 of a single number
     * @param array Takes in array as it uses it to check if there is 5 of the same number
     * @return Returns true if there is 5 of kind and false if there is not 5 of a kind
     */
    private boolean yahtzeeScore(ArrayList<Integer> array)
    {
        int values;
        boolean yes = false;
        for(int i = 0; i < newGame.diceInHand; i++)
        {
            values = 0;
            for(int j = 1; j < 7; j++)
            {
                if(array.get(i) == j)
                {
                    values += 1;
                }
                if(values == newGame.diceInHand)
                {
                    yes = true;
                }
            }
        }
        return yes;
    }

    /**
     * resetUpper takes the array upperScores and sets all its values to 0 so that one the array is called again
     * scores are not messed with from the previous hand toss
     */
    private void resetUpper()
    {
        for(int i = 0; i < newGame.diceSides; i++)
        {
            upperScores[i] = 0;
        }
    }

    /**
     * lowerUpper takes the array upperScores and sets all its values to 0 so that one the array is called again
     * scores are not messed with from the previous hand toss
     */
    private void resetLower()
    {
        for(int i = 0; i < 6; i++)
        {
            lowerScore[i] = 0;
        }
    }

    /**
     * upperScoreBonus checks to see if the upper score the player has is higher than 63
     * If so then it returns 35 if not then 0 is returned
     * @return Returns either 35 or 0 depending on if the upper score is higher than 63
     */
    private int upperScoreBonus()
    {
        int totalUpperScore = 0;
        for(int i = 0; i < newGame.diceSides; i++)
        {
            totalUpperScore += totalScoreCard.get(i);
        }
        if(totalUpperScore >= 63)
        {
            System.out.println("You got the upper section bonus of 35 points");
            return 35;
        }
        else
        {
            System.out.println("You did not get the upper section bonus");
            return 0;
        }
    }

    /**
     * masterPrint is a function that prints out the whole scorecard.
     * Is being used to debug and will not be implemented into the game
     */
    public void masterPrint()
    {
        for(int i = 0; i < newGame.diceSides; i++)
        {
            System.out.println(i + 1 + ": " + totalScoreCard.get(i));
        }
        System.out.println("3k: " + totalScoreCard.get(newGame.diceSides));
        System.out.println("4k: " + totalScoreCard.get(newGame.diceSides + 1));
        System.out.println("FH: " + totalScoreCard.get(newGame.diceSides + 2));
        System.out.println("Sm. Straight: " + totalScoreCard.get(newGame.diceSides + 3));
        System.out.println("Lg. Straight: " + totalScoreCard.get(newGame.diceSides + 4));
        System.out.println("Yahtzee: " + totalScoreCard.get(newGame.diceSides + 5));

    }
}
