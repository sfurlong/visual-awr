package org.altaprise.vawr.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class RootFrame extends JFrame {
    private GridLayout gridLayout = new GridLayout();
    private BorderLayout borderLayout = new BorderLayout();
    private JMenuBar menubarFrame = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem itemFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem itemHelpAbout = new JMenuItem();
    private String aboutMessage =
        "Visual AWR" + "\n" +
        "Stephen Furlong 2014" + "\n" +
        "Version " + "01f";
    private String aboutTitle = "About";
    private RootPanel _rootTabbedPanel = new RootPanel();
    private static JFrame _frameRef = null;

    /**The default constructor for form
     */
    public RootFrame() {
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
    }


    /**the JbInit method
     */
    public void jbInit() throws Exception {
        this.getContentPane().setLayout(gridLayout);
        this.getContentPane().add(_rootTabbedPanel, BorderLayout.CENTER);
        this.setSize(new Dimension(835, 550));
        this.setTitle("Visual AWR");


        setJMenuBar(menubarFrame);
        itemFileExit.setText("Exit");
        itemFileExit.setMnemonic('X');
        itemFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
                                                           Event.ALT_MASK,
                                                           false));
        itemFileExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    file_exit_action(e);
                }
            });
        menuFile.add(itemFileExit);
        menuFile.setText("File");
        menuFile.setMnemonic('F');
        menubarFrame.add(menuFile);
        menuHelp.setText("Help");
        menuHelp.setMnemonic('H');
        itemHelpAbout.setText("About");
        itemHelpAbout.setMnemonic('A');

        itemHelpAbout.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    help_about_action(e);
                }
            });
        menuHelp.add(itemHelpAbout);
        menubarFrame.add(menuHelp);
        
        _frameRef = this;

    }

    private void file_exit_action(ActionEvent e) {
        System.exit(0);
    }

    private void help_about_action(ActionEvent e) {
        JOptionPane.showMessageDialog(this, aboutMessage, aboutTitle,
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    public static void startWaitCursor() {
        RootPaneContainer root = (RootPaneContainer) _frameRef.getRootPane().getTopLevelAncestor();
        root.getGlassPane().setCursor(
                Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        root.getGlassPane().setVisible(true);
    }
     
    public static void stopWaitCursor() {
        RootPaneContainer root = (RootPaneContainer) _frameRef.getRootPane().getTopLevelAncestor();
        root.getGlassPane().setCursor(
                Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        root.getGlassPane().setVisible(false);
    }
    
    public static JFrame getFrameRef() {
        return _frameRef;
    }

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exemp) {
            exemp.printStackTrace();
        }

        String startDir = System.getProperty("user.dir");
        System.out.println("Started in directory: " + startDir);


        try {
            final RootFrame frame = new RootFrame();
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.jbInit();


            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = frame.getSize();
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }
            frame.setLocation(screenSize.width/2 - frameSize.width/2,
                              screenSize.height/2 - frameSize.height/2);
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
