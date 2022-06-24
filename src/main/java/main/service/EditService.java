package main.service;

import main.entity.Article;
import main.entity.Category;
import main.entity.EditField;
import main.entity.Reference;
import main.repo.ArticleRepository;
import main.repo.CategoryRepository;
import main.repo.ReferenceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class EditService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ReferenceRepository referenceRepository;

    public EditService(ArticleRepository articleRepository, CategoryRepository categoryRepository, ReferenceRepository referenceRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.referenceRepository = referenceRepository;
    }
    @Transactional
    public String edit(String name, EditField editField) throws NonUniqueResultException {
        List<Article> optionalArticle = articleRepository.getArticlesByTitleIgnoreCase(name);
        if(optionalArticle.size() > 1){
            return "Несколько статей с таким названием, не знаю какую редактировать";
        }
        if(optionalArticle.size() == 1){
            Article article = optionalArticle.get(0);
            if(editField.getAuxT() != null)
                article.setAuxT(editField.getAuxT());
            if(editField.getTitle() != null)
                article.setTitle(editField.getTitle());
            article.setTimestamp(new Timestamp(System.currentTimeMillis()));
            articleRepository.save(article);
            if(editField.getCategories() != null) {
                referenceRepository.deleteReferencesByAid(article.getId());
                String[] categories = editField.getCategories();
                for (String s : categories) {
                    Optional<Category> optionalCategory = categoryRepository.getCategoryByNameIgnoreCase(s);
                    if (optionalCategory.isPresent()) {
                        Category category = optionalCategory.get();
                        Reference reference = new Reference();
                        reference.setAid(article.getId());
                        reference.setCid(category.getId());
                        referenceRepository.save(reference);
                    } else {
                        Category category = new Category();
                        category.setName(s);
                        categoryRepository.save(category);
                        Reference reference = new Reference();
                        reference.setAid(article.getId());
                        reference.setCid(category.getId());
                        referenceRepository.save(reference);
                    }
                }
            }
            return "Данные были успешно обновлены";
        }
        else{
            return "Такой страницы не существует";
        }
    }
}
