package banking.ads.domain.users.contracts;

import java.util.List;

import banking.ads.domain.users.entities.User;

public interface IUserRepository {
	
		public User save(User user);
		public User getById(long userId);
		public User getByName(String name);
		public List<User> getPaginated(int page, int pageSize);


}



