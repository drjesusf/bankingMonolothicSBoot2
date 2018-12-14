package banking.ads.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import banking.ads.application.transactions.contracts.TransactionApplicationServiceInterface;
import banking.ads.application.transactions.dtos.NewTransferDto;
import banking.ads.application.transactions.dtos.NewTransferResponseDto;
import banking.ads.application.transactions.services.TransactionApplicationService;

@RestController
public class TransfersController {
	private TransactionApplicationServiceInterface transactionApplicationService;
	
	public TransfersController(TransactionApplicationServiceInterface transactionApplicationService) {
		this.transactionApplicationService = transactionApplicationService;
	}
	
	public TransfersController() {
		this.transactionApplicationService = new TransactionApplicationService();
	}
	
	@PostMapping("/performTransfer")
	public NewTransferResponseDto performTransfer(@RequestBody NewTransferDto newTransferDto) {
		return transactionApplicationService.performTransfer(newTransferDto);
	}
}
