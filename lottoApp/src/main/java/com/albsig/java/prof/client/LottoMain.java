package com.albsig.java.prof.client;

import com.albsig.java.prof.view.LottoView;

/**
 * The main class to launch the Lotto application.
 */
public class LottoMain {

  /**
   * The main method of the Lotto application.

   * @param args The command line arguments (not used in this application).
   */
  public static void main(String[] args) {

    // Create an instance of the LottoView
    LottoView oberfleache = new LottoView();

    // Open the LottoView
    oberfleache.open();
  }
}
