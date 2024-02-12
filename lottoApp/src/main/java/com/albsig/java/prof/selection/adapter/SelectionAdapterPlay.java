package com.albsig.java.prof.selection.adapter;

import com.albsig.java.prof.animation.KnightRiderAnimation;
import java.util.ArrayList;
import java.util.Random;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;


/**
 * A SelectionAdapter for handling play events in a Lotto game.
 */
public class SelectionAdapterPlay extends SelectionAdapter {

  private ArrayList<Button> buttonList;
  private Display display;
  private KnightRiderAnimation runnable;
  private Button saveButton;
  private MenuItem saveItem;
  private Button resetButton;

  /**
   * Constructs a new SelectionAdapterPlay with the specified parameters.

   * @param buttonList The list of buttons representing the Lotto numbers.
   * @param display The Display object for SWT.
   * @param runnable The KnightRiderAnimation object for animating Lotto numbers.
   * @param saveButton The button used to save the Lotto numbers.
   * @param saveItem The menu item used to save the Lotto numbers.
   * @param resetButton The button used to reset the Lotto game.
   */
  public SelectionAdapterPlay(ArrayList<Button> buttonList, Display display,
      KnightRiderAnimation runnable, Button saveButton, MenuItem saveItem, Button resetButton) {
    this.buttonList = buttonList;
    this.display = display;
    this.runnable = runnable;
    this.saveButton = saveButton;
    this.saveItem = saveItem;
    this.resetButton = resetButton;
  }

  /**
   * Called when a widget is selected. Handles the play events in the Lotto game.

   * @param e The SelectionEvent that occurred.
   */
  @Override
  public void widgetSelected(SelectionEvent e) {
    // Disable the reset button
    resetButton.setEnabled(false);

    // Start the KnightRiderAnimation
    Display d = e.widget.getDisplay();
    d.timerExec(80, runnable);

    // Generate a random Lotto draw
    ArrayList<Integer> zahlen50 = new ArrayList<Integer>();
    for (int i = 1; i <= 50; i++) {
      zahlen50.add(i);
    }

    ArrayList<Integer> ziehung = new ArrayList<Integer>();
    Random lottofee = new Random();
    for (int i = 0; i < 5; i++) {
      int nextPos = lottofee.nextInt(zahlen50.size());
      int nextNumber = zahlen50.remove(nextPos);
      ziehung.add(nextNumber);
    }

    ArrayList<Integer> zahlen12 = new ArrayList<Integer>();
    for (int i = 1; i <= 12; i++) {
      zahlen12.add(i);
    }

    for (int i = 0; i < 2; i++) {
      int nextPos = lottofee.nextInt(zahlen12.size());
      int nextNumber = zahlen12.remove(nextPos);
      ziehung.add(nextNumber);
    }

    // Display the Lotto draw with animation
    for (int i = 0; i < 7; i++) {
      final int index = i;
      display.timerExec(2000 * i, new Runnable() {
        public void run() {
          buttonList.get(index).setText("" + ziehung.get(index));
          buttonList.get(index).setBackground(display.getSystemColor(SWT.COLOR_GRAY));
          if (index == 6) {
            // Enable save button and menu item when the animation completes
            saveButton.setEnabled(true);
            saveItem.setEnabled(true);

            // Stop the KnightRiderAnimation
            SelectionAdapterStop stop = new SelectionAdapterStop(Display.getCurrent(), runnable);
            stop.stop();

            // Enable reset button
            resetButton.setEnabled(true);
          }
        }
      });
    }

    // Disable the play button after one play
    Button playButton = (Button) e.getSource();
    playButton.setEnabled(false);
  }
}
