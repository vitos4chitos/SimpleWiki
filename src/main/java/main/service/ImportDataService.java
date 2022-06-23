package main.service;

import main.entity.Article;
import main.entity.Category;
import main.entity.Reference;
import main.repo.ArticleRepository;
import main.repo.CategoryRepository;
import main.repo.ReferenceRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ImportDataService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ReferenceRepository referenceRepository;

    public ImportDataService(ArticleRepository articleRepository, CategoryRepository categoryRepository, ReferenceRepository referenceRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.referenceRepository = referenceRepository;
    }

    public void importData(String path) throws IOException, RuntimeException{
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                parseJson(line);
                line = reader.readLine();
            }
            reader.close();
            fr.close();
    }

    private void parseJson(String jsonString){
        try {
            Object obj = new JSONParser().parse(jsonString);
            JSONObject jo = (JSONObject) obj;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Article article = new Article();
            //wiki
            article.setWiki(jo.get("wiki").toString());
            //lang
            article.setLanguage(jo.get("language").toString());
            //title
            article.setTitle(jo.get("title").toString());
            String timestamp = jo.get("timestamp").toString()
                    .replace("T", " ").replace("Z", "");
            Date parsedDate = dateFormat.parse(timestamp);
            //timestamp
            article.setTimestamp(new Timestamp(parsedDate.getTime()));
            String create_timestamp = jo.get("create_timestamp").toString()
                    .replace("T", " ").replace("Z", "");
            Date parsedDateCreate = dateFormat.parse(create_timestamp);
            //create_timestamp
            article.setCreateTimestamp(new Timestamp(parsedDateCreate.getTime()));
            String[] auxt;
            JSONArray jsonArrayAuxt = (JSONArray) jo.get("auxiliary_text");
            //auxiliary_text
            if (jsonArrayAuxt != null && jsonArrayAuxt.size() != 0) {
                auxt = new String[jsonArrayAuxt.size()];
                for (int i = 0; i < jsonArrayAuxt.size();i++){
                    auxt[i] = jsonArrayAuxt.get(i).toString();
                }
                article.setAuxT(auxt);
            }
            else{
                auxt = new String[0];
                article.setAuxT(auxt);
            }
            Long articleId = saveArticle(article);
            JSONArray jsonCategory = (JSONArray) jo.get("category");
            List<String> category = new ArrayList<>();
            if(jsonCategory != null && jsonCategory.size() != 0){
                for (Object o : jsonCategory) {
                    category.add(o.toString());
                }
                saveCategory(articleId, category);
            }
        }
         catch (ParseException | java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Long saveArticle(Article article){
        articleRepository.save(article);
        return article.getId();
    }

    private void saveCategory(Long id, List<String> categories){
        for(String category: categories){
            Optional<Category> optionalCategory = categoryRepository.getCategoryByName(category);
            if(optionalCategory.isPresent()){
                Category categoryFromDB = optionalCategory.get();
                Reference reference = new Reference();
                reference.setAid(id);
                reference.setCid(categoryFromDB.getId());
                referenceRepository.save(reference);
            }
            else{
                Category categoryToDB = new Category();
                categoryToDB.setName(category);
                categoryRepository.save(categoryToDB);
                Reference reference = new Reference();
                reference.setAid(id);
                reference.setCid(categoryToDB.getId());
                referenceRepository.save(reference);
            }
        }
    }
}
