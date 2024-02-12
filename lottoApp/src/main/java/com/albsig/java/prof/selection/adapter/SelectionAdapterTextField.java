package com.albsig.java.prof.selection.adapter;

import java.util.ArrayList;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A SelectionAdapter for changing background color of text fields in a Lotto game.
 */
public class SelectionAdapterTextField extends SelectionAdapter {

  private Shell shell;
  private ArrayList<Button> buttonsPlay;

  /**
   * Constructs a new SelectionAdapterTextField with the specified parameters.

   * @param buttonsPlay The list of buttons representing the Lotto numbers.
   * @param shell The Shell object for SWT.
   */
  public SelectionAdapterTextField(ArrayList<Button> buttonsPlay, Shell shell) {
    this.buttonsPlay = buttonsPlay;
    this.shell = shell;
  }

  /**
   * Called when a widget is selected. Opens a color dialog for selecting the background color.

   * @param e The SelectionEvent that occurred.
   */
  @Override
  public void widgetSelected(SelectionEvent e) {
    ColorDialog dialog = new ColorDialog(shell);
    dialog.setText("Color Selection");
    dialog.setRGB(new RGB(255, 0, 0));
    RGB selectedColor = dialog.open();
    Display display = Display.getCurrent();
    dialog.setRGB(new RGB(0, 0, 0));

    if (selectedColor != null) {
      for (Button b : buttonsPlay) {
        Color color = new Color(display, selectedColor);
        b.setBackground(color);
      }
    }
  }
}
