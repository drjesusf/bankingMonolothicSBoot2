package banking.ads.infrastructure.users.persistence.hibernate.repository;

import banking.ads.domain.users.entities.User;
import banking.ads.infrastructure.hibernate.HibernateRepository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.ads.domain.users.contracts.IUserRepository;

@Transactional
@Repository
public class UserHibernateRepository extends HibernateRepository<User> implements IUserRepository {

	@SuppressWarnings("deprecation")
	public User getByName(String name) {
		User user = null;
		Criteria criteria = getSession().createCriteria(User.class, "u");
		criteria.createAlias("u.claims", "c", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("u.userName", name));
		user = (User) criteria.uniqueResult();
		return user; 
	}
	
	public User getById(long userId) {
		User user = null;
		user = getSession().get(User.class, userId);
		return user;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<User> getPaginated(int page, int pageSize) {
		List<User> users = null;
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pageSize);
		users = criteria.list();
		return users;
	}
	
	public User save(User user) {
		return super.save(user);
	}

}
