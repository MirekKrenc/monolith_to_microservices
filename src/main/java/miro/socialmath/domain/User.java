package miro.socialmath.domain;

import javax.persistence.*;

/**
 * info about user
 */
@Entity
@Table(indexes = {@Index(name="alias_u", columnList = "alias", unique = true)})
public final class User {

    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private long id;


    private final String alias;

    public User(String alias) {
        if (alias == null)
            this.alias = "john doe";
        else
            this.alias = alias;
    }

    public User() {
        this("john doe");
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return "User{" +
                "alias='" + alias + '\'' +
                '}';
    }
}
