package com.albsig.java.prof.file.os;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A utility class for reading from and writing to files.
 */
public class Fileio {

  /**
   * Reads the content of a file line by line and returns it as a String.

   * @param filename The name of the file to be read.
   * @return The content of the file as a String.
   */
  public static String read(String filename) {
    String content = "";
    String line;
    try {

      // Open the file
      BufferedReader filereader = new BufferedReader(new FileReader(filename));
      // Read the file line by line
      while ((line = filereader.readLine()) != null) {
        content += line + "\n";
      } // end while

      // Close the file
      filereader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } // end catch

    // Return the content of the file
    return content;
  } // end method read ()

  /**
   * Writes content to a file.

   * @param filename The name of the file to be written.
   * @param content The content to be written to the file.
   */
  public static void write(String filename, String content) {

    try {
      // Open the file
      FileWriter writer = new FileWriter(filename);

      // Write the content from the second parameter to the file
      writer.write(content);

      // Close the file
      writer.close();
    } catch (IOException e) {
      // Print the stack trace if an exception occurs
      e.printStackTrace();
    } // end catch

  } // end method write ()
} // end class FileIO
