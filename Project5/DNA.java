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
      System.err.println("Error: The DNA arrays should only contain Base character");
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
    
    /* traverse through the dna array until either passed numbases nodes or the current node is null */
    while (i < numbases && node != null)
    {
      i++;
      node = node.getNext();
    }
    
    /* if the node is not null, append the current node to the last of this node */
    if (node != null)
    {
      this.getBack().setNext(node);
      node.setPrevious(this.getBack());
      this.setBack(dna.getBack());
    }
  }  
  
  /**
   * find the overlaps part between the first dna and the second dna
   * @param dna1 the first dna
   * @param dna2 the second dna
   * @param n the number of match base
   * @return true if two base overlap, false if not
   */
  public static boolean overlaps(DNA dna1, DNA dna2, int n)
  {
    DLNode node1 = dna1.getBack();
    DLNode node2 = dna2.getFront();
   
    /* return false if the number of base is greater than either dnas length */
    if (n > dna1.length() || n > dna2.length() || n == 0)
      return false;
    
    /* get the nth node from the back of the first dna */
    for (int i = 0; i < n - 1; i++)
      node1 = node1.getPrevious();
    
    int j = 0;
    
    /* traverse through the back of first dna and the head of second dna 
     * and compare the element in each corresponding position in each dna
     */
    while (j < n - 1 && node1.getElement() == node2.getElement())
    {
      j++;
      node1 = node1.getNext();
      node2 = node2.getNext();
    }
    
    /* return false if the while loop cannot finish n - 1 steps, which means they are not overlaps */
    if (node1 != null && node2 != null && node1.getElement() != node2.getElement())
      return false;
    
   /* else return true */
    return true;
  }
  
  public static void main(String args[])
  {
    /* print out error if the user enter more or less than two string */
    if (args.length != 2)
    {
      System.out.println("Error: You should enter two strings");
      return;
    }
    
    try 
    {
      DNA d1 = new DNA();
      DNA d2 = new DNA();
      int spliced12 = 0;
      int spliced21 = 0;
      
      d1 = DNA.string2DNA(args[0]);
      d2 = DNA.string2DNA(args[1]);
      
      int index = 1;
      
      /* traverse through two dna and find the maximum number of overlap bases in two case
       * the second dna is appended to the first dna
       * the first dna is appended to the second dna
       */
      while (index <= d1.length() && index <= d2.length())
      {
        if (overlaps(d1, d2, index))
          spliced12 =  index;
        
        if (overlaps(d2, d1, index))
          spliced21 = index;
        index++;
      }
      
      /* splice the second onto the first */
      if (spliced12 > spliced21)
      {
        d1.splice(d2, spliced12);
        System.out.println(d1);
      }
      /* splice the fisrt onto the second */
      else
      {
        d2.splice(d1, spliced21);
        System.out.println(d2);
      }
    } catch (Exception e) {}
  }
}   