package miro.socialmath.domain;

/**
 * info about attempts of {@link User} to solve o {@link Multiplication}
 */
public class MultiplicationResultAttempt {

    private final User user;
    private final Multiplication multiplication;
    private final int resultAttempt;


    private final boolean correct;

    public MultiplicationResultAttempt(User user, Multiplication multiplication, int resultAttempt, boolean attempt) {
        this.user = user;
        this.multiplication = multiplication;
        this.resultAttempt = resultAttempt;
        this.correct = attempt;
    }

    //no arg constructor for son purpose
    public MultiplicationResultAttempt() {
        this(null, null, -1, false);
    }

    public User getUser() {
        return user;
    }

    public Multiplication getMultiplication() {
        return multiplication;
    }

    public int getResultAttempt() {
        return resultAttempt;
    }


    public boolean isCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "MultiplicationResultAttempt{" +
                "user=" + user +
                ", multiplication=" + multiplication +
                ", resultAttempt=" + resultAttempt +
                ", correct=" + correct +
                '}';
    }
}
