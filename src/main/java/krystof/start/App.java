package krystof.start;

import krystof.Scan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackageClasses = Scan.class)
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
