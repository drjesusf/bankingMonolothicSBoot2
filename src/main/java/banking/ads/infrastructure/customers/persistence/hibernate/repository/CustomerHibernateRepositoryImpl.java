package banking.ads.infrastructure.customers.persistence.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.ads.domain.customers.contracts.CustomerRepository;
import banking.ads.domain.customers.entities.Customer;
import banking.ads.infrastructure.hibernate.HibernateRepository;

@Transactional
@Repository
public class CustomerHibernateRepositoryImpl extends HibernateRepository<Customer> implements CustomerRepository{
	public CustomerHibernateRepositoryImpl() {
		
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Customer> get(int page,int pageSize){
		List<Customer> customers= null;
		Criteria criteria = getSession().createCriteria(Customer.class,"c");
		criteria.createAlias("c.accounts", "a", JoinType.LEFT_OUTER_JOIN);
		
		criteria.setFirstResult(page);
		criteria.setMaxResults(pageSize);
		customers = criteria.list();
		return customers;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Customer getIdentityDocument(String identityDocument) {
		Criteria criteria = getSession().createCriteria(Customer.class,"c");
		criteria.createAlias("c.accounts", "a", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("c.identityDocument", identityDocument));
		
		Customer customer = (Customer)criteria.uniqueResult();
		return customer;
		 
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Customer> getByLastname(String lastName) {
		List<Customer> customers= null;
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.add(Restrictions.like("lastName", lastName));
		customers = criteria.list();
		return customers;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Customer get(long customerId) {		
		//Customer customer = getSession().get(Customer.class,customerId,"c");
		Customer customer = null;
		Criteria criteria = getSession().createCriteria(Customer.class, "c");
		criteria.createAlias("c.accounts", "a", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("c.id", customerId));
		customer = (Customer)criteria.uniqueResult();
		
		return customer;
	}
}
