package build_in_class;
import javax.crypto.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.*;
import java.util.Scanner;

public class People
{
	public String name;
	public Key publicKey;
	public String emailAddr;
	
	public People()
	{
		name = "unknown";
		emailAddr = "none";
	}
	
	public People(People p)
	{
		name = p.name;
		publicKey = p.publicKey;
		emailAddr = p.emailAddr;
	}
	
	public People (String name, Key publicKey, String emailAddr)
	{
		this.name = name;
		this.publicKey = publicKey;
		this.emailAddr = emailAddr;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Key getPubicKey()
	{
		return publicKey;
	}
	
	public String getEmailAddr()
	{
		return emailAddr;
	}
	
	public void setPublicKey(Key publicKey)
	{
		this.publicKey = publicKey;
	}

	public void setEmailAddr(String emailAddr)
	{
		this.emailAddr = emailAddr;
	}
	
	public boolean validEmailAddr()
	{
		int index1 = emailAddr.indexOf('@', 0);
		
		if (emailAddr.indexOf('@', index1) > index1) // in case of duplicate @
			return false;
		
		if (emailAddr.indexOf('.',  index1) == -1) // in case, there is not dot(.) character after @
			return false;
		return true;
	}
	
	public boolean validEmailAddr(String emailAddr)
	{
		int index1 = emailAddr.indexOf('@', 0);
		
		if (emailAddr.indexOf('@', index1) > index1) // in case of duplicate @
			return false;
		
		if (emailAddr.indexOf('.',  index1) == -1) // in case, there is not dot(.) character after @
			return false;

		return true;
	}
	
	public boolean isExit(String s) throws IOException
    {
        File currentDirectory = new File(new File(".").getAbsolutePath());
        String p = currentDirectory.getCanonicalPath();
        p = p + "\\user\\people.txt";
	
        FileInputStream file = new FileInputStream(p);
            
        Scanner reader = new Scanner(file, "UTF-8");
        String temp = "";
        
        while (reader.hasNextLine())
        {
            temp = reader.nextLine();
            
            if (s.equals(temp))
            {
                file.close();
                reader.close();
                return true;
            }
        } 
        file.close();
        reader.close();
        return false;
    }
    
    public void write2flie(String s) throws IOException
    {
        if (this.isExit(s) == false)
        {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String p = currentDirectory.getCanonicalPath();
            p = p + "\\user\\people.txt";
            
            FileOutputStream file = new FileOutputStream(p);
            
            PrintWriter writer = new PrintWriter(file,true);
            writer.println(s);
            
            file.close();
            writer.close();
        }
    }
}
