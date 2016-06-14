package build_in_class;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

import java.io.*;
import java.math.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class crypto
{
	SecretKey secret;
	public PublicKey pub;
	PrivateKey pri;
	public KeyPair kp;
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, IOException
	{
		// TODO Auto-generated method stub
		
		crypto c = new crypto();
		c.importKey("timber.shaw6905@gmail.com", 1);
		String data = "abcd";
		try
		{
			// sender site
			byte[] sign = c.sign(data);
			String sendData = new String(Hex.encode(sign));
			System.out.println(new String(Hex.encode(sign)));
			
			// receiver
			crypto d = new crypto();
			byte[] sign_recv = Hex.decode(sendData);
			boolean i = d.very_sign(sign_recv, "timber.shaw6905@yahoo.com", data.getBytes());
			System.out.println("Signature is match? " + i);
		} catch (SignatureException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		c.keyPairGenerator();
		c.exportKey(c.kp, "timber.shaw6905@gmail.com", 1);
		c.keyPairGenerator();
		c.exportKey(c.kp, "nguyenngoc_duy20@yahoo.com", 1);
		SecretKey k = c.secretKeyGen_S("1234");
		byte[]  e = c.encryptSymmetric("123456789123456789", k, 0);
		String s = new String(e);
		System.out.println(new String(Hex.encode(e)));
		System.out.println(s);
		s = "7cd70cccacd3b280400252fa720e2cba35deeb6deb5952d9233ccb3442a20a51";
		System.out.println(s);
		e = Hex.decode(s);
		System.out.println(new String(e));
		System.out.println(c.decryptSymmetric(e, k, 0));
		*/
		
		/*
		c.importKey("nguyenngoc.duy20@gmail.com", 1);
		SecretKey k = c.secretKeyGen_S("abcdefgh");
		byte[] pl = c.encryptAsymmetric("Plain Text", k, c.pub, 0);
		System.out.println(c.pri.toString());
		KeyFactory fact = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pub = fact.getKeySpec(c.pub, RSAPublicKeySpec.class);
		RSAPrivateKeySpec pri = fact.getKeySpec(c.pri,RSAPrivateKeySpec.class);
		BigInteger m = pri.getModulus();
		BigInteger e = pri.getPrivateExponent();
		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(m, e);
		c.pri = fact.generatePrivate(priKeySpec);
		
		System.out.println(c.pri.toString());
		System.out.println("Modulus: " + m + "\nExponent: " + e);
		System.out.println("Decrypted: " + c.decryptAsymmetric(pl, 0));
		*/
		
		/*
		//SecretKey k = c.secretKeyGen();
		//String content = "abc";
		//byte[] a = c.encryptSymmetric(content, k, 0);
		String b = c.decryptSymmetric(a, k, 0);
		System.out.println("Encrypted: " +  new String(a, "UTF-8") + "\nDecrypted: " + b);
		c.protectPrivate("nguyenngoc.duy20@gmail.com", "abcdefgh");
		c.deProtectPrivate("nguyenngoc.duy20@gmail.com", "abcdefgh");
		
		
		c.exportKey(c.keyPairGenerator(), "nguyenngoc.duy20@gmail.com", 1);
		System.out.println("Public key: " + c.pub.toString() + "\nPrivate key: " + c.pri.toString());
		
		crypto d = new crypto();
		d.importKey("nguyenngoc.duy20@gmail.com", 1);
		System.out.println("Public key: " + d.pub.toString() + "\nPrivate key: " + d.pri.toString());
		//c.protectPrivate("nguyenngoc.duy20@gmail.com");
		*/
	}
	
	// symmetric encryption
	public SecretKey secretKeyGen() throws NoSuchAlgorithmException
	{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		return this.secret = keyGen.generateKey();
	}
	
	public SecretKey secretKeyGen_S(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(s.getBytes("UTF-8"));
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
        //System.out.println("Key size: " + hash.length + " bytes\nHash: " + result);
        
        return this.secret = new SecretKeySpec(hash, 0, hash.length/2, "AES");
	}
	
	public byte[] encryptSymmetric(String content, SecretKey k, int flag) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
	{
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, k);
		byte[] data;
		byte[] encryptedData = {};
		
		if(flag == 0) // content is string
		{
			data = content.getBytes();
			encryptedData = c.doFinal(data);
		}
		
		if(flag == 1) // content is file
		{
			
		}
		return encryptedData;
	}
	
	public String decryptSymmetric(byte[] encryptedData, SecretKey k, int flag) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
	{
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, k);
		
		byte[] data = {};
		if(flag == 0)
		{
			data = c.doFinal(encryptedData);
		}
		
		if(flag == 1)
		{
			
		}
		return new String(data, "UTF-8");
	}
	
	// asymmetric encryption
	public KeyPair keyPairGenerator() throws NoSuchAlgorithmException
	{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair keyPair = kpg.generateKeyPair();
		kp = keyPair;
		pub = keyPair.getPublic();
		pri = keyPair.getPrivate();
		return keyPair;
	}
	
	public Key exportKey(KeyPair kp, String email, int flag) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		// flag == 0, export public key
		// flag == 1, export public/private key
		//Key k = kp.getPublic();
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "//keys";
		File root = new File(p);
		root.mkdirs();
		
		String temp = p + "//pubKey_" + email.substring(0, email.indexOf('@'));
		KeyFactory fact = KeyFactory.getInstance("RSA");
		PemFile pemFile = new PemFile(pub, "RSA PUBLIC KEY");
		pemFile.write(temp);
		System.out.println("Write public key to " + temp.substring(temp.lastIndexOf("\\"), temp.length()));
		/*
		RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(), RSAPublicKeySpec.class);
		RSAPrivateKeySpec pri = fact.getKeySpec(kp.getPrivate(),RSAPrivateKeySpec.class);
		
		ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(temp)));
		try 
		{
		  oout.writeObject(pub.getModulus());
		  oout.writeObject(pub.getPublicExponent());
		} catch (Exception e) 
		{
		  throw new IOException("IO fault", e);
		} finally 
		{
		  oout.close();
		}
		*/
		if (flag == 1)
		{
			temp = p + "//priKey_" + email.substring(0, email.indexOf('@'));
			
			/*oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(temp)));
			try 
			{
			  oout.writeObject(pri.getModulus());
			  oout.writeObject(pri.getPrivateExponent());
			} catch (Exception e) 
			{
			  throw new IOException("IO fault", e);
			} finally 
			{
			  oout.close();
			}
			*/
			pemFile = new PemFile(pri, "RSA PRIVATE KEY");
			pemFile.write(temp);
			System.out.println("Write private key to " + temp.substring(temp.lastIndexOf("\\"), temp.length()));
		}
		return pub;
	}
	
	public Key importKey(String email, int flag) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		// flag == 0, import public key
		// flag == 1, import public/private key
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "//keys";
		String temp = p + "//pubKey_" + email.substring(0, email.indexOf('@'));
		PemFile pemFile = new PemFile(temp);
		byte[] content = pemFile.read(temp).getContent();
		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
		KeyFactory fact = KeyFactory.getInstance("RSA");
		pub = fact.generatePublic(pubKeySpec);
		
		/*
		InputStream in = new FileInputStream(temp);
		ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));
		BigInteger m = new BigInteger("0");
		BigInteger e = new BigInteger("0");
		try {
			m = (BigInteger) oin.readObject();
			e = (BigInteger) oin.readObject();
		} catch (Exception e1)
		{
			throw new RuntimeException("Runtime fault", e1);
		} finally
		{
			oin.close();
		}
		
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(m, e);
		KeyFactory fact = KeyFactory.getInstance("RSA");
		pub = fact.generatePublic(pubKeySpec);
		*/
		if (flag == 1)
		{
			temp = p + "//priKey_" + email.substring(0, email.indexOf('@'));
			pemFile = new PemFile(temp);
			content = pemFile.read(temp).getContent();
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(content);
			pri = fact.generatePrivate(privateKeySpec);
			/*
			in = new FileInputStream(temp);
			oin = new ObjectInputStream(new BufferedInputStream(in));
			m = new BigInteger("0");
			e = new BigInteger("0");
			try {
				m = (BigInteger) oin.readObject();
				e = (BigInteger) oin.readObject();
			} catch (Exception e1)
			{
				throw new RuntimeException("Runtime Fault", e1);
			} finally
			{
				oin.close();
			}
			
			RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(m, e);
			fact = KeyFactory.getInstance("RSA");
			pri = fact.generatePrivate(priKeySpec);
			*/
		}
		
		return this.pub;
	}
	
	public byte[] encryptAsymmetric(String content, SecretKey k, Key pubKey, int flag)
	{
		// encrypt data
		byte[] encryptedData = null;
		try {
			encryptedData = this.encryptSymmetric(content, k, flag);
			System.out.println("Encrypted Data length: " + encryptedData.length);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// encrypt key
		byte[] encryptedKey = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			encryptedKey = cipher.doFinal(k.getEncoded());
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// determine encrypted key length
		int l = encryptedKey.length;
		ByteBuffer b = ByteBuffer.allocate(4);
		b.order(ByteOrder.LITTLE_ENDIAN);
		b.putInt(l);
		byte[] keyLength = b.array();
		System.out.println("Encrypted Key length: " + l + "\t" + encryptedKey.toString());
		byte[] temp = new byte[4 + encryptedKey.length + encryptedData.length];
		
		// padding byte[] message
		for (int i = 0; i < 4; i++)
		{
			temp[i] = keyLength[i];
		}

		for(int i = 0; i < encryptedKey.length; i++)
		{
			temp[i + 4] = encryptedKey[i];
		}
		
		for(int i = 0; i < encryptedData.length; i++)
		{
			temp[i + encryptedKey.length + 4] = encryptedData[i];
		}
		System.out.println("Total Encrypted: " + temp.length + "\t" + temp.toString());
		return temp;
	}
	
	public String decryptAsymmetric(byte[] encrypted, int flag)
	{
		// parsing encrypted message
		System.out.println("Total Encrypted: " + encrypted.length + "\t" + encrypted.toString());
		byte[] keyLength = {encrypted[0], encrypted[1], encrypted[2],encrypted[3]};

		ByteBuffer b = ByteBuffer.wrap(keyLength);
		b.order(ByteOrder.LITTLE_ENDIAN);
		int l = b.getInt();
		byte[] encryptedKey = new byte[l];
		
		for (int i = 0; i < l; i++)
		{
			encryptedKey[i] = encrypted[i + 4];
		}

		System.out.println("Encrypted Key length: " + encryptedKey.length + "\t" + encryptedKey.toString());
		byte[] encryptedData = new byte[encrypted.length - l - 4];
		for (int i = l + 4; i < encrypted.length; i++)
		{
			encryptedData[i - l - 4] = encrypted[i];
		}
		
		// decrypt
		String result = "";
		try
		{
			// decrypt key
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, pri);
			byte[] decrypted = cipher.doFinal(encryptedKey);
			System.out.println("Decrypted Key length: " + decrypted.length);
			
			// decrypt data
			System.out.println("Encrypted Data length: " + encryptedData.length);
			SecretKey k = new SecretKeySpec(decrypted, 0, decrypted.length, "AES");
			result = this.decryptSymmetric(encryptedData, k, flag);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public byte[] sign(String data) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException
	{
		byte[] s = data.getBytes("UTF-8");
		Signature sign = Signature.getInstance("MD5WithRSA");
		sign.initSign(this.pri);
		sign.update(s);
		return sign.sign();
	}
	
	public boolean very_sign(byte[] s, String email, byte[] content) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, InvalidKeyException, SignatureException
	{
		crypto c = new crypto();
		PublicKey pubKey = (PublicKey) c.importKey(email, 0);
		Signature sign = Signature.getInstance("MD5WithRSA");
		sign.initVerify(c.pub);
		
		sign.update(content);
		
		return sign.verify(s);
	}
	
	public void protectPrivate(String email, String pass) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "//keys";
		String temp = p + "//priKey_" + email.substring(0, email.indexOf('@'));

		BufferedReader br = new BufferedReader(new FileReader(temp));
		String content = "";
		String buffer = "";
		while ((buffer = br.readLine()) != null)
		{
			if(content.equals(""))
				content = buffer;
			else
				content = content + "\n" + buffer;
		}
		br.close();
		
		crypto c = new crypto();
		c.secretKeyGen_S(pass);
		byte[] encrypted = c.encryptSymmetric(content, c.secret, 0);
		
		FileOutputStream f = new FileOutputStream(temp);
		f.write(encrypted);
		f.close();
	}
	
	public void deProtectPrivate(String email, String pass) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String p = currentDirectory.getCanonicalPath();
		p = p + "//keys";
		String temp = p + "//priKey_" + email.substring(0, email.indexOf('@'));
		
		File f = new File(temp);
		byte[] encrypted = null;
		FileInputStream fin = null;
		try
		{
			fin = new FileInputStream(f);
			encrypted = new byte[(int) f.length()];
			fin.read(encrypted);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		finally
		{
			if(fin != null)
			{
				fin.close();
			}
		}
		
		crypto c = new crypto();
		c.secretKeyGen_S(pass);
		String content  = c.decryptSymmetric(encrypted, c.secret, 0);
		PrintWriter fout = new PrintWriter(temp);
		fout.print(content);
		fout.close();
	}
}
