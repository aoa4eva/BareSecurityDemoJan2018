package me.afua.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long id;

    @Column(unique=true)
    private String role;

    @ManyToMany(mappedBy="roles")
    private List<UserClass> users;

    public UserRole() {
        this.users = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<UserClass> getUsers() {
        return users;
    }

    public void setUsers(List<UserClass> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
