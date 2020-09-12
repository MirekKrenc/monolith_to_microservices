package miro.socialmath.service;

import miro.socialmath.domain.Multiplication;
import miro.socialmath.domain.MultiplicationResultAttempt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    private final RandomGeneratorService randomGeneratorService;

    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService) {
        this.randomGeneratorService = randomGeneratorService;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        return new Multiplication(randomGeneratorService.generateRandomFactor(), randomGeneratorService.generateRandomFactor());
    }

    @Override
    public boolean checkAttempt(MultiplicationResultAttempt attempt) {
        boolean correct = attempt.getMultiplication().getResult() == attempt.getResultAttempt();

        //in attemt object the default value of correct var is false, so check if there were no hacking try
        Assert.isTrue(!attempt.isCorrect(), "Yau can't cheat and send the attepmt as true");

        //create copy for future data layer
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(
                attempt.getUser(),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                correct
        );

        return correct;
    }
}
