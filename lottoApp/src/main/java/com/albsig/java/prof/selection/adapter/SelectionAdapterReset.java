package com.albsig.java.prof.selection.adapter;

import com.albsig.java.prof.animation.KnightRiderAnimation;
import java.util.ArrayList;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MenuItem;


/**
 * A SelectionAdapter for handling reset events in a Lotto game.
 */
public class SelectionAdapterReset extends SelectionAdapter {

  private ArrayList<Button> buttonsPlay;
  private Button playButton;
  private Button saveButton;
  private MenuItem saveItem;
  private KnightRiderAnimation runnable;

  /**
   * Constructs a new SelectionAdapterReset with the specified parameters.

   * @param buttonsPlay The list of buttons representing the Lotto numbers.
   * @param playButton The button used to play the Lotto game.
   * @param saveButton The button used to save the Lotto numbers.
   * @param saveItem The menu item used to save the Lotto numbers.
   * @param runnable The KnightRiderAnimation object for animating Lotto numbers.
   */
  public SelectionAdapterReset(ArrayList<Button> buttonsPlay, Button playButton, Button saveButton,
      MenuItem saveItem, KnightRiderAnimation runnable) {
    this.buttonsPlay = buttonsPlay;
    this.playButton = playButton;
    this.saveButton = saveButton;
    this.saveItem = saveItem;
    this.runnable = runnable;
  }

  /**
   * Called when a widget is selected. Handles the reset events in the Lotto game.

   * @param e The SelectionEvent that occurred.
   */
  @Override
  public void widgetSelected(SelectionEvent e) {
    // Reset all buttons in the play area
    for (Button b : buttonsPlay) {
      b.setBackground(null);
      b.setForeground(null);
      b.setSelection(false);
      if (b.getData("group") != null) {
        if (b.getData("group").equals("X")) {
          b.setText("x");
        }
        if (b.getData("group").equals("E")) {
          b.setText("e");
        }
      }
    }

    // Reset the selected count in the field adapter
    SelectionAdapterField resetAdapter = new SelectionAdapterField();
    resetAdapter.setSelectedCountGroup1(0);
    resetAdapter.setSelectedCountGroup2(0);

    // Disable play, save button, and save item
    playButton.setEnabled(false);
    saveButton.setEnabled(false);
    saveItem.setEnabled(false);

    // Stop the KnightRiderAnimation
    SelectionAdapterStop stop = new SelectionAdapterStop(Display.getCurrent(), runnable);
    stop.stop();
  }
}
