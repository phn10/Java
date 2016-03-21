/**
 * This class represents the natural number
 * @author Phong Nguyen
 */
public class NaturalN extends IntegerN
{
  /**
   * Constructor
   * @param num the natural number
   * @throws ArithmeticException if the natural number is less than 0
   */
  public NaturalN(int num)
  {
    super(num);
    if (num < 0)
      throw new ArithmeticException("A natural number cannot be less than 0");
  }
}
