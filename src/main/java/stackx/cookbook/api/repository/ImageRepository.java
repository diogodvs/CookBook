package stackx.cookbook.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackx.cookbook.api.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
