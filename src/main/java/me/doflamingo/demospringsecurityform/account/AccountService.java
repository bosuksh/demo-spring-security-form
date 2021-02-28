package me.doflamingo.demospringsecurityform.account;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

  private final AccountRepository accountRepository;
  private final PasswordEncoder passwordEncoder;

  public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account findAccount = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    return User.builder()
             .username(findAccount.getUsername())
             .password(findAccount.getPassword())
             .roles(findAccount.getRole())
             .build();
  }

  public Account createNewAccount(Account account) {
    account.encodePassword(passwordEncoder);
    return accountRepository.save(account);
  }
}
