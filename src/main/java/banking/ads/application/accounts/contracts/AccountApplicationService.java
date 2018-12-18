package banking.ads.application.accounts.contracts;

import java.util.List;

import org.springframework.stereotype.Service;

import banking.ads.application.accounts.dtos.BankAccountDto;

@Service
public interface AccountApplicationService {

	List<BankAccountDto> get(long customerId);
	void create(long customerId, BankAccountDto bankAccountDto) throws Exception;

}