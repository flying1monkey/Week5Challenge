package edu.berliner.week4challenge.controllers;

import edu.berliner.week4challenge.models.Person;
import edu.berliner.week4challenge.repositories.EducationRepo;
import edu.berliner.week4challenge.repositories.JobRepo;
import edu.berliner.week4challenge.repositories.PersonRepo;
import edu.berliner.week4challenge.repositories.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    PersonRepo personRepo;
    @Autowired
    EducationRepo edRepo;
    @Autowired
    JobRepo jobRepo;
    @Autowired
    SkillRepo skillRepo;

    @GetMapping("/")
    public String home()
    {
        return "home";
    }

    @GetMapping("/home")
    public String alsoHome()
    {
        return "home";
    }

    @GetMapping("/addperson")
    public String addPerson(Model model)
    {
        model.addAttribute("addperson", new Person());
        return "addperson";
    }

    @PostMapping("/addperson")
    public String submitPerson(@Valid @ModelAttribute("person")Person person, BindingResult result)
    {
        personRepo.save(person);
        return "submitperson";
    }

}
