/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleeditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author TriHuynh
 */
public class SampleEditor {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Date date = new Date();
    System.out.println(date.getTime());
    String patern = "EE dd MMM HH:mm";
    SimpleDateFormat format = new SimpleDateFormat(patern);
    String newDate = format.format(date.getTime());
    System.out.println(newDate);
  }

}
