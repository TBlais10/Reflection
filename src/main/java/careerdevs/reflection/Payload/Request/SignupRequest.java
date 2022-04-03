package careerdevs.reflection.Payload.Request;

import java.util.Set;

public class SignupRequest {

    private String username;
    private String password;
    private Set<String> roles;

    public SignupRequest(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return roles;
    }

    public void setRole(Set<String> role) {
        this.roles = role;
    }
}
