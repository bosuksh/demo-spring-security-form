package me.doflamingo.demospringsecurityform.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
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
  @WithAnonymousUser
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
  @WithUser
  public void index_with_user() throws Exception {
    mockMvc.perform(get("/"))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("어드민 유저로 index page")
  @WithAdminUser
  public void index_with_admin() throws Exception {
    //given

    //when
    mockMvc.perform(get("/"))
      .andDo(print())
    //then
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("일반 유저로 admin page")
  @WithUser
  public void admin_with_user() throws Exception {
    //given

    //when
    mockMvc.perform(get("/admin"))
      .andDo(print())
    //then
      .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("어드민 유저로 admin page")
  @WithAdminUser
  public void admin_with_admin() throws Exception {
    //given

    //when
    mockMvc.perform(get("/admin"))
      .andDo(print())
      //then
      .andExpect(status().isOk());
  }
}