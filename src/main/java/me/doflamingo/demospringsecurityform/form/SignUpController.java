package me.doflamingo.demospringsecurityform.form;

import me.doflamingo.demospringsecurityform.account.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

  @GetMapping
  public String signupForm(Model model) {
    model.addAttribute("account", new Account());
    return "signup";
  }
}
