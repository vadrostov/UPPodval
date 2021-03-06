package com.vadimrostov.uyutp.data.domain.user;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UP_ROLE")
public class Role {


    Long id;

    String name;

    Set<User> users;

    String readableName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ROLE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Column(name = "ROLE_READABLE")
    public String getReadableName() {
        return readableName;
    }

    public void setReadableName(String readableName) {
        this.readableName = readableName;
    }

    @Override
    public boolean equals(Object obj) {
        Role role=(Role)obj;

        return this.id.equals(role.id);
    }
}
