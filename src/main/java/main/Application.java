package main;


import main.service.ImportDataService;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Objects;


@SpringBootApplication
public class Application {

    @Autowired
    ImportDataService importDataService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            if(args.length > 1 && Objects.equals(args[0], "true")){
                String script = "C:\\Users\\Admin\\Desktop\\gazprom\\src\\main\\sql\\db.sql";
                try {
                    ScriptRunner scriptRunner = new ScriptRunner(
                            DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin"));
                    scriptRunner.runScript(new BufferedReader(new FileReader(script)));
                    importDataService.importData(args[1]);
                    System.out.println("The database was successfully updated from the file " + args[1]);
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
                System.out.println(args[1]);
            }
            else{
                System.out.println("Nothing to change");
            }
        };
    }

}