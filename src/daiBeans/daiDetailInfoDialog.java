//Title:
//Version:
//Copyright:
//Author:
//Company:
//Description:

package daiBeans;

import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import daiBeans.daiButton;

public class daiDetailInfoDialog extends JDialog {
    Panel panel = new Panel();
    daiButton button_ok = new daiButton();
    JTextArea JTextArea_msg = new JTextArea();
    String dlgMsg;
    JScrollPane jScrollPane1 = new JScrollPane();

    public daiDetailInfoDialog(JFrame frame, String title, boolean modal,
                            String msg) {
        super(frame, title, modal);
        dlgMsg = msg;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public daiDetailInfoDialog(JFrame frame, String title, boolean modal) {
        super(frame, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public daiDetailInfoDialog(JFrame frame, String title) {
        this(frame, title, false);
    }

    public daiDetailInfoDialog(JFrame frame) {
        this(frame, "", false);
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        button_ok.setMnemonic(KeyEvent.VK_O);
        button_ok.setSelected(true);
        button_ok.setText("OK");
        button_ok.setEnabled(true);
        button_ok.setBounds(new Rectangle(185, 210, 49, 30));
        button_ok.addActionListener(new StandardDialog1_button_ok_actionAdapter(this));

        panel.setLayout(null);
        panel.setBounds(new Rectangle(0, 0, 400, 270));
        jScrollPane1.getViewport().add(JTextArea_msg, null);
        panel.add(jScrollPane1, null);
        panel.add(button_ok, null);
        JTextArea_msg.setText(dlgMsg);
        JTextArea_msg.setEditable(false);

        //Decorate the Window
        jScrollPane1.setBounds(new Rectangle(10, 10, 375, 185));
        this.getContentPane().add(panel);
        this.addWindowListener(new StandardDialog1_this_windowAdapter(this));
        this.setPreferredSize(new Dimension(400, 300));

        showDlg();
    }

    public void showDlg() {
        //Center the window
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        setLocation((screenSize.width - frameSize.width) / 2,
                    (screenSize.height - frameSize.height) / 2);
        show();
    }


    // OK

    void button_ok_actionPerformed(ActionEvent e) {
        dispose();
    }

    // Cancel

    void button_cancel_actionPerformed(ActionEvent e) {
        dispose();
    }

    void this_windowClosing(WindowEvent e) {
        dispose();
    }
}

class StandardDialog1_button_ok_actionAdapter implements ActionListener {
    daiDetailInfoDialog adaptee;

    StandardDialog1_button_ok_actionAdapter(daiDetailInfoDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.button_ok_actionPerformed(e);
    }
}

class StandardDialog1_button_cancel_actionAdapter implements ActionListener {
    daiDetailInfoDialog adaptee;

    StandardDialog1_button_cancel_actionAdapter(daiDetailInfoDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.button_cancel_actionPerformed(e);
    }
}

class StandardDialog1_this_windowAdapter extends WindowAdapter {
    daiDetailInfoDialog adaptee;

    StandardDialog1_this_windowAdapter(daiDetailInfoDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void windowClosing(WindowEvent e) {
        adaptee.this_windowClosing(e);
    }
}
