/**
 * Name: Phong Nguyen
 * ID  : phn10
 */

import java.util.ListIterator;

/**
 * A class represent a DNA array
 */
public class DNA extends DoubleLinkedList<DNA.Base>
{
  /* include all the base of dna array */
  public enum Base
  {
    A, C, G, T;
  }
  
  /**
   * return the string representation of the DNA array
   * @return the string representation of the DNA array
   */
  @Override
  public String toString()
  {
    /* string builder */
    StringBuilder str = new StringBuilder();
    /* list iterator of the dna */
    ListIterator list = this.iterator();
    
    /* iterate through the list and add the char representation of the each element
     * to the string */
    for (Base b : this)
      str.append((String.valueOf(b)));
    
    String string = str.toString();
    return string;
  } 
  
  /**
   * receive a string and convert that string to DNA array
   * @parram s input string
   * @return DNA list representation of the DNA array
   */
  public static DNA string2DNA(String s)
  {
    DNA dna = new DNA();
   
    /* create an warning message when the string is empty */
    if (s.length() == 0)
      throw new IllegalArgumentException("The String should not be empty");

    try
    {
      /* iterate through array and add each element of the string to the dna */
      for (char ch : s.toCharArray())
      {
        dna.addToBack(DNA.Base.valueOf(String.valueOf(ch)));  
      }
    } catch (IllegalArgumentException e) {
      System.err.println("The String should only contains Base character");
      return null;
    }
      
    return dna;
  }

  /* splice two dna array into one 
   * @param dna the second dna
   * @param numbases the number of merged element 
   */
  public void splice(DNA dna, int numbases)
  {
    int i = 0;
    DLNode<DNA.Base> node = dna.getFront();
    
    while (i < numbases && node != null)
    {
      i++;
      node = node.getNext();
    }
    
    if (node != null)
    {
      this.getBack().setNext(node);
      node.setPrevious(this.getBack());
      this.setBack(dna.getBack());
    }
  }  
  
  public static boolean overlaps(DNA dna1, DNA dna2, int n)
  {
    DLNode node1 = dna1.getBack();
    DLNode node2 = dna2.getFront();
   
    if (n > dna1.length() || n > dna2.length() || n == 0)
      return false;
    
    for (int i = 0; i < n - 1; i++)
      node1 = node1.getPrevious();
    
    int j = 0;
    while (j < n - 1 && node1.getElement() == node2.getElement())
    {
      j++;
      node1 = node1.getNext();
      node2 = node2.getNext();
    }
    
    if (node1 != null && node2 != null && node1.getElement() != node2.getElement())
      return false;
   
    return true;
  }
  
  public static void main(String args[])
  {
    if (args.length != 2)
    {
      System.out.println("Error: You should enter two string");
      return;
    }
    
    try 
    {
      DNA d1 = new DNA();
      DNA d2 = new DNA();
      int sliced12 = 0;
      int sliced21 = 0;
      
      d1 = DNA.string2DNA(args[0]);
      d2 = DNA.string2DNA(args[1]);
      
      int index = 1;
      
      while (index <= d1.length() && index <= d2.length())
      {
        if (overlaps(d1, d2, index))
          sliced12 =  index;
        
        if (overlaps(d2, d1, index))
          sliced21 = index;
        index++;
      }
      
      if (sliced12 > sliced21)
      {
        d1.splice(d2, sliced12 - 1);
        System.out.println(d1);
      }
      else
      {
        d2.splice(d1, sliced21 - 1);
        System.out.println(d2);
      }
    } catch (Exception e) 
    { System.out.println("Error: The two DNA strands should only contains DNA Base"); }
  }
}   