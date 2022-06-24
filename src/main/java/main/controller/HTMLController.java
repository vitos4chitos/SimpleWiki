package main.controller;

import main.service.HTMLService;
import main.service.WikiService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class HTMLController {

    @Autowired
    WikiService wikiService;

    @Autowired
    HTMLService htmlService;

    @GetMapping("/html/{name}")
    public String getHtml(@PathVariable String name){
        try {
            JSONObject jsonObject = wikiService.wiki(name);
            return htmlService.converter(jsonObject, name);

        }
        catch (ParseException e) {
            return "<!doctype html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "  <meta charset=\"utf-8\">\n" +
                    "  <title>Page Not Found</title>\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "  <style>\n" +
                    "    * {\n" +
                    "      line-height: 1.2;\n" +
                    "      margin: 0;\n" +
                    "    }\n" +
                    "\n" +
                    "    html {\n" +
                    "      color: #888;\n" +
                    "      display: table;\n" +
                    "      font-family: sans-serif;\n" +
                    "      height: 100%;\n" +
                    "      text-align: center;\n" +
                    "      width: 100%;\n" +
                    "    }\n" +
                    "\n" +
                    "    body {\n" +
                    "      display: table-cell;\n" +
                    "      vertical-align: middle;\n" +
                    "      margin: 2em auto;\n" +
                    "    }\n" +
                    "\n" +
                    "    h1 {\n" +
                    "      color: #555;\n" +
                    "      font-size: 2em;\n" +
                    "      font-weight: 400;\n" +
                    "    }\n" +
                    "\n" +
                    "    p {\n" +
                    "      margin: 0 auto;\n" +
                    "      width: 280px;\n" +
                    "    }\n" +
                    "\n" +
                    "    @media only screen and (max-width: 280px) {\n" +
                    "\n" +
                    "      body,\n" +
                    "      p {\n" +
                    "        width: 95%;\n" +
                    "      }\n" +
                    "\n" +
                    "      h1 {\n" +
                    "        font-size: 1.5em;\n" +
                    "        margin: 0 0 0.3em;\n" +
                    "      }\n" +
                    "\n" +
                    "    }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "  <h1>Page Not Found</h1>\n" +
                    "  <p>Sorry, but the page you were trying to view does not exist.</p>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
        }
    }
}
