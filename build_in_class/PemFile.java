package build_in_class;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Key;
 
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;
 
public class PemFile {
	private PemObject pemObject;
	 
	public PemFile (Key key, String description)
	{
		this.pemObject = new PemObject(description, key.getEncoded());
	}
	 
	public PemFile (String filename)
	{
		
	}
	
	public void write(String filename) throws FileNotFoundException, IOException
	{
		PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
		try 
		{
			pemWriter.writeObject(this.pemObject);
			System.out.println("PEM content: " + this.pemObject.getContent());
		} finally
		{
			pemWriter.close();
		}
	}
	
	public PemObject read(String filename)throws FileNotFoundException, IOException
	{
		PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
		try
		{
			this.pemObject = pemReader.readPemObject();
		} finally
		{
			pemReader.close();
		}
		return this.pemObject;
	}
}