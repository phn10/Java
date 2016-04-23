// Name: Phong Nguyen
// ID: phn10

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
   * equals method
   */
  public boolean equals(Object obj)
  {
    if (obj instanceof DoubleLinkedList)
    {
      DoubleLinkedList list2 = (DoubleLinkedList)obj;
      Iterator it1 = this.iterator();
      Iterator it2 = list2.iterator();
      
      while(it1.hasNext() && it2.hasNext())
      {
        if (it1.next() != it2.next())
          return false;
      }
      if (it1.hasNext() || it2.hasNext())
        return false;
      else
        return true;
    }
    else
      return false;
  }
  
  /**
   * append a new double link list to this list
   */
  public void append(DoubleLinkedList<T> list)
  {
    this.getBack().setNext(list.getFront());
    list.getFront().setPrevious(this.getBack());
  }

  /**
   * get length
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
      
      // create a next pointer
      private DLNode<T> nodePtr = DoubleLinkedList.this.getFront();
      private DLNode<T> prevPtr;
      private boolean setAvailable = false;
      
      @Override
      public boolean hasNext()
      {
        return nodePtr != null;
      }
      
      @Override
      public T next() 
      {
        if (!this.hasNext())
          throw new NoSuchElementException();
        
        // return the current element and move to the next node
        setAvailable = true;
        T element = nodePtr.getElement();
        nodePtr = nodePtr.getNext();
        
        // setup prevPtr after call next()
        nodePtr.getPrevious().setPrevious(prevPtr);
        prePtr(
        return element;
      }
      
      @Override
      public boolean hasPrevious()
      {
        if (prevPtr != null)
          return true;
        else
          return false;
      }
          
      @Override
      public T previous()
      {
        setAvailable = true;
        T element = prevPtr.getElement();
        prevPtr = prevPtr.getPrevious();
        nodePtr = nodePtr.getPrevious();
        return element;
      }
      
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
          node = new DLNode<T>(element, nodePtr.getPrevious().getPrevious(), nodePtr);
          nodePtr.setPrevious(node);
          nodePtr.getPrevious().getPrevious().setNext(node);
        }
      }
        
      /*
       * replace the element returned by next() or previous() by new element
       */
      @Override
      public void set(T element)
      {
        if (!setAvailable)
          throw new IllegalStateException();
        else
        {
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
      
      @Override
      public void remove()
      {
        setAvailable = false;
        /* if calling remove() before next() */
        if (nodePtr == DoubleLinkedList.this.getFront())
        {
          throw new IllegalStateException();
        }
        
        /* removing the first element */
        if (nodePtr == DoubleLinkedList.this.getFront().getNext())
        {
          DoubleLinkedList.this.setFront(DoubleLinkedList.this.getFront().getNext());
          DoubleLinkedList.this.getFront().setPrevious(null);
        }
        
        /* removing the last element */
        else if (nodePtr == DoubleLinkedList.this.getBack().getNext())
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
