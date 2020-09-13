package miro.socialmath.domain;

import javax.persistence.*;

/**
 * info about attempts of {@link User} to solve o {@link Multiplication}
 */
@Entity
public class MultiplicationResultAttempt {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private final User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MULTIPLICATION_ID")
    private final Multiplication multiplication;
    private final int resultAttempt;


    private final boolean correct;

    public MultiplicationResultAttempt(User user, Multiplication multiplication, int resultAttempt, boolean correct) {
        this.user = user;
        this.multiplication = multiplication;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MultiplicationResultAttempt{" +
                "id=" + id +
                ", user=" + user +
                ", multiplication=" + multiplication +
                ", resultAttempt=" + resultAttempt +
                ", correct=" + correct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        MultiplicationResultAttempt mra = (MultiplicationResultAttempt) o;
        return this.resultAttempt == mra.resultAttempt &&
                this.getMultiplication().getFactorA() == mra.getMultiplication().getFactorA() &&
                this.getMultiplication().getFactorB() == mra.getMultiplication().getFactorB() &&
                this.user.getAlias().equals(mra.user.getAlias());
    }
}
