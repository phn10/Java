public class LibraryAccount extends Account
{
  private double bookFine;           // store the amount of fine each day a book is overdue
  private double reserveFine;        // store the amount of fine each day a reserve item is overdue
  private int book;                  // store the number of overdue book 
  private int reserveItem;           // store the number of overdue reserve item 

  
  // first constructor, set the library account number
  public LibraryAccount(String accountNumber)
  {
    super(accountNumber); 
  }
  
  // second contructor, set the library account number, balance limit, book fine and reserve fine
  public LibraryAccount(String accountNumber, int limit, double bookFine, double reserveFine)
  {
    super(accountNumber, limit);
    this.bookFine = bookFine;
    this.reserveFine = reserveFine;
  }
  
  // set the amount of each overdue book fine
  public void setBookFine(double bookFine)
  {
    this.bookFine = bookFine;
  }
  
  // return the amount of each overdue book fine
  public double getBookFine()
  {
    return bookFine;
  }
  
  // set the amount of each overdue reserve item fine
  public void setReserveFine(double reserveFine)
  {
    this.reserveFine = reserveFine;
  }
  
  // return the amount of each overdue reserve item fine
  public double getReserveFine()
  {
    return reserveFine;
  }
  
  // increase the number of overdue books by one
  public void incrementOverdueBooks()
  {
    this.book += 1;
  }
  
  // decrease the number of overdue book by one
  public void decrementOverdueBooks()
  {
    this.book -= 1;
  }
  
  // return the number of overdue book
  public int getNumberOverdueBooks()
  {
    return book;
  }
  
  // increase the number of overdue reserve item by one
  public void incrementOverdueReserve()
  {
    this.reserveItem += 1;
  }
  
  // decrease the number of overdue reserve item by one
  public void decrementOverdueReserve()
  {
    this.reserveItem -= 1;
  }
  
  // return the number of overdue reserve item
  public int getNumberOverdueReserve()
  {
    return reserveItem;
  }
  
  // boolean method returb true if the account can borrow more book
  // return false if the accouun cannot borrow anymore
  public boolean canBorrow()
  {
    if (this.getBalance() < this.getBalanceLimit())
      return true;
    else
      return false;
  }
  
  // increase the account balance by the sum of fine
  public void endOfDay()
  {
    this.charge((book * bookFine) + (reserveItem * reserveFine));
  }
}
