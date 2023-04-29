package stackx.cookbook.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackx.cookbook.api.model.User;
import stackx.cookbook.api.repository.RecipesRepository;
import stackx.cookbook.api.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRep;

    // CRUD

    // CREATE
    @PostMapping
    public User createUser(@RequestBody User user){
        return userRep.save(user);
    }

    //READ

    //READ all users
    @GetMapping
    public List<User> readUsers(){
     return userRep.findAll();

    }

    //READ one User by Id
    @GetMapping("/{id}")
    public Optional<User> readUserById(@PathVariable Integer id){
        return userRep.findById(id);
    }

    // Update

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id , @RequestBody User updatedUser){
        Optional<User> optionalUser = userRep.findById(id);
        if(optionalUser.isPresent()){
            User userEntity = optionalUser.get();
            userEntity.setName(updatedUser.getName());
            userEntity.setLogin(updatedUser.getLogin());
            userEntity.setPassword(updatedUser.getPassword());
            userRep.save(userEntity);
            return ResponseEntity.ok(userEntity);
        } else {
            return ResponseEntity.notFound().build();

        }

    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteUserbyId(@PathVariable Integer id){
        userRep.deleteById(id);
    }

    //LOGIN

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String Login(@RequestParam String login, @RequestParam String password ){
        User user = new User();
        List<User> userListTemporary = userRep.findAll();

        if (userListTemporary.contains(login)){
            user.setIdUser(userListTemporary.indexOf(login));

            if (password == user.getPassword()){
                return " user id = " + user.getIdUser();
            }
        }
            return " id nao encontrado";
    }

}
