package me.doflamingo.demospringsecurityform.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping("/account/{role}/{username}/{password}")
  public Account createNewAccount(@ModelAttribute Account account) {
    return accountService.createNewAccount(account);
  }

}
