package com.albsig.java.prof.selection.adapter;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * A SelectionAdapter for handling selection events on buttons.
 */
public class SelectionAdapterField extends SelectionAdapter {

  private int maxCountGroup1 = 5;
  private int maxCountGroup2 = 2;
  private static int selectedCountGroup1 = 0;
  private static int selectedCountGroup2 = 0;
  public Button playButton;

  /**
   * Constructs a new SelectionAdapterField with the specified playButton.

   * @param playButton The button used to trigger an action.
   */
  public SelectionAdapterField(Button playButton) {
    this.playButton = playButton;
  }

  /**
   * Constructs a new SelectionAdapterField.
   */
  public SelectionAdapterField() {}

  /**
   * Gets the count of selected buttons in group 1.

   * @return The count of selected buttons in group 1.
   */
  public static int getSelectedCountGroup1() {
    return selectedCountGroup1;
  }

  /**
   * Sets the count of selected buttons in group 1.

   * @param selectedCountGroup1 The count of selected buttons in group 1.
   */
  public static void setSelectedCountGroup1(int selectedCountGroup1) {
    SelectionAdapterField.selectedCountGroup1 = selectedCountGroup1;
  }

  /**
   * Gets the count of selected buttons in group 2.

   * @return The count of selected buttons in group 2.
   */
  public static int getSelectedCountGroup2() {
    return selectedCountGroup2;
  }

  /**
   * Sets the count of selected buttons in group 2.

   * @param selectedCountGroup2 The count of selected buttons in group 2.
   */
  public static void setSelectedCountGroup2(int selectedCountGroup2) {
    SelectionAdapterField.selectedCountGroup2 = selectedCountGroup2;
  }

  /**
   * Called when a widget is selected. Handles the selection events on buttons.

   * @param e The SelectionEvent that occurred.
   */
  @Override
  public void widgetSelected(SelectionEvent e) {
    Button button = (Button) e.getSource();
    Composite parent = button.getParent();

    if (parent.getData("group").equals("groupButtons")) {
      // Group 1 selected
      if (button.getSelection()) {
        selectedCountGroup1++;
        if (selectedCountGroup1 > maxCountGroup1) {
          button.setSelection(false);
          selectedCountGroup1--;
        }
      } else {
        selectedCountGroup1--;
      }
    } else if (parent.getData("group").equals("groupButtonsEuro")) {
      // Group 2 selected
      if (button.getSelection()) {
        selectedCountGroup2++;
        if (selectedCountGroup2 > maxCountGroup2) {
          button.setSelection(false);
          selectedCountGroup2--;
        }
      } else {
        selectedCountGroup2--;
      }
    }

    if (selectedCountGroup1 == maxCountGroup1 && selectedCountGroup2 == maxCountGroup2) {
      playButton.setEnabled(true);
    } else {
      playButton.setEnabled(false);
    }

  }
}
