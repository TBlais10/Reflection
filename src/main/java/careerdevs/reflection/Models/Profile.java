package careerdevs.reflection.Models;

import careerdevs.reflection.Auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fname;
    private String lname;
    private long age;
    private String town;
    private String state;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany
    @JsonIncludeProperties({"date", "title"})
    private Set<JournalEntry> journalEntries;

    public Profile() {
    }

    public Profile(String fname, String lname, long age, String town, String state, User user) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.town = town;
        this.state = state;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(Set<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }
}
