package stackx.cookbook.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "tb_recipe") @Getter @Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Integer idRecipe;

    private String title;

    @Column(updatable = true, length = 2000)
    private String img;

    @Column(updatable = true, length = 4000)
    private String ingredients;

    @Column(updatable = true, length = 4000)
    private String preparationMethod;

}
