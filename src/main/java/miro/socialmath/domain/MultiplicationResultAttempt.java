package miro.socialmath.domain;

/**
 * info about attempts of {@link User} to solve o {@link Multiplication}
 */
public class MultiplicationResultAttempt {

    private final User user;
    private final Multiplication multiplication;
    private final int resultAttempt;

    public MultiplicationResultAttempt(User user, Multiplication multiplication, int resultAttempt) {
        this.user = user;
        this.multiplication = multiplication;
        this.resultAttempt = resultAttempt;
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

    @Override
    public String toString() {
        return "MultiplicationResultAttempt{" +
                "user=" + user +
                ", multiplication=" + multiplication +
                ", resultAttempt=" + resultAttempt +
                '}';
    }
}
