double xScale = 2;
double centerX = 0;
double centerY = 0;
double interval;

PImage buffer;
Maths mandelbrot;

void setup()
{
  //size(1200,700);
  fullScreen();
  mandelbrot = new Maths(100);
  interval = (2*xScale)/width;
  buffer = createImage(width,height,RGB);
  render();
}

void render()
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

void draw()
{
  background(0);
  image(buffer,0,0);
  
  double x = (mouseX-(width/2))*interval + centerX;
  double y = (mouseY-(height/2))*-1*interval + centerY;
  
  fill(0,255,0);
  text("Center: " + centerX + "+" + centerY + "i",10,15);
  text("Mouse: " + x + "+" + y + "i", 10,height-15);
  text("Iterations: " + mandelbrot.maxIterations(), 10,30);
}

void keyPressed()
{
  if (keyCode == UP)
  {
    xScale *= 0.5;
    mandelbrot.setIterations(Math.round(mandelbrot.maxIterations()*1.25));
  }
  else if (keyCode == DOWN)
  {
    xScale /= 0.5;
    mandelbrot.setIterations(Math.round(mandelbrot.maxIterations()/1.25));
  }
  
  interval = (2*xScale)/width;
  
  render();
}

void mouseClicked()
{
  double x = (mouseX-(width/2))*interval + centerX;
  double y = (mouseY-(height/2))*-1*interval + centerY;
  
  centerX = x;
  centerY = y;
  render();
}