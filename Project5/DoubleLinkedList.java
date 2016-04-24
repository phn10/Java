/**
 * Name: Phong Nguyen
 * ID  : phn10
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked linked list.
 */
public class DoubleLinkedList<T> implements Iterable<T> {
  /** a reference to the first node of the double linked list */
  private DLNode<T> front;
  
  /** a reference to the last node of a double linked list */
  private DLNode<T> back;
  
  /** Create an empty double linked list. */
  public DoubleLinkedList() {
    front = back = null;
  }
  
  /** 
   * Returns true if the list is empty.
   * @return  true if the list has no nodes
   */
  public boolean isEmpty() {
    return (getFront() == null);
  }
  
  /**
   * Returns the reference to the first node of the linked list.
   * @return the first node of the linked list
   */
  protected DLNode<T> getFront() {
    return front;
  }
  
  /**
   * Sets the first node of the linked list.
   * @param node  the node that will be the head of the linked list.
   */
  protected void setFront(DLNode<T> node) {
    front = node;
  }
  
  /**
   * Returns the reference to the last node of the linked list.
   * @return the last node of the linked list
   */
  protected DLNode<T> getBack() {
    return back;
  }
  
  /**
   * Sets the last node of the linked list.
   * @param node the node that will be the last node of the linked list
   */
  protected void setBack(DLNode<T> node) {
    back = node;
  }
  
  /*----------------------------------------*/
  /* METHODS TO BE ADDED DURING LAB SESSION */
  /*----------------------------------------*/
  
  /**
   * Add an element to the head of the linked list.
   * @param element  the element to add to the front of the linked list
   */
  public void addToFront(T element) {
    if (this.isEmpty())
    {
      DLNode<T> newNode = new DLNode<T>(element, null, null);
      this.setFront(newNode);
      this.setBack(newNode);
    }
    else
    {
      DLNode<T> newNode = new DLNode<T>(element, null, this.getFront());
      this.getFront().setPrevious(newNode);
      this.setFront(newNode);
    }
  }
  
  /**
   * Add an element to the tail of the linked list.
   * @param element  the element to add to the tail of the linked list
   */
  public void addToBack(T element) {
    if (this.isEmpty())
    {
      DLNode<T> newNode = new DLNode<T>(element, null, null);
      this.setFront(newNode);
      this.setBack(newNode);
    }
    else
    {
      DLNode<T> newNode = new DLNode<T>(element, this.getBack(), null);
      this.getBack().setNext(newNode);
      this.setBack(newNode);
    }
  }
  
  /**
   * Remove and return the element at the front of the linked list.
   * @return the element that was at the front of the linked list
   * @throws NoSuchElementException if attempting to remove from an empty list
   */
  public T removeFromFront() {
    if (this.isEmpty())
    {
      throw new NoSuchElementException();
    }
    else
    {
      T element = this.getFront().getElement();
      this.setFront(this.getFront().getNext());
      return element;
    }
  }
  
  /**
   * Remove and return the element at the back of the linked list.
   * @return the element that was at the back of the linked list
   * @throws NoSuchElementException if attempting to remove from an empty list
   */
  public T removeFromBack() {
    if (this.isEmpty())
    {
      throw new NoSuchElementException();
    }
    else
    {
      T element = this.getBack().getElement();
      this.setBack(this.getBack().getPrevious());
      
      if (this.getBack() == null)
        this.setFront(null);
      
      return element;
    }
  }
  
  
  /**
   * Compare the two double linked list
   * @param obj the compared linked list
   * @return true if two list are equal, false if not
   */
  @Override
  public boolean equals(Object obj)
  { 
    /* if obj is DoubleLinkedList */
    if (obj instanceof DoubleLinkedList)
    {
      /* change obj current type to DoubleLinkedList */
      DoubleLinkedList list2 = (DoubleLinkedList)obj; 
      
      /* if either list is empty, return false */
      if (this.isEmpty() || list2.isEmpty())
        return false;
      
      Iterator it1 = this.iterator();
      Iterator it2 = list2.iterator();
      
      /* iterate through list1 and list2 
       * and compare the element at the same position of two list */
      while(it1.hasNext() && it2.hasNext())
      {
        if (it1.next() != it2.next())
          return false;
      }
      
      /* if one of the list have element, return false */
      if (it1.hasNext() || it2.hasNext())
        return false;
      else
        return true;
    }
    else
      return false;
  }
  
  /**
   * append the second list to the first list which calls this method
   * @param list the second list
   */
  public void append(DoubleLinkedList<T> list)
  {
    this.getBack().setNext(list.getFront());
    list.getFront().setPrevious(this.getBack());
    this.setBack(list.getBack());
  }
  
