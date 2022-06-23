package main.repo;

import main.entity.Article;
import main.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    Reference getReferenceById(Long id);
    List<Reference> getReferencesByAid(Long AId);
}
