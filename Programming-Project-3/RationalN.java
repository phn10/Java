/**
 * This class represents a rational number.
 * @author Phong Nguyen
 */
public class RationalN extends RealN 
{
  private int num;          // the numerator of the rational number
  private int den;          // the denominator of the rational number
  
  /**
   * Constructor 
   * @param num the numerator of the rational number
   * @param den the denoninator of the rational number
   * @throws ArithmeticException if the denominator equals to 0
   */
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
  
   /**
   * Get the value of the numerator
   */
  public int getNumerator()
  {
    return num;
  }
  
  /**
   * Get the value of the denominator
   */
  public int getDenominator()
  {
    return den;
  }
  
  /**
   * Get the greatest common divisor between the numerator and denominator
   */
  public int gcd()
  {
    int a = this.getNumerator();         // numerator of the rational number
    int b = this.getDenominator();       // denominator of the rational number
    while (a % b != 0)
    {
      int temp = a;
      a = b;
      b = temp % b;
    }
    return b;
  }
  
  /**
   * Return the string representation of the rational number
   */
  public String toString() 
  {
    if (this.getNumerator() == 0)
    {
      return "0";
    }
    else
    {
      if (this.getDenominator() == 1 || this.getDenominator() / this.gcd() == 1)
        return Integer.toString(this.getNumerator() / this.gcd());
      else
      {
        if (this.getNumerator() * (double)this.getDenominator() >= 0)
          return Math.abs(this.getNumerator() / this.gcd()) + "/" + Math.abs(this.getDenominator() / this.gcd());
        else
          return "-" + Math.abs(this.getNumerator() / this.gcd()) + "/" + Math.abs(this.getDenominator() / this.gcd());
      }
    }
  }
}
