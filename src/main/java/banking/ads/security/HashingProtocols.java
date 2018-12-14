package banking.ads.security;


import banking.ads.security.Bcrypt;

public class HashingProtocols {
	private static final Bcrypt bcrypt = new Bcrypt(11);

	public static String hash(String password) {
		return bcrypt.hash(password);
	}
	
	public static boolean verifyHash(String password, String hash) {
	    return bcrypt.verifyHash(password, hash);
	}

}
