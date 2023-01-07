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

import static org.aspectj.weaver.UnresolvedType.add;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    RecipesRepository recRep;

    @Autowired
    UserRepository userRep;

    //CRUD

    //CREATE
    @PostMapping("/{id}")
    public ResponseEntity saveRecipe(@RequestBody Recipe recipe, @PathVariable Integer id) {
        Optional<User> optionalUser = userRep.findById(id);
        if (optionalUser.isPresent()) {
            userRep.findById(id).get().setRecipes(Collections.singletonList(recRep.save(recipe)));
            return ResponseEntity.ok(optionalUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //READ
    @GetMapping("/user/{id}")
    public List<Recipe> getAllRecipes(@PathVariable Integer id){
      return userRep.findById(id).get().getRecipes();
    }

    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable Integer id){
        return recRep.findById(id);
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
