/**
 * This class has 4 methods: add, subtract, multiply, and divide, between two numbers.
 * @author Phong Nguyen
 */
public class Arithmetic
{
  /** FIND SUM **\
   
  /**
   * Find the sum of two natural number 
   * @param num1 the first natural number
   * @param num2 the second natural number
   */ 
  public static NaturalN add(NaturalN num1, NaturalN num2)
  {
    int num = num1.getNumerator() + num2.getNumerator();
    NaturalN nat = new NaturalN(num);
    return  nat;
  }
  
  /**
   * Find the sum of two integer number 
   * @param num1 the first integer number
   * @param num2 the second integer number
   */ 
  public static IntegerN add(IntegerN num1, IntegerN num2)
  {
    int num3 = num1.getNumerator() + num2.getNumerator();
    IntegerN num = new IntegerN(num3);
    return  num;
  }
  
  /**
   * Find the sum of two rational number 
   * @param num1 the first rational number
   * @param num2 the second rational number
   */ 
  public static RationalN add(RationalN num1, RationalN num2)
  {
    int numerator;           // numerator of the sum of two rational numbers
    int denominator;         // denominator of the sum of two rational numbers
      
    numerator = (num1.getNumerator() * num2.getDenominator() + num2.getNumerator() * num1.getDenominator());
    denominator = num1.getDenominator() * num2.getDenominator();
      
    RationalN num = new RationalN(numerator, denominator);
    return num;
  }
  
  /**
   * Find the sum of two real number 
   * @param num1 the first real number
   * @param num2 the second real number
   */ 
  public static RealN add(RealN num1, RealN num2)
  {
    double num3 = num1.getRealPart() + num2.getRealPart();
    RealN num = new RealN(num3);
    return num;
  }
  
  /**
   * Find the sum of two complex number 
   * @param num1 the first complex number
   * @param num2 the second complex number
   */ 
  public static ComplexN add(ComplexN num1, ComplexN num2)
  {
    double real = num1.getRealPart() + num2.getRealPart();
    double img = num1.getImaginaryPart() + num2.getImaginaryPart();
    
    ComplexN num = new ComplexN(real, img);
    return num;
  }
  
  
  /** FIND SUBTRACTION **\
 
  /*
   * Find the subtraction of two natural numbers
   * @param num1 the first natural number
   * @param num2 the second natural number
   * @throws ArithmeticException if the second number is greater than the first number
   */
  public static NaturalN subtract(NaturalN num1, NaturalN num2)
  {
    int num3;
    if(num1.getNumerator() < num2.getNumerator())
      throw new ArithmeticException("Cannot subtract bigger number from smaller number");
    else
      num3 = num1.getNumerator() - num2.getNumerator();
    
    NaturalN num = new NaturalN(num3);
    return num;
  }
  
 /*
   * Find the subtraction of two integer numbers
   * @param num1 the first integer number
   * @param num2 the second integer number
   */
  public static IntegerN subtract(IntegerN num1, IntegerN num2)
  {
    int num3 = num1.getNumerator() - num2.getNumerator(); 
    NaturalN num = new NaturalN(num3);
    return num;
  }
      
  /*
   * Find the subtraction of two rational numbers
   * @param num1 the first rational number
   * @param num2 the second rational number
   */
  public static RationalN subtract(RationalN num1, RationalN num2)
  {
    int numerator;           // numerator of the sum of two rational numbers
    int denominator;         // denominator of the sum of two rational numbers
      
    numerator = (num1.getNumerator() * num2.getDenominator() - num2.getNumerator() * num1.getDenominator());
    denominator = num1.getDenominator() * num2.getDenominator();
      
    RationalN num = new RationalN(numerator, denominator);
    return num;
  }
  
  /*
   * Find the subtraction of two real numbers
   * @param num1 the first real number
   * @param num2 the second real number
   */
  public static RealN subtract(RealN num1, RealN num2)
  {
    double num3 = num1.getRealPart() - num2.getRealPart(); 
    RealN num = new RealN(num3);
    return num;
  }
  
  /*
   * Find the subtraction of two complex numbers
   * @param num1 the first complex number
   * @param num2 the second complex number
   */
  public static ComplexN subtract(ComplexN num1, ComplexN num2)
  {
    double real = num1.getRealPart() - num2.getRealPart();
    double img = num1.getImaginaryPart() - num2.getImaginaryPart();
    
    ComplexN num = new ComplexN(real, img);
    return num;
  }
  
  
  /** FIND MULTIPLICATION **\
  /*
   * Find the multiplication of two natural numbers
   * @param num1 the first natural number
   * @param num2 the second natural number
   */
  public static NaturalN multiply(NaturalN num1, NaturalN num2)
  {
    int num3 = num1.getNumerator() * num2.getNumerator();
    NaturalN num = new NaturalN(num3);
    return num;
  }
  
