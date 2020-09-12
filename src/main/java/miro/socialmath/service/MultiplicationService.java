package miro.socialmath.service;

import miro.socialmath.domain.Multiplication;

public interface MultiplicationService {
    /**
     *
     * @return Multiplication object with random factors
     * between 1 and 99
     */
    Multiplication createRandomMultiplication();
}
