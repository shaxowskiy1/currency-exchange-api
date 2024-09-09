package ru.shaxowskiy.springlesson.people.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.springlesson.people.dao.DaoBook;
import ru.shaxowskiy.springlesson.people.dao.daoPeople;
import ru.shaxowskiy.springlesson.people.model.Book;
import ru.shaxowskiy.springlesson.people.model.Person;

@Controller
@RequestMapping("/books")
public class BookController {
    private final DaoBook daoBook;
    private final daoPeople daoPeople;

    @Autowired
    public BookController(DaoBook daoBook, daoPeople daoPeople) {
        this.daoBook = daoBook;
        this.daoPeople = daoPeople;
    }

    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("books", daoBook.getBooks());
        return "/books/show";
    }


    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") Integer id,
                           @ModelAttribute("person") Person person) {

        model.addAttribute("book", daoBook.getBook(id));
        model.addAttribute("person", daoBook.getOwnerByPersonId(id));


        return "/books/showID";
    }

    @GetMapping("/new")
    public String showNewBook(Model model) {
        model.addAttribute("book", new Book());
        return "/books/new";
    }
    @PostMapping
    public String newBook(@ModelAttribute("book") @Valid Book book,
                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "/books/new";
        }
        daoBook.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String updateBook(@PathVariable("id") Integer id,
                             Model model) {
        model.addAttribute("book", daoBook.getBook(id));
        return "/books/edit";
    }
    @PatchMapping("/{id}")
    public String patchBook(@ModelAttribute("book") @Valid Book book,
                            @PathVariable("id") Integer id,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/edit";
        }
        daoBook.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        daoBook.delete(id);
        return "redirect:/books";
    }
}


