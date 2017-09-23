import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Mandelbrot extends PApplet {

double xScale = 2;
double centerX = 0;
double centerY = 0;
double interval;

PImage buffer;
Maths mandelbrot;

public void setup()
{
  //size(1200,700);
  
  mandelbrot = new Maths(100);
  interval = (2*xScale)/width;
  buffer = createImage(width,height,RGB);
  colorMode(HSB);
  render();
}

public void render()
{
  for (int i = 0; i < width; i++)
  {
    for (int j = 0; j < height; j++)
    {
      double x = (i-(width/2))*interval + centerX;
      double y = (j-(height/2))*-1*interval + centerY;
      
      buffer.set(i,j,mandelbrot.isSet(new Complex(x,y)));
    }
  }
}

public void draw()
{
  background(0);
  image(buffer,0,0);
  
  double x = (mouseX-(width/2))*interval + centerX;
  double y = (mouseY-(height/2))*-1*interval + centerY;
  
  fill(0,0,0);
  text("Center: " + centerX + "+" + centerY + "i",10,15);
  text("Mouse: " + x + "+" + y + "i", 10,height-15);
  text("Iterations: " + mandelbrot.maxIterations(), 10,30);
}

public void keyPressed()
{
  if (keyCode == UP)
  {
    xScale *= 0.5f;
    mandelbrot.setIterations(Math.round(mandelbrot.maxIterations()*1.25f));
  }
  else if (keyCode == DOWN)
  {
    xScale /= 0.5f;
    mandelbrot.setIterations(Math.round(mandelbrot.maxIterations()/1.25f));
  }
  
  interval = (2*xScale)/width;
  
  render();
}

public void mouseClicked()
{
  double x = (mouseX-(width/2))*interval + centerX;
  double y = (mouseY-(height/2))*-1*interval + centerY;
  
  centerX = x;
  centerY = y;
  render();
}
public class Maths
{
  private int maxIterations;
  
  public Maths(int _maxIterations)
  {
    maxIterations = _maxIterations;
  }
  
  public int isSet(Complex c)
  {
    int iterations = 0;
    int pixCol = color(255);
    
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
    
    pixCol = color(map(iterations,1,maxIterations,0,255),255,255);
    if (iterations == maxIterations)
      pixCol = color(0,0,0);
      
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
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Mandelbrot" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
