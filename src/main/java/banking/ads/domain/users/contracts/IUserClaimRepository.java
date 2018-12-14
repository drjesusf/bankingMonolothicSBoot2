package banking.ads.domain.users.contracts;

import java.util.List;

import banking.ads.domain.users.entities.UserClaim;

public interface IUserClaimRepository {
	
	public List<UserClaim> findByUserId(Long integer) throws Exception;

}
