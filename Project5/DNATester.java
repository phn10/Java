/**
 * Name: Phong Nguyen
 * ID  : phn10
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * A class that tests the methods of the DNA class
 * @author Phong Nguyen
 */
public class DNATester 
{
  /**
   * test the toString method in DNA class 
   */
  @Test
  public void testToString()
  {
    DNA dna = new DNA();
    
    /* test zero */
    try 
    {
      assertEquals("Test zero: no element on the dna strand", dna.toString(), "");
    } catch (Exception e)
    { /* everythings is fine */ }
    
    dna.addToFront(DNA.Base.A);
    /* test one */
    assertEquals("Test one: test toString failed when there is one element on the dna strand", dna.toString(), "A");
    
    dna.addToBack(DNA.Base.C);
    dna.addToBack(DNA.Base.C);
    dna.addToBack(DNA.Base.T);
    dna.addToBack(DNA.Base.A);
    dna.addToBack(DNA.Base.G);
    /* test many */
    assertEquals("Test many: test toString failed when there are more than one element on the dna strand", dna.toString(), "ACCTAG");
  }
  
  /**
   * test the string2DNA method in DNA class 
   */
  @Test
  public void testString2DNA()
  {
    DNA dna1 = new DNA();
    DNA dna2 = new DNA();
    
    dna1.addToFront(DNA.Base.A);
    dna2 = DNA.string2DNA("A");
    
    /* test one */
    assertEquals("Test one: test string2DNA failed when there is one element on the dna strand", dna1.equals(dna2), true);
    
    dna1.addToBack(DNA.Base.C);
    dna1.addToBack(DNA.Base.G);
    dna1.addToBack(DNA.Base.A);
    dna1.addToBack(DNA.Base.T);
    dna2 = DNA.string2DNA("ACGAT");
    
    /* test many */
    assertEquals("Test one: test string2DNA failed when there are more than one element on the dna strand", dna1.equals(dna2), true);
  }   
  
  /**
   * test the splice method in DNA class 
   */
  @Test
  public void testSplice()
  {
    DNA dna1 = new DNA();
    DNA dna2 = new DNA();
    
    dna1.addToFront(DNA.Base.A);
    dna2.addToFront(DNA.Base.C);
    dna2.addToBack(DNA.Base.G);
    dna2.addToBack(DNA.Base.T);
    dna1.splice(dna2, 0);
    /* test zero
     * cut out zero element from the second dna
     * the first dna should be ACGT
     */
    assertEquals("Test zero: test splice failed to splice zero element from the second dna strand", dna1, DNA.string2DNA("ACGT"));
    
    dna1 = new DNA();
    dna2 = new DNA();
    
    dna1.addToFront(DNA.Base.A);
    dna2.addToFront(DNA.Base.C);
    dna2.addToBack(DNA.Base.G);
    dna2.addToBack(DNA.Base.T);
    dna1.splice(dna2, 1);
    /* test first
     * cut out first element from the second dna
     * the first dna should be AGT
     */
    assertEquals("Test first: test splice failed to splice fisrt element from the second dna strand", dna1, DNA.string2DNA("AGT"));
    
    dna1 = new DNA();
    dna2 = new DNA();
    
    dna1.addToFront(DNA.Base.A);
    dna2.addToFront(DNA.Base.C);
    dna2.addToBack(DNA.Base.G);
    dna2.addToBack(DNA.Base.T);
    dna1.splice(dna2, 2);
    /* test middle
     * cut out second element from the second dna
     * the first dna should be AT
     */
    assertEquals("Test middle: test splice failed to splice middle element from the second dna strand", dna1, DNA.string2DNA("AT"));
    
    dna1 = new DNA();
    dna2 = new DNA();
    
    dna1.addToFront(DNA.Base.A);
    dna2.addToFront(DNA.Base.C);
    dna2.addToBack(DNA.Base.G);
    dna2.addToBack(DNA.Base.T);
    dna1.splice(dna2, 3);
    /* test middle
     * cut out last element from the second dna
     * the first dna should be A
     */
    assertEquals("Test last: test splice failed to splice last element from the second dna strand", dna1, DNA.string2DNA("A"));    
  }
  
  /**
   * test the overlaps method in DNA class
   */
  @Test
  public void testOverLaps()
  {
    DNA dna1 = new DNA();
    DNA dna2 = new DNA();
    
    dna1 = DNA.string2DNA("AAAAA");
    dna2 = DNA.string2DNA("AAAAA");
    
    /* test zero */
    assertEquals("Test zero", DNA.overlaps(dna1, dna2, 0), false);
    
    /*test first */
    assertEquals("Test first: test overlaps failed when the cursor points to the first element", DNA.overlaps(dna1, dna2, 1), true);
    
    /*test middle */
    assertEquals("Test middle: test overlaps failed when the cursor points to the middle element", DNA.overlaps(dna1, dna2, 3), true);
    
    /*test last */
    assertEquals("Test first: test overlaps failed when the cursor points to the first element", DNA.overlaps(dna1, dna2, 5), true);
  }
   
  

}