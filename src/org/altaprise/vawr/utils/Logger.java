//  This class is used to log runtime data.
//  It has been implemented as a Singleton pattern.
//  NOTE:  This class should not be instantiated directly.
//          New instances should be initialized to null and
//          the getInstance() method should be used to get a
//          handle to this class.
package org.altaprise.vawr.utils;

import java.util.Calendar;

import javax.swing.JFrame;


public class Logger {
    JFrame parentFrame;

    //Get our static instance data.

    public static Logger getInstance() {
        if (_logger == null) {
            _logger = new Logger();
        }
        return _logger;
    }

    public void logError(JFrame frame, String msg) {

        if (_toStdOut) {
            System.out.println(msg);
        }

        if (_toDlg) {
            //DetailInfoDialog dlg = new DetailInfoDialog(parentFrame, "Error Message", true, msg);
        }
    }

    public void logError(String msg) {
        if (_toStdOut) {
            System.out.println(msg);
        }

    }

    public void trace(int traceLevel, String className, String methodName,
                      String msg) {
        if (_traceLevel < traceLevel)
            return;

        if (_toStdOut) {
            System.out.println(msg);
        }

    }

    /////////////////  Private Methods/Data //////////////////////////

    //Private Constructor.  Enforces Singleton.

    private Logger() {
    }

    private String getDateTimeStamp() {
        Calendar now = Calendar.getInstance();
        return now.getTime().toString();
    }

    //Declaration of the Private Static metaData.
    //Since it's static, only one copy will exist of the instance data
    //a'la the Singleton pattern.
    private static Logger _logger = new Logger();
    private boolean _toStdOut = true;
    private boolean _toDlg = true;
    private int _traceLevel = 1;

}


