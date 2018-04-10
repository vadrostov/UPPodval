package com.vadimrostov.uyutp.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "UP_NEW_MESSAGE")
public class UPMessage {


    private Long id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEW_MESSAGE+ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
