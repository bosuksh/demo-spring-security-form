package me.doflamingo.demospringsecurityform.config;

import me.doflamingo.demospringsecurityform.account.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE - 10)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AccountService accountService;

  public SecurityConfig(AccountService accountService) {
    this.accountService = accountService;
  }

  public AccessDecisionManager accessDecisionManager() {
    DefaultWebSecurityExpressionHandler handler = getExpressionHandler();

    WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
    webExpressionVoter.setExpressionHandler(handler);

    List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(webExpressionVoter);
    return new AffirmativeBased(decisionVoters);
  }

  public DefaultWebSecurityExpressionHandler getExpressionHandler() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy);
    return handler;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .mvcMatchers("/","/info","/account/**","/h2-console/**").permitAll()
      .mvcMatchers("/admin").hasRole("ADMIN")
      .mvcMatchers("/user").hasRole("USER")
      .anyRequest().authenticated()
    //.accessDecisionManager(accessDecisionManager());
    .expressionHandler(getExpressionHandler());

    http.formLogin();
    http.httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(accountService);
  }
}
