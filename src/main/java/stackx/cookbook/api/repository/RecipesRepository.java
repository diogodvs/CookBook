package stackx.cookbook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackx.cookbook.api.model.Recipe;
@Repository
public interface RecipesRepository extends JpaRepository <Recipe, Integer> {

}
