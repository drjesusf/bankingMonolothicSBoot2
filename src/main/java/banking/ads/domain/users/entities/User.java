package banking.ads.domain.users.entities;

import java.util.Set;


public class User {
	private long id;
	private String userName;
	private String password;
    private Set<UserClaim> claims;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

   public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public Set<UserClaim> getClaims() {
	return claims;
}

public void setClaims(Set<UserClaim> claims) {
	this.claims = claims;
}

}
