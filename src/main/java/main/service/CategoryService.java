package main.service;

import main.entity.Category;
import main.entity.Reference;
import main.repo.ArticleRepository;
import main.repo.CategoryRepository;
import main.repo.ReferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ReferenceRepository referenceRepository;

    public CategoryService(CategoryRepository categoryRepository, ReferenceRepository referenceRepository) {
        this.categoryRepository = categoryRepository;
        this.referenceRepository = referenceRepository;
    }

    public String info(String category){
        Optional<Category> optionalCategory = categoryRepository.getCategoryByNameIgnoreCase(category);
        if(optionalCategory.isPresent()){
            Category categoryFromBD = optionalCategory.get();
            List<Reference> references = referenceRepository.getReferencesByCid(categoryFromBD.getId());
            return "Количество статей с категорией " + categoryFromBD.getName() + ": " + references.size();
        }
        else{
            return "Такой статьи не существует";
        }
    }
}
