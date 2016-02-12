public class StudentAccount extends Account
{
  private String name = " ";        // store student name variable
  private String address = " ";     // store student address variable
  private double balance = 0;       // store student balance
  private double refund = 0;        // store the refund amount
   
  LibraryAccount libraryAccount;    // library account
  CreditAccount tuitionAccount;     // tuition account 
  CreditAccount diningAccount;      // dining account
  
  // class constructor set student account and student name
  public StudentAccount(String account, String name)
  {
    super(account);
    this.name = name;
  }
  
  // modify the student's name 
  public void setName(String name)
  {
    this.name = name;
  }
  
  // return the student' name
  public String getName()
  {
    return name;
  }
  
  // modify the students' address 
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  // return the student's address
  public String getAddress()
  {
    return address;
  }
  
  // modify the libraryAccount object
  public void setLibraryAccount(LibraryAccount library) 
  {
    libraryAccount = library;
  }
  
  // return the libraryAccount object 
  public LibraryAccount getLibraryAccount()
  {
    return libraryAccount;
  }
  
  // modify the tuitionAccount object
  public void setTuitionAccount(CreditAccount tuition)
  {
    tuitionAccount = tuition;
  }
  
  // return the tuitionAccount object
  public CreditAccount getTuitionAccount()
  {
    return tuitionAccount;
  }
  
  // modify the diningAccount object
  public void setDiningAccount(CreditAccount dining)
  {
    diningAccount = dining;
  }
  
  // return the diningAccount object
  public CreditAccount getDiningAccount()
  {
    return diningAccount;
  }
  
  // return the student's account balance after the refund
  public double getBalance()
  {
    balance = libraryAccount.getBalance() + 
              tuitionAccount.getBalance() + 
              diningAccount.getBalance();
    return balance - refund;
  }
  
  // charge the student account
  // if the charge amount is less than refund, the refund will be negate by the difference
  // else if the charge is more than refund, the account balance will be increased by the difference
  public void charge(double charge)
  {
    if (this.refund < charge)
    {
      charge -= this.refund;
      this.refund = 0;
      tuitionAccount.charge(charge);
    }
    else
    {
      this.refund -= charge;
    }
  }
    
  public void credit(double credit)
  {
    if (credit > tuitionAccount.getBalance())
    {
      credit -= tuitionAccount.getBalance();
      tuitionAccount.credit(tuitionAccount.getBalance());
        
      if (credit > diningAccount.getBalance())
      {
        credit -= diningAccount.getBalance();
        diningAccount.credit(diningAccount.getBalance());
          
        if (credit > libraryAccount.getBalance())
        {
           credit -= libraryAccount.getBalance();
           libraryAccount.credit(libraryAccount.getBalance());
           refund = credit;
          }
           else
           {
             libraryAccount.credit(credit);
           }
          }
        else
        {
          diningAccount.credit(credit);
        } 
      }
      else
      {
        tuitionAccount.credit(credit);
      }
    }
}
