public class CreditAccount extends Account
{
  private double rate = 0;                               // store the interest rate 
  private double paidThisMonth = 0;                      // store the amount that has been credited to the account this month 
  private double monthlyPayment = 0;     // store the amount that must be paid to avoid increase in interest
 
  // constructor which set the account number and rate interest 
  public CreditAccount(String account, double rate)
  {
    super(account);
    this.rate = rate;
  }
 
  // set the account interest rate
  public void setInterestRate(double rate)
  {
    this.rate = rate;
  }
  
  // return the account interest rate
  public double getInterestRate()
  {
    return rate;
  }
  
  // return the amount needed to pay in this month
  public double getMonthlyPayment()
  {
    return monthlyPayment;
  }
  
  // return the amount that has been paid this month
  public double getAmountPaidThisMonth()
  {
    return paidThisMonth;
  }
  
  // if money paid this month is less than monthly payment, increase the balance 
  // reset variable for a new month
  public void endOfMonth()
  {
    if (this.monthlyPayment > this.paidThisMonth)
    {
      this.charge(this.getBalance() * rate / 12);
    }
    this.monthlyPayment = this.getBalance();
    this.paidThisMonth = 0;
  }
  
  // decrease the account balance by credit amount and increase the amount paid this month
  public void credit(double credit)
  {
    this.paidThisMonth += credit;
    super.credit(credit);
  }
}
