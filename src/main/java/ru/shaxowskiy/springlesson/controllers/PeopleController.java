package ru.shaxowskiy.springlesson.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.springlesson.dao.daoPeople;
import ru.shaxowskiy.springlesson.model.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private daoPeople daoPeople;

    /*
    Выводим в отображение список всех людей из DAO
     */
    @GetMapping
    public String index(Model model){
        model.addAttribute("people", daoPeople.getListOfPeople());

        return "/people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id,
                       Model model){
        /*
        Выводим в отображение список по одному человек по указанному id из DAO
         */
        model.addAttribute("person", daoPeople.getPersonById(id));
        return "/people/show";
    }
    /*
    GET метод для страницы добавления нового человека!
     */

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());

        return "/people/new";
    }

    /*
    POST метод для отправки через форму
     */
    @PostMapping
    public String createPerson(@ModelAttribute Person person,
                               Model model){
        //model.addAttribute("person", person);
        daoPeople.save(person);


        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id,
                         Model model){
        model.addAttribute("person", daoPeople.getPersonById(id));

        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") Person person,
                               @PathVariable("id") int id){
        daoPeople.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        daoPeople.delete(id);
        return "redirect:/people";
    }

}
