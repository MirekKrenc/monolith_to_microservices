package miro.socialmath.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class RandomGeneratorServiceTest {

    @Autowired
    private RandomGeneratorService randomGeneratorService;

    @Test
    void generateRandomFactorIsBetweenExpectedLimits() throws Exception{

        //when quite big sample of randomly generated values is generated
        List<Integer> randomFactors = IntStream.range(0, 1000)
        .map(i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        //then all the value sis going to be between range
        assertEquals(true, randomGeneratedNumbersAreInRange(randomFactors, RandomGeneratorService.MIN_RANGE, RandomGeneratorService.MAX_RANGE));

    }

    private boolean randomGeneratedNumbersAreInRange(List<Integer> randomFactors, int minRange, int maxRange) {
        for (Integer i: randomFactors)
        {
            if (i<minRange || i>maxRange) {
                return false;
            }
        }
        return true;
    }
}