package banking.ads.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import banking.ads.api.reponses.ResponseHandler;
import banking.ads.application.accounts.contracts.AccountApplicationService;
import banking.ads.application.accounts.dtos.BankAccountDto;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
	@Autowired
	AccountApplicationService accountApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@RequestMapping(value = "/{customerId}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@PathVariable("customerId") long customerId, @RequestBody BankAccountDto bankAccountDto) throws Exception {
        try {
        	accountApplicationService.create(customerId, bankAccountDto);
            return new ResponseEntity<Object>(bankAccountDto, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	
	@RequestMapping(value="/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> get(@PathVariable("customerId") long customerId) {
        try {
            List<BankAccountDto> customers = accountApplicationService.get(customerId);
            return this.responseHandler.getDataResponse(customers, HttpStatus.OK);
        } catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
    }	
}
