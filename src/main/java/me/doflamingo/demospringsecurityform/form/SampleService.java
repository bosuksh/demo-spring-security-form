package me.doflamingo.demospringsecurityform.form;

import me.doflamingo.demospringsecurityform.account.Account;
import me.doflamingo.demospringsecurityform.account.AccountContext;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  public void dashboard() {
    Account account = AccountContext.getAccount();
    System.out.println("account = " + account.getUsername());
  }
}
