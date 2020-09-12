package miro.socialmath.domain;

/**
 * info about user
 */
public final class User {

    private final String alias;

    public User(String alias) {
        this.alias = alias;
    }

    public User() {
        this("xx");
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
