public class RationalN extends RealN 
{
  private int num;
  private int den;
  
  // constructor
  public RationalN(int num, int den)
  {
    super((double)num / (double)den);
    if (den == 0)
    {
      throw new ArithmeticException("An integer cannot be divided by 0");
    } 
    else
    {
      this.num = num;
      this.den = den;
    }
  }
  
  public int getNumerator()
  {
    return num;
  }
  
  public int getDenominator()
  {
    return den;
  }
  
  public static int gcd(int a, int b)
  {
    while (a % b != 0)
    {
      int temp = a;
      a = b;
      b = temp % b;
    }
    return b;
  }
  
  public String toString()
  {
    if (this.getNumerator() == 0)
    {
      return "0";
    }
    else
    {
      if (this.getDenominator() == 1 || this.getDenominator() / this.gcd(this.getNumerator(), this.getDenominator()) == 1)
        return Integer.toString(this.getNumerator() / this.gcd(this.getNumerator(), this.getDenominator()));
      else
      {
        if (this.getNumerator() * (double)this.getDenominator() >= 0)
          return Math.abs(this.getNumerator() / this.gcd(this.getNumerator(), this.getDenominator())) + "/" + 
          Math.abs(this.getDenominator() / this.gcd(this.getNumerator(), this.getDenominator()));
        else
          return "-" + Math.abs(this.getNumerator() / this.gcd(this.getNumerator(), this.getDenominator())) + "/" + 
          Math.abs(this.getDenominator() / this.gcd(this.getNumerator(), this.getDenominator()));
      }
    }
  }
}
