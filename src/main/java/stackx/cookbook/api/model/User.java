package stackx.cookbook.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity @Table(name = "tb_user") @Getter @Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private String login;

    private String password;

    private String name;

    @Column(updatable = true, length = 10000, nullable = false)
    @OneToMany
    private List<Recipe> recipesList;


}
