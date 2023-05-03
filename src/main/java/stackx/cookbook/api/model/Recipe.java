package stackx.cookbook.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity @Table(name = "tb_recipe") @Getter @Setter
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @JoinColumn
    private Integer idRecipe;

    private String title;

    @Column(nullable = false, updatable = true)
    private String category;

    @JoinColumn(updatable = true)
    private Long imgId;

    @Column(updatable = true, length = 4000)
    private String ingredients;

    @Column(updatable = true, length = 4000)
    private String preparationMethod;




}


