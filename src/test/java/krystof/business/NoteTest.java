package krystof.business;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by polansky on 31.12.2017.
 */
public class NoteTest {


    @Test
    public void bindign() throws Exception {

        Note note = new Note();
       // Label label = note.getLabels().get(0);
//        Label label = note.getLabels().get(0);

        ArrayList<Label> arrayList = new ArrayList<>();
        Label label1 = arrayList.get(0);

       // System.out.println(label==null);
        System.out.println(label1==null);
    }
}