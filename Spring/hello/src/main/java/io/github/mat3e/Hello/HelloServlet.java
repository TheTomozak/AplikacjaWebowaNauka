package io.github.mat3e.Hello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloServlet.class);

    private HelloService service;

    HelloServlet(HelloService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity getGreetingWithoutParams() {
        return ResponseEntity.ok(service.prepareGreeting(null, null));
    }

    @GetMapping(params = {"lang", "name"})
    ResponseEntity getGreetingWithTwoParams(@RequestParam("lang") Integer langId, @RequestParam String name) { //Response

        LOGGER.info("Got request with parameters ");
        return ResponseEntity.ok(service.prepareGreeting(name, langId));
    }

    @GetMapping(params = {"name"})
    ResponseEntity getGreetingWithOnlyName(@RequestParam String name) { //Response

        LOGGER.info("Got request with only name ");
        return ResponseEntity.ok(service.prepareGreeting(name, null));
    }

    @GetMapping(params = {"lang"})
    ResponseEntity getGreetingWithOnlyLang(@RequestParam("lang") Integer langId) { //Response

        LOGGER.info("Got request with only name ");
        return ResponseEntity.ok(service.prepareGreeting(null, langId));
    }

}

