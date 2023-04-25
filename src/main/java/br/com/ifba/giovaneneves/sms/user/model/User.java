package br.com.ifba.giovaneneves.sms.user.model;

import br.com.ifba.giovaneneves.sms.infrastructure.model.AbstractEntity;
import br.com.ifba.giovaneneves.sms.user.role.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

import java.util.List;

@Entity
@Table(name="users")
@Data
public class User extends AbstractEntity {

    public User(){
        super();
        this.setActive(false);
    }

    @NotNull(message = "user cannot be empty")
    private String name;

    @Email
    private String email;

    @Size(min = 4, message = "cannot be less than 4 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    private boolean active;

}
