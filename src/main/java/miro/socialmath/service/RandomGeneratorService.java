package miro.socialmath.service;

public interface RandomGeneratorService {

    /**
     * range of generated numbers
     */
    public static int MIN_RANGE = 11;
    public static int  MAX_RANGE = 100;
    /**
     *
     * @return a randomly generated factor
     */
    int generateRandomFactor();
}
