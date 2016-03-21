public class Arithmetic
{
  private static ComplexN num;
  private static double real;
  private static double img;
  
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
      RationalN rat1 = (RationalN)num1;
      RationalN rat2 = (RationalN)num2;
      double denominator = rat1.getDenominator() * (rat2.getDenominator() / RationalN.gcd(rat1.getDenominator(), rat2.getDenominator()));
      double numerator = rat1.getNumerator() * (rat2.getDenominator() / RationalN.gcd(rat1.getDenominator(), rat2.getDenominator())) +
        rat2.getNumerator() * (rat1.getDenominator() / RationalN.gcd(rat1.getDenominator(), rat2.getDenominator()));
      num = new RationalN((int)numerator, (int)denominator);
    }
    else if (num1 instanceof RealN && num2 instanceof RealN)
      num = new RealN(real);
    else if (num1 instanceof ComplexN && num2 instanceof ComplexN)
      num = new ComplexN(real, img);
    
    return num;
  }
  
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
      RationalN rat1 = (RationalN)num1;
      RationalN rat2 = (RationalN)num2;
      double denominator = rat1.getDenominator() * (rat2.getDenominator() / RationalN.gcd(rat1.getDenominator(), rat2.getDenominator()));
      double numerator = rat1.getNumerator() * (rat2.getDenominator() / RationalN.gcd(rat1.getDenominator(), rat2.getDenominator())) -
        rat2.getNumerator() * (rat1.getDenominator() / RationalN.gcd(rat1.getDenominator(), rat2.getDenominator()));
      num = new RationalN((int)numerator, (int)denominator);
    }
    else if (num1 instanceof RealN && num2 instanceof RealN)
      num = new RealN(real);
    else if (num1 instanceof ComplexN && num2 instanceof ComplexN)
      num = new ComplexN(real, img);
    
    return num;
  }
  
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
      RationalN rat1 = (RationalN)num1;
      RationalN rat2 = (RationalN)num2;
      double denominator = rat1.getDenominator() * rat2.getDenominator();
      double numerator = rat1.getNumerator() * rat2.getNumerator();
      num = new RationalN((int)numerator, (int)denominator);
    }
    else if (num1 instanceof RealN && num2 instanceof RealN)
      num = new RealN(real);
    else if (num1 instanceof ComplexN && num2 instanceof ComplexN)
      num = new ComplexN(real, img);
    
    return num;
  }
  
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
        RationalN rat1 = (RationalN)num1;
        RationalN rat2 = (RationalN)num2;
        double denominator = rat1.getDenominator() * rat2.getNumerator();
        double numerator = rat1.getNumerator() * rat2.getDenominator();
        num = new RationalN((int)numerator, (int)denominator);
      }
      else if (num1 instanceof RealN && num2 instanceof RealN)
        num = new RealN(real);
      else if (num1 instanceof ComplexN && num2 instanceof ComplexN)
        num = new ComplexN(real, img);
    }
    
    return num;
  }
}
