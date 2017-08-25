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
import org.springframework.web.bind.annotation.*;

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
        if(job.getEndDate().isEmpty())
        {
            job.setEndDate("Present");
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
        if(skillRepo.count()>=20)
        {
            model.addAttribute("toomany", true);
        }
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
        //checks for 0 education items, 1 is required
        if(edRepo.count()==0)
        {
            model.addAttribute("noeducation", true);
        }
        //ok to add empty iterable
        model.addAttribute("education", edRepo.findAll());

        //checks for 0 education items, 1 is required
        if(skillRepo.count()==0)
        {
            model.addAttribute("noskills", true);
        }
        //ok to add empty iterable
        model.addAttribute("skills", skillRepo.findAll());

        //person object is added, null or not
        model.addAttribute("person", personRepo.findOne((long) 1));

        //checks for 0 jobs
        if(jobRepo.count()==0)
        {
            model.addAttribute("nowork", true);
        }
        model.addAttribute("jobs", jobRepo.findAll());
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


    @GetMapping("/edit/{repo}/{id}")
    public String updateEntry(@PathVariable("repo") String repo, @PathVariable("id") long id, Model model){
        if(repo.equalsIgnoreCase("person"))
        {
            model.addAttribute("addperson", personRepo.findOne(id));
            return "addperson";
        }
        if(repo.equalsIgnoreCase("work"))
        {
            model.addAttribute("addjob", jobRepo.findOne(id));
            return "addwork";
        }
        if(repo.equalsIgnoreCase("education"))
        {
            model.addAttribute("addeducation", edRepo.findOne(id));
            return "addeducation";
        }
        if(repo.equalsIgnoreCase("skill"))
        {
            model.addAttribute("addskill", skillRepo.findOne(id));
            return "addskill";
        }
        System.out.println("You've got a problem");
        return "edit";
    }

    @GetMapping("/delete/{repo}/{id}")
    public String delEntry(@PathVariable("repo") String repo, @PathVariable("id") long id){
        if(repo.equalsIgnoreCase("person"))
        {
            personRepo.delete(id);
        }
        else if(repo.equalsIgnoreCase("work"))
        {
            jobRepo.delete(id);
        }
        else if(repo.equalsIgnoreCase("education"))
        {
            edRepo.delete(id);
        }
        else if(repo.equalsIgnoreCase("skill"))
        {
            skillRepo.delete(id);
        }
        else {
            System.out.println("You've got a problem");
        }
        return "redirect:/edit";
    }
}
