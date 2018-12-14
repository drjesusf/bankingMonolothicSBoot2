package banking.ads.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Bcrypt {
	
	private final int logRounds;

	public Bcrypt(int logRounds) {
		this.logRounds = logRounds;
	}

	public String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
	}

	public boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}

}
