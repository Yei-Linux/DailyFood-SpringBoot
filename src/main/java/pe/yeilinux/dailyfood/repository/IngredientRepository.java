package pe.yeilinux.dailyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.yeilinux.dailyfood.entity.IngredientEntity;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity,Long> {
}
