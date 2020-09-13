package miro.socialmath.service;

import miro.socialmath.domain.Multiplication;
import miro.socialmath.domain.MultiplicationResultAttempt;
import miro.socialmath.domain.User;
import miro.socialmath.repository.MultiplicationRepository;
import miro.socialmath.repository.MultiplicationResultAttemptRepository;
import miro.socialmath.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    //reporistoru mocks
    @Mock
    private UserRepository userRepository;
    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;
    @Mock
    private MultiplicationRepository multiplicationRepository;

    @Before
    public void setUp() {
        // With this call to initMocks we tell Mockito to process the annotations
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(
                randomGeneratorService,
                attemptRepository,
                userRepository,
                multiplicationRepository);
    }

    @Test
    //@Ignore
    public void createRandomMultiplicationTest() {
        // given (our mocked Random Generator service will return first 50, then 30)
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

        // assert
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }

    @Test
    public void checkCorrectAttemptTest() {
        //given
        Multiplication multiplication = new Multiplication(40, 50);
        User user = new User("NowakJ");

        //first send by user must have false
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(
                user,
                multiplication,
                2000,
                false);

        //and the second one with correc answert must be true
        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(
                user,
                multiplication,
                2000,
                true);

        given(userRepository.findByAlias("Nowak")).willReturn(Optional.empty());

        //when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        //then
        assertThat(attemptResult).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
    }

    @Test
    public void checkIncorrectAttemptTest() {
        //given
        Multiplication multiplication = new Multiplication(40, 50);
        User user = new User("NowakJ");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(
                user,
                multiplication,
                1000,
                false);

        given(userRepository.findByAlias("Nowak")).willReturn(Optional.empty());

        //when
        boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);

        //then
        assertThat(attemptResult).isFalse();
        verify(attemptRepository).save(attempt);
    }

    @Test
    public void retrieveStatsTest() {
        //given
        Multiplication multiplication = new Multiplication(30, 40);
        User user = new User("Johny");
        MultiplicationResultAttempt a1 = new MultiplicationResultAttempt(user, multiplication,1300,false);
        MultiplicationResultAttempt a2 = new MultiplicationResultAttempt(user, multiplication,1310,false);
        MultiplicationResultAttempt a3 = new MultiplicationResultAttempt(user, multiplication,1320,false);
        MultiplicationResultAttempt a4 = new MultiplicationResultAttempt(user, multiplication,1330,false);
        MultiplicationResultAttempt a5 = new MultiplicationResultAttempt(user, multiplication,1200,true);

        List<MultiplicationResultAttempt> resultAttempts = Lists.newArrayList(a1, a2, a3, a4, a5);

        given(userRepository.findByAlias("Johny")).willReturn(Optional.empty());
        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("Johny")).willReturn(resultAttempts);

        //when
        List<MultiplicationResultAttempt> latestAttempts = multiplicationServiceImpl.getStatsForUser("Johny");

        //then
        assertThat(latestAttempts).isEqualTo(resultAttempts);
    }
}