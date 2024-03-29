package stackx.cookbook.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stackx.cookbook.api.model.Image;
import stackx.cookbook.api.model.Recipe;
import stackx.cookbook.api.model.User;
import stackx.cookbook.api.repository.RecipesRepository;
import stackx.cookbook.api.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController extends ImageController {

    @Autowired
    RecipesRepository recRep;

    @Autowired
    UserRepository userRep;

    //CRUD

    //CREATE
    @PostMapping("/user={idUser}")
    public ResponseEntity saveRecipe(@RequestBody Recipe recipe,
                                     @PathVariable("idUser") Integer idUser,
                                     @RequestParam("img") MultipartFile file) throws IOException {

        Optional<User> userTemporary = userRep.findById(idUser);

        if(userTemporary.isPresent()) {
            recipe.setImgId(this.uploadImage(file));
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
    public ResponseEntity updateRecipe(@PathVariable Integer id,
                                       @RequestBody Recipe updatedRecipe,
                                       @RequestParam("img") MultipartFile fileImage) throws IOException {
        Optional<Recipe> optionalRecipe = recRep.findById(id);
         if (optionalRecipe.isPresent()){
            Recipe entityRecipe = optionalRecipe.get();
            entityRecipe.setImgId(uploadImage(fileImage));
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
    public void deleteRecipebyId(@PathVariable Integer id) {
        recRep.deleteById(id);
    }

}
