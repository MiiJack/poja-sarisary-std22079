package hei.school.sarisary.repository;

import hei.school.sarisary.repository.model.ImageTransformation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageTransformationRepository extends JpaRepository<ImageTransformation, String> {
  @Override
  Optional<ImageTransformation> findById(String id);
}
