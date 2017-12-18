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


@SpringBootApplication

public class App implements CommandLineRunner{
    @Autowired
    private NoteRepository repository;

    @Value("${nejakavar}")
    private String neco;

    @Value("${spring.h2.console.enabled}")
    private boolean necob;

    @Override
    public void run(String...args) throws Exception {
        System.out.println("bezim");
        System.out.println(neco);
        System.out.println(necob);

        String labelAString = "labelA";
        Label labelA1 = new Label(labelAString);
//        Label labelA2 = new Label("labelA");
//        Label labelC = new Label("labelC");
        Label labelB = new Label("labelB");

        Note note1 = new Note(
                "note1", new HashSet<Label>(Arrays.asList(
                labelA1, labelB)));

        labelA1.setNotes(new HashSet<Note>());
        labelB.setNotes(new HashSet<Note>());
        labelA1.getNotes().add(note1);
        labelB.getNotes().add(note1);
//        Note note2 = new Note(
//                "note2", new HashSet<Label>(Arrays.asList(
//                        labelA1, labelC)));

        repository.deleteAll();
//

        repository.save(note1);

    }

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}

//@Component
//public class CommandLineAppStartupRunner implements CommandLineRunner {
//    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
//
//    @Override
//    public void run(String...args) throws Exception {
//        logger.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C.", Arrays.toString(args));
//    }
//}