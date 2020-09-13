package miro.socialmath.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import miro.socialmath.domain.Multiplication;
import miro.socialmath.domain.MultiplicationResultAttempt;
import miro.socialmath.domain.User;
import miro.socialmath.service.MultiplicationService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MultiplicationService multiplicationService;

    private JacksonTester<MultiplicationResultAttempt> jsonResultAttempt;
    private JacksonTester<MultiplicationResultAttempt> jacksonTesterResponse;

    private JacksonTester<List<MultiplicationResultAttempt>> jsonResultAttemptList;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception {
//        genericPostRequestTest(true);
        genericParameterizedTest(true);
    }


    @Test
    public void postResultReturnNotCorrect() throws Exception {
//        genericPostRequestTest(false);
        genericParameterizedTest(false);
    }

    private void genericPostRequestTest(final boolean isCorrect) throws Exception {
        //given
        given(multiplicationService.checkAttempt(any(MultiplicationResultAttempt.class))).willReturn(isCorrect);
        User user = new User("Kowalsky");
//        Multiplication multiplication = multiplicationService.createRandomMultiplication(); <- works ok
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, false);

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/results")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonResultAttempt.write(attempt).getJson()))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jacksonTesterResponse
                .write(new MultiplicationResultAttempt(
                        attempt.getUser(),
                        attempt.getMultiplication(),
                        attempt.getResultAttempt(),
                        isCorrect
                )).getJson());

    }

    void genericParameterizedTest(final boolean correct) throws Exception {
        // given (remember we're not testing here the service itself)
        given(multiplicationService
                .checkAttempt(any(MultiplicationResultAttempt.class)))
                .willReturn(correct);
        User user = new User("john");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(
                user, multiplication, 3500, false);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/results").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonResultAttempt.write(attempt).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jacksonTesterResponse.write(
                        new MultiplicationResultAttempt(
                                attempt.getUser(),
                                attempt.getMultiplication(),
                                attempt.getResultAttempt(),
                                correct
                        )
                ).getJson());
    }

    @Test
    public void getUserStatsTest() throws Exception {
        //given
        User user = new User("john");
        Multiplication multiplication = new Multiplication(50,60);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
        List<MultiplicationResultAttempt> recentAttempts = Lists.newArrayList(attempt, attempt);

        given(multiplicationService.getStatsForUser("john")).willReturn(recentAttempts);

        //when
        MockHttpServletResponse response = mockMvc.perform(
          get("/results").param("alias", "john"))
                .andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonResultAttemptList.write(recentAttempts).getJson());
    }


}