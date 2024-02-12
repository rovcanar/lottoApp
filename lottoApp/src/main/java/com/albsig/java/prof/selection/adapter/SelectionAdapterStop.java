package com.albsig.java.prof.selection.adapter;

import com.albsig.java.prof.animation.KnightRiderAnimation;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;


/**
 * A SelectionAdapter for stopping animation in a Lotto game.
 */
public class SelectionAdapterStop extends SelectionAdapter {

  private Display display;
  private KnightRiderAnimation runnable;

  /**
   * Constructs a new SelectionAdapterStop with the specified parameters.

   * @param display The Display object for SWT.
   * @param runnable The KnightRiderAnimation object for animating Lotto numbers.
   */
  public SelectionAdapterStop(Display display, KnightRiderAnimation runnable) {
    this.display = display;
    this.runnable = runnable;
  }

  /**
   * Called when a widget is selected. Stops the animation in the Lotto game.

   * @param e The SelectionEvent that occurred.
   */
  @Override
  public void widgetSelected(SelectionEvent e) {
    stop();
  }

  /**
   * Stops the animation.
   */
  public void stop() {
    Display d = display;
    d.timerExec(-1, runnable);
  }
}
