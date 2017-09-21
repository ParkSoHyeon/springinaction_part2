package spittr;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.Size;

public class Spitter {
    private long id;
    @NotNull
    @Size(min=5, max=16)
    private String username;

    @NotNull
    @Size(min=5, max = 25)
    private String password;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;


    public Spitter(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Spitter(long id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName( ){
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String password() {
        return password;
    }
}
