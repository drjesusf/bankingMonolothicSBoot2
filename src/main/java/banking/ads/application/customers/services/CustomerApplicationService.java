package banking.ads.application.customers.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.ads.domain.accounts.entities.Account;
import banking.ads.domain.customers.contracts.CustomerRepository;
import banking.ads.domain.customers.entities.Customer;
import seedWork.Notification;
import banking.ads.application.accounts.dtos.BankAccountDto;
import banking.ads.application.customers.dtos.CustomerDto;

@Service
public class CustomerApplicationService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Value("${maxPageSize}")
	private int maxPageSize;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public List<CustomerDto> get(int page, int pageSize) {
		Notification notification = this.getValidation(page, pageSize);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
        Set<BankAccountDto> accountsDto = null;
		List<Customer> customers = this.customerRepository.get(page, pageSize);
		for (Customer customer : customers) {
			accountsDto = new HashSet<BankAccountDto>();
			CustomerDto customerDto =  new CustomerDto();
			customerDto.setId(customer.get_id());
			customerDto.setFirstName(customer.getFirstName());
			customerDto.setIdentityDocument(customer.getIdentityDocument());
			customerDto.setIsActive(customer.getIsActive());
			customerDto.setLastName(customer.getLastName());
			
			for (Account account : customer.getAccounts()) {
				BankAccountDto accountDto = new BankAccountDto();
				accountDto.setId(account.get_id());
				accountDto.setBalance(account.getBalance());
				accountDto.setNumber(account.getNumber());
				accountDto.setLocked(account.getIsLocked());
				accountsDto.add(accountDto);
			}
			customerDto.setBankAccountsDto(accountsDto);
			customersDto.add(customerDto);
		}
		//List<CustomerDto> customersDto = mapper.map(customers, new TypeToken<List<CustomerDto>>() {}.getType());
        return customersDto;
    }
	
	private Notification getValidation(int page, int pageSize) {
		Notification notification = new Notification();
		if (pageSize > maxPageSize) {
			notification.addError("Page size can not be greater than 100");
		}
		return notification;
	}

	public CustomerDto getByIdentityDocument(String identityDocument) {
		Customer customer = this.customerRepository.getIdentityDocument(identityDocument);
		CustomerDto customerDto = mapper.map(customer, new TypeToken<CustomerDto>() {}.getType());
		return customerDto;
	}

	public List<CustomerDto> getByLastname(String lastName) {
		List<Customer> customers = this.customerRepository.getByLastname(lastName);
		List<CustomerDto> customersDto = mapper.map(customers, new TypeToken<List<CustomerDto>>() {}.getType());
        return customersDto;
	}
}
