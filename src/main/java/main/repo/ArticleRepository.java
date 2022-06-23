package main.repo;

import main.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article getArticleById(Long id);
    Optional<Article> getArticleByTitleIgnoreCase(String title);
}