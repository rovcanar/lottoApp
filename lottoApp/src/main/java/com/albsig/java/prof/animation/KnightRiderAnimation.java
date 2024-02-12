package com.albsig.java.prof.animation;

import java.util.Random;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;

/**
 * A class that performs a simple animation of a "Knight-Rider" effect on an SWT Canvas.
 */
public class KnightRiderAnimation implements Runnable {

  private Canvas canvas;

  private int positionX;
  private int positionY;

  private int step = 5;
  private int red = 0;
  private int green = 0;
  private int blue = 255;
  private final int initialSize = 75;
  private final int milliseconds = 20;
  private boolean start = false;

  /**
   * Constructor for the KnightRiderAnimation class.

   * @param c The Canvas on which the animation is performed.
   * @param display The Display object for SWT.
   */
  public KnightRiderAnimation(Canvas c, Display display) {
    this.canvas = c;
    positionX = c.getClientArea().width / 2;
    positionY = c.getClientArea().height / 2;


    c.addPaintListener(new PaintListener() {
      // Called on EVERY resize of the Canvas.
      // Called on EVERY redraw() call of the Canvas.
      public void paintControl(PaintEvent event) {

        setColorValues();

        if (start) {
          // Draw the oval:
          event.gc.setForeground(new Color(display, red, green, blue));
          event.gc.setLineWidth(5);
          event.gc.drawOval(positionX, positionY, initialSize, initialSize);
        }
        start = true;
      }
    });
  }


  private void setColorValues() {

    // Random values for color change between -20 and 20
    Random r = new Random();
    int deltaR = r.nextInt(41) - 20;
 
    // Color values must remain between 0 and 255
    red += deltaR;
    if (red < 0) {
      red = 0;
    }
    if (red > 255) {
      red = 255;
    } 
    
    int deltaG = r.nextInt(41) - 20;
    green += deltaG;
    if (green < 0) {
      green = 0;
    }
    if (green > 255) {
      green = 255;
    } 

    int deltaB = r.nextInt(41) - 20;
    blue += deltaB;
    if (blue < 0) {
      blue = 0;
    }
    if (blue > 255) {
      blue = 255;
    } 
    



  }


  /**
   * Moves the "Knight-Rider" animation to the next coordinate.
   */
  public void knightRider() {

    // Animation moves back and forth in the x-direction ("Knight-Rider-LEDs")
    positionX += step;

    Rectangle rect = canvas.getClientArea();
    positionY = rect.height / 2;


    // If reached the end of the canvas: move backward
    if (positionX > rect.width - initialSize) {

      step = -5;
      positionX = rect.width - initialSize;
    }
    // If reached the start of the canvas: move forward
    if (positionX < 0) {
      step = 5;
      positionX = 0;
    }

    // Canvas redraws itself (triggers PaintListener above)
    canvas.redraw();

  }



  /**
   * This method is started as a concurrent thread by the Display.
   */
  @Override
  public void run() {
    knightRider(); // Move to the next position
    canvas.getDisplay().timerExec(milliseconds, this);

  }



}
