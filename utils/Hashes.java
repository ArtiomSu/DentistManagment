package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Hashes {

	String salt = "";
	public Hashes(){
		
	}
	
	private void genSalt(){
		SecureRandom random = new SecureRandom();
		byte[] salty = random.generateSeed(32);
		salt = new String(salty);
	}
	
	public String getSalt(){
		return salt;
	}
	
	public void setSalt(String s){
		salt = s;
	}
	
	public String hashWord(String word){
		if(salt == null){genSalt();}
		String forhash = word + salt;
		String encrypted = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(forhash.getBytes());
			encrypted = new String(messageDigest.digest());
			

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return encrypted;
	}
	
	public boolean compareHash(String hashed, String word, String salty){
		String newhashed = hashWord(word + salty);
		boolean isequal = false;
		if(hashed.equals(newhashed)){
			isequal = true;
		}
		
		return isequal;
	}
	
	
	
}
