package banking.ads.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import banking.ads.api.reponses.ResponseHandler;
import banking.ads.application.users.dtos.UserAuthDto;
import banking.ads.application.users.dtos.UserDto;
import banking.ads.application.users.services.UserApplicationService;
import banking.ads.security.JwtTokenProvider;
import banking.ads.security.Role;



@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserApplicationService userApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@RequestMapping(method = RequestMethod.POST, path = "/signin", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> login(@RequestBody UserDto requestLoginUserDto) throws Exception {
		try {
			UserAuthDto userAuthDto = userApplicationService.validateUser(requestLoginUserDto);
			if (userAuthDto.isAuthenticated()) {
				for (Object obj : userAuthDto.getClaims()) {System.out.println(obj);}
				return new ResponseEntity<Object>(userAuthDto, HttpStatus.OK);
			}
			return this.responseHandler.getResponse("Invalid User Name / Password", HttpStatus.NOT_FOUND);
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}
	@RequestMapping(method = RequestMethod.POST, path = "/signup", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> singup(@RequestBody UserDto requestSignupUserDto) throws Exception {
		try {
			UserDto newUserDto = userApplicationService.create(requestSignupUserDto);
			return new ResponseEntity<Object>(newUserDto, HttpStatus.OK);
			
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/create", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> create(@RequestBody UserDto requestSignupUserDto) throws Exception {
		try {
			UserDto newUserDto = userApplicationService.create(requestSignupUserDto);
			return new ResponseEntity<Object>(newUserDto, HttpStatus.OK);
			
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			return this.responseHandler.getAppExceptionResponse();
		}
	}
	
	
	
	/** @PostMapping("login")
	public String login(@RequestBody User user) {
		if(user == null) return "";
		String url = user.getEmail() + " Hello;";
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.ROLE_ADMIN);
		String token = jwtTokenProvider.createToken(user.getEmail(),roles);
		return token;
	} **/
}
