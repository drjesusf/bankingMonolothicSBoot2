package banking.ads.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import banking.ads.application.customers.dtos.CustomerDto;
import banking.ads.application.customers.services.CustomerApplicationService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	CustomerApplicationService customerApplicationService;
	
	@RequestMapping(method = RequestMethod.GET, path = "", produces = "application/json; charset=UTF-8")
    @ResponseBody
    ResponseEntity<Object> getPaginated(
    		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize) {
		
		List<CustomerDto> customers = customerApplicationService.get(page, pageSize);
		return new ResponseEntity<Object>(customers, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "byIdentityDocument", produces = "application/json; charset=UTF-8")
	ResponseEntity<Object> getByIdentityDocument(
			@RequestParam(value = "identityDocument", required = true, defaultValue = "") String identityDocument) {
		CustomerDto customer = customerApplicationService.getByIdentityDocument(identityDocument);
		return new ResponseEntity<Object>(customer, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "byLastname", produces = "application/json; charset=UTF-8")
	ResponseEntity<Object> getByLastname(
			@RequestParam(value = "lastname", required = true, defaultValue = "") String lastName) {
		List<CustomerDto> customers = customerApplicationService.getByLastname(lastName);
		return new ResponseEntity<Object>(customers, HttpStatus.OK);
	}
}
