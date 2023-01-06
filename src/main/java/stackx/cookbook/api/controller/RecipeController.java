package stackx.cookbook.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackx.cookbook.api.model.Recipe;
import stackx.cookbook.api.repository.RecipesRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController  {

    @Autowired
    RecipesRepository recRep;

    //CRUD

    //CREATE
    @PostMapping
    public Recipe saveRecipe(@RequestBody Recipe recipe){
        return recRep.save(recipe);
    }

    //READ
    @GetMapping
    public List<Recipe> getAllRecipes(){
        return recRep.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable Integer id){
        return recRep.findById(id);
    }

    //UPDATE
    @PostMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Integer id, @RequestBody Recipe recipe) {
        return recRep.save(recipe);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteRecipebyId(@PathVariable Integer id){
        recRep.deleteById(id);

    }
}
