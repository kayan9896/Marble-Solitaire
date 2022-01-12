import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/**
 * A Junit test for the MarbleSolitaireModelImpl class
 */
public class MarbleSolitaireModelImplTest {
    private MarbleSolitaireModelImpl a;
    private MarbleSolitaireModelImpl b;
    private MarbleSolitaireModelImpl c;
    private MarbleSolitaireModelImpl d;

    /**
     * Test the constructor
     */
    @Before
    public void setup(){
        try {
            a = new MarbleSolitaireModelImpl();
            b = new MarbleSolitaireModelImpl(3, 1);
            c = new MarbleSolitaireModelImpl(5);
            d = new MarbleSolitaireModelImpl(15,13,11);
        }
        catch (IllegalArgumentException e){
            fail("An exception should not be thrown");
        }
        try{
            new MarbleSolitaireModelImpl(45,69);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
        try{
            new MarbleSolitaireModelImpl(9,145,-69);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
        try{
            new MarbleSolitaireModelImpl(9,0,0);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
        try{
            new MarbleSolitaireModelImpl(6);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
        try{
            new MarbleSolitaireModelImpl(-1);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
        try{
            new MarbleSolitaireModelImpl(0);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
    }

    /**
     * Test the move method
     */
    @Test
    public void testMove(){
        try {
            a.move(5,3,3,3);
        }
        catch (IllegalArgumentException e){
            fail("An exception should not be thrown");
        }
        try{
            a.move(5,3,3,3);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
        try{
            a.move(2,3,-4,3);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
        try{
            a.move(0,0,4,3);
            fail("An exception should be thrown");
        }
        catch (IllegalArgumentException e){}
        try {
            a.move(2,3,4,3);
        }
        catch (IllegalArgumentException e){
            fail("An exception should not be thrown");
        }
    }

    /**
     * Test the isGameOver method
     */
    @Test
    public void testIsGameOver(){
        assertFalse(a.isGameOver());
        a.move(1,3,3,3);
        a.move(4,3,2,3);
        a.move(3,1,3,3);
        a.move(3,4,3,2);
        a.move(3,6,3,4);
        assertFalse(a.isGameOver());
        a.move(6,3,4,3);
        assertTrue(a.isGameOver());
    }

    /**
     * Test the getGameState method
     */
    @Test
    public void testGetGameState(){
        assertEquals("    O O O    \n" +
                "    O O O    \n" +
                "O O O O O O O\n" +
                "O O O _ O O O\n" +
                "O O O O O O O\n" +
                "    O O O    \n" +
                "    O O O    " ,a.getGameState());
        a.move(1,3,3,3);
        assertEquals("    O O O    \n" +
                "    O _ O    \n" +
                "O O O _ O O O\n" +
                "O O O O O O O\n" +
                "O O O O O O O\n" +
                "    O O O    \n" +
                "    O O O    " ,a.getGameState());
        a.move(4,3,2,3);
        assertEquals("    O O O    \n" +
                "    O _ O    \n" +
                "O O O O O O O\n" +
                "O O O _ O O O\n" +
                "O O O _ O O O\n" +
                "    O O O    \n" +
                "    O O O    " ,a.getGameState());
        assertEquals("    O O O    \n" +
                "    O O O    \n" +
                "O O O O O O O\n" +
                "O _ O O O O O\n" +
                "O O O O O O O\n" +
                "    O O O    \n" +
                "    O O O    " ,b.getGameState());
        assertEquals("      O O O O O      \n" +
                "      O O O O O      \n" +
                "      O O O O O      \n" +
                "O O O O O O O O O O O\n" +
                "O O O O O O O O O O O\n" +
                "O O O O O _ O O O O O\n" +
                "O O O O O O O O O O O\n" +
                "O O O O O O O O O O O\n" +
                "      O O O O O      \n" +
                "      O O O O O      \n" +
                "      O O O O O      " ,c.getGameState());
        c.move(7,5,5,5);
        assertEquals("      O O O O O      \n" +
                "      O O O O O      \n" +
                "      O O O O O      \n" +
                "O O O O O O O O O O O\n" +
                "O O O O O O O O O O O\n" +
                "O O O O O O O O O O O\n" +
                "O O O O O _ O O O O O\n" +
                "O O O O O _ O O O O O\n" +
                "      O O O O O      \n" +
                "      O O O O O      \n" +
                "      O O O O O      " ,c.getGameState());
    }

    /**
     * Test the getScore method
     */
    @Test
    public void testGetScore(){
        assertEquals(32,a.getScore());
        a.move(1,3,3,3);
        a.move(4,3,2,3);
        a.move(3,1,3,3);
        a.move(3,4,3,2);
        a.move(3,6,3,4);
        a.move(6,3,4,3);
        assertEquals(26,a.getScore());
        assertEquals(84,c.getScore());
    }
}