  /**
   * get number of nodes in this list
   * @return the number of nodes in this list
   */
  public int length()
  {
    int i = 0;
    DLNode<T> nodePtr = this.getFront();
    while (nodePtr != null)
    {
      i++;
      nodePtr = nodePtr.getNext();
    }
    
    return i;
  }
  
  /**
   * Returns an interator for the linked list that starts at the head of the list and runs to the tail.
   * @return  the iterator that runs through the list from the head to the tail
   */
  @Override
  public ListIterator<T> iterator() {
    return new ListIterator<T>() {
      
      /* create a new node point to the first node of this list */
      private DLNode<T> nodePtr = DoubleLinkedList.this.getFront();
      
      /* user can only call set() and remove() when setAvailable is true */
      private boolean setAvailable = false;
      
      /* isBack determines if the nodePtr points to the end of the list */
      private boolean isBack = false;
      
      /**
       * determine the possibility of moving next  
       * @return true if can move next, false if not
       */
      @Override
      public boolean hasNext()
      {
        return !isBack;
      }
      
      /**
       * return the element of the next node
       * @return the element of the next node
       */
      @Override
      public T next() 
      {
        if (!this.hasNext())
          throw new NoSuchElementException();
        
        /* if the node pointer points to the back node */
        if (nodePtr == DoubleLinkedList.this.getBack())
          isBack = true;
        
        setAvailable = true;
        
        // return the element of nodePtr and move nodePtr to the next node
        T element = nodePtr.getElement();
        
        if (!isBack)
          nodePtr = nodePtr.getNext();
        
        return element;
      }
      
      /**
       * determine the possibility of moving backward  
       * @return true if can move back, false if not
       */
      @Override
      public boolean hasPrevious()
      {
        if (nodePtr.getPrevious() == null || nodePtr.getPrevious().getPrevious() == null)
          return false;
        else
          return true;
      }
      
      /**
       * return the element of the previous node
       * @return the element of the previous node
       */
      @Override
      public T previous()
      {
        if (!this.hasPrevious())
          throw new NoSuchElementException();
        
        setAvailable = true;
        
        // return the element of the previous two node of nodePtr and move nodePtr to previous node
        
        T element;
        if (isBack)
          element = nodePtr.getPrevious().getElement();
        else
        {
          element = nodePtr.getPrevious().getPrevious().getElement();
          nodePtr = nodePtr.getPrevious();
        }
        /* the nodePtr doesn't point to the last node anymore */
        isBack = false;

        return element;
      }
      
      /**
       * add one new element to the list, the node contains this element must follow the API rules
       * @param element the element user want to add
       */
      @Override
      public void add(T element)
      {
        setAvailable = false;
        DLNode<T> node;
        // case 1: add before the list
        if (!this.hasPrevious() || DoubleLinkedList.this.isEmpty())
          DoubleLinkedList.this.addToFront(element);
        
        // case 2: add at the end of the list
        else if (!this.hasNext())
          DoubleLinkedList.this.addToBack(element);
        
        // case 3: add to the middle
        else
        {
          node = new DLNode<T>(element, nodePtr.getPrevious(), nodePtr);
        }
      }
      
      /**
       * replace the element of the current node (the node immediately before nodePtr) by new element
       * @param element the element user want to set
       */
      @Override
      public void set(T element)
      {
        if (!setAvailable)
          throw new IllegalStateException();
        else
        {
          if (nodePtr.getNext() == null)
            nodePtr.setElement(element);
          else
            nodePtr.getPrevious().setElement(element);
        }
      }
      
      @Override
      public int nextIndex()
      {
        throw new UnsupportedOperationException();
      }
      
      @Override
      public int previousIndex()
      { 
        throw new UnsupportedOperationException();
      }
      
      /**
       * remove the node immediately before the nodePtr
       */
      @Override
      public void remove()
      {
        setAvailable = false;
        
        /* if calling remove() before next() */
        if (nodePtr == DoubleLinkedList.this.getFront())
        {
          throw new IllegalStateException();
        }
        
        /* we have to divide into 3 cases:
         * case 1: remove the first element
         * case 2: remove the last eleemnt
         * case 3: remove the middle element
         */
        
        /* case 1: remove the first element */
        if (nodePtr == DoubleLinkedList.this.getFront().getNext())
        {
          DoubleLinkedList.this.setFront(DoubleLinkedList.this.getFront().getNext());
          DoubleLinkedList.this.getFront().setPrevious(null);
        }
        
        /* case 2: remove the last element */
        else if (nodePtr == DoubleLinkedList.this.getBack())
        {
          DoubleLinkedList.this.setBack(DoubleLinkedList.this.getBack().getPrevious());
          DoubleLinkedList.this.getBack().setNext(null);
        }
        
        /* removing middle element */
        else
        {
          nodePtr.getPrevious().getPrevious().setNext(nodePtr);
          nodePtr.setPrevious(nodePtr.getPrevious().getPrevious());
        }
      }
    };
  }
}