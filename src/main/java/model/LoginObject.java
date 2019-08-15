package model;

import java.util.Objects;

public class LoginObject {
    private String email;

    public LoginObject(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
