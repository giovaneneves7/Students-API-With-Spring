package br.com.ifba.giovaneneves.sms.user.role.model;

import br.com.ifba.giovaneneves.sms.infrastructure.model.AbstractEntity;
import br.com.ifba.giovaneneves.sms.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role extends AbstractEntity {

    @NotNull
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<User> users;

    @Deprecated
    public Role(){

    }
    public Role(String role){
        super();
        this.role = role;
    }
}
