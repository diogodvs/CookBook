package stackx.cookbook.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackx.cookbook.api.model.Recipe;
import stackx.cookbook.api.model.User;
import stackx.cookbook.api.repository.RecipesRepository;
import stackx.cookbook.api.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    RecipesRepository recRep;

    UserController uc;
    @Autowired
    UserRepository userRep;

    //CRUD

    //CREATE
    @PostMapping("/user={idUser}")
    public String saveRecipe(@RequestBody Recipe recipe, @PathVariable("idUser") Integer idUser) {
        Optional<User> userTemporary = userRep.findById(idUser);
        if(userTemporary.isPresent()) {
            List<Recipe> recipesListTemporary = userTemporary.get().getRecipesList();
            recipesListTemporary.add(recipe);
            userTemporary.get().setRecipesList(recipesListTemporary);
            recRep.save(recipe);
            return "Recipe Save Sucessfully.";
        }
        return "User not found.";
    }

    // READ
    @GetMapping
    public List<Recipe> getAllRecipes(){
        return recRep.findAll();
    }

    //UPDATE
    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Integer id, @RequestBody Recipe recipe) {
        return recRep.save(recipe);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteRecipebyId(@PathVariable Integer id){
        recRep.deleteById(id);

    }
}
