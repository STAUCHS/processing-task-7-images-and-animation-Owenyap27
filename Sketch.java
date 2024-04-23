import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
  //Setting variables
  PImage imgBackground;
  PImage imgJett;
  PImage imgKnive;

  float fltJettX = 0;
  float fltJettY = 0;

  float fltCircleX = 100;
  float fltCircleY = 100;

  float fltKniveX = 100;
  float fltKniveY = 200;
  float velKniveX = 4; // Speed of UFO

  float velJettX = (5/2); // Speed of Jett
  float velJettY = (5/2); // Speed of Jett

  float fltCircleXSpeed = 5; // Speed of Circle
  float fltCircleYSpeed = 5; // Speed of Circle

  public void settings() {
    size(1000, 700);
  }

  // Loading all the images
  public void setup() {
    imgBackground = loadImage("Emmanuel_haven4_1666929773076.jpg");
    // Resizing the images
    imgJett = loadImage("IMG_3220.png");
    imgJett.resize(imgJett.width/8, imgJett.height/8);

    imgKnive = loadImage("DAGGAR.png");
    imgKnive.resize(imgKnive.width/8, imgKnive.height/8);
   
    imgBackground.resize (imgBackground.width*2, imgBackground.height * 2);
    
  }

  public void draw() {
    background(0,0,0);

    //draw circle and animate
    
    fill (240,50,50);

    fltCircleX += fltCircleXSpeed;
    fltCircleY += fltCircleYSpeed;

    // setting boundaries
  if (fltCircleX + 25 >= width || fltCircleX - 25 <= -10) {
    fltCircleXSpeed *= -1; // Reverse the horizontal direction if the Jett hits the edges
  }
  if (fltCircleY + 25 >= height || fltCircleY - 25 <= 0) {
    fltCircleYSpeed *= -1; 
  }
    
    
    // Setting the position of the image so it shows the black hole
    image(imgBackground, 0, 0);

    // Move and show the Jett
    image(imgJett, fltJettX, fltJettY);
    fltJettX += velJettX;
    fltJettY += velJettY;

    // Collision detection for the Jett
    if (fltJettX + imgJett.width >= width || fltJettX <= -10) {
      velJettX *= -1; // Reverse the horizontal direction if the Jett hits the edges
    }
    if (fltJettY + imgJett.height >= height || fltJettY <= 0) {
      velJettY *= -1; 
    }

    // Move the Knive in a sine wave 
    float sinValue = sin((float) (frameCount * 0.05)); 
    float sinOffset = map(sinValue, -1, 1, -200, 200); // Adjust the amplitude of the wave

    fltKniveY = 300 + sinOffset; // Set the Knive Y position based on the sine wave

    image(imgKnive, fltKniveX, fltKniveY);
    fltKniveX += velKniveX;

    // Collision detection with window boundaries for the Knive
    if (fltKniveX <= 0) {
      fltKniveX = 0; // Set UFO's X position to 0 if it reaches the left window edge
      velKniveX *= -1; // Reverse the horizontal direction
    } else if (fltKniveX + imgKnive.width >= width) {
      fltKniveX = width - imgKnive.width; // Set Knive X position to the window width - Knive's width
      velKniveX *= -1; // Reverse the horizontal direction
      
    }
    // Draw ellipse (Doesn't work some reason if at top)
    ellipse (fltCircleX, fltCircleY,50,50);
  }
}