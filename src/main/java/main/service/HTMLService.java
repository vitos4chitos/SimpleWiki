package main.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class HTMLService {

    public HTMLService(){}

    public String converter(JSONObject jsonObject, String name){
        StringBuilder stringBuilder = new StringBuilder();
        JSONArray jsonArray = (JSONArray) jsonObject.get("auxiliary_text");
        String auxt = "";
        for(Object string: jsonArray){
            auxt += string.toString() + ". ";
        }
        JSONArray categories = (JSONArray) jsonObject.get("category");
        String cat = "";
        for(Object string: categories){
            cat += "    <li>" + string.toString() + "</li>\n";
        }
        stringBuilder.append("<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <title>" + name + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<header>\n" +
                "  <div>\n" +
                "    <h1>" + name + "</h1>\n" +
                "    <p>" + auxt + "</p>\n" +
                "  </div>\n" +
                "</header>\n" +
                "\n" +
                "<section>\n" +
                "  <h2>Что-то про время создания и время последнего обновления в UNIX time</h2>\n" +
                "  <p>" + jsonObject.get("create_timestamp").toString() + "</p>\n" +
                "  <p>" + jsonObject.get("timestamp").toString() + "</p>\n" +
                "</section>\n" +
                "\n" +
                "<section>\n" +
                "  <h2>Категории</h2>\n" +
                "  <ul>\n" +
               cat +
                "  </ul>\n" +
                "</section>\n" +
                "\n" +
                "<section>\n" +
                "  <h2>Метаинформация</h2>\n" +
                "  <ul>\n" +
                "    <li>"+ jsonObject.get("wiki").toString() + "</li>\n" +
                "    <li>" + jsonObject.get("language") + "</li>\n" +
                "  </ul>\n" +
                "</section>\n" +
                "\n" +
                "<footer>\n" +
                "  <div>\n" +
                "    <p>© Козлов В.Н., 2022</p>\n" +
                "    <p>Для Gazprom</p>\n" +
                "  </div>\n" +
                "</footer>\n" +
                "</body>\n" +
                "</html>");
        return stringBuilder.toString();
    }
}
