public class Maths
{
  private int maxIterations;
  
  public Maths(int _maxIterations)
  {
    maxIterations = _maxIterations;
  }
  
  public color isSet(Complex c)
  {
    int iterations = 0;
    color pixCol = color(255);
    
    double a = 0;
    double b = 0;
    
    while (iterations < maxIterations)
    {
      double za = (Math.pow(a,2) - Math.pow(b,2)) + c.a();
      double zb = (2*a*b) + c.b();
      
      a = za;
      b = zb;
      
      iterations++;
      
      if (Math.sqrt(Math.pow(za,2)+Math.pow(zb,2)) > 2)
        break;
    }
    
    pixCol = color(map(iterations,1,maxIterations,0,255));
    if (iterations == maxIterations)
      pixCol = color(0);
      
    return pixCol;
  }
  
  public void setIterations(int _maxIterations)
  {
    maxIterations = _maxIterations;
  }
  
  public int maxIterations() { return maxIterations; }
}

final static class Complex
{
  final double a;
  final double b;
  
  public Complex(double _a, double _b)
  {
    a = _a;
    b = _b;
  }
  
  public double a() { return a; }
  public double b() { return b; }
}