package krystof.Controller;

import krystof.business.Label;
import krystof.business.NoteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LabelController {


    private NoteHandler handler;

    @Autowired
    public LabelController(NoteHandler handler) {
        this.handler = handler;
    }

    @RequestMapping(value = "/savelabel", method = RequestMethod.GET)
    public String showSaveLabelForm(Model model) {
        model.addAttribute("labelentity", new Label());
        return "savelabel";
    }

    @RequestMapping(value = "/savelabel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String submitLabel(Label label) {
        System.out.println(label);
//        Label savedLabel = handler.save(label);
//        System.out.println("saved" + savedLabel);
//        System.out.println(model);
//        model.addAttribute("labelentity", savedLabel);
//        System.out.println(model);
//        //todo save it, ale jak to predat view jako novy objekt? lepsi
//        //by bzlo psmeroa tna label a id a dat to tomu id jako parametr
        return "result";
    }


}