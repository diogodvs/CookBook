package stackx.cookbook.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity @Table(name = "tb_user") @Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private String login;

    private String password;

    private String name;

    @Column(updatable = true, length = 10000, nullable = false)
    @JoinTable(name="user-id_recipes",
            joinColumns={@JoinColumn(name="user_id",
                    referencedColumnName="idUser")},
            inverseJoinColumns={@JoinColumn(name="recipe_id",
                    referencedColumnName="idRecipe")})
    @OneToMany
    private List<Recipe> recipes;


}
