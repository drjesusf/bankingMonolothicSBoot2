package banking.ads.infrastructure.accounts.persistence.hibernate.repository;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.ads.domain.accounts.contracts.BankAccountRepository;
import banking.ads.domain.accounts.entities.Account;
import banking.ads.infrastructure.hibernate.HibernateRepository;

@Transactional
@Repository
public class AccountHibernateRepository extends HibernateRepository<Account> implements BankAccountRepository {

	@SuppressWarnings("deprecation")
	@Override
	public Account getByNumber(String accountNumber) {
		Criteria criteria = getSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("number", accountNumber));
		Account account = (Account)criteria.uniqueResult();
		
		return account;
		// TODO Auto-generated method stub
		/*if(accountNumber == "10001")
		return new Account() {{set_id(1);setNumber("10001");setBalance(1000);}};
		else if(accountNumber == "10002")
			return new Account(){{set_id(2);setNumber("10002");setBalance(0);}};
		
		return new Account();*/
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Account getByNumberWithUpgradeLock(String accountNumber) {
		Criteria criteria = getSession().createCriteria(Account.class);
		criteria.add(Restrictions.eq("number", accountNumber));
		Account account = (Account)criteria.uniqueResult();
		//criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
		return account;
		// TODO Auto-generated method stub
		/*if(accountNumber.trim().equals("10001"))
			return new Account() {{set_id(1);setNumber("10001");setBalance(1000);}};
		else if(accountNumber.trim().equals("10002"))
			return new Account(){{set_id(2);setNumber("10002");setBalance(0);}};
		
		return new Account();*/
	}

}
