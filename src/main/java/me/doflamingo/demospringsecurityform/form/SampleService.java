package me.doflamingo.demospringsecurityform.form;

import me.doflamingo.demospringsecurityform.account.Account;
import me.doflamingo.demospringsecurityform.account.AccountContext;
import me.doflamingo.demospringsecurityform.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  public void dashboard() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User principal = (User) authentication.getPrincipal();
    System.out.println("account = " + principal.getUsername());
  }

  @Async
  public void asyncService() {
    SecurityLogger.log("Async Service");
    System.out.println("Async Service is Called");
  }
}
