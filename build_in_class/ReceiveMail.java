package build_in_class;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.*;
import javax.mail.*;

import org.bouncycastle.util.encoders.Hex;

import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3Store;

public class ReceiveMail
{
	public List<MailItem> listMail;
	public List<MailItem> deletedMail;
	
	public static void main(String[] args)
	{
		ReceiveMail r = new ReceiveMail();
		r.recv_mail("gmail", "timber.shaw6905@gmail.com", "kumnang2*69", false);
		/*
		for (int i = 0; i < r.listMail.size(); i++)
		{
			System.out.println("\n===============\nEmail #" + r.listMail.get(i).id);
			System.out.println("From: " + r.listMail.get(i).from.name + " <" + r.listMail.get(i).from.emailAddr + ">");
			System.out.println("To: " + r.listMail.get(i).to.name + " <" + r.listMail.get(i).to.emailAddr + ">");
			System.out.println("Date: " + r.listMail.get(i).date.Date2String());
			System.out.println("Subject: " + r.listMail.get(i).subject);
			//System.out.println("From: " + r.listMail.get(i).from);
		}*/
		/*
		try
		{
			r.LoadEmail();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		r.printListMail();
		/*
		try
		{
			r.StoreEmail();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public ReceiveMail()
	{
		listMail = new ArrayList<MailItem>();
		deletedMail = new ArrayList<MailItem>();
	}
	
	public void StoreDeletedMail() throws IOException
	{

		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "\\db";
		String temp = p + "\\";
		
		// synchronize listMail and deletedMail
		PrintWriter t = new PrintWriter(p + "deleted", "UTF-8");
		t.println(deletedMail.size());
		for (int i = 0; i < deletedMail.size(); i++)
		{
			t.println(deletedMail.get(i).id);
		}
		t.close();
		for (int i = 0; i < deletedMail.size(); i++)
		{
			int j = -1;
			if ((j = listMail.indexOf(deletedMail.get(i))) > -1)
			{
				listMail.remove(j);
			}
			
			File f = new File(temp + deletedMail.get(i).id + ".mail");
			f.delete();			
		}
		StoreEmail();
	}
	
	public void StoreEmail() throws IOException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "\\db";
		File temp = new File(p);
		temp.mkdirs();
		PrintWriter t = new PrintWriter(p + "\\total", "UTF-8");
		System.out.println("Total: " + listMail.size());
		t.println(listMail.size());
		for (int i = 0; i < listMail.size(); i++)
		{
			t.println(listMail.get(i).id);
		}
		t.close();
		for (int i = 0; i < listMail.size(); i++)
		{
			try 
			{
				listMail.get(i).print2file();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void LoadEmail() throws NumberFormatException, IOException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "\\db";

		BufferedReader br;
		try 
		{
			br = new BufferedReader(new FileReader(p + "\\total"));
		} catch (FileNotFoundException e)
		{
			StoreEmail();
			//e.printStackTrace();
			return;
		}
		int total = Integer.parseInt(br.readLine());
		System.out.println("Total: " + total);
		for (int i = 0; i < total; i++)
		{
			MailItem m = new MailItem();
			m.id = Long.parseLong(br.readLine());
			m.readfile();
			
			boolean flag = false;
			for (int j = 0; j < listMail.size(); j++)
			{
				if (m.isDup(listMail.get(j)))
				{
					flag = true;
					break;
				}
			}
			
			if (flag == false)
			{
				System.out.println("\n====\nLoaded Mail ID: " + m.id);
				listMail.add(m);
			}
		}
		br.close();
	}
	
	public void printListMail()
	{
		System.out.println("Total: " + listMail.size());
		System.out.format("%1$-10s | %2$-30s | %3$-30s | %4$-30s | %5$-30s\n", "ID", "Name", "From", "Date", "Subject");
		System.out.println("===================================================================================================================================");
		for(int i = 0; i < listMail.size(); i++)
		{
			System.out.printf("%1$-10d | %2$-30s | %3$-30s | %4$-30s | %5$-30s\n", listMail.get(i).id, listMail.get(i).from.name, listMail.get(i).from.emailAddr, listMail.get(i).date.Date2String(), listMail.get(i).subject);
		}
	}

	public void recv_mail(String host, String user, String pass, boolean mode) // mode == true (imap), mode == false (pop3)
	{
		Properties props = new Properties();
		try 
		{
			this.LoadEmail();
		} catch (NumberFormatException | IOException e)
		{
			e.printStackTrace();
		}
		this.printListMail();
		
		if(mode == false) // using imap
		{
			props.setProperty("mail.store.protocol", "imaps");
			try 
			{
	            Session session = Session.getInstance(props, null);
	            Store store = session.getStore();
	            if(host.equals("gmail"))
	            	store.connect(constant.gmail_imap_host, user, pass);
	            if(host.equals("yahoo"))
	            	store.connect(constant.yahoo_imap_host, user, pass);
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);
	            UIDFolder uf = (UIDFolder)  inbox;
	            long uid = 0;
	            Message[] msg = inbox.getMessages();
	            for(int i = 0; i< msg.length; i++)
	            {
	            	Message mess = msg[i];
	            	uid = uf.getUID(mess);
	   
		            MailItem m = new MailItem();
	            	System.out.println("=========\nEmail " + i + "\n");
		            Address[] in = mess.getFrom();
		            System.out.println("Message UID:" + uid);
		            System.out.println("Mail number: " + mess.getMessageNumber());
		            for (Address address : in)
		            {
		                //System.out.println("FROM:" + address.toString());
		                if(address.toString().lastIndexOf(' ') == -1)
		                {
		                	m.from.emailAddr = address.toString();
		                }
		                else
		                {
			                m.from.name = address.toString().substring(0, address.toString().lastIndexOf(' '));
			                m.from.emailAddr = address.toString().substring(address.toString().indexOf('<') + 1, address.toString().indexOf('>'));
		                }
		            }
		            m.to.name = "Me";
		            m.to.emailAddr = user;
		            Object content = mess.getContent();
		            //System.out.println("SENT DATE:" + mess.getSentDate());
		            
		            m.date.day = mess.getSentDate().getDay();
		            m.date.month = mess.getSentDate().getMonth();
		            m.date.year = mess.getSentDate().getYear() + 1900;
		            m.date.hour = mess.getSentDate().getHours();
		            m.date.minute = mess.getSentDate().getMinutes();
		            
		            //System.out.println("SUBJECT:" + mess.getSubject());
		            m.subject = mess.getSubject();
		            
		            if (content instanceof Multipart)
		            {
		            	Multipart mp = (Multipart) content;
		            	BodyPart bp = mp.getBodyPart(0);
			            //System.out.println("CONTENT:\n" + bp.getContent());
			            m.content = bp.getContent();
		            }
		            else if (content instanceof String)
		            {
		            	//System.out.println("CONTENT:\n" + mess.getContent());
		            	m.content = mess.getContent();
		            }
		            
		            boolean flag = false;
		            m.id = uid;
		            for (int j = 0; j < listMail.size(); j++)
		            {
		            	if(m.isDup(listMail.get(j)))
		            	{
		            		flag = true;
		            		System.out.println("Duplicated ID: " + listMail.get(j).id);
		            		break;
		            	}
		            }
		            
		            for (int j = 0; j < deletedMail.size(); j++)
		            {
		            	if(m.isDup(deletedMail.get(j)))
		            	{
		            		flag = true;
		            		System.out.println("Deleted Mail ID: " + listMail.get(j).id);
		            		break;
		            	}
		            }
		            
		            if (flag == false) // is not duplicate
		            {
		            	System.out.println("is not duplicate");
		            	/*
			            if(listMail.size() == 0)
			            	m.id = 0;
			            else
			            	m.id = listMail.get(listMail.size() - 1).id + 1;
			            	*/
			            listMail.add(m);
			            System.out.println("Add message to list");
		            }
	            }
	        } catch (Exception mex)
			{
	            mex.printStackTrace();
	        }
		}
		else // using pop3
		{
			props.setProperty("mail.store.protocol", "pop3s");
			try 
			{
	            Session session = Session.getInstance(props, null);
	            Store store = session.getStore();
	            if(host.equals("gmail"))
	            	store.connect(constant.gmail_pop3_host, user, pass);
	            if(host.equals("yahoo"))
	            	store.connect(constant.yahoo_pop3_host, user, pass);
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);
	            com.sun.mail.pop3.POP3Folder uf = (com.sun.mail.pop3.POP3Folder) inbox;
	            long uid = 0;
	            Message[] msg = inbox.getMessages();
	            for(int i = 0; i< msg.length; i++)
	            {
	            	Message mess = msg[i];
	            	if(inbox instanceof com.sun.mail.pop3.POP3Folder)
	            		uf = (com.sun.mail.pop3.POP3Folder) inbox;
	            	System.out.println(uf.getUID(mess));
	            	if(host.equals("gmail"))
	            		uid = Long.valueOf(uf.getUID(mess).substring(uf.getUID(mess).length() - 3, uf.getUID(mess).length()), 16);
	            	else
	            	{
	            		String id = uf.getUID(mess);
	            		uid = ByteBuffer.wrap(Hex.encode(id.getBytes())).getLong();
	            	}
		            MailItem m = new MailItem();
	            	System.out.println("=========\nEmail " + i + "\n");
		            Address[] in = mess.getFrom();
		            System.out.println("Mail UID: " + uid);
		            for (Address address : in)
		            {
		                //System.out.println("FROM:" + address.toString());
		                if(address.toString().lastIndexOf(' ') == -1)
		                {
		                	m.from.emailAddr = address.toString();
		                }
		                else
		                {
			                m.from.name = address.toString().substring(0, address.toString().lastIndexOf(' '));
			                m.from.emailAddr = address.toString().substring(address.toString().indexOf('<') + 1, address.toString().indexOf('>'));
		                }
		            }
		            m.to.name = "Me";
		            m.to.emailAddr = user;
		            Object content = mess.getContent();
		            //System.out.println("SENT DATE:" + mess.getSentDate());
		            
		            m.date.day = mess.getSentDate().getDay();
		            m.date.month = mess.getSentDate().getMonth();
		            m.date.year = mess.getSentDate().getYear() + 1900;
		            m.date.hour = mess.getSentDate().getHours();
		            m.date.minute = mess.getSentDate().getMinutes();
		            
		            //System.out.println("SUBJECT:" + mess.getSubject());
		            m.subject = mess.getSubject();
		            
		            if (content instanceof Multipart)
		            {
		            	Multipart mp = (Multipart) content;
		            	BodyPart bp = mp.getBodyPart(0);
			            //System.out.println("CONTENT:\n" + bp.getContent());
			            m.content = bp.getContent();
		            }
		            else if (content instanceof String)
		            {
		            	//System.out.println("CONTENT:\n" + mess.getContent());
		            	m.content = mess.getContent();
		            }
		            
		            boolean flag = false;
		            m.id = uid;
		            for (int j = 0; j < listMail.size(); j++)
		            {
		            	if(m.isDup(listMail.get(j)))
		            	{
		            		flag = true;
		            		System.out.println("Duplicated ID: " + listMail.get(j).id);
		            		break;
		            	}
		            }

		            for (int j = 0; j < deletedMail.size(); j++)
		            {
		            	if(m.isDup(deletedMail.get(j)))
		            	{
		            		flag = true;
		            		System.out.println("Deleted Mail ID: " + listMail.get(j).id);
		            		break;
		            	}
		            }
		            
		            if (flag == false) // is not duplicate
		            {
			            /*if(listMail.size() == 0)
			            	m.id = 0;
			            else
			            	m.id = listMail.get(listMail.size() - 1).id + 1;
			            	*/
			            listMail.add(m);
		            }
	            }
	        } catch (Exception mex)
			{
	            mex.printStackTrace();
	        }
		}
		try
		{
			this.StoreEmail();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
