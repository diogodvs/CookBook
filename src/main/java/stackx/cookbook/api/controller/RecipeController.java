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

    @Autowired
    UserRepository userRep;

    //CRUD

    //CREATE
    @PostMapping("/user={idUser}")
    public ResponseEntity saveRecipe(@RequestBody Recipe recipe, @PathVariable("idUser") Integer idUser) {
        Optional<User> userTemporary = userRep.findById(idUser);
        if(userTemporary.isPresent()) {
            List<Recipe> recipesListTemporary = userTemporary.get().getRecipesList();
            recipesListTemporary.add(recipe);
            userTemporary.get().setRecipesList(recipesListTemporary);
            recRep.save(recipe);
            return ResponseEntity.ok(recipe);
        }
        return ResponseEntity.notFound().build();
    }

    // READ
    @GetMapping
    public List<Recipe> getAllRecipes(){
        return recRep.findAll();
    }

    @GetMapping("/user={idUser}/recipe={id}")
    public ResponseEntity getRecipeById(@PathVariable Integer id, @PathVariable Integer idUser){
        Optional<User> optionalUser = userRep.findById(idUser);
        if (optionalUser.isPresent()){
            Recipe entityRecipe = optionalUser.get().getRecipesList().get(id-1);
            return ResponseEntity.ok(entityRecipe);
        }
        return ResponseEntity.notFound().build();
    }

    //UPDATE
    @PutMapping("/user={idUser}/recipe={id}")
    public ResponseEntity updateRecipe(@PathVariable Integer id, @RequestBody Recipe updatedRecipe) {
        Optional<Recipe> optionalRecipe = recRep.findById(id);
         if (optionalRecipe.isPresent()){
            Recipe entityRecipe = optionalRecipe.get();
            entityRecipe.setImg(updatedRecipe.getImg());
            entityRecipe.setTitle(updatedRecipe.getTitle());
            entityRecipe.setIngredients(updatedRecipe.getIngredients());
            entityRecipe.setPreparationMethod(updatedRecipe.getPreparationMethod());
            recRep.save(entityRecipe);

            return ResponseEntity.ok(entityRecipe);
      }
         return ResponseEntity.notFound().build();
    }

    //DELETE
    @DeleteMapping("/user={idUser}/recipe={id}")
    public void deleteRecipebyId(@PathVariable Integer id){
        recRep.deleteById(id);

    }
}
