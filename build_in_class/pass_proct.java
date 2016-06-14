package build_in_class;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class pass_proct
{
	public static void main(String[] args) throws IOException 
    {
		/*Path currentRelativePath = Paths.get("");
		String p = currentRelativePath.toAbsolutePath().toString();*/
		String s;
		Scanner inp = new Scanner(System.in);
		  
		System.out.print("Nhap chuoi: ");
		s = inp.nextLine();
		pass_proct p = new pass_proct();
		try
		{
			String rightPath = p.String2Path(s);
			System.out.println(rightPath.substring(rightPath.indexOf("\\proct"), rightPath.lastIndexOf("\\")));
			p.create_dir_proct(s);
			p.SHA(s, p.String2Path(s));
			System.out.println("Authority Result: " + p.authentication(s));
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String ecryptPass(String pass)
	{
		String e = pass;
		
		return e;
	}
	
	public static String String2Path(String input) throws IOException
	{
		// input: Chuoi string
		// output: duong dan den file passwd.sha
		// Vi du:
		// 	input: abc
		//	output: "<current_dir>\\proct\\97\\98\\99\\passwd.sha"
		// Luu y: <current_dir> duoc lay bang code:
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
   
		String path = "";
        path = path + p;
        path = path + "\\";
        path = path + "proct";
        path = path + "\\";
        for (int i = 0; i < input.length(); i++) 
        {
            int acii = (int) input.charAt(i);
            String temp = "" + acii;
            path = path + temp;
            path = path + "\\";
        }
        path = path + "passwd.sha";
        
		return path;
	}

	public static String Path(String input) throws IOException
	{
		// input: Chuoi string
		// output: duong dan den file passwd.sha
		// Vi du:
		// 	input: abc
		//	output: "<current_dir>\\proct\\97\\98\\99\\passwd.sha"
		// Luu y: <current_dir> duoc lay bang code:
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
   
		String path = "";
        path = path + p;
        path = path + "\\";
        path = path + "proct";
        path = path + "\\";
        for (int i = 0; i < input.length(); i++) 
        {
            int acii = (int) input.charAt(i);
            String temp = "" + acii;
            path = path + temp;
            path = path + "\\";
        }            
		return path;
	}
	
	public static void SHA(String input, String path)
	{
		// input: chuoi String
		// output: hash bang SHA256 va ghi ma hash vao file trong duong dan String2Path(input)
		try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            
            for (int i = 0; i < hash.length; i++)
            {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) 
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            String result = hexString.toString();
            
            File f = new File(path.substring(0, path.lastIndexOf('\\')));
            if(!f.exists())
            {
            	f.mkdirs();
            }
            
            FileOutputStream file = new FileOutputStream(path);
            PrintWriter writer = new PrintWriter(file,true);
            writer.println(result);
            
            file.close();
            writer.close();
        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex);
        }
	}
	
	public static boolean authentication(String input)
	{
		// Xac thuc dung password
		// input: chuoi String.
		// output: xac nhan password input co dung hay khong thong qua so sanh voi gia tri hash chua trong String2Path(input). Neu match return 1, not match return 0.
		// prototype:
			// 1. Bam gia tri input bang SHA256
			// 2. Lay duong dan String2Path(input)
			// 3. Doc file passwd.sha trong duong dan vua lay.
			// 4. So sanh gia tri bam SHA256 và gia tri chua trong file passwd.sha
		try
        {
            String path = pass_proct.String2Path(input);
        
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            
            for (int i = 0; i < hash.length; i++)
            {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) 
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            String result = hexString.toString();
            File f = new File(path);
            if(!f.exists())
            	return false;
            FileInputStream file = new FileInputStream(path);
            
            Scanner reader = new Scanner(file, "UTF-8");
            String pass = "";
           
            while (reader.hasNextLine())
            {
                pass = reader.nextLine();
            }
            
            file.close();
            reader.close();
            
            if (result.equals(pass))
            {
               return true; 
            }    
            else
            {
                return false;
            }
	    }
	    catch (IOException | NoSuchAlgorithmException e) 
	    {
	        e.printStackTrace();
	    }
        return true;
	}
	
	public static void create_dir_proct(String input) throws IOException, NoSuchAlgorithmException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		//System.out.println("Current relative path is: " + p);
		File root = new File(p + "\\proct");
		root.mkdirs();
		List<String> path = new ArrayList<String>();
		  
		for (int i = 0; i < input.length(); i++)
		{
		  int ascii = (int) input.charAt(i);
		    
		  path.add("" + ascii);
		}
        
        String rightPath = String2Path(input);
        /*
        for (int i = 0; i < path.size(); i++)
        {
        	if(!path.get(i).equals(rightPath.substring(rightPath.indexOf("\\proct"), rightPath.lastIndexOf("\\"))))
        	{
    			System.out.println("Determine whether remove or not " + path.get(i));
    			int d = Math.round(Math.round(Math.random()*5));
        		if(d >= 0 && d < 4)
        		{
        			System.out.println("Removed " + path.get(i));
        			path.remove(i);
        			i--;
        		}
        	}
        }
        */
		final int high_of_tree = input.length();
		pass_proct.mkDirs(root, path, high_of_tree, rightPath, input);
	}
	
	public static void mkDirs(File root, List<String> dirs, int high_of_tree, String rightPath, String input) throws IOException, NoSuchAlgorithmException
	{
        if (high_of_tree == 0)
        {
			// Lam them phan tao file passwd.sha trong day nha
			// thong tin chua trong passwd.sha la gia tri bam SHA256 cua mot chuoi bat ki
			return;
        }
        
        for (String s : dirs)
        {
	     	File subdir = new File(root, s);
	     	subdir.mkdir();

            System.out.println("Path: " + subdir.toString());
            
            String filePath = subdir.getCanonicalPath();
            filePath = filePath + "\\passwd.sha";
            
            if (filePath.equals(rightPath))
            {
                File file_2 = new File(subdir,"passwd.sha");
                file_2.createNewFile(); 
                System.out.println("filePath: " + filePath);
                System.out.println("rightPath: " + rightPath);
                SHA(input, filePath);
                break;
            }
            else
            {
            	int d = Math.round(Math.round(Math.random()*56));
            	System.out.println("High of tree: " + high_of_tree(filePath));
            	if(high_of_tree(filePath) < 4)
        		{
                    System.out.println("filePath: " + filePath);
                    //System.out.println("rightPath: " + rightPath);
	                File file_2 = new File(subdir,"passwd.sha");
	                if(!file_2.exists())
	                	file_2.createNewFile(); 
	                
	                String temp = String.valueOf(Math.random() * 99999) + "efugwefiu87";
	                MessageDigest digest = MessageDigest.getInstance("SHA-256");
	                byte[] hash = digest.digest(temp.getBytes("UTF-8"));
	                StringBuffer hexString = new StringBuffer();
	                    
	                for (int i = 0; i < hash.length; i++)
	                {
	                    String hex = Integer.toHexString(0xff & hash[i]);
	                    if(hex.length() == 1) 
	                    {
	                        hexString.append('0');
	                    }
	                    hexString.append(hex);
	                }
	            
	                String result = hexString.toString();
	            
	                FileOutputStream file = new FileOutputStream(filePath);
	            
	                PrintWriter writer = new PrintWriter(file,true);
	                writer.println(result);
	            
	                file.close();
	                writer.close();
	                
	                //System.out.println("hash: " + result);
	                //System.out.println();
        		}
            	else
            	{
            		System.out.println("Removed : "  + filePath);
            	}
            }
			//System.out.println("Create Directory: " + subdir.toString());
            if(high_of_tree(filePath) < 4)
            	mkDirs(subdir, dirs, high_of_tree - 1, rightPath, input);
        }
	}
	
	public static int high_of_tree(String filePath)
	{
		filePath = filePath.substring(filePath.indexOf("\\proct"), filePath.lastIndexOf("\\"));
		int c =0;
		for(int i = 0; i < filePath.length(); i++)
		{
			if(filePath.charAt(i) == '\\')
				c++;
		}
		 return c;
	}
}
