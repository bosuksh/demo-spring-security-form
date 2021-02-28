package me.doflamingo.demospringsecurityform.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("비로그인 유저로 admin page")
  public void admin_with_anonymous() throws Exception {
    //given

    //when
    mockMvc.perform(get("/").with(anonymous()))
      .andDo(print())
      //then
      .andExpect(status().isOk());
  }
  @Test
  @DisplayName("일반 유저로 index page")
  public void index_with_user() throws Exception {
    mockMvc.perform(get("/").with(user("user").roles("USER")))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("어드맨 유저로 index page")
  public void index_with_admin() throws Exception {
    //given

    //when
    mockMvc.perform(get("/").with(user("ADMIN").roles("ADMIN")))
      .andDo(print())
    //then
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("일반 유저로 admin page")
  public void admin_with_user() throws Exception {
    //given

    //when
    mockMvc.perform(get("/admin").with(user("user").roles("USER")))
      .andDo(print())
    //then
      .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("어드민 유저로 admin page")
  public void admin_with_admin() throws Exception {
    //given

    //when
    mockMvc.perform(get("/admin").with(user("admin").roles("ADMIN")))
      .andDo(print())
      //then
      .andExpect(status().isOk());
  }
}