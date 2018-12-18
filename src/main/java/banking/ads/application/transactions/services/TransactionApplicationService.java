package banking.ads.application.transactions.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import banking.ads.application.transactions.contracts.TransactionApplicationServiceInterface;
import banking.ads.application.transactions.dtos.NewTransferDto;
import banking.ads.application.transactions.dtos.NewTransferResponseDto;
import banking.ads.domain.accounts.contracts.BankAccountRepository;
import banking.ads.domain.accounts.entities.Account;
import banking.ads.domain.transactions.contracts.ITransferDomainService;

@Service
public class TransactionApplicationService implements TransactionApplicationServiceInterface{

	@Autowired
	private ITransferDomainService transferDomainService;
	@Autowired
	private BankAccountRepository accountRepository;
	
	/*public TransactionApplicationService(ITransferDomainService transferDomainService, IAccountRepository accountRepository) {
		this.transferDomainService = transferDomainService;
		this.accountRepository = accountRepository;
	}*/
	/*public TransactionApplicationService() {
		this.transferDomainService = new TransferDomainService();
		this.accountRepository = new AccountHibernateRepository();
	}*/
	
	@Override
	@Transactional
	public NewTransferResponseDto performTransfer(NewTransferDto newTransferDto) {
		// TODO UnitOfWork
		//Account originAccount = new Account() {{set_id(1);setNumber("10001");setBalance(1000);}};
		//Account destinationAccount = new Account(){{set_id(2);setNumber("10002");setBalance(0);}};
		Account originAccount = accountRepository.getByNumberWithUpgradeLock(newTransferDto.fromAccountNumber);
		Account destinationAccount = accountRepository.getByNumberWithUpgradeLock(newTransferDto.toAccountNumber);
		//GetByNumberWithUpgradeLock
		double amount = newTransferDto.amount;
		transferDomainService.performTransfer(originAccount, destinationAccount, amount);
		accountRepository.saveOrUpdate(originAccount);
		accountRepository.saveOrUpdate(destinationAccount);
		
		return new NewTransferResponseDto(){{
				httpStatusCode=201;
				stringResponse="";
			}};
	}
}
