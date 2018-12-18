package banking.ads.application.accounts.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.ads.application.accounts.contracts.AccountApplicationService;
import banking.ads.application.accounts.dtos.BankAccountDto;
import banking.ads.application.customers.dtos.CustomerDto;
import banking.ads.domain.accounts.contracts.BankAccountRepository;
import banking.ads.domain.accounts.entities.Account;
import banking.ads.domain.customers.contracts.CustomerRepository;
import banking.ads.domain.customers.entities.Customer;
import seedWork.Notification;

@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {
	@Autowired
	BankAccountRepository bankAccountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	private ModelMapper mapper;
    
	@Override
	public void create(long customerId, BankAccountDto bankAccountDto) throws Exception {
		Notification notification = this.createValidation(bankAccountDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		Account bankAccount = mapper.map(bankAccountDto, Account.class);
		bankAccount.setIsLocked(false);
		Customer customer = customerRepository.get(customerId);
		bankAccount.setCustomer(customer);
		bankAccount = bankAccountRepository.save(bankAccount);
		bankAccountDto = mapper.map(bankAccount, BankAccountDto.class);
    }
	//TODO MANDAR MENSAJE A PROPERTIES O CONSTANTES
	private Notification createValidation(BankAccountDto bankAccountDto) throws Exception {
		Notification notification = new Notification();
		Account bankAccount = bankAccountRepository.getByNumber(bankAccountDto.getNumber());
		if (bankAccount != null) {
			notification.addError("BankAccount number is already registered");
		}
		return notification;
	}
	
	@Override
	public List<BankAccountDto> get(long customerId) {
		Customer customer = customerRepository.get(customerId);
		List<BankAccountDto> bankAccountsDto = mapper.map(customer.getAccounts(), new TypeToken<List<BankAccountDto>>() {}.getType());
		
		return bankAccountsDto;
	}
}
