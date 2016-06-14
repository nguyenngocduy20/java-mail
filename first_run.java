import build_in_class.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

interface constant
{
	public static final String smtp_port_noauth = "25";
	public static final String gmail_smtp_host = "smtp.gmail.com";
	public static final String gmail_pop3_host = "pop.gmail.com";
	public static final String gmail_imap_host = "imap.gmail.com";
	public static final String gmail_smtp_port_ssl = "465";
	public static final String gmail_smtp_port_tls = "587";
	public static final String gmail_pop3_port_ssl = "995";
	public static final String gmail_imap_port_ssl = "993";
	
	public static final String yahoo_smtp_host = "smtp.mail.yahoo.com";
	public static final String yahoo_pop3_host = "pop.mail.yahoo.com";
	public static final String yahoo_imap_host = "imap.mail.yahoo.com";
	public static final String yahoo_smtp_port_ssl = "465";
	public static final String yahoo_pop3_port_ssl = "995";
	public static final String yahoo_imap_port_ssl = "993";
}

public class first_run 
{

	private JFrame first_run;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private user_configuration u;

	/**
	 * Launch the application.
	 */
	public static void first_Run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					first_run window = new first_run();
					window.u = new user_configuration(false);
					window.first_run.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public first_run() throws IOException {
		u = new user_configuration(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		first_run = new JFrame();
		first_run.setTitle("Email Configuration");
		JLabel lblThisIsThe = new JLabel("This is the first time you configure your account:");
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		JLabel lblUsername = new JLabel("Username:");
		JLabel lblPassword = new JLabel("Password:");
		JComboBox hostBox = new JComboBox();
		JLabel lblCofigurationInformation = new JLabel("Cofiguration Information:");
		JLabel lblSMTP = new JLabel("SMTP:");
		JLabel lblOutgoingProtocol = new JLabel("Outgoing Protocol:");
		JLabel lblIncommingProtocol = new JLabel("Incomming Protocol:");
		JLabel lblP_I_Indicator = new JLabel("New label");
		JComboBox box_imap_pop = new JComboBox();
		JLabel lblPort = new JLabel("Port: ");
		JLabel lblSsl = new JLabel("");
		JLabel lbl_smtp = new JLabel("");
		JLabel lbl_incomming_proto = new JLabel("");
		JLabel lblPort_1 = new JLabel("Port: ");
		JLabel lbl_incomming_port = new JLabel("");
		JButton btnCancel = new JButton("Cancel");
		JButton btnOk = new JButton("OK");
		
		first_run.getContentPane().setMinimumSize(new Dimension(1024, 720));
		first_run.setBounds(100, 100, 515, 386);
		first_run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		first_run.getContentPane().setLayout(null);
		
		lblThisIsThe.setBounds(10, 11, 328, 14);
		first_run.getContentPane().add(lblThisIsThe);
		
		usernameField.setBounds(10, 47, 328, 23);
		first_run.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField.setBounds(10, 90, 328, 23);
		first_run.getContentPane().add(passwordField);
		
		lblUsername.setBounds(10, 34, 114, 14);
		first_run.getContentPane().add(lblUsername);
		
		lblPassword.setBounds(10, 75, 114, 14);
		first_run.getContentPane().add(lblPassword);
		
		hostBox.setBounds(364, 47, 114, 20);
		first_run.getContentPane().add(hostBox);
		hostBox.addItem("@gmail.com");
		hostBox.addItem("@yahoo.com");
		
		lblCofigurationInformation.setBounds(10, 155, 328, 14);
		first_run.getContentPane().add(lblCofigurationInformation);
		
		lblSMTP.setBounds(20, 203, 57, 14);
		first_run.getContentPane().add(lblSMTP);
		
		lblOutgoingProtocol.setBounds(10, 180, 328, 14);
		first_run.getContentPane().add(lblOutgoingProtocol);
		
		lblIncommingProtocol.setBounds(10, 228, 141, 14);
		first_run.getContentPane().add(lblIncommingProtocol);
		
		lblP_I_Indicator.setBounds(20, 253, 86, 14);
		first_run.getContentPane().add(lblP_I_Indicator);
		lblP_I_Indicator.setText("IMAP:");
		
		box_imap_pop.setBounds(160, 225, 86, 20);
		first_run.getContentPane().add(box_imap_pop);
		box_imap_pop.addItem("IMAP");
		box_imap_pop.addItem("POP3");
		
		lblPort.setBounds(364, 203, 46, 14);
		first_run.getContentPane().add(lblPort);
		
		lblSsl.setBounds(420, 203, 93, 14);
		first_run.getContentPane().add(lblSsl);
		lblSsl.setText(constant.gmail_smtp_port_ssl);
		
		lbl_smtp.setBounds(105, 203, 233, 14);
		first_run.getContentPane().add(lbl_smtp);
		lbl_smtp.setText(constant.gmail_smtp_host);
		
		lbl_incomming_proto.setBounds(105, 253, 233, 14);
		first_run.getContentPane().add(lbl_incomming_proto);
		lbl_incomming_proto.setText(constant.gmail_imap_host);
		
		lblPort_1.setBounds(364, 253, 46, 14);
		first_run.getContentPane().add(lblPort_1);
		
		lbl_incomming_port.setBounds(420, 253, 46, 14);
		first_run.getContentPane().add(lbl_incomming_port);
		lbl_incomming_port.setText(constant.gmail_imap_port_ssl);
		
		btnCancel.setBounds(400, 313, 89, 23);
		first_run.getContentPane().add(btnCancel);
		
		btnOk.setBounds(296, 313, 89, 23);
		first_run.getContentPane().add(btnOk);
		

		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				first_run.dispose();
				System.exit(0);
			}
		});
		

		hostBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int i = hostBox.getSelectedIndex();
				try 
				{
					u = new user_configuration();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				u.username = usernameField.getText();
				u.password = new String (passwordField.getPassword());
				if(i == 0) // gmail
				{
					u.host = "gmail";
					u.smtp = constant.gmail_smtp_host;
					u.imap = constant.gmail_imap_host;
					u.pop3 = constant.gmail_pop3_host;
					lbl_smtp.setText(u.smtp);
					lblSsl.setText(constant.gmail_smtp_port_ssl);
					lbl_incomming_proto.setText(constant.gmail_imap_host);
				}
				if(i == 1) // yahoo
				{
					u.host = "yahoo";
					u.smtp = constant.yahoo_smtp_host;
					u.imap = constant.yahoo_imap_host;
					u.pop3 = constant.yahoo_pop3_host;
					lbl_smtp.setText(u.smtp);
					lblSsl.setText(constant.yahoo_smtp_port_ssl);
					lbl_incomming_proto.setText(constant.yahoo_imap_host);
				}
			}
		});
		

		box_imap_pop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i = hostBox.getSelectedIndex();
				try 
				{
					u = new user_configuration();
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				u.username = usernameField.getText();
				u.password = new String (passwordField.getPassword());
				if(i == 0) // gmail
				{
					u.host = "gmail";
					u.smtp = constant.gmail_smtp_host;
					u.imap = constant.gmail_imap_host;
					u.pop3 = constant.gmail_pop3_host;
					lbl_smtp.setText(u.smtp);
					lblSsl.setText(constant.gmail_smtp_port_ssl);
					lbl_incomming_proto.setText(constant.gmail_imap_host);
				}
				if(i == 1) // yahoo
				{
					u.host = "yahoo";
					u.smtp = constant.yahoo_smtp_host;
					u.imap = constant.yahoo_imap_host;
					u.pop3 = constant.yahoo_pop3_host;
					lbl_smtp.setText(u.smtp);
					lblSsl.setText(constant.yahoo_smtp_port_ssl);
					lbl_incomming_proto.setText(constant.yahoo_imap_host);
				}
				
				
				i = box_imap_pop.getSelectedIndex();
				if (i == 0) // imap
				{
					lblP_I_Indicator.setText("IMAP:");
					u.i_or_p = false;
					lbl_incomming_port.setText("993");
					if (u.host.equals("gmail"))
						lbl_incomming_proto.setText(constant.gmail_imap_host);
					if (u.host.equals("yahoo"))
						lbl_incomming_proto.setText(constant.yahoo_imap_host);
				}
				if (i == 1) // pop3
				{
					lblP_I_Indicator.setText("POP3:");
					u.i_or_p = true;
					lbl_incomming_port.setText("995");
					if (u.host.equals("gmail"))
						lbl_incomming_proto.setText(constant.gmail_pop3_host);
					if (u.host.equals("yahoo"))
						lbl_incomming_proto.setText(constant.yahoo_pop3_host);
				}
			}
		});
		

		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i = hostBox.getSelectedIndex();
				try 
				{
					u = new user_configuration();
				} catch (IOException e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				u.username = usernameField.getText();
				u.password = new String (passwordField.getPassword());
				
				if(i == 0) // gmail
				{
					u.host = "gmail";
					u.smtp = constant.gmail_smtp_host;
					u.imap = constant.gmail_imap_host;
					u.pop3 = constant.gmail_pop3_host;
					lbl_smtp.setText(u.smtp);
					lblSsl.setText(constant.gmail_smtp_port_ssl);
				}
				if(i == 1) // yahoo
				{
					u.host = "yahoo";
					u.smtp = constant.yahoo_smtp_host;
					u.imap = constant.yahoo_imap_host;
					u.pop3 = constant.yahoo_pop3_host;
					lbl_smtp.setText(u.smtp);
					lblSsl.setText(constant.yahoo_smtp_port_ssl);
					lbl_incomming_proto.setText(constant.yahoo_imap_host);
				}
				
				i = box_imap_pop.getSelectedIndex();
				if (i == 0) // imap
				{
					lblP_I_Indicator.setText("IMAP:");
					u.i_or_p = false;
					lbl_incomming_port.setText("993");
					if (u.host.equals("gmail"))
						lbl_incomming_proto.setText(constant.gmail_imap_host);
					if (u.host.equals("yahoo"))
						lbl_incomming_proto.setText(constant.yahoo_imap_host);
				}
				if (i == 1) // pop3
				{
					lblP_I_Indicator.setText("POP3:");
					u.i_or_p = true;
					lbl_incomming_port.setText("995");
					if (u.host.equals("gmail"))
						lbl_incomming_proto.setText(constant.gmail_pop3_host);
					if (u.host.equals("yahoo"))
						lbl_incomming_proto.setText(constant.yahoo_pop3_host);
				}
				if(u.username.equals("") || u.password.equals(""))
					u.isConfig = false;
				else
					u.isConfig = true;
				
				if(u.isConfig() == true)
				{
					//u.isConfig = true;
					u.printInfo();
					try 
					{
						u.write2file();
						pass_proct.create_dir_proct(u.password);
						pass_proct.SHA(u.password, pass_proct.String2Path(u.password));
					} catch (IOException | NoSuchAlgorithmException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					first_run.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Please type entire needed information");
			}
		});
	}
}
