package banking.ads.application.transactions.contracts;

import banking.ads.application.transactions.dtos.NewTransferDto;
import banking.ads.application.transactions.dtos.NewTransferResponseDto;

public interface TransactionApplicationServiceInterface {
	NewTransferResponseDto performTransfer(NewTransferDto newTransferDto);
}
