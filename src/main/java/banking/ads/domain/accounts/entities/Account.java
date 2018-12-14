package banking.ads.domain.accounts.entities;

import banking.ads.domain.accounts.constants.AccountConstants;
import banking.ads.domain.customers.entities.Customer;
import seedWork.Entity;
import seedWork.Notification;

public class Account extends Entity{
	private long id;
	private String number;
	private double balance;
	private boolean isLocked;
	private Customer customer;
	
	public void Lock() {
		if(!isLocked) isLocked = true;
	}
	public void UnLock() {
		if(isLocked) isLocked = false;
	}
	public boolean hasIdentity() {
		if(number == null) return false;
		return !number.isEmpty();
	} 
	
	public void withdrawMoney(double amount) {
		Notification notification = canWithdrawMoney(amount);
		ThrowExceptionIfAny(notification);
		
		this.balance = this.balance - amount;
	}
	
	private Notification canWithdrawMoney(double amount) {
		Notification notification = new Notification();
		if(amount<=0) notification.addError(AccountConstants.amountMustBeGreaterThanZero);
		if(!hasIdentity()) notification.addError(AccountConstants.accountHasNoIdentity);
		if(isLocked) notification.addError(AccountConstants.accountIsLocked);
		if(!canBeWithdrawed(amount)) notification.addError(AccountConstants.cannotWithdrawAmountIsGreaterThanBalance);
		
		return notification;
	}
	
	private boolean canBeWithdrawed(double amount) {
		return !isLocked && balance>= amount;
	}
	
	public void depositMoney(double amount) { 
		Notification notification = canDepositMoney(amount);
		ThrowExceptionIfAny(notification);
		
		balance+=amount;
	}
	private Notification canDepositMoney(double amount) {
		Notification notification = new Notification();
		if(amount<=0) notification.addError(AccountConstants.amountMustBeGreaterThanZero);
		if(!hasIdentity()) notification.addError(AccountConstants.accountHasNoIdentity);
		if(isLocked) notification.addError(AccountConstants.accountIsLocked);
		
		return notification;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public boolean getIsLocked() {
        return isLocked;
    }
	 public void setIsLocked(boolean isLocked) {
	        this.isLocked = isLocked;
	  }
	
}
