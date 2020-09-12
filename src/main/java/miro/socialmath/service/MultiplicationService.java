package miro.socialmath.service;

import miro.socialmath.domain.Multiplication;

public interface MultiplicationService {
    /**
     *
     * @return Multiplication object with random factors
     * between 11 and 100
     */
    Multiplication createRandomMultiplication();
}
