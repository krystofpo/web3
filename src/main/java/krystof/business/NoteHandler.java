package krystof.business;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import krystof.Data.LabelRepository;
import krystof.Data.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by polansky on 12.12.2017.
 */
@Service
public class NoteHandler { //todo refactor change name to reposservice

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private LabelRepository labelRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public NoteHandler() {
    }

    protected NoteRepository getNoteRepository() {
        return noteRepository;
    }

    protected LabelRepository getLabelRepository() {
        return labelRepository;
    }

    public void setLabelRepository(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Note findOne(long id) {
        System.out.println(noteRepository.toString());
//        return noteRepository.findOne(id);
        //noteRepository.deleteAll();
//        noteRepository.save(new Note("notePokus","pokus"));
//        return noteRepository.findByLabel("pokus").get(0);
        return noteRepository.findOne(id);
    }

    //saves a new Note or finds existing one.
    //method accepts a Note with saved or unsaved Labels
    public Note save(Note note) {
        if (sameNoteIsSavedAlready(note)) {
            throw new NoteHandlerException(
                    "Error: Attempt to save a note which " +
                            "already exists. Use update instead.");
        } else {
            return saveNewNote(note);
        }
    }

    private Note saveNewNote(Note note) {
        Set<Label> labels = note.getLabels();
        if (!isEmpty(labels)) {
            setSavedLabelsToNote(note);
        }
        return saveNoteWithSavedLabels(note);
    }

    private boolean sameNoteIsSavedAlready(Note note) {
        return !isEmpty(noteRepository.findByNote(note.getNote()));
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

    public List<Note> findByLabel(Label labelA) {
        return noteRepository.findByLabel(labelA);
    }

    public Label save(Label label) {
        if (label == null || isBlank(label.getLabel())) {
            return null;
        }
        return findOrSaveLabel(label);
    }

    public List<Note> findByLabels(List<Label> labels) {
        if (isEmpty(labels)) {
            return new ArrayList<>();
        }

        ListWithFlag listWithFlag = findSavedLabelsOrFlagNonSavedLabel(labels);
        if (listWithFlag.containsNonSavedLabel()) {
            return new ArrayList<>();
        }
        List<Label> savedLabels = listWithFlag.getSavedLabels();



//
//        public List<Customer> getCustomer(String... names) {
//            QCustomer customer = QCustomer.customer;
//            JPAQuery<Customer> query = queryFactory.selectFrom(customer);
//            BooleanBuilder builder = new BooleanBuilder();
//            for (String name : names) {
//                builder.or(customer.name.eq(name));
//            }
//            query.where(builder);
//            return query.fetch();
//        }



//TODO moje
        JPAQuery query = new JPAQuery(entityManager);
        krystof.business.QNote note = krystof.business.QNote.note1;

        BooleanBuilder builder = new BooleanBuilder();

        for (Label savedLabel : savedLabels) {
            builder.and(note.labels.contains(savedLabel));

        }

        List<Note> notes = query.from(note)
                .where(builder)
                .list(note);

        return notes;





//
//        switch (savedLabels.size()) {
//
//            case 1:
//                JPAQuery query = new JPAQuery(entityManager);
//                krystof.business.QNote qNote = krystof.business.QNote.note1;
//                List<Note> notes = query.from(qNote)
//                        .where(qNote.labels.contains(savedLabels.get(0)))
//                        .list(qNote);
//                return notes;
//            default:
//                return new ArrayList<>();
//        }
    }

    ListWithFlag findSavedLabelsOrFlagNonSavedLabel(List<Label> labels) {
        if (isEmpty(labels)) {
            return null;
        }

        ListWithFlag listWithFlag = new ListWithFlag();

        for (Label unsavedLabel : labels) {

            Label savedLabel = findOne(unsavedLabel.getLabel());
            if (savedLabel == null) {
                listWithFlag.setSavedLabels(new ArrayList<>());
                listWithFlag.setContainsNonSavedLabel(true);
                return listWithFlag;
            }
            listWithFlag.getSavedLabels().add(savedLabel);

        }

        return listWithFlag;
    }

    public Label findOne(String label) {
        if (isBlank(label)) {
            return null;
        }

        List<Label> labels = labelRepository.findByLabel(label);
        if (isEmpty(labels)) {
            return null;
        }
        return labels.get(0);
    }
}


