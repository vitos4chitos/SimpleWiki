package main.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import main.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class SearchController {

    @Autowired
    WikiService wikiService;

    @GetMapping("/wiki/{name}")
    public String wiki(@PathVariable String name, @RequestParam(required = false) String pretty) {
        if(pretty == null)
            try {
                return wikiService.wiki(name).toString();
            }
            catch (ParseException e){
                return null;
            }
        else{
            try {
                String json = wikiService.wiki(name).toJSONString();
                System.out.println(json);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement je = JsonParser.parseString(json);
                String prettyJsonString = gson.toJson(je);
                System.out.println(prettyJsonString);
                return prettyJsonString;
            }
            catch (ParseException e){
                return null;
            }
        }
    }


}
