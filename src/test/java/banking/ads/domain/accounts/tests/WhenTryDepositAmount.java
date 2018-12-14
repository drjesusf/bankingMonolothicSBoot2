package banking.ads.domain.accounts.tests;

import banking.ads.domain.accounts.entities.Account;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class WhenTryDepositAmount extends TestCase{
	private Account _account;
	
	protected void setUp() {
		
	}
	
	@Test
	public void testIfAmountIsPositive() {
		int initialBalance = 0;
		_account = new Account();
		_account.setNumber("100001");
		_account.setBalance(initialBalance);
		
		long amount = 50;
		_account.depositMoney(amount);
		
		assertEquals(50.0, _account.getBalance());
	}
	
	@Test
	public void testIfAmountIsNegative() {
		int initialBalance = 0;
		_account = new Account();
		_account.setNumber("100001");
		_account.setBalance(initialBalance);
		
		long amount = -20;
		assertThrows(IllegalArgumentException.class, ()->{
			_account.depositMoney(amount);
		});		
	}
	
}
