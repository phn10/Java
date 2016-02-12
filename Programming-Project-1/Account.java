public class Account
{
  private String account;    // store account number of the account 
  private double balance;    // store the account's balance 
  private int limit;         // store the account balance limit 
  
  // first constructor, receive a String  
  // set the account number field
  public Account(String account)
  {
    this.account = account;
  }
  
  // second constructor, receive a String and a integer
  // set the account number and balance's limit
  public Account(String account, int limit)
  {
    this.account = account;
    this.limit = limit;
  }
  
  // return the account number
  public String getAccountNumber()
  {
    return account;
  }
  
  // return the account balance
  public double getBalance()
  {
    return balance;
  }
  
  // increase the account balance by the charge value
  public void charge(double charge)
  {
    this.balance += charge;
  }
  
  // subtract the account balance by the credit value
  public void credit(double credit)
  {
    this.balance -= credit;
  }
  
  // set the account balance limit
  public void setBalanceLimit(int limit)
  {
    this.limit = limit;
  }
  
  // return the account balance limit
  public int getBalanceLimit()
  {
    return limit;
  }
}
