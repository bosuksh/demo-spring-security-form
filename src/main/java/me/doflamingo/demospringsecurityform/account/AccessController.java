package me.doflamingo.demospringsecurityform.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AccessController {

  @GetMapping("/access-denied")
  public String accessDenied(Principal principal, Model model) {
    String username = principal.getName();
    model.addAttribute("username", username);
    return "access-denied";
  }
}
