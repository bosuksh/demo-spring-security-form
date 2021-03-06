package me.doflamingo.demospringsecurityform.form;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SignUpControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("signUp Form 테스트")
  public void signUpForm() throws Exception {
    //given

    //when
    mockMvc.perform(get("/signup"))
    //then
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("_csrf")));
  }

  @Test
  @DisplayName("sign up 성공 테스트")
  public void signUp() throws Exception {
    //given
    String username = "doflamingo";
    String password = "123";
    //when
    mockMvc.perform(post("/signup")
      .param("username", username)
      .param("password", password)
      .with(csrf()))
    //then
    .andDo(print())
    .andExpect(status().is3xxRedirection());
  }
}