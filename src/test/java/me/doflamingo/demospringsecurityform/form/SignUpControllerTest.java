package me.doflamingo.demospringsecurityform.form;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}