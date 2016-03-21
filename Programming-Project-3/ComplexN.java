/**
 * This class represents complex number
 * @author Phong Nguyen
 */
public class ComplexN 
{
  private double realNum;          // real part of the complex number
  private double imgNum;           // imaginary part of the complex number
  
  /**
   * Constructor
   * @param realNum real part of the complex number
   * @param imgNum imaginary part of the complex number
   */
  public ComplexN(double realNum, double imgNum)
  {
    this.realNum = realNum;
    this.imgNum = imgNum;
  }
  
  /**
   * Get the real part of the complex number
   */
  public double getRealPart()
  {
    return realNum;
  }
  
  /**
   * Get the imaginary part of the complex number
   */
  public double getImaginaryPart()
  {
    return imgNum;
  }
  
  /**
   * Get the greatest common divisor between the numerator and denominator
   */
  public String toString()
  {
    if (this.getImaginaryPart() > 0)
      return this.getRealPart() + " + " + this.getImaginaryPart() + "i";
    else if (this.getImaginaryPart() < 0)
      return this.getRealPart() + " - " + Math.abs(this.getImaginaryPart()) + "i";
    else
      return Double.toString(this.getRealPart());
  }
  
  /**
   * Compare two numbers 
   * @param num a number with unknown type
   */
  public boolean equals(Object num)
  {
    if (num instanceof ComplexN)
    {
      ComplexN comp = (ComplexN)num;         // type caset the Object as Complex number
      
      if(this.getRealPart() == comp.getRealPart() && this.getImaginaryPart() == comp.getImaginaryPart())
        return true;
      else
        return false;
    }
    else
      return false;
  }
}
