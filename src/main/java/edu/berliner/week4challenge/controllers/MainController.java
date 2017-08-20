package edu.berliner.week4challenge.controllers;

import edu.berliner.week4challenge.models.Education;
import edu.berliner.week4challenge.models.Job;
import edu.berliner.week4challenge.models.Person;
import edu.berliner.week4challenge.models.Skill;
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


    //for blank url, go to home page
    @GetMapping("/")
    public String home()
    {
        return "home";
    }
    //home page
    @GetMapping("/home")
    public String alsoHome()
    {
        return "home";
    }
    //add person page
    @GetMapping("/addperson")
    public String addPerson(Model model)
    {
        model.addAttribute("addperson", new Person());
        return "addperson";
    }
    //for error checking addperson and adding the new person to the database
    @PostMapping("/addperson")
    public String submitPerson(@Valid @ModelAttribute("addperson")Person person, BindingResult result)
    {
        //checks for validation errors
        if(result.hasErrors())
        {
            return "addperson";
        }
        //only allow one person in the database
        person.setId((long) 1);
        personRepo.save(person);
        return "submitperson";
    }

    //add job page
    @GetMapping("/addwork")
    public String addJob(Model model)
    {
        model.addAttribute("addjob", new Job());
        return "addwork";
    }

    //for error checking addwork and adding the new job to the database
    @PostMapping("/addwork")
    public String submitWork(@Valid @ModelAttribute("addjob")Job job, BindingResult result)
    {
        //checks for validation errors
        if(result.hasErrors())
        {
            return "addwork";
        }
        jobRepo.save(job);
        return "submitwork";
    }

    //add education page
    @GetMapping("/addeducation")
    public String addEducation(Model model)
    {
        model.addAttribute("addeducation", new Education());
        return "addeducation";
    }

    //for error checking addeducation and adding the new school to the database
    @PostMapping("/addeducation")
    public String submitEducation(@Valid @ModelAttribute("addeducation")Education ed, BindingResult result)
    {
        //checks for validation errors
        if(result.hasErrors())
        {
            return "addeducation";
        }
        edRepo.save(ed);
        return "submiteducation";
    }

    //add skill page
    @GetMapping("/addskill")
    public String addSkill(Model model)
    {
        model.addAttribute("addskill", new Skill());
        return "addskill";
    }

    //for error checking addskill and adding the new skill to the database
    @PostMapping("/addskill")
    public String submitSkill(@Valid @ModelAttribute("addskill")Skill skill, BindingResult result)
    {
        //checks for validation errors
        if(result.hasErrors())
        {
            return "addskill";
        }
        skillRepo.save(skill);
        return "submitskill";
    }

    @GetMapping("/generate")
    public String generateResume(Model model)
    {
        return "generate";
    }

    @GetMapping("/edit")
    public String editEntry(Model model)
    {
        model.addAttribute("person", personRepo.findOne((long)1));
        model.addAttribute("jobs", jobRepo.findAll());
        model.addAttribute("education", edRepo.findAll());
        model.addAttribute("skills", skillRepo.findAll());
        return "edit";
    }
}
