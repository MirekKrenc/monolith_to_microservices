package miro.socialmath.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The class represents mulitplication of to int numbers
 * from ange 11 to 100 so teh result is int also
 */
@Entity
public final class Multiplication {

    @Id
    @Column(name="MULTIPLICATION_ID")
    @GeneratedValue
    private long id;

    private int factorA;
    private int factorB;

    private int result;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }

    public Multiplication() {
        this(0,0);
    }

    public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", result (A*B)=" + result +
                '}';
    }
}
