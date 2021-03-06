package banking.ads.application.customers.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import banking.ads.application.accounts.dtos.BankAccountDto;

public class CustomerDto {
	private long id;
	private String firstName;
	private String lastName;
	private String identityDocument;
	public String getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}

	private Boolean isActive;
	private Set<BankAccountDto> bankAccountsDto;
	
	public CustomerDto() {
    }
	
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@JsonProperty(value="isActive")
	public Boolean isActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<BankAccountDto> getBankAccountsDto() {
		return bankAccountsDto;
	}

	public void setBankAccountsDto(Set<BankAccountDto> bankAccountsDto) {
		this.bankAccountsDto = bankAccountsDto;
	}
}
