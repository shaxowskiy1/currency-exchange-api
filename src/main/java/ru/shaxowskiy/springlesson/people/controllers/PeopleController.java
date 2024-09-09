package ru.shaxowskiy.springlesson.people.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.springlesson.people.dao.DaoBook;
import ru.shaxowskiy.springlesson.people.dao.daoPeople;
import ru.shaxowskiy.springlesson.people.model.Person;
import ru.shaxowskiy.springlesson.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final daoPeople DaoPeople;
    private final PersonValidator personValidator;
    private final DaoBook daoBook;

    @Autowired
    public PeopleController(daoPeople daoPeople, PersonValidator personValidator, DaoBook daoBook) {
        this.DaoPeople = daoPeople;
        this.personValidator = personValidator;
        this.daoBook = daoBook;
    }

    /*
        Выводим в отображение список всех людей из DAO
         */
    @GetMapping
    public String index(Model model){
        model.addAttribute("people", DaoPeople.getListOfPeople());

        return "/people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id,
                       Model model){
        /*
        Выводим в отображение список по одному человек по указанному id из DAO
         */
        model.addAttribute("person", DaoPeople.getPersonById(id));
        model.addAttribute("book", daoBook.getBookByPersonId(id));
        return "/people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());

        return "/people/new";
    }

    /*
    POST метод для отправки через форму
     */

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "/people/new";
        }
        DaoPeople.save(person);


        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id,
                         Model model){
        model.addAttribute("person", DaoPeople.getPersonById(id));

        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id){
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()){
            return "/people/edit";
        }
        DaoPeople.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        DaoPeople.delete(id);
        return "redirect:/people";
    }



}
