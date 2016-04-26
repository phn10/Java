/**
 * Name: Phong Nguyen
 * ID  : phn10
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * A class that tests the methods of the DoubleLinkedList class
 * @author Phong Nguyen
 */
public class DoubleLinkedListTester {
  
  /**
   * Tests the addToFront method of DoubleLinkedList.
   */
  @Test
  public void testAddToFront() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    list.addToFront(3);
    list.addToFront(2);
    list.addToFront(1);
    DLNode<Integer> head = list.getFront();
    DLNode<Integer> tail = list.getBack();
    
    assertEquals("Testing first node of list", new Integer(1), head.getElement());
    assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
    assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
    assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
    
    assertEquals("Testing node at back of list", new Integer(3), tail.getElement());
    assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
    assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
    assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
  }

  /**
   * Tests the addToBack method of DoubleLinkedList.
   */
  @Test
  public void testAddToBack() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    list.addToBack(1);
    list.addToBack(2);
    list.addToBack(3);
    DLNode<Integer> head = list.getFront();
    DLNode<Integer> tail = list.getBack();
      
    assertEquals("Testing last node of list", new Integer(3), tail.getElement());
    assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
    assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
    assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
    
    assertEquals("Testing node at front of list", new Integer(1), head.getElement());
    assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
    assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
    assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
  }
  
  /**
   * Tests the removeFromFront method of DoubleLinkedList.
   */
  @Test
  public void testRemoveFromFront() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    list.addToFront(1);
    list.addToFront(2);
    list.addToFront(3);
    assertEquals("Removing element of list", new Integer(3), list.removeFromFront());
    assertEquals("Removing a second element", new Integer(2), list.removeFromFront());
    assertEquals("Removing a third element", new Integer(1), list.removeFromFront());
    assertEquals("Removed last element of list", true, list.isEmpty());
    try {
      list.removeFromFront();
      fail("Removing from empty list did not throw an exception.");
    }
    catch (java.util.NoSuchElementException e) {
      /* everything is good */
    }
    catch (Exception e) {
      fail("Removing from empty list threw the wrong type of exception.");
    }
    
    list.addToBack(6);
    list.addToBack(7);
    assertEquals("Removing element added to back of list", new Integer(6), list.removeFromFront());
    assertEquals("Removing second element added to back", new Integer(7), list.removeFromFront());
  }
  
  /**
   * Tests the removeFromBack method of DoubleLinkedList.
   */
  @Test
  public void testRemoveFromBack() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    list.addToBack(5);
    list.addToFront(4);
    list.addToBack(6);
    assertEquals("Removing element from back of list", new Integer(6), list.removeFromBack());
    assertEquals("Removing second element from back of list", new Integer(5), list.removeFromBack());
    assertEquals("Removing element from back that was added to front", new Integer(4), list.removeFromBack());
    assertEquals("Removing last element of list", true, list.isEmpty());
    try {
      list.removeFromBack();
      fail("Removing from empty list did not throw an exception.");
    }
    catch (java.util.NoSuchElementException e) {
      /* everything is good */
    }
    catch (Exception e) {
      fail("Removing from empty list threw the wrong type of exception.");
    }
  }
  
  /**
   * Tests the linked list iterator.
   */
  @Test
  public void testListIterator() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    for (int i = 5; i > 0; i--)
      list.addToFront(i);
    
    /* checking that we get out what we put it */
    int i = 1;
    for (Integer x: list)
      assertEquals("Testing value returned by iterator", new Integer(i++), x);
    
    if (i != 6)
      fail("The iterator did not run through all the elements of the list");
  }
  
  /**
   * Tests the remove feature of the linked list iterator.
   */
  @Test
  public void testListIteratorRemove() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    for (int i = 5; i > 0; i--)
      list.addToFront(i);
    
    /* testing removing the first element through the iterator */
    Iterator<Integer> listIterator = list.iterator();
    listIterator.next();
    listIterator.remove();
    
    /* the list should now be 2 through 5 */
    int i = 2;
    for (Integer x: list)
      assertEquals("The iterator failed when removing the first element", new Integer(i++), x);
    if (i != 6)
      fail("The iterator failed when removing the first element");
    
    /* testing removing element 3 */
    listIterator = list.iterator();
    listIterator.next();
    listIterator.next();
    listIterator.remove();
    
    DLNode<Integer> head = list.getFront();
    DLNode<Integer> tail = list.getBack();
    
    assertEquals("Iterator failed to remove middle element", new Integer(2), head.getElement());
    assertEquals("Iterator failed to remove middle element", new Integer(4), head.getNext().getElement());
    assertEquals("Iterator failed to remove middle element", new Integer(5), head.getNext().getNext().getElement());
    assertEquals("Iterator failed to remove middle element", tail.getNext(), head.getNext().getNext().getNext());
    
    assertEquals("Iterator failed to remove middle element", new Integer(5), tail.getElement());
    assertEquals("Iterator failed to remove middle element", new Integer(4), tail.getPrevious().getElement());
    assertEquals("Iterator failed to remove middle element", new Integer(2), tail.getPrevious().getPrevious().getElement());
    assertEquals("Iterator failed to remove middle element", head.getPrevious(), tail.getPrevious().getPrevious().getPrevious());
    
    /* testing removing the last element */
    while (listIterator.hasNext())
      listIterator.next();
    listIterator.remove();
    
    head = list.getFront();
    tail = list.getBack();
    
    assertEquals("Iterator failed to remove middle element", new Integer(2), head.getElement());
    assertEquals("Iterator failed to remove middle element", new Integer(4), head.getNext().getElement());
    assertEquals("Iterator failed to remove middle element", null, head.getNext().getNext());
    
    assertEquals("Iterator failed to remove middle element", new Integer(4), tail.getElement());
    assertEquals("Iterator failed to remove middle element", new Integer(2), tail.getPrevious().getElement());
    assertEquals("Iterator failed to remove middle element", null, tail.getPrevious().getPrevious());
    
    /* testing removing before calling next */
    listIterator = list.iterator();
    try {
      listIterator.remove();
      fail("Calling iterator's remove() before calling next() should throw an IllegalStateException");
    }
    catch (IllegalStateException e) {
      // all is good
    }
    catch (Exception e) {
      fail("The wrong exception thrown by the iterator remove() method.");
    }
  }
  
  /*------------------------------------------------------*/
  /* THIS IS THE PART I WORTE FOR THE TESTING THE PROJECT */
  /*------------------------------------------------------*/
  
  /* test equals() method of DoubleLinkedList*/
  @Test
  public void testEquals()
  {
    DoubleLinkedList<Integer> list1 = new DoubleLinkedList<Integer>();
    DoubleLinkedList<Integer> list2 = new DoubleLinkedList<Integer>();
    String l = new String("this is a string");
    
    assertEquals("Test zero: there is no element in both list", list1.equals(list2), false);
    
    list1.addToFront(1);
    list2.addToFront(1);
    assertEquals("Test one: there is one element in both list", list1.equals(list2), true);
    
    /* test conflict types */
    assertEquals("Test confilct types: DoubleLinkedList cannot be compared to String", list1.equals(l), false);
    
    list1.addToFront(2);
    list2.addToFront(2);
    /* test many */
    assertEquals("Test many: there are two element in both list", list1.equals(list2), true);
    
    list1.addToFront(4);
    list2.addToFront(4);
    /* test many */
    assertEquals("Test many: there are three element in both list", list1.equals(list2), true);
    
    list2.addToBack(5);
    /* test many */
    assertEquals("Test many: two lists have different length", list1.equals(list2), false);
    
    list1 = new DoubleLinkedList<Integer>();
    list2 = new DoubleLinkedList<Integer>();
    
    list1.addToFront(1);
    list1.addToBack(2);
    list1.addToBack(3);
    list1.addToBack(4);
    
    list2.addToFront(5);
    list2.addToBack(2);
    list2.addToBack(3);
    list2.addToBack(4);
    
    /* test first: the first elements in two list are different */
    /* 1234 and 5234 */
    assertEquals("Test first: the first elements in two list are different", list1.equals(list2), false);
    
    
    list1 = new DoubleLinkedList<Integer>(); 
    list2 = new DoubleLinkedList<Integer>();
    
    list1.addToFront(1);
    list1.addToBack(2);
    list1.addToBack(3);
    list1.addToBack(4);
    
    list2.addToFront(1);
    list2.addToBack(2);
    list2.addToBack(5);
    list2.addToBack(4);
    
    /* the last elements in two list are different */
    /* 1234 and 1254 */
    assertEquals("Test middle: the middle elements in two list are different", list1.equals(list2), false);
    
    
    list1 = new DoubleLinkedList<Integer>();
    list2 = new DoubleLinkedList<Integer>();
    
    list1.addToFront(1);
    list1.addToBack(2);
    list1.addToBack(3);
    list1.addToBack(4);
    
    list2.addToFront(1);
    list2.addToBack(2);
    list2.addToBack(5);
    list2.addToBack(4);
    
    /* the last elements in two list are different */
    /* 1234 and 1235 */
    assertEquals("Test last: the last elements in two list are different", list1.equals(list2), false);
  }
  
  /* test append() method of DoubleLinkedList */
  @Test
  public void testAppend()
  {
    DoubleLinkedList<Integer> list1 = new DoubleLinkedList<Integer>();
    DoubleLinkedList<Integer> list2 = new DoubleLinkedList<Integer>();
    DoubleLinkedList<Integer> list3 = new DoubleLinkedList<Integer>();
    
    list1.addToFront(1);    // add 1 to the front of list1
    list2.addToFront(2);    // add 2 to the front of list2
    list1.append(list2);    // append list2 to list1
    list3.addToFront(1);    // add 1 and 2 to list3
    list3.addToBack(2);
    
    assertEquals("Test many: there are more than one element in the list", list1.equals(list3), true);
  }
  
  /* test length() method of DoubleLinkedList */
  @Test
  public void testLength()
  {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    assertEquals("Test zero: there is none element in each list", list.length(), 0);
    
    list.addToFront(1);
    assertEquals("Test one: there is one element in each list", list.length(), 1);
    
    list.addToFront(2);
    list.addToFront(3);
    assertEquals("Test many: there are many element in each list", list.length(), 3);
  }
  
  /* test two new features of the listIterator 
   * including: previous(), hasPrevious()*/
  @Test
  public void testNextandPreviousIterator()
  {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
    for (int i = 5; i > 0; i--)
      list.addToFront(i);
    
    ListIterator<Integer> it = list.iterator();
    
    /* test next() and hasNext() method */
    assertEquals("Test first element", it.hasNext(), true);
    assertEquals("Test first element", (int)it.next(), 1);
    assertEquals("Test middle element", it.hasNext(), true);
    assertEquals("Test middle element", (int)it.next(), 2);
    assertEquals("Test middle element", it.hasNext(), true);
    assertEquals("Test middle element", (int)it.next(), 3);
    assertEquals("Test middle element", it.hasNext(), true);
    assertEquals("Test middle element", (int)it.next(), 4);
    assertEquals("Test middle element", it.hasNext(), true);
    assertEquals("Test last element", (int)it.next(), 5);
    assertEquals("Test last element", it.hasNext(), false);
    
    /*test previous() and hasPrevious() method */
    assertEquals("Test last element", it.hasPrevious(), true);
    assertEquals("Test last element", (int)it.previous(), 4);
    assertEquals("Test middle element", it.hasPrevious(), true);
    assertEquals("Test middle element", (int)it.previous(), 3);
    assertEquals("Test middle element", it.hasPrevious(), true);
    assertEquals("Test middle element", (int)it.previous(), 2);
    assertEquals("Test middle element", it.hasPrevious(), true);
    assertEquals("Test first element", (int)it.previous(), 1);
    assertEquals("Test first element", it.hasPrevious(), false);
    
    /* special condition: when the list has only two element */
    list = new DoubleLinkedList<Integer>();
    list.addToFront(1);
    list.addToBack(2);
    it = list.iterator();
    
    assertEquals("Special condition: test first", new Integer(1), it.next());
    assertEquals("Special condition: test last", new Integer(2), it.next());
    assertEquals("Special condition: test last", false, it.hasNext());
    
    assertEquals("Special condition: test first", new Integer(1), it.previous());
    assertEquals("Special condition: test first", false, it.hasPrevious());
  }   
  
  /* test the set() features of the listIterator */
   @Test
   public void testSetListIterator()
   {
     DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
     for (int i = 5; i > 0; i--)
       list.addToFront(i);
     
     ListIterator<Integer> listIterator = list.iterator();
     
     /* set the first element */
     listIterator.next();
     listIterator.set(6);
     
     /* the list now should be 6 2 3 4 5 */
     int i = 2;
     assertEquals("The iterator failed when setting the first element", new Integer(6), list.getFront().getElement());
     assertEquals("The iterator failed when setting the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when setting the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when setting the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when setting the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when setting the first element", false , listIterator.hasNext());
     
     

     /* testing setting element 3  to 12 */ 
     listIterator = list.iterator();
     listIterator.next();
     listIterator.next();
     listIterator.next();
     listIterator.set(12);
     
     DLNode<Integer> head = list.getFront();
     DLNode<Integer> tail = list.getBack();
     
     /* the list now should be 6 2 12 4 5 */
     assertEquals("Iterator failed to set middle element", new Integer(6), head.getElement());
     assertEquals("Iterator failed to set middle element", new Integer(2), head.getNext().getElement());
     assertEquals("Iterator failed to set middle element", new Integer(12), head.getNext().getNext().getElement());
     assertEquals("Iterator failed to set middle element", new Integer(4), head.getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to set middle element", new Integer(5), head.getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to set middle element", null, head.getNext().getNext().getNext().getNext().getNext());
     
     assertEquals("Iterator failed to set middle element", new Integer(5), tail.getElement());
     assertEquals("Iterator failed to set middle element", new Integer(4), tail.getPrevious().getElement());
     assertEquals("Iterator failed to set middle element", new Integer(12), tail.getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to set middle element", new Integer(2), tail.getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to set middle element", new Integer(6), tail.getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to set middle element", null, tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious());
     
     /* testing setting the last element */ 
     while (listIterator.hasNext())
       listIterator.next();
     listIterator.set(33);
     
     head = list.getFront();
     tail = list.getBack();
     
     /* the list now should be 6 2 12 4 33 */
     assertEquals("Iterator failed to set last element", new Integer(6), head.getElement());
     assertEquals("Iterator failed to set last element", new Integer(2), head.getNext().getElement());
     assertEquals("Iterator failed to set last element", new Integer(12), head.getNext().getNext().getElement());
     assertEquals("Iterator failed to set last element", new Integer(4), head.getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to set last element", new Integer(33), head.getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to set last element", null, head.getNext().getNext().getNext().getNext().getNext());
     
     assertEquals("Iterator failed to set last element", new Integer(33), tail.getElement());
     assertEquals("Iterator failed to set last element", new Integer(4), tail.getPrevious().getElement());
     assertEquals("Iterator failed to set last element", new Integer(12), tail.getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to set last element", new Integer(2), tail.getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to set last element", new Integer(6), tail.getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to set last element", null, tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious());
     
     /* testing setting before calling next() */ 
     listIterator = list.iterator();
     try {
       listIterator.set(33);
       fail("Calling iterator's set() before calling next() should throw an IllegalStateException");
     }
     catch (IllegalStateException e) {
       // all is good
     }
     catch (Exception e) {
       fail("The wrong exception thrown by the iterator remove() method.");
     }
     
     /* testing setting after calling add() or remove() */
     listIterator = list.iterator();
     try {
       listIterator.next();
       listIterator.add(10);
       listIterator.remove(); 
       listIterator.set(33);
       fail("Calling iterator's set() after calling neither add() or remove() should throw an IllegalStateException");
     }
     catch (IllegalStateException e) {
       // all is good
     }
     catch (Exception e) {
       fail("The wrong exception thrown by the iterator remove() method.");
     }
   }
   
   /* test the add() features of the listIterator */
   @Test
   public void testAddListIterator()
   {
     DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
     for (int i = 5; i > 0; i--)
       list.addToFront(i);
     
     ListIterator<Integer> listIterator = list.iterator();
     
     /* add the first element after calling next()*/
     listIterator.next();
     listIterator.add(6);
     
     /* the list now should be 6 1 2 3 4 5 */
     int i = 1;
     listIterator = list.iterator();
     assertEquals("The iterator failed when adding the first element", new Integer(6), listIterator.next());
     assertEquals("The iterator failed when adding the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when adding the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when adding the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when adding the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when adding the first element", new Integer(i++), listIterator.next());
     assertEquals("The iterator failed when adding the first element", false , listIterator.hasNext());
     
     /* testing adding 12 after third element */
     listIterator = list.iterator();
     listIterator.next();
     listIterator.next();
     listIterator.next();
     listIterator.add(12);
     
     DLNode<Integer> head = list.getFront();
     DLNode<Integer> tail = list.getBack();
     
     /* the list now should be 6 1 2 12 3 4 5 */
     assertEquals("Iterator failed to add middle element", new Integer(6), head.getElement());
     assertEquals("Iterator failed to add middle element", new Integer(1), head.getNext().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(2), head.getNext().getNext().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(12), head.getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(3), head.getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(4), head.getNext().getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(5), head.getNext().getNext().getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add middle element", null, head.getNext().getNext().getNext().getNext().getNext().getNext().getNext());
     
     assertEquals("Iterator failed to add middle element", new Integer(5), tail.getElement());
     assertEquals("Iterator failed to add middle element", new Integer(4), tail.getPrevious().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(3), tail.getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(12), tail.getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(2), tail.getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(1), tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add middle element", new Integer(6), tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add middle element", null, tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious());
     
     /* testing removing the last element */
     while (listIterator.hasNext())
       listIterator.next();
     listIterator.add(21);
     
     head = list.getFront();
     tail = list.getBack();
     
     /* the list now should be 6 1 2 12 3 4 5 21 */  
     assertEquals("Iterator failed to add last element", new Integer(6), head.getElement());
     assertEquals("Iterator failed to add lsat element", new Integer(1), head.getNext().getElement());
     assertEquals("Iterator failed to add last element", new Integer(2), head.getNext().getNext().getElement());
     assertEquals("Iterator failed to add last element", new Integer(12), head.getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add last element", new Integer(3), head.getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add last element", new Integer(4), head.getNext().getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add last element", new Integer(5), head.getNext().getNext().getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add last element", new Integer(21), head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getElement());
     assertEquals("Iterator failed to add last element", null, head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());

     assertEquals("Iterator failed to add last element", new Integer(21), tail.getElement());
     assertEquals("Iterator failed to add last element", new Integer(5), tail.getPrevious().getElement());
     assertEquals("Iterator failed to add last element", new Integer(4), tail.getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add last element", new Integer(3), tail.getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add last element", new Integer(12), tail.getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add last element", new Integer(2), tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add last element", new Integer(1), tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add last element", new Integer(6), tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getElement());
     assertEquals("Iterator failed to add last element", null, tail.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious());  
   }
}