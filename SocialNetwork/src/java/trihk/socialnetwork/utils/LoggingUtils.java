/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author TriHuynh
 */
public class LoggingUtils {

  public static void writeLog(String file, String msg) {
    Logger logger = Logger.getLogger("SocialNetwork_Log");
    FileHandler fileHandler;
    try {
      fileHandler = new FileHandler("./test/SN_LogFile.log");
      logger.addHandler(fileHandler);
      SimpleFormatter formatter = new SimpleFormatter();
      fileHandler.setFormatter(formatter);
      logger.log(Level.INFO, "{0} - {1}", new Object[]{file, msg});
    } catch (SecurityException | IOException e) {
      // TODO
    }
  }
}
