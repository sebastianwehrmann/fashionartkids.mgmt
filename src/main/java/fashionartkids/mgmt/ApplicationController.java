package fashionartkids.mgmt;

import fashionartkids.mgmt.model.form.TalentForm;
import fashionartkids.mgmt.model.talent.Talent;
import fashionartkids.mgmt.service.TalentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApplicationController {

    @Autowired
    private TalentService talentService;

    @GetMapping("/")
    public String homeView() {
        return "home";
    }

    @GetMapping("/models")
    public String getAll() {
        return "talent-list";
    }

    @GetMapping(value = "/models/fetch")
    public @ResponseBody DataTablesOutput<Talent> list(DataTablesInput input) {
        return talentService.fetchAll(input);
    }

    @GetMapping( "/model/{id}")
    public String getModel(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("talent", talentService.fetch(id));
        } catch (Exception e) {

        }
        return "talent";
    }
    @GetMapping("/model/new")
    public String addModelView(Model model) {
        model.addAttribute("formData", new Talent());
        return "talent-new";
    }

    @PostMapping("/model/new")
    public String addModel(@Valid @ModelAttribute("formData") Talent formData, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "talent-new";
        }
        try {
            talentService.add(formData);
            redirectAttributes.addFlashAttribute("message", "Erfolgreich angelegt.");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/models";
    }


    @GetMapping("model/{id}/edit")
    public String editModel(@PathVariable("id") int id, Model model) throws ChangeSetPersister.NotFoundException {
        model.addAttribute("talent", talentService.fetch(id));
        return "talent-edit";
    }

    @PostMapping( "/model/{id}/edit")
    public String updateModel(@PathVariable("id") int id, @Valid @ModelAttribute("formData") Talent formData, Model model) {
        try {
            model.addAttribute("talent", talentService.update(id, formData));
        } catch (Exception e) {

        }

        return "redirect:/model/{id}";
    }


    @DeleteMapping( "/model/{id}/delete")
    public String deleteModel(@PathVariable("id") int id) {
        talentService.delete(id);
        return "redirect:/models";
    }

    @GetMapping("/model/search")
    public String searchModel(Model model) {
        return "talent-search";
    }

    @GetMapping("/jobs")
    public String getAllJobs(Model model) {
        return "job-list";
    }
}
