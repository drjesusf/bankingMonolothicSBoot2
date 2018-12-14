package banking.ads.application.users.dtos;

import banking.ads.application.users.dtos.UserDto;

public class UserClaimDto {
	
	private long id;
    private String type;
    private String value;
    private UserDto user;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}

}
