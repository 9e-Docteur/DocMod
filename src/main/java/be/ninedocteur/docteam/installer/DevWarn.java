package be.ninedocteur.docteam.installer;

import be.ninedocteur.docmod.DocMod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Locale;

public class DevWarn {

    private static JPanel panel = new JPanel();
    private static JFrame window = new JFrame();
    private static JButton close;
    private static final ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == close){

            }
        }
    };

    public static void main(String[] args) {
       startFrame();
    }

    public static void startFrame(){
        window.setTitle("DocMod - WARNING!");
        close = new JButton("Ok");
        window.setBounds(100, 100, 600, 200);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(new JLabel( "WARNING: DocMod is running in dev mode!"), BorderLayout.CENTER);
        //panel.add(close, BorderLayout.CENTER);
        close.setBounds(100, 100, 500, 100);
        window.add(panel);
        close.addActionListener(listener);
        window.setVisible(true);
    }
}
