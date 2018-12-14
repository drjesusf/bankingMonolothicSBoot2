package banking.ads.domain.customers.contracts;

import java.util.List;

import banking.ads.domain.customers.entities.Customer;
import seedWork.IRepository;

public interface CustomerRepository extends IRepository<Customer> {
	public List<Customer> get(int page, int pageSize);

	public Customer getIdentityDocument(String identityDocument);

	public List<Customer> getByLastname(String lastName);
}
