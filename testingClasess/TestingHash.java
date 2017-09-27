package testingClasess;

import utils.Hashes;

public class TestingHash {

	public static void main(String[] args) {

		Hashes hash = new Hashes();
		String password = "bobisnotmyuncle";
		password = hash.hashWord(password);
		String salt = hash.getSalt();
		
		hash = new Hashes();
		boolean isSame = hash.compareHash(password, "bobisnotmyuncle", salt);
		System.out.println(isSame);
		
		
	}

}
