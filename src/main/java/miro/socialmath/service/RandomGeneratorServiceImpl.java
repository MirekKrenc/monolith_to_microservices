package miro.socialmath.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {
    /**
     * range of generated numbers
     */
    public static int MIN_RANGE = 11;
    public static int  MAX_RANGE = 100;

    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAX_RANGE - MIN_RANGE) +1) + MIN_RANGE;
    }
}
