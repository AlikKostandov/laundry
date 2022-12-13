package com.students.laundry.controllers.rest;

import com.students.laundry.entities.Session;
import com.students.laundry.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@AllArgsConstructor
public class SessionRestController {
    private SessionService sessionsService;

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionsService.getAllWindows();
    }

//    @GetMapping("/dtos")
//    public List<BookDto> getAllBooksDto() {
//        return sessionsService.findAllDtos();
//    }

    @GetMapping("/{id}")
    public Session getBookById(@PathVariable Long id) {
        return sessionsService.findById(id);
    }

//    @PutMapping(consumes = "application/json", produces = "application/json")
//    public Session occupyWindow(@RequestBody Session session) { return sessionsService.occupyWindow(session.getSessionId(), session.getUser().getPassNumber());}

//    @PostMapping(consumes = "application/json", produces = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book createNewBook(@RequestBody Book book) {
//        if (book.getId() != null) {
//            book.setId(null);
//        }
//        return bookService.saveOrUpdate(book);
//    }
//
//    @PutMapping(consumes = "application/json", produces = "application/json")
//    public Book modifyBook(@RequestBody Book book) {
//        if (!bookService.existsById(book.getId())) {
//            throw new ResourceNotFoundException("Book with id: " + book.getId() + " doesn't exists");
//        }
//        return bookService.saveOrUpdate(book);
//    }

}
