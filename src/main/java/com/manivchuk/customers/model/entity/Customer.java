package com.manivchuk.customers.model.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cutomers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 24)
    @Column(nullable = false)
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "can't be empty")
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist(){
        createdAt = new Date();
    }
}