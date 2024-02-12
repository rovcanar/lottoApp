package com.albsig.java.prof.selection.adapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


/**
 * A SelectionAdapter that closes the given Shell when triggered.
 */
public class SelectionAdapterClose extends SelectionAdapter {

  private Shell shell;

  /**
   * Constructs a new SelectionAdapterClose with the specified Shell.

   * @param shell The Shell to be closed.
   */
  public SelectionAdapterClose(Shell shell) {
    this.shell = shell;
  }

  /**
   * Called when the widget is selected. Closes the associated Shell after displaying a confirmation
   * message.

   * @param e The SelectionEvent that occurred.
   */
  @Override
  public void widgetSelected(SelectionEvent e) {
    MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
    messageBox.setMessage("Do you really want to exit the program?");
    messageBox.setText("Exit Program");
    int response = messageBox.open();
    if (response == SWT.YES) {
      shell.close();
    }
  }

}
