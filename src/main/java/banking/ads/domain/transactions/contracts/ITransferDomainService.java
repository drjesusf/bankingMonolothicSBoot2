package banking.ads.domain.transactions.contracts;

import banking.ads.domain.accounts.entities.Account;

public interface ITransferDomainService {
	void performTransfer(Account originAccount, Account destinationAccount, double amount);
}
