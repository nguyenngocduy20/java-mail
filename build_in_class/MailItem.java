package build_in_class;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MailItem
{
	//public String name;
	public long id;
	public String subject;
	public Date date;
	public People from;
	public People to;
	public People cc;
	public Object content;
	
	public MailItem()
	{
		//name = "unknown";
		id = -1;
		subject = "none";
		from = new People();
		date = new Date();
		to = new People();
		cc = new People();
		content = new Object();
	}
	
	public boolean isDup(MailItem m)
	{
		//if(m.from.emailAddr.equals(this.from.emailAddr) && m.id == this.id && m.date.isEqual(this.date))
		if(m.id == this.id)
			return true;
		return false;
	}
	
	public void print2file() throws IOException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "\\db";
		PrintWriter f = new PrintWriter(p + "\\" + id + ".mail", "UTF-8");
		f.println(id);
		f.println(from.name + " <" + from.emailAddr + ">");
		f.println(to.name + " <" + to.emailAddr + ">");
		f.println(date.Date2String());
		f.println(subject);
		f.println(content.toString());
		f.close();
	}
	
	public void readfile() throws IOException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "\\db\\" + id + ".mail";
		
		BufferedReader br = new BufferedReader(new FileReader(p));
		br.readLine();
		String temp = br.readLine();
		if (temp == null)
			return;
        from.name = temp.toString().substring(0, temp.toString().lastIndexOf(' '));
        from.emailAddr = temp.toString().substring(temp.toString().indexOf('<') + 1, temp.toString().indexOf('>'));
        
        temp = br.readLine();
        to.name = temp.toString().substring(0, temp.toString().lastIndexOf(' '));
        to.emailAddr = temp.toString().substring(temp.toString().indexOf('<') + 1, temp.toString().indexOf('>'));
        
        date.String2Date(br.readLine());
        subject = br.readLine();
        
        System.out.println("Reading Email ID : " + this.id);
        System.out.println(from.name + " <" + from.emailAddr + ">");
        System.out.println(to.name + " <" + to.emailAddr + ">");
        System.out.println(date.Date2String());
        System.out.println(subject);
        
        temp = br.readLine();
        content = (Object) "";
        while(temp != null)
        {
        	content = (Object) content.toString() + temp + "\n";
        	temp = br.readLine();
        }
        //System.out.println("Mail Content: \n" + content.toString());
        br.close();
	}
}
