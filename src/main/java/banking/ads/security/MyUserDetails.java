package banking.ads.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import banking.ads.domain.users.contracts.IUserRepository;
import banking.ads.domain.users.entities.User;


@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private IUserRepository userRepository;
  public MyUserDetails() {
  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //final User user = userRepository.findByUsername(username);
	  final User user = userRepository.getByName(username);

    if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(user.getPassword())//
        //.authorities(user.getClaims())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
