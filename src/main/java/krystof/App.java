package krystof;

import krystof.Data.NoteRepository;
import krystof.business.Label;
import krystof.business.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@SpringBootApplication

public class App implements CommandLineRunner{

    @Autowired
    private NoteRepository repository;

    @Override
    public void run(String...args) throws Exception {


    }

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}