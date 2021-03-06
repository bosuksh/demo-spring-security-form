package me.doflamingo.demospringsecurityform.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityLogger {

  public static void log(String content) {
    System.out.println(content);
    Thread thread = Thread.currentThread();
    System.out.println("current thread: "+ thread.getName());
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("principal: "+ authentication.getPrincipal());
  }
}
