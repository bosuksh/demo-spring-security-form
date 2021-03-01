package me.doflamingo.demospringsecurityform.form;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {

  public void dashboard() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User principal = (User) authentication.getPrincipal();
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    boolean authenticated = authentication.isAuthenticated();
    System.out.println(authenticated);
  }
}
