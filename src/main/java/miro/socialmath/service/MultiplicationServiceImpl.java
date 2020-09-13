package miro.socialmath.service;

import miro.socialmath.domain.Multiplication;
import miro.socialmath.domain.MultiplicationResultAttempt;
import miro.socialmath.domain.User;
import miro.socialmath.repository.MultiplicationRepository;
import miro.socialmath.repository.MultiplicationResultAttemptRepository;
import miro.socialmath.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    private final RandomGeneratorService randomGeneratorService;
    private final UserRepository userRepository;
    private final MultiplicationResultAttemptRepository attemptRepository;
    private final MultiplicationRepository multiplicationRepository;

    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService,
                                     MultiplicationResultAttemptRepository attemptRepository,
                                     UserRepository userRepository, MultiplicationRepository multiplicationRepository) {
        this.randomGeneratorService = randomGeneratorService;
        this.userRepository = userRepository;
        this.attemptRepository = attemptRepository;
        this.multiplicationRepository = multiplicationRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        Multiplication multiplication = new Multiplication(randomGeneratorService.generateRandomFactor(), randomGeneratorService.generateRandomFactor());
        final Optional<Multiplication> multiplicationFromRepo = multiplicationRepository.findMultiplicationByFactorAAndAndFactorB(multiplication.getFactorA(), multiplication.getFactorB());
        if (multiplicationFromRepo.isPresent())
            return multiplicationFromRepo.get();
        else
            return multiplication;
    }

    @Transactional
    @Override
    public boolean checkAttempt(MultiplicationResultAttempt attempt) {
         //check if user with alias already exists in repository
        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());
        System.out.println(user.toString());

        //in attemt object the default value of correct var is false, so check if there were no hacking try
        Assert.isTrue(!attempt.isCorrect(), "Yau can't cheat and send the attepmt as true");

        //check if attempt is correct
        boolean isCorrect = attempt.getResultAttempt() == attempt.getMultiplication().getFactorA() *
                attempt.getMultiplication().getFactorB();

        //create copy for data layer
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(
                user.orElse(attempt.getUser()),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                isCorrect
        );

        //stores the attempt in repository
        attemptRepository.save(multiplicationResultAttempt);

        return isCorrect;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        final List<MultiplicationResultAttempt> top5ByUserAliasOrderByIdDesc = attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
        top5ByUserAliasOrderByIdDesc.stream()
                .forEach(System.out::println);
        return  top5ByUserAliasOrderByIdDesc;
    }
}
