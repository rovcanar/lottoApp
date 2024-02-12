package com.albsig.java.prof.selection.adapter;

import com.albsig.java.prof.file.os.Fileio;
import java.io.File;
import java.util.ArrayList;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;



/**
 * A SelectionAdapter for handling save events in a Lotto game.
 */
public class SelectionAdapterSave extends SelectionAdapter {

  private ArrayList<Button> buttonsPlay;

  /**
   * Constructs a new SelectionAdapterSave with the specified parameters.

   * @param buttonsPlay The list of buttons representing the Lotto numbers.
   */
  public SelectionAdapterSave(ArrayList<Button> buttonsPlay) {
    this.buttonsPlay = buttonsPlay;
  }

  /**
   * Called when a widget is selected. Handles the save events in the Lotto game.

   * @param e The SelectionEvent that occurred.
   */
  @Override
  public void widgetSelected(SelectionEvent e) {
    // Format the selected Lotto numbers for saving
    String format = "Tipp 5_50: ";
    int counter = 0;
    for (Button b : buttonsPlay) {
      if (b.getSelection()) {
        format += b.getText() + " ";
        counter++;
        if (counter == 5) {
          format += "\nTipp 2_12: ";
        }
      }
    }

    // Construct the file name and write the format to a text file
    String fileName = System.getProperty("user.home") + File.separator + "LottoTipp.txt";
    Fileio.write(fileName, format);
  }
}
