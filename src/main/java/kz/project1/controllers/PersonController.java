package kz.project1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kz.project1.dao.PersonDAO;
import kz.project1.models.Book;
import kz.project1.models.Person;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Person> people = personDAO.getAll();
        model.addAttribute("people", people);
        return "person/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("books", personDAO.getPersonBooks(id));
        return "person/show";
    }

    @GetMapping("/add")
    public String addGET(@ModelAttribute("person") Person person) {
        return "person/add";
    }

    @PostMapping("")
    public String addPOST(@ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "person/add";
        }
        personDAO.addPerson(person);
        return "redirect:/people/";
    }

    @GetMapping("/{id}/edit")
    public String updateGET(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPerson(id));
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String updatePOST(@ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "person/edit";
        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePOST(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
}
