public class NaturalN extends IntegerN
{
  public NaturalN(int num)
  {
    super(num);
    if (num < 0)
      throw new ArithmeticException("A natural number cannot be less than 0");
  }
}
