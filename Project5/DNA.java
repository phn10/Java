import java.util.ListIterator;

public class DNA extends DoubleLinkedList<DNA.Base>
{
  public enum Base
  {
    A, C, G, T;
  }
  
  @Override
  public String toString()
  {
    StringBuilder str = new StringBuilder();
    ListIterator list = this.iterator();
    
    for (Base b : this)
      str.append((String.valueOf(b)));
    
    String string = str.toString();
    return string;
  } 
  
  public static DNA string2DNA(String s)
  {
    DNA dna = new DNA();
   
    if (s.length() == 0)
      throw new IllegalArgumentException("The String should not be empty");

    try
    {
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
    }
  }  
  
  public static boolean overlaps(DNA dna1, DNA dna2, int n)
  {
    DLNode node1 = dna1.getBack();
    DLNode node2 = dna2.getFront();
   
    if (n > dna1.length() || n > dna2.length())
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
      System.out.println("Should have two string");
      return;
    }
    
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
      System.out.println(sliced12);
      d1.splice(d2, sliced12 - 1);
      System.out.println(d1);
    }
    else
    {
       System.out.println(sliced21);
      d2.splice(d1, sliced21 - 1);
      System.out.println(d2);
    }
  }
}   