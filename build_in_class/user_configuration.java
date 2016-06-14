package build_in_class;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class user_configuration
{
	public String username;
	public String host;
	public String password;
	public String smtp;
	public String imap;
	public String pop3;
	public boolean i_or_p;
	public boolean isConfig;
	
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		/*
		user_configuration u = new user_configuration("abc", "gmail", "cde", "smtp.gmail.com", "imap.gmail.com", "pop.gmail.com", false);
		u.write2file();
		u.readFile();
		u.printInfo();
		*/
	}

	public user_configuration() throws IOException
	{
		this.readFile();
	}
	
	public user_configuration(boolean isConfig) throws IOException
	{
		if (isConfig)
		{
			this.readFile();
		}
		else
		{
			this.isConfig = isConfig;
			this.write2file();
		}
	}
	
	public user_configuration(String username, String host, String password, String smtp, String imap, String pop3, boolean i_or_p)
	{
		this.username = username;
		this.host = host;
		this.password = password;
		this.smtp = smtp;
		this.imap = imap;
		this.pop3 = pop3;
		this.i_or_p=i_or_p;
		this.isConfig = true;
	}
	
	public void write2file() throws IOException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "\\user";
		File t = new File(p);
		t.mkdirs();
		
		File file_2 = new File(p,"people.txt");
        file_2.createNewFile();
		
		PrintWriter f = new PrintWriter(p + "\\user.cfg", "UTF-8");
		if(!this.isConfig)
			f.write('0');
		else
		{
			f.println('1');
			f.println(username);
			//f.println(password);
			f.println(host);
			f.println(smtp);
			f.println(imap);
			f.println(pop3);
			if(!i_or_p)
				f.println(0); // imap
			else
				f.println(1); // pop3
		}
		f.close();
	}
	
	public void readFile() throws IOException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "\\user\\user.cfg";
		
		BufferedReader br = new BufferedReader(new FileReader(p));
		String cur = br.readLine();
		if(Integer.parseInt(cur) == 0)
		{
			username = password = host = smtp = imap = pop3 = "";
			i_or_p = false;
			isConfig = true;
		}
		else
		{
			if(Integer.parseInt(cur) == 1)
			{
				username = br.readLine();
				//password = br.readLine();
				host = br.readLine();
				smtp = br.readLine();
				imap = br.readLine();
				pop3 = br.readLine();
				if(Integer.parseInt(br.readLine()) == 0)
					i_or_p = false;
				else
					i_or_p = true;
				isConfig = true;
			}
			else
			{
				br.close();
				return;
			}
		}
		br.close();
	}
	
	public void printInfo()
	{
		if(isConfig)
		{
			System.out.println("Username:" + username);
			System.out.println("Host:" + host);
			System.out.println("SMTP:" + smtp);
			if(i_or_p)
			System.out.println("POP3:" + pop3);
			else
			System.out.println("IMAP:" + imap);
		}
		else
		{
			System.out.println("User Information is not configured");
		}
	}
	
	public boolean isConfig()
	{
		if(this.username.equals("") || this.host.equals("") || this.smtp.equals("") || this.imap.equals("") || this.pop3.equals("") || this.isConfig == false)
		{
			System.out.println("User is not configured");
			return false;
		}
		System.out.println("User is configured");
		printInfo();
		return true;
	}
}
