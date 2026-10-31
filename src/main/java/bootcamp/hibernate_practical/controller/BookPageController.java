package bootcamp.hibernate_practical.controller;

import bootcamp.hibernate_practical.dto.CreateBookRequest;
import bootcamp.hibernate_practical.service.BookService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Year;

@Controller
public class BookPageController {

    private final BookService bookService;

    public BookPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/", "/books"})
    public String books(Model model, Authentication authentication) {
        CreateBookRequest form = new CreateBookRequest();
        form.setPublicationYear(Year.now().getValue());
        model.addAttribute("bookForm", form);
        addPageData(model, authentication);
        return "books";
    }

    @PostMapping("/books")
    public String createBook(@Valid @ModelAttribute("bookForm") CreateBookRequest form,
                             BindingResult bindingResult,
                             Model model,
                             Authentication authentication) {
        if (bindingResult.hasErrors()) {
            addPageData(model, authentication);
            return "books";
        }

        bookService.createBook(form);
        return "redirect:/books";
    }

    private void addPageData(Model model, Authentication authentication) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("username", authentication.getName());
        model.addAttribute("isAdmin", authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")));
    }
}
