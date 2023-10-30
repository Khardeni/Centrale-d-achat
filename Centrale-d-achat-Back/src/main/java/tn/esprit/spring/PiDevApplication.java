package tn.esprit.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tn.esprit.spring.configuration.DatabaseUtil;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class PiDevApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(PiDevApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
    }



    @Scheduled(cron=" 0 0 0 1/1 * ?")
    @PostConstruct
    public static boolean backup()
            throws IOException, InterruptedException {
        String dbName ="pidev1";
        String outputFile = " backups/"+String.valueOf(LocalDate.now())+".sql";
        File file = new File("C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\conf.cnf");
        boolean exists = file.exists();
        if (exists == true){
            System.out.println("Executable: " + file.canExecute());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
        }
        //String command = String.format("mkdir C:\\Users\\Houssem\\Desktop\\jehhe");
        System.out.println("executing database cloning..");
        Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c","mysqldump --defaults-extra-file=C:\\MyOwnLibrary\\Library$\\pidev\\Centrale-d-achat\\conf.cnf " +
                "--skip-column-statistics --add-drop-table --databases "+dbName+" > "+outputFile});
        System.out.println("executed database cloning process..");
        int processComplete = process.waitFor();
        //int processComplete1 = process.waitFor();
        System.out.println("waiting for database cloning process..");

        return processComplete == 0;
    }

}
