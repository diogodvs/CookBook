package stackx.cookbook.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    private Integer idRecipe;

    private String img;

    private String ingredients;

    private String preparationMethod;

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }
}
