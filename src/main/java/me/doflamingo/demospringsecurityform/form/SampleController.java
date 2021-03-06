package me.doflamingo.demospringsecurityform.form;

import me.doflamingo.demospringsecurityform.account.AccountRepository;
import me.doflamingo.demospringsecurityform.common.SecurityLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

  private final SampleService sampleService;
  private final AccountRepository accountRepository;

  public SampleController(SampleService sampleService, AccountRepository accountRepository) {
    this.sampleService = sampleService;
    this.accountRepository = accountRepository;
  }


  @GetMapping("/")
  public String index(Model model, Principal principal){
    if(principal == null)
      model.addAttribute("message","Hello Spring Security");
    else
      model.addAttribute("message", "Hello "+ principal.getName());
    return "index";
  }

  @GetMapping("/info")
  public String info(Model model){
    model.addAttribute("message","Hello Info");
    return "info";
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model, Principal principal){
    sampleService.dashboard();
    model.addAttribute("message","Hello "+ principal.getName());
    return "dashboard";
  }

  @GetMapping("/admin")
  public String admin(Model model, Principal principal){
    model.addAttribute("message","Hello Admin! "+ principal.getName());
    return "admin";
  }

  @GetMapping("/user")
  public String user(Model model, Principal principal){
    model.addAttribute("message","Hello User! "+ principal.getName());
    return "user";
  }

  @GetMapping("/async-handler")
  @ResponseBody
  public Callable<String> asyncHandler(){
    SecurityLogger.log("MVC");
    return new Callable<>() {
      @Override
      public String call() throws Exception {
        SecurityLogger.log("async Handler");
        return "async handler";
      }
    };
  }

  @GetMapping("/async-service")
  @ResponseBody
  public String asyncService() {
    SecurityLogger.log("Before Async Service");
    sampleService.asyncService();
    SecurityLogger.log("After Async Service");
    return "async service";
  }
}
