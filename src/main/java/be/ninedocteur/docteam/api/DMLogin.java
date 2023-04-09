package be.ninedocteur.docteam.api;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DMLogin extends Screen implements ActionListener{

	
	JFrame frame = new JFrame();
	JButton loginBtn = new JButton("Login");
	JButton cancelBtn = new JButton("Cancel");
	JTextField usernameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JLabel info = new JLabel("Please connect to your DocTeam Account.");
	JLabel userNameLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	
	public DMLogin() {
		super(Component.empty());
		
		userNameLabel.setBounds(50, 100, 75, 25);
		passwordLabel.setBounds(50, 150, 75, 25);
		
		info.setBounds(125, 250, 250, 35);
		//info.setFont(new Font(null, Font.PLAIN, 25));
		
		usernameField.setBounds(125, 100, 200, 25);
		passwordField.setBounds(125, 150, 200, 25);
		
		loginBtn.setBounds(125, 200, 100, 25);		
		loginBtn.addActionListener(this);
		
		cancelBtn.setBounds(225, 200, 100, 25);		
		cancelBtn.addActionListener(this);
		
		frame.add(userNameLabel);
		frame.add(passwordLabel);
		frame.add(info);
		frame.add(usernameField);
		frame.add(passwordField);
		frame.add(loginBtn);
		frame.add(cancelBtn);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelBtn) {
			frame.dispose();
		}
		
		if(e.getSource() == loginBtn) {
			//TODO: LOGIN
			String userFieldText = usernameField.getText();
			String passFieldText = passwordField.getText();
			DocTeamAPI api = new DocTeamAPI(userFieldText, passFieldText);
			if(DocTeamAPI.isConnected()) {
				frame.dispose();
			}
		}
	}

}
