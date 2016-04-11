import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test Reversi class
 */
public class ReversiTester
{ 
  /* convert array of integer to board */
  public Reversi int2board(int[][] array)
  {
    int rows = array.length;
    int columns = array[0].length;
    Reversi rev = new Reversi(rows, columns);
    rev.setArray(array);
    return rev;
  }
  
  /* test first constructor */
  @Test
  public void testFirstConstructor()
  {
    Reversi rev = new Reversi(4, 5);
    rev.setVisible(false);
    int[][] array = { { 0, 0, 0, 0, 0 }, 
                      { 0, 1, 2, 0, 0 }, 
                      { 0, 2, 1, 0, 0 }, 
                      { 0, 0, 0, 0, 0 } };
    assertArrayEquals("Testing the first constructor", rev.getArray(), array);
  } 
  
  /* test second constructor */
  @Test
  public void testSecondConstructor()
  {
    Reversi rev = new Reversi(5);
    rev.setVisible(false);
    int[][] array = { { 0, 0, 0, 0, 0 }, 
                      { 0, 1, 2, 0, 0 }, 
                      { 0, 2, 1, 0, 0 }, 
                      { 0, 0, 0, 0, 0 }, 
                      { 0, 0, 0, 0, 0 } };
    assertArrayEquals("Testing the second constructor", rev.getArray(), array);
  }  
  
  /* test third constructor */
  @Test 
  public void testThirdConstructor()
  {
    Reversi rev = new Reversi();
    rev.setVisible(false);
    int[][] array = { { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                      { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                      { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                      { 0, 0, 0, 1, 2, 0, 0, 0 }, 
                      { 0, 0, 0, 2, 1, 0, 0, 0 }, 
                      { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                      { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                      { 0, 0, 0, 0, 0, 0, 0, 0 } };
    assertArrayEquals("Testing the third constructor", rev.getArray(), array);
  }
  
  /* test checkNumberOfFlips */
  @Test
  public void testCheckNumberOfFlips()
  {
    int[][] array = { {0, 0, 0, 0, 0},
                      {1, 2, 2, 2, 0},
                      {1, 2, 0, 2, 0},
                      {1, 2, 2, 2, 0} };
    Reversi rev = new Reversi(4, 5);
    rev.setArray(array);
    rev.setVisible(false);
    
    assertEquals("Test zero: the move will count zero flip", rev.checkNumberOfFlips(1, 0, 2, 0, 1), 0);
    assertEquals("Test zero: the move will count zero flip", rev.checkNumberOfFlips(2, 0, 4, 0, -1), 0);
    assertEquals("Test one: the move will count one flip", rev.checkNumberOfFlips(1, 2, 2, 0, -1), 1);
    assertEquals("Test many: the move will count 3 flips", rev.checkNumberOfFlips(1, 3, 4, 0, -1), 3); 
  }
  
  /* test checkLegalMoves */
  @Test
  public void testCheckLegalMoves()
  {
    int[][] array = { {0, 0, 0, 0, 0},
                      {1, 2, 1, 2, 0},
                      {1, 2, 0, 2, 0},
                      {1, 2, 2, 2, 0} };
    Reversi rev = new Reversi(4, 5);
    rev.setArray(array);
    rev.setVisible(false);
    
    assertEquals("Test zero: the move is illegal", rev.checkLegalMoves(1, 0, 0, false), false);
    assertEquals("Test one: the move is legal and will flip one piece", rev.checkLegalMoves(1, 1, 4, false), true); 
    assertEquals("Test many: the move is legal and will flip many pieces", rev.checkLegalMoves(1, 3, 4, false), true);
  }
  
  /* test flips */
  @Test
  public void testFlips()
  {
    int[][] array = { {2, 1, 0, 0, 0, 0},
                      {2, 2, 2, 2, 2, 0} }; 
    int[][] array1 = { {2, 1, 0, 0, 0, 1},
                       {2, 2, 2, 2, 2, 0} };
    int[][] array2 = { {2, 1, 0, 0, 0, 1},
                       {1, 1, 1, 1, 1, 0} };
    
    Reversi rev = new Reversi(2, 6);
    rev.setArray(array);
    rev.setVisible(false);
    
    rev.flips(1, 0, 4, 0, 1, 0);
    assertArrayEquals("Test zero: flips nothing", rev.getArray(), array);
    rev.flips(1, 0, 4, 0, 1, 1);
    assertArrayEquals("Test one: flips one piece", rev.getArray(), array1);
    rev.flips(1, 1, 5, 0, -1, 5);
    assertArrayEquals("Test many: flips 5 pieces", rev.getArray(), array2);
  }
  
  /* test checkTurn */
  @Test
  public void testCheckTurn()
  {
    int[][] array1 = { {2, 2, 2, 2, 2, 2},
                       {2, 2, 2, 2, 2, 0} };
    int[][] array2 = { {2, 2, 2, 1, 2, 0},
                       {2, 2, 2, 2, 2, 2} };
    int[][] array3 = { {1, 2, 0, 2, 2, 1},
                       {2, 1, 2, 0, 2, 0} };
    Reversi rev = new Reversi(2, 6);
    rev.setVisible(false);
    rev.setArray(array1);
    assertEquals("Test zero: there is not any legal move next", rev.checkTurn(1), false);
    
    rev.setArray(array2);
    assertEquals("Test one: there is one legal move next", rev.checkTurn(1), true);
    
    rev.setArray(array3);
    assertEquals("Test many: there are many legal moves next", rev.checkTurn(1), true);
  }
  
  /* test countPiece */
  @Test
  public void testCountPiece()
  {
     int[][] array = { {0, 0, 0, 0, 0},
                       {0, 2, 0, 2, 0},
                       {0, 2, 0, 2, 0},
                       {0, 2, 2, 2, 3} };
     Reversi rev = new Reversi(4, 5);
     rev.setArray(array);
     rev.setVisible(false);
     
     assertEquals("Test zero: there is not any number 1 in the array", rev.countPiece(1), 0);
     assertEquals("Test one: there is one number 3 in the array", rev.countPiece(3), 1);
     assertEquals("Test many: there are many number 2 in the array", rev.countPiece(2), 7);
  }  
}
