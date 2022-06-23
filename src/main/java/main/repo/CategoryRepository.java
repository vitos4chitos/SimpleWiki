package main.repo;

import main.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryById(Long id);
    Optional<Category> getCategoryByName(String name);

    Optional<Category> getCategoryByNameIgnoreCase(String name);
}
