package krystof.Controller;

import krystof.business.Label;
import krystof.business.NoteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "/savelabel", method = RequestMethod.POST)
    public String submitLabel(@ModelAttribute(name = "labelentity") Label labelentity) {

        //todo save it, ale jak to predat view jako novy objekt? lepsi
        //by bzlo psmeroa tna label a id a dat to tomu id jako parametr
        return "result";
    }


}