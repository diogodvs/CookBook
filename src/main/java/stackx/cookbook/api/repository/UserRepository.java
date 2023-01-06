package stackx.cookbook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackx.cookbook.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
