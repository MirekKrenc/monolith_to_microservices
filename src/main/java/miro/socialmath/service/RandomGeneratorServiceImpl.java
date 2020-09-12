package miro.socialmath.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {


    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAX_RANGE - MIN_RANGE) +1) + MIN_RANGE;
    }
}
