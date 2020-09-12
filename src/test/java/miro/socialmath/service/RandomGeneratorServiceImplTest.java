package miro.socialmath.service;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * test with no SpringBoot context
 */
public class RandomGeneratorServiceImplTest {

    private RandomGeneratorService randomGeneratorService;

    @Before
    public void setUp() {
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedRange() throws Exception {

        //when good example of randomly generated numbers
        List<Integer> randomFactors = IntStream.range(1, 1000)
                .map(i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        //then all of them will be between range
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
