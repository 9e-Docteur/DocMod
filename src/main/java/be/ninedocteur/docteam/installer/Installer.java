package be.ninedocteur.docteam.installer;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docteam.PublicServerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Locale;

public class Installer{

    private static File targetFile;
    private static JPanel panel;
    private static JLabel label;
    private static JFrame window;
    private static JButton installButton;
    private static final ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == installButton){
                try {
                    InputStream in = new BufferedInputStream(new FileInputStream(new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath())));
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(getMCDir().getAbsolutePath() + "/mods/DocMod")));
                    byte[] buffer = new byte[1024];
                    int lengthRead = in.read(buffer);
                    while(lengthRead > 0){
                        out.write(buffer, 0, lengthRead);
                        out.write(buffer, 0, lengthRead);
                        out.flush();
                    }
                }catch(IOException ex){
                    ex.printStackTrace();
                } catch(URISyntaxException exx){
                    exx.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] args) {
        window = new JFrame();
        panel = new JPanel();
        label = new JLabel("DocMod Installer");
        installButton = new JButton("Install DocMod " + DocMod.FULLDOCMODVERSION);
        window.setTitle("DocMod Installer");
        window.setBounds(100, 100, 600, 400);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JLabel( "WARNING: DocMod Install Program in not complete!"), BorderLayout.CENTER);
        panel.add(new JLabel("Install to: " + getMCDir().getAbsolutePath() + "/mods"), BorderLayout.SOUTH);
        panel.add(installButton, BorderLayout.SOUTH);
        window.add(panel);
        installButton.addActionListener(listener);
        window.setVisible(true);
    }

    public static File getMCDir() {
        String userHomeDir = System.getProperty("user.home", ".");
        String osType = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        String mcDir = ".minecraft";
        if (osType.contains("win") && System.getenv("APPDATA") != null)
            return new File(System.getenv("APPDATA"), mcDir);
        if (osType.contains("mac"))
            return new File(new File(new File(userHomeDir, "Library"), "Application Support"), "minecraft");
        return new File(userHomeDir, mcDir);
    }
}
