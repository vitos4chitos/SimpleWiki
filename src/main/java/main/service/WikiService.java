package main.service;

import main.entity.Article;
import main.entity.Category;
import main.entity.Reference;
import main.repo.ArticleRepository;
import main.repo.CategoryRepository;
import main.repo.ReferenceRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WikiService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ReferenceRepository referenceRepository;

    public WikiService(ArticleRepository articleRepository, CategoryRepository categoryRepository, ReferenceRepository referenceRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.referenceRepository = referenceRepository;
    }

    public JSONObject wiki(String name) throws ParseException {
        Optional<Article> optionalArticle = articleRepository.getArticleByTitleIgnoreCase(name);
        if(optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            System.out.println(article.getWiki());
            JSONObject jsonObject = new JSONObject();
            String create_timestamp = article.getCreateTimestamp().toString();
            String timestamp = article.getTimestamp().toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date ctDate = dateFormat.parse(create_timestamp);
            Date tDate = dateFormat.parse(timestamp);
            jsonObject.put("create_timestamp", ctDate.getTime() / 1000L);
            jsonObject.put("timestamp", tDate.getTime() / 1000L);
            jsonObject.put("language", article.getLanguage());
            jsonObject.put("wiki", article.getWiki());
            jsonObject.put("title", article.getTitle());
            JSONArray jsonArrayAuxT = new JSONArray();
            String[] auxT = article.getAuxT();
            if(auxT != null && auxT.length > 0) {
                for (int i = 0; i < auxT.length; i++) {
                    jsonArrayAuxT.add(auxT[i]);
                }
            }
            jsonObject.put("auxiliary_text", jsonArrayAuxT);
            List<Reference> referenceList = referenceRepository.getReferencesByAid(article.getId());
            JSONArray jsonArrayReference = new JSONArray();
            for(Reference reference: referenceList){
                Category category = categoryRepository.getCategoryById(reference.getCid());
                jsonArrayReference.add(category.getName());
            }
            jsonObject.put("category", jsonArrayReference);
            return jsonObject;
        }
        return null;
    }
}
