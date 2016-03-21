/**
 * This class has 4 methods: add, subtract, multiply, and divide, between two numbers.
 * @author Phong Nguyen
 */
public class Arithmetic
{
  private static ComplexN num;   // the result of each methods
  private static double real;    // the real part of the number
  private static double img;     // the imaginary part of the number
  
  /**
   * Find the sum of two number 
   * @param num1 the first number
   * @param num2 the second number
   */
  public static ComplexN add(ComplexN num1, ComplexN num2)
  {
    real = num1.getRealPart() + num2.getRealPart();
    img = num1.getImaginaryPart() + num2.getImaginaryPart();
    
    if (num1 instanceof NaturalN && num2 instanceof NaturalN)
      num = new NaturalN((int) real);
    else if (num1 instanceof IntegerN && num2 instanceof IntegerN)
      num = new IntegerN((int)real);
    else if (num1 instanceof RationalN && num2 instanceof RationalN)
    {
      RationalN rat1;          // first rational number
      RationalN rat2;          // second rational number
      int numerator;           // numerator of the sum of two rational numbers
      int denominator;         // denominator of the sum of two rational numbers
      
      rat1 = (RationalN)num1;  // type cast the first input as rational number
      rat2 = (RationalN)num2;  // type cast the second input as rational number
      
      numerator = (int)(rat1.getNumerator() * rat2.getDenominator() + rat2.getNumerator() * rat1.getDenominator());
      denominator = (int)(rat1.getDenominator() * rat2.getDenominator());
      
      num = new RationalN(numerator, denominator);
    }
    else if (num1 instanceof RealN && num2 instanceof RealN)
      num = new RealN(real);
    else if (num1 instanceof ComplexN && num2 instanceof ComplexN)
      num = new ComplexN(real, img);
    
    return num;
  }
  
  /**
   * Find the result when subtract the first number by the second number
   * @param num1 the first number
   * @param num2 the second number
   * @throws ArithmeticException if both number are NaturalN type and the second number is greater than the first number
   */
  public static ComplexN subtract(ComplexN num1, ComplexN num2)
  {
    real = num1.getRealPart() - num2.getRealPart();
    img = num1.getImaginaryPart() - num2.getImaginaryPart();
    
    if (num1 instanceof NaturalN && num2 instanceof NaturalN)
    {
      if (num1.getRealPart() < num2.getRealPart())
        throw new ArithmeticException("Cannot subtract smaller number from a larger one");
      else
        num = new NaturalN((int) real);
    }
    else if (num1 instanceof IntegerN && num2 instanceof IntegerN)
      num = new IntegerN((int)real);
    else if (num1 instanceof RationalN && num2 instanceof RationalN)
    {
      RationalN rat1;            // first rational number
      RationalN rat2;            // second rational number
      int numerator;             // numerator of the sum of two rational numbers
      int denominator;           // denominator of the sum of two rational numbers
      
      rat1 = (RationalN)num1;    // type cast the first input as rational number
      rat2 = (RationalN)num2;    // type cast the second input as rational number
      
      numerator = (int)(rat1.getNumerator() * rat2.getDenominator() - rat2.getNumerator() * rat1.getDenominator());
      denominator = (int)(rat1.getDenominator() * rat2.getDenominator());
      
      num = new RationalN(numerator, denominator);
    }
    else if (num1 instanceof RealN && num2 instanceof RealN)
      num = new RealN(real);
    else if (num1 instanceof ComplexN && num2 instanceof ComplexN)
      num = new ComplexN(real, img);
    
    return num;
  }
  
  /**
   * Find the result when multiply the first number by the second number
   * @param num1 the first number
   * @param num2 the second number
   */
  public static ComplexN multiply(ComplexN num1, ComplexN num2)
  {
    real = num1.getRealPart() * num2.getRealPart() - num1.getImaginaryPart() * num2.getImaginaryPart();
    img = num1.getRealPart() * num2.getImaginaryPart() + num1.getImaginaryPart() * num2.getRealPart();
 
    if (num1 instanceof NaturalN && num2 instanceof NaturalN)
      num = new NaturalN((int) real);
    else if (num1 instanceof IntegerN && num2 instanceof IntegerN)
      num = new IntegerN((int)real);
    else if (num1 instanceof RationalN && num2 instanceof RationalN)
    {
      RationalN rat1;             // first rational number
      RationalN rat2;             // second rational number
      int numerator;              // numerator of the sum of two rational numbers
      int denominator;            // denominator of the sum of two rational numbers
      
      rat1 = (RationalN)num1;     // type cast the first input as rational number
      rat2 = (RationalN)num2;     // type cast the second input as rational number
      
      numerator = rat1.getNumerator() * rat2.getNumerator();
      denominator = rat1.getDenominator() * rat2.getDenominator();
      
      num = new RationalN(numerator, denominator);
    }
    else if (num1 instanceof RealN && num2 instanceof RealN)
      num = new RealN(real);
    else if (num1 instanceof ComplexN && num2 instanceof ComplexN)
      num = new ComplexN(real, img);
    
    return num;
  }
  
  /**
   * Find the result when divide the first number by the second number
   * @param num1 the first number
   * @param num2 the second number
   * @throws ArithmeticException if the divisor equals to 0
   */
  public static ComplexN divide(ComplexN num1, ComplexN num2)
  {
    if (num2.getRealPart() == 0 && num2.getImaginaryPart() == 0)
      throw new ArithmeticException("Cannot divide a number by 0");
    else
    {
      real = (num1.getRealPart() * num2.getRealPart() + num1.getImaginaryPart() * num2.getImaginaryPart()) / 
        (Math.pow(num2.getRealPart(), 2) + Math.pow(num2.getImaginaryPart(), 2));
      img = -(num1.getRealPart() * num2.getImaginaryPart()) + num1.getImaginaryPart() * num2.getRealPart() / 
        (Math.pow(num2.getRealPart(), 2) + Math.pow(num2.getImaginaryPart(), 2));
      
      if (num1 instanceof NaturalN && num2 instanceof NaturalN)
        num = new NaturalN((int) real);
      else if (num1 instanceof IntegerN && num2 instanceof IntegerN)
        num = new IntegerN((int)real);
      else if (num1 instanceof RationalN && num2 instanceof RationalN)
      {
        RationalN rat1;             // first rational number
        RationalN rat2;             // second rational number
        int numerator;              // numerator of the sum of two rational numbers
        int denominator;            // denominator of the sum of two rational numbers
      
        rat1 = (RationalN)num1;     // type cast the first input as rational number
        rat2 = (RationalN)num2;     // type cast the second input as rational number
        
        numerator = rat1.getNumerator() * rat2.getDenominator();
        denominator = rat1.getDenominator() * rat2.getNumerator();
   
        num = new RationalN(numerator, denominator);
      }
      else if (num1 instanceof RealN && num2 instanceof RealN)
        num = new RealN(real);
      else if (num1 instanceof ComplexN && num2 instanceof ComplexN)
        num = new ComplexN(real, img);
    }
    
    return num;
  }
}
