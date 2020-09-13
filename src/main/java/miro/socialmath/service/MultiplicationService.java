package miro.socialmath.service;

import miro.socialmath.domain.Multiplication;
import miro.socialmath.domain.MultiplicationResultAttempt;

import java.util.List;

public interface MultiplicationService {
    /**
     *
     * @return Multiplication object with random factors
     * between 11 and 100
     */
    Multiplication createRandomMultiplication();

    /**
     * @return true if attemt matches the correct result
     * othervise false
     */
    boolean checkAttempt(final MultiplicationResultAttempt multiplicationResultAttempt);

    /**
     *
     * @param userAlias
     * @return last attempts
     */
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
}
