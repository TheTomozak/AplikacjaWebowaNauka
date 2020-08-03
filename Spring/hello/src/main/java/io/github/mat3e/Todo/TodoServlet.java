package io.github.mat3e.Todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mat3e.Hello.HelloServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/todos")
class TodoServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloServlet.class);

    private TodoRepository repository;

    TodoServlet(TodoRepository repository) {
        this.repository = repository;

    }

    @GetMapping
    ResponseEntity<List<Todo>> findAllTodos() {
        LOGGER.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }


    @PutMapping("/{id}")
    ResponseEntity<Todo> toggleTodo(@PathVariable Integer id) {
        LOGGER.info("Put request");
        var todo = repository.findById(id);
        todo.ifPresent(t -> {
            t.setDone(!t.getDone());            //to powinno byc w serwisie
            repository.save(t);

        });

        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        LOGGER.info("Post request");

        return ResponseEntity.ok(repository.save(todo));

    }


}
