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

import kz.project1.dao.BookDAO;
import kz.project1.dao.PersonDAO;
import kz.project1.models.Book;
import kz.project1.models.Person;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = bookDAO.getBook(id);
        if (book.getPerson_id() == 0)
            model.addAttribute("people", personDAO.getAll());
        else {
            Person findedPerson = personDAO.getPerson(book.getPerson_id());
            model.addAttribute("findedPerson", findedPerson);

        }
        model.addAttribute("book", book);
        return "book/show";
    }

    @GetMapping("/add")
    public String addGET(@ModelAttribute("book") Book book) {
        return "book/add";
    }

    @PostMapping("")
    public String addPOST(@ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/add";
        bookDAO.addBook(book);
        return "redirect:/book/";
    }

    @GetMapping("/{id}/edit")
    public String updateGET(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getBook(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String updatePOST(@ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/edit";
        bookDAO.updateBook(id, book);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/person")
    public String updateReader(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDAO.updateReader(id, person.getPerson_id());
        System.out.println(person.getPerson_id());
        return "redirect:/book/" + id;
    }

    @DeleteMapping("/{id}")
    public String deletePOST(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}/person")
    public String deleteReader(@PathVariable("id") int id) {
        bookDAO.deleteReader(id);
        return "redirect:/book/" + id;
    }
}
