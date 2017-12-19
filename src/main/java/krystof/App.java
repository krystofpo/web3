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

    @Override
    public void run(String...args) throws Exception {
//        String labelAString = "labelA";
//        Label labelA = new Label(labelAString);
//
//        Label labelB = new Label("labelB");
//
//        Note note1 = new Note(
//                "note1", new HashSet<Label>(Arrays.asList(
//                labelA, labelB)));
//
//        repository.deleteAll();
////
//
//        repository.save(note1);
////        repository.save(note2);
//
//
//        Note actualNote = repository.findOne(1L);
////        List<Note> actualNotes = repository.oneNoteWithTwoLabels(labelAString);
//
//        System.out.println(actualNote);

    }

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}