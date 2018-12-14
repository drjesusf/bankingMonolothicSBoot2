package banking.ads.infrastructure.accounts.persistence.hibernate.repository;

import banking.ads.domain.accounts.contracts.IAccountRepository;
import banking.ads.domain.accounts.entities.Account;
import banking.ads.infrastructure.hibernate.HibernateRepository;

public class AccountHibernateRepository extends HibernateRepository<Account> implements IAccountRepository {


	@Override
	public Account getByNumber(String accountNumber) {
		// TODO Auto-generated method stub
		if(accountNumber == "10001")
		return new Account() {{set_id(1);setNumber("10001");setBalance(1000);}};
		else if(accountNumber == "10002")
			return new Account(){{set_id(2);setNumber("10002");setBalance(0);}};
		
		return new Account();
	}
	
	
	@Override
	public Account getByNumberWithUpgradeLock(String accountNumber) {
		// TODO Auto-generated method stub
		if(accountNumber.trim().equals("10001"))
			return new Account() {{set_id(1);setNumber("10001");setBalance(1000);}};
		else if(accountNumber.trim().equals("10002"))
			return new Account(){{set_id(2);setNumber("10002");setBalance(0);}};
		
		return new Account();
	}

}
