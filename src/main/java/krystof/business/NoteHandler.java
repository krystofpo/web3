package krystof.business;

import krystof.Data.LabelRepository;
import krystof.Data.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * Created by polansky on 12.12.2017.
 */
@Service
public class NoteHandler { //todo refactor change name to reposservice

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private LabelRepository labelRepository;

    public NoteHandler() {
    }

    protected NoteRepository getNoteRepository() {
        return noteRepository;
    }

    protected LabelRepository getLabelRepository() {
        return labelRepository;
    }

    public Note findOne(long id) {
        System.out.println(noteRepository.toString());
//        return noteRepository.findOne(id);
        noteRepository.deleteAll();
//        noteRepository.save(new Note("notePokus","pokus"));
//        return noteRepository.findByLabel("pokus").get(0);
        return noteRepository.findOne(id);
    }

    //saves a new Note or finds existing one.
    //method accepts a Note with saved or unsaved Labels
    public Note save(Note note) {
        Set<Label> labels = note.getLabels();
        if (!isEmpty(labels)) {
            setSavedLabelsToNote(note);
        }
        return saveNoteWithSavedLabels(note);
    }


    //todo
    //does not consider difference between labels in existing note and new note
    //if differeces in labels exist, update should be called
    //is note is found, the new note saved new labels, that are not linked to any note
    private Note saveNoteWithSavedLabels(Note note) {
        List<Note> savedNotes = noteRepository.findByNote(note.getNote());
        if (isEmpty(savedNotes)) {
            return noteRepository.save(note);
        } else {
            return savedNotes.get(0);
        }
    }

    private void setSavedLabelsToNote(Note note) {
        Set<Label> savedLabels = createSetOfSavedLabels(note.getLabels());
        note.setLabels(savedLabels);
    }

    private Set<Label> createSetOfSavedLabels(Set<Label> unsavedLabels) {
        Set<Label> savedLabels = new HashSet<>();
        unsavedLabels.stream().
                forEach(l -> saveLabelAndAddToSet(l, savedLabels));
        return savedLabels;
    }

    private void saveLabelAndAddToSet(Label label, Set<Label> labels) {
        Label savedLabel = findOrSaveLabel(label);
        labels.add(savedLabel);
    }

    private Label findOrSaveLabel(Label label) {
        List<Label> savedLabels = labelRepository.findByLabel(label.getLabel());
        if (isEmpty(savedLabels)) {
            return labelRepository.save(label);
        } else {
            return savedLabels.get(0);
        }
    }

    public void deleteAllNotes() {
        noteRepository.deleteAll();
    }

    public void deleteAllLabels() {
        labelRepository.deleteAll();
    }

    public List<Note> findAllNotes() {
        List<Note> notes = noteRepository.findAll();
        if (notes == null) {
            return new ArrayList<>();
        } else {
            return notes;
        }
    }

    public List<Label> findAllLabels() {
        List<Label> labels = labelRepository.findAll();
        if (labels == null) {
            return new ArrayList<>();
        } else {
            return labels;
        }
    }
}