  /*
   * Find the multiplication of two integer numbres
   * @param num1 the first integer number
   * @param num2 the second integer number
   */
  public static IntegerN multiply(IntegerN num1, IntegerN num2)
  {
    int num3 = num1.getNumerator() * num2.getNumerator();
    IntegerN num = new IntegerN(num3);
    return num;
  }
  
  /*
   * Find the multiplication of two rational numbers
   * @param num1 the first rational number
   * @param num2 the second rational number
   */
  public static RationalN multiply(RationalN num1, RationalN num2)
  {
    int numerator = num1.getNumerator() * num2.getNumerator();
    int denominator = num1.getDenominator() * num2.getDenominator();
    
    RationalN num = new RationalN(numerator, denominator);
    return num;
  }
  
  /*
   * Find the multiplication of two real numbres
   * @param num1 the first real number
   * @param num2 the second real number
   */
  public static RealN multiply(RealN num1, RealN num2)
  {
    double num3 = num1.getRealPart() * num2.getRealPart();
    RealN num = new RealN(num3);
    return num;
  }
  
  /*
   * Find the multiplication of two complex numbers
   * @param num1 the complex number
   * @param num2 the complex number
   */
  public static ComplexN multiply(ComplexN num1, ComplexN num2)
  {
    double real = num1.getRealPart() * num2.getRealPart() - num1.getImaginaryPart() * num2.getImaginaryPart();
    double img = num1.getRealPart() * num2.getImaginaryPart() + num1.getImaginaryPart() * num2.getRealPart();
      
    ComplexN num = new ComplexN(real, img);
    return num;
  }
  
  
  /** FIND DIVISION **\
  
  /*
   * Find division of two natural numbers
   * @param num1 the first natural number
   * @param num2 the second natural number
   * @throws ArithmeticException if the second number, divisor, equals to 0
   */
  public static NaturalN divide(NaturalN num1, NaturalN num2)
  {
    int num3 = num1.getNumerator() / num2.getNumerator();
    if (num2.getNumerator() == 0)
      throw new ArithmeticException("Cannot divide a number by 0");
    
    NaturalN num = new NaturalN(num3);
    return num;
  }
  
  /*
   * Find division of two integer numbers
   * @param num1 the first integer number
   * @param num2 the second integer number
   * @throws ArithmeticException if the second number, divisor, equals to 0
   */
  public static IntegerN divide(IntegerN num1, IntegerN num2)
  {
    int num3 = num1.getNumerator() / num2.getNumerator();
    if (num2.getNumerator() == 0)
      throw new ArithmeticException("Cannot divide a number by 0");
    
    IntegerN num = new IntegerN(num3);
    return num;
  }
  
  /*
   * Find division of two rational numbers
   * @param num1 the first rational number
   * @param num2 the second rational number
   * @throws ArithmeticException if the second number, divisor, equals to 0
   */
  public static RationalN divide(RationalN num1, RationalN num2)
  {
    int numerator = num1.getNumerator() * num2.getDenominator();
    int denominator = num1.getDenominator() * num2.getNumerator();
    
    if (num2.getNumerator() == 0)
      throw new ArithmeticException("Cannot divide a number by 0");
    
    RationalN num = new RationalN(numerator, denominator);
    return num;
  }
  
  /*
   * Find division of two real numbers
   * @param num1 the first real number
   * @param num2 the second real number
   * @throws ArithmeticException if the second number, divisor, equals to 0
   */
  public static RealN divide(RealN num1, RealN num2)
  {
    double num3 = num1.getRealPart() / num2.getRealPart();
    if (num2.getRealPart() == 0)
      throw new ArithmeticException("Cannot divide a number by 0");
    
    RealN num = new RealN(num3);
    return num;
  }
  
  /*
   * Find the division of two complex numbers
   * @param num1 the first complex number
   * @param num2 the second complex number
   * @throws ArithmeticException if the second number, divisor, equals to 0
   */
  public static ComplexN divide(ComplexN num1, ComplexN num2)
  {
    double real = (num1.getRealPart() * num2.getRealPart() + num1.getImaginaryPart() * num2.getImaginaryPart()) / 
        (Math.pow(num2.getRealPart(), 2) + Math.pow(num2.getImaginaryPart(), 2));
    double img = -(num1.getRealPart() * num2.getImaginaryPart()) + num1.getImaginaryPart() * num2.getRealPart() / 
        (Math.pow(num2.getRealPart(), 2) + Math.pow(num2.getImaginaryPart(), 2));
    
    if (num2.getRealPart() == 0 && num2.getImaginaryPart() == 0)
      throw new ArithmeticException("Cannot divide a number by 0");
        
    ComplexN num = new ComplexN(real, img);
    return num;
  }
}
