package com.albsig.java.prof.view;

import com.albsig.java.prof.animation.KnightRiderAnimation;
import com.albsig.java.prof.selection.adapter.SelectionAdapterClose;
import com.albsig.java.prof.selection.adapter.SelectionAdapterField;
import com.albsig.java.prof.selection.adapter.SelectionAdapterPlay;
import com.albsig.java.prof.selection.adapter.SelectionAdapterReset;
import com.albsig.java.prof.selection.adapter.SelectionAdapterSave;
import com.albsig.java.prof.selection.adapter.SelectionAdapterTextColor;
import com.albsig.java.prof.selection.adapter.SelectionAdapterTextField;
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * The view class responsible for creating the UI for the Lotto game.
 */
public class LottoView {

  private Display display;
  private Shell shell;
  private Group groupAnimation;
  private Group groupButtons;
  private Group groupButtonsEuro;
  private Group groupButtonsZiehung;
  private Button ziehung1;
  private Button ziehung2;
  private Button ziehung3;
  private Button ziehung4;
  private Button ziehung5;
  private Button ziehungE1;
  private Button ziehungE2;
  private Canvas canvas;

  /**
   * Constructs a new LottoView and initializes the UI components.
   */
  public LottoView() {
    // Initialize SWT Display and Shell
    display = new Display();
    shell = new Shell(display);
    shell.setLayout(new RowLayout(SWT.VERTICAL));
    shell.setText("Lotto");

    // Create Menu bar
    Menu menuBar = new Menu(shell, SWT.BAR);
    shell.setMenuBar(menuBar);

    // File Menu
    MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
    fileMenuHeader.setText("Datei");

    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
    fileMenuHeader.setMenu(fileMenu);

    MenuItem saveItem = new MenuItem(fileMenu, SWT.PUSH);
    saveItem.setText("Speichern");
    saveItem.setEnabled(false);

    MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
    exitItem.setText("Beenden");

    // Composite for Buttons
    Composite buttonComposite = new Composite(shell, SWT.NONE);
    buttonComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

    // Buttons
    Button playButton = new Button(buttonComposite, SWT.PUSH);
    playButton.setImage(new Image(display, "resources/images/running_ovr@2x.gif"));
    playButton.setEnabled(false);

    Button saveButton = new Button(buttonComposite, SWT.PUSH);
    saveButton.setImage(new Image(display, "resources/images/icons8-speichern-16.png"));
    saveButton.setEnabled(false);

    Button colorTextButton = new Button(buttonComposite, SWT.PUSH);
    colorTextButton.setImage(new Image(display, "resources/images/icons8-ein-16.png"));

    Button colorFieldsButton = new Button(buttonComposite, SWT.PUSH);
    colorFieldsButton.setImage(new Image(display, "resources/images/color_settings.gif"));

    Button resetButton = new Button(buttonComposite, SWT.PUSH);
    resetButton.setImage(new Image(display, "resources/images/icons8-x-16.png"));

    // Groups for Lotto numbers selection
    Group groupField = new Group(shell, SWT.NONE);
    groupField.setLayout(new GridLayout(2, false));
    groupButtons = new Group(groupField, SWT.NONE);
    groupButtons.setLayout(new GridLayout(5, true));
    groupButtons.setText("5 aus 50");
    groupButtons.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    groupButtons.setData("group", "groupButtons");

    ArrayList<Button> buttonsPlay = new ArrayList<>();

    // Create buttons for selecting 5 out of 50 numbers
    for (int i = 1; i <= 50; i++) {
      Button button = new Button(groupButtons, SWT.TOGGLE);
      button.setText("" + i);
      button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      button.setFont(new Font(display, "Arial", 8, SWT.BOLD));
      button.addSelectionListener(new SelectionAdapterField(playButton));
      buttonsPlay.add(button);
    }

    // Group for animation
    groupAnimation = new Group(groupField, SWT.NONE);
    groupAnimation.setLayout(new FillLayout());
    groupAnimation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    // Group for Euro numbers selection
    groupButtonsEuro = new Group(groupField, SWT.NONE);
    groupButtonsEuro.setLayout(new GridLayout(5, true));
    groupButtonsEuro.setText("2 aus 12");
    groupButtonsEuro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    groupButtonsEuro.setData("group", "groupButtonsEuro");

    // Create buttons for selecting 2 out of 12 Euro numbers
    for (int i = 1; i <= 12; i++) {
      Button button = new Button(groupButtonsEuro, SWT.TOGGLE);
      button.setText("" + i);
      button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      button.setFont(new Font(display, "Arial", 8, SWT.BOLD));
      button.addSelectionListener(new SelectionAdapterField(playButton));
      buttonsPlay.add(button);
    }

    // Group for displaying drawn numbers
    groupButtonsZiehung = new Group(groupField, SWT.NONE);
    groupButtonsZiehung.setLayout(new FillLayout(SWT.VERTICAL));
    groupButtonsZiehung.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    // Create buttons for displaying drawn numbers
    // Ziehung 5 aus 50
    Composite ziehung50Composite = new Composite(groupButtonsZiehung, SWT.NONE);
    ziehung50Composite.setLayout(new GridLayout(5, false));
    Label ziehung50Label = new Label(ziehung50Composite, SWT.NONE);
    ziehung50Label.setText("Ziehung 5 aus 50:");
    ziehung50Label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 5, 1));

    // Initialize buttons for drawn numbers (5 out of 50)
    ziehung1 = new Button(ziehung50Composite, SWT.NONE);
    ziehung1.setText("x");
    ziehung1.setData("group", "X");
    ziehung2 = new Button(ziehung50Composite, SWT.NONE);
    ziehung2.setText("x");
    ziehung2.setData("group", "X");
    ziehung3 = new Button(ziehung50Composite, SWT.NONE);
    ziehung3.setText("x");
    ziehung3.setData("group", "X");
    ziehung4 = new Button(ziehung50Composite, SWT.NONE);
    ziehung4.setText("x");
    ziehung4.setData("group", "X");
    ziehung5 = new Button(ziehung50Composite, SWT.NONE);
    ziehung5.setText("x");
    ziehung5.setData("group", "X");

    // Ziehung 2 aus 12
    Composite ziehung12Composite = new Composite(groupButtonsZiehung, SWT.NONE);
    ziehung12Composite.setLayout(new GridLayout(2, false));
    Label ziehung12Label = new Label(ziehung12Composite, SWT.NONE);
    ziehung12Label.setText("Ziehung 2 aus 12:");
    ziehung12Label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));
    ziehungE1 = new Button(ziehung12Composite, SWT.NONE);
    ziehungE1.setText("e");
    ziehungE1.setData("group", "E");
    ziehungE2 = new Button(ziehung12Composite, SWT.NONE);
    ziehungE2.setText("e");
    ziehungE2.setData("group", "E");

    // Add buttons to the buttonsPlay list
    ArrayList<Button> buttonList = new ArrayList<>();
    buttonList.add(ziehung1);
    buttonList.add(ziehung2);
    buttonList.add(ziehung3);
    buttonList.add(ziehung4);
    buttonList.add(ziehung5);
    buttonList.add(ziehungE1);
    buttonList.add(ziehungE2);

    buttonsPlay.add(ziehung1);
    buttonsPlay.add(ziehung2);
    buttonsPlay.add(ziehung3);
    buttonsPlay.add(ziehung4);
    buttonsPlay.add(ziehung5);
    buttonsPlay.add(ziehungE1);
    buttonsPlay.add(ziehungE2);

    // Create Canvas for animation
    canvas = new Canvas(groupAnimation, SWT.DOUBLE_BUFFERED);
    KnightRiderAnimation animation = new KnightRiderAnimation(canvas, display);

    // Add Selection Listeners to Buttons
    playButton.addSelectionListener(new SelectionAdapterPlay(buttonList, display, animation,
        saveButton, saveItem, resetButton));
    colorTextButton.addSelectionListener(new SelectionAdapterTextColor(buttonsPlay, shell));
    colorFieldsButton.addSelectionListener(new SelectionAdapterTextField(buttonsPlay, shell));
    resetButton.addSelectionListener(
        new SelectionAdapterReset(buttonsPlay, playButton, saveButton, saveItem, animation));
    saveButton.addSelectionListener(new SelectionAdapterSave(buttonsPlay));
    saveItem.addSelectionListener(new SelectionAdapterSave(buttonsPlay));
    exitItem.addSelectionListener(new SelectionAdapterClose(shell));

    // Pack and open the shell
    shell.pack();
  }

  /**
   * Opens the LottoView shell and starts the SWT event loop.
   */
  public void open() {
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }
}
