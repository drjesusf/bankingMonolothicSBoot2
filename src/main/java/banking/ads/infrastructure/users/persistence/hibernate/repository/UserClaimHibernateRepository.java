package banking.ads.infrastructure.users.persistence.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.ads.domain.users.contracts.IUserClaimRepository;
import banking.ads.domain.users.entities.UserClaim;
import banking.ads.infrastructure.hibernate.HibernateRepository;


@Transactional
@Repository
public class UserClaimHibernateRepository extends HibernateRepository<UserClaim> implements IUserClaimRepository{

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<UserClaim> findByUserId(Long userId) throws Exception {
		List<UserClaim> userClaims = null;
		Criteria criteria = getSession().createCriteria(UserClaim.class, "uc");
		criteria.createAlias("uc.user", "u");
		//criteria.setFetchMode("user", FetchMode.SELECT); 
		criteria.add(Restrictions.eq("u.id", userId));
		userClaims = criteria.list();
		return userClaims;
	}

}
