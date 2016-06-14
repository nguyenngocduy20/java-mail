import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class password_dialog {

	private JFrame pass_dialog;
	private JPasswordField passwordField;
	private String confirm_txt;
	private JLabel lblConfirmationText;
	/**
	 * Launch the application.
	 */
	public static void password_dialog(String s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					password_dialog window = new password_dialog(s);
					window.pass_dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public password_dialog(String s) {
		initialize(s);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String s) {
		pass_dialog = new JFrame();
		pass_dialog.setTitle("Password Requirement");
		pass_dialog.setBounds(100, 100, 450, 300);
		pass_dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pass_dialog.getContentPane().setLayout(null);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(passwordField.getText().equals("") || passwordField.getText() == null)
				{
				}
				else
				{
					confirm_txt = passwordField.getText();
				}
			}
		});
		btnOK.setBounds(47, 214, 89, 23);
		pass_dialog.getContentPane().add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				pass_dialog.dispose();
			}
		});
		btnCancel.setBounds(276, 214, 89, 23);
		pass_dialog.getContentPane().add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Please enter password here");
		passwordField.setBounds(35, 137, 346, 31);
		pass_dialog.getContentPane().add(passwordField);
		
		this.confirm_txt = s;
		lblConfirmationText = new JLabel(this.confirm_txt);
		lblConfirmationText.setBounds(35, 26, 346, 100);
		lblConfirmationText.setText(confirm_txt);
		pass_dialog.getContentPane().add(lblConfirmationText);
	}
	
	public String getPassword()
	{
		return this.passwordField.getText();
	}
	
	public void setText(String s)
	{
		this.confirm_txt = s;
		System.out.println(this.confirm_txt);
	}
	
	public boolean isFinish()
	{
		System.out.println("Password Dialog: " + pass_dialog.isVisible());
		return pass_dialog.isVisible();
	}
	
	public void setVisible()
	{
		pass_dialog.setVisible(true);
	}
}
