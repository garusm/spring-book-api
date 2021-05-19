package pl.bookapi.beans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.bookapi.beans.model.Book;
import pl.bookapi.beans.model.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // SHOW BOOKS METHOD
    @GetMapping("")
    public @ResponseBody
    List<Book> getList() {
        return bookService.getBooks();
    }

    // ADD A BOOK METHOD.
    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }

    // CHECK A BOOK METHOD.
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return this.bookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Entity not found"
            );
        });
    }

    // DELETE A BOOK METHOD.
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.delete(id);
    }

    // UPDATE A BOOK METHOD.
    @PutMapping("")
    @ResponseBody
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }

    // TEST METHOD.
    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

}
