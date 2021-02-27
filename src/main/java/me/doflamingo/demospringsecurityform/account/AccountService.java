package me.doflamingo.demospringsecurityform.account;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
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
    account.encodePassword();
    return accountRepository.save(account);
  }
}
