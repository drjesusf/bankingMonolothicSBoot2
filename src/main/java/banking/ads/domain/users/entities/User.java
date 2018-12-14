package banking.ads.domain.users.entities;

import java.util.List;
import java.util.Set;

/*import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/
import javax.validation.constraints.Size;

import banking.ads.security.Role;


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
