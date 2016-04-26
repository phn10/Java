/**
 * Name: Phong Nguyen
 * ID  : phn10
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.io.*;

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
    assertEquals("Test many: test string2DNA failed when there are more than one element on the dna strand", dna1.equals(dna2), true);
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
   
  /**
   * test main method in DNA class
   */
  @Test
  public void testMain()
  {
    DNA dna1 = new DNA();
    DNA dna2 = new DNA();
    
    
    /* test zero
     * two strands do not overlap 
     * two strands: AAAAA and GGGG, there is no overlaps
     * expcect the main method printout nothing
     */
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    DNA.main(new String[] {"AAAAA", "GGGG"});

    assertEquals("Test first: the main method failed to splice when only first element overlaps", outContent.toString(), "" + "\n");
    System.setOut(null);      // clear setOut
    
    /* test first
     * only the first element in the second dna strand overlaps the first dna strand 
     * two strands: AGGTCT and TAAAAC, the overlaps part is T
     * expcect the main method printout AGGTCTAAAC
     */
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    DNA.main(new String[] {"AGGTCT", "TAAAAC"});

    assertEquals("Test first: the main method failed to splice when only first element overlaps", outContent.toString(), "AGGTCTAAAAC" + "\n");
    System.setOut(null);      // clear setOut
    
    
    /* test middle
     * the second dna overlaps half of the first dna strand
     * two strands: CGCTCACCTAT and ATAATCGCTC, the overlap part is AT and CGCTC,
     * but since CGCT is bigger than AT, expcect the main method printout AGGTCTAAAC
     */
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    DNA.main(new String[] {"CGCTCACCTAT", "ATAATCGCTC"});

    assertEquals("Test middle: the main method failed to splice at middle element", outContent.toString(), "ATAATCGCTCACCTAT" + "\n");
    System.setOut(null);
    
    
    /* test last
     * the first dna overlaps all of the second dna strand
     * two strands: AAAAAT and AAAA, the overlap part is AAAA
     * expcect the main method printout AAAAAT
     */
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    DNA.main(new String[] {"AAAAAT", "AAAA"});

    assertEquals("Test last: the main method failed to splice to splice at last element", outContent.toString(), "AAAAAT" + "\n");
    System.setOut(null);
  }
}