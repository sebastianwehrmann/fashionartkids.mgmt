package fashionartkids.mgmt;

import fashionartkids.mgmt.model.form.TalentForm;
import fashionartkids.mgmt.model.talent.Talent;
import fashionartkids.mgmt.service.TalentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("formData", new TalentForm());
        return "talent-new";
    }

    @PostMapping("/model/new")
    public String addModel(@Valid @ModelAttribute("formData") TalentForm formData, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
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

    @PostMapping( "/model/{id}")
    public String updateModel(@PathVariable("id") int id, Model model) {
        //TalentEntity talent = talentRepository.findById(id).get();
        return "";
    }

    @GetMapping("model/{id}/edit")
    public String editModel(@PathVariable("id") int id, Model model) {
        return "talent-edit";
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
