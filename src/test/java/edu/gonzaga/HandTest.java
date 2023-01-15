package edu.gonzaga;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    @Test
    void testHand()
    {
        Game newGame = new Game(1);
        Integer lengthExpected = newGame.diceInHand;
        Hand newHand = new Hand();
        newHand.handPlacement();
        System.out.println("Hand should be equal to length expected: " + lengthExpected);
        assertTrue(newHand.hand.size() == lengthExpected);
    }

    @Test
    void testRollKeep()
    {
        Game newGame = new Game(1);
        Integer lengthExpected = newGame.diceInHand;
        Hand newHand = new Hand();
        newHand.handPlacement();
        System.out.println("RollKeep should be equal to length expected: " + lengthExpected);
        assertTrue(newHand.rollKeep.size() == lengthExpected);
    }

    @Test
    void testCheckReRoll()
    {
        Hand newHand = new Hand();
        newHand.handPlacement();
        System.out.println("CheckReRoll should return true");
        assertTrue(newHand.checkReRoll());
    }

}
