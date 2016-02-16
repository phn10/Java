public class StudentAccount extends Account
{
  private String name = " ";        // store student name variable
  private String address = " ";     // store student address variable   
  private double refund = 0;        // store the refund amount
   
  LibraryAccount libraryAccount = null;    // library account
  CreditAccount tuitionAccount = null;     // tuition account 
  CreditAccount diningAccount = null;      // dining account
  
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
    double balance = 0;
    
    if (tuitionAccount != null && refund > 0)
    {
      if (refund > tuitionAccount.getBalance())
      {
        refund -= tuitionAccount.getBalance();
        tuitionAccount.credit(tuitionAccount.getBalance());
      }
      else
      {
        tuitionAccount.credit(refund);
        refund = 0;
      }
    }
    
    if (diningAccount != null && refund > 0)
    {
      if (refund > diningAccount.getBalance())
      {
        refund -= diningAccount.getBalance();
        tuitionAccount.credit(diningAccount.getBalance());
      }
      else
      {
        diningAccount.credit(refund);
        refund = 0;
      }
    }
   
    if (libraryAccount != null)
      balance += libraryAccount.getBalance();
    if (tuitionAccount != null)
      balance += tuitionAccount.getBalance();
    if (diningAccount != null)
      balance += diningAccount.getBalance();
    
    return balance;
  }
  
  // charge the student account
  // if the charge amount is less than refund, the refund will be negated by the difference
  // else if the charge is more than refund, the account balance will be increased by the difference
  public void charge(double charge)
  {
    if (this.refund < charge && tuitionAccount != null)
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
  
  // subtract the student account balance in tuition account, dining account and library account order
  public void credit(double credit)
  {
    if (tuitionAccount != null && credit > 0)
    {
      if (credit > tuitionAccount.getMonthlyPayment())
      {
        credit -= tuitionAccount.getMonthlyPayment();
        tuitionAccount.credit(tuitionAccount.getMonthlyPayment());
      }
      else
      {
        tuitionAccount.credit(credit);
        credit = 0;
      }
    }
    
    if (diningAccount != null && credit > 0)
    {
      if (credit > diningAccount.getMonthlyPayment())
      {
        credit -= diningAccount.getMonthlyPayment();
        diningAccount.credit(diningAccount.getMonthlyPayment());
      }
      else
      {
        diningAccount.credit(credit);
        credit = 0;
      }
    }
    
    if (libraryAccount != null  && credit > 0)
    {   
      if (credit > libraryAccount.getBalance())
      {
        credit -= libraryAccount.getBalance();
        libraryAccount.credit(libraryAccount.getBalance());
      }
      else
      {
        libraryAccount.credit(libraryAccount.getBalance());
        credit = 0;
      }
    }
    
    refund = credit;
  }
}
      }
      else
      {
        tuitionAccount.credit(credit);
      }
    }
}
