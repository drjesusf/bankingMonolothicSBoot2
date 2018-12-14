package banking.ads.application.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import banking.ads.security.JwtTokenProvider;
import banking.ads.security.Role;
import banking.ads.api.enumeration.RequestBodyType;
import seedWork.Notification;
import banking.ads.application.users.dtos.UserAuthDto;
import banking.ads.application.users.dtos.UserClaimDto;
import banking.ads.security.HashingProtocols;
import banking.ads.application.users.dtos.UserDto;
import banking.ads.domain.users.entities.User;
import banking.ads.domain.users.entities.UserClaim;
import banking.ads.domain.users.contracts.IUserRepository;
import banking.ads.domain.users.contracts.IUserClaimRepository;

@Service
public class UserApplicationService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IUserClaimRepository userClaimRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Value("${maxPageSize}")
	private int maxPageSize;
	
	public UserDto create(UserDto userDto) throws Exception{
		Notification notification = this.createValidation(userDto);
		if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		String hashPassword = HashingProtocols.hash(userDto.getPassword());
		userDto.setPassword(hashPassword);
		User user = mapper.map(userDto, User.class);
		user = userRepository.save(user);
		userDto = mapper.map(user, UserDto.class);
        return userDto;
    }
	
	private Notification createValidation(UserDto userDto) throws Exception{
		Notification notification = new Notification();
		if (userDto == null || userDto.getName().equals(RequestBodyType.INVALID.toString())) {
			notification.addError("Invalid JSON data in request body.");
		}
		User user = userRepository.getByName(userDto.getName().trim());
		if (user != null) {
			notification.addError("User name is already registered");
		}
		return notification;
	}

	public UserAuthDto validateUser(UserDto userDto) throws Exception{
		UserAuthDto userAuthDto = new UserAuthDto();
		User authUser = null;
		authUser = this.userRepository.getByName(userDto.getName());
		if (authUser == null) {
			return userAuthDto;
			
		}		
		if (!HashingProtocols.verifyHash(userDto.getPassword(), authUser.getPassword())) {
			return userAuthDto;
		}
		userAuthDto = this.buildUserAuthDto(authUser);
		return userAuthDto;
	}

	private List<UserClaimDto> getUserClaims(User authUser) throws Exception {
		List<UserClaim> claims = this.userClaimRepository.findByUserId(authUser.getId());
		if(claims == null || claims.isEmpty()) return new ArrayList<UserClaimDto>();
		
		return mapper.map(claims, new TypeToken<List<UserClaimDto>>() {}.getType());
	}
	
	private UserAuthDto buildUserAuthDto(User authUser) throws Exception {
		UserAuthDto userAuthDto = new UserAuthDto();
		userAuthDto.setId(authUser.getId());
		userAuthDto.setName(authUser.getUserName());
		userAuthDto.setAuthenticated(true);
		userAuthDto.setBearerToken(new UUID(0L, 0L).toString());
		List<UserClaimDto> claims = this.getUserClaims(authUser);
		userAuthDto.setClaims(claims);
		
		String token = jwtTokenProvider.buildJwtToken(userAuthDto);
		userAuthDto.setBearerToken(token); 
		//for(int i=0;i < claims.size(); i++) {System.out.println(claims.get(i).toString());};
		userAuthDto.setClaims(claims);
		//userAuthDto.setBearerToken(jwtTokenProvider.createToken(userAuthDto, ));
		return userAuthDto;
	}
	
	public UserDto get(long userId) {
		ModelMapper modelMapper = new ModelMapper();
		User user = this.userRepository.getById(userId);
		UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
    
    public List<UserDto> getPaginated(int page, int pageSize) {
		Notification notification = this.getPaginatedValidation(page, pageSize);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		List<User> users = this.userRepository.getPaginated(page, pageSize);
		List<UserDto> usersDto = mapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
        return usersDto;
    }
    
    private Notification getPaginatedValidation(int page, int pageSize) {
		Notification notification = new Notification();
		if (pageSize > maxPageSize) {
			notification.addError("Page size can not be greater than 100");
		}
		return notification;
	}

}
