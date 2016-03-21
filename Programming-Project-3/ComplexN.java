public class ComplexN 
{
  private double realNum;
  private double imgNum;
  
  public ComplexN(double realNum, double imgNum)
  {
    this.realNum = realNum;
    this.imgNum = imgNum;
  }
  
  public double getRealPart()
  {
    return realNum;
  }
  
  public double getImaginaryPart()
  {
    return imgNum;
  }
  
  public String toString()
  {
    if (this.getImaginaryPart() > 0)
      return this.getRealPart() + " + " + this.getImaginaryPart() + "i";
    else if (this.getImaginaryPart() < 0)
      return this.getRealPart() + " - " + Math.abs(this.getImaginaryPart()) + "i";
    else
      return Double.toString(this.getRealPart());
  }
  
  public boolean equals(Object num)
  {
    if (num instanceof ComplexN)
    {
      ComplexN comp = (ComplexN)num;
      
      if(this.getRealPart() == comp.getRealPart() && this.getImaginaryPart() == comp.getImaginaryPart())
        return true;
      else
        return false;
    }
    else
      return false;
  }
 }
